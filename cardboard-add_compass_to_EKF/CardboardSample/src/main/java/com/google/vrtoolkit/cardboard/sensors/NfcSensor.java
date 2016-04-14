//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard.sensors;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Handler;
import android.util.Log;

import com.google.vrtoolkit.cardboard.CardboardDeviceParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NfcSensor
{
    private static final String TAG = "NfcSensor";
    private static final int MAX_CONNECTION_FAILURES = 1;
    private static final long NFC_POLLING_INTERVAL_MS = 250L;
    private static NfcSensor sInstance;
    private final Context context;
    private final NfcAdapter nfcAdapter;
    private final Object tagLock;
    private final List<ListenerHelper> listeners;
    private BroadcastReceiver nfcBroadcastReceiver;
    private IntentFilter[] nfcIntentFilters;
    private Ndef currentNdef;
    private Tag currentTag;
    private boolean currentTagIsCardboard;
    private Timer nfcDisconnectTimer;
    private int tagConnectionFailures;

    public static NfcSensor getInstance(Context context)
    {

        if (sInstance == null) {
            sInstance = new NfcSensor(context);
        }

        return sInstance;
    }

    private NfcSensor(Context context)
    {

        this.context = context.getApplicationContext();
        this.nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        this.listeners = new ArrayList();
        this.tagLock = new Object();

        if (this.nfcAdapter != null) {
            this.nfcBroadcastReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    NfcSensor.this.onNfcIntent(intent);
                }
            };
        }
    }

    public void addOnCardboardNfcListener(NfcSensor.OnCardboardNfcListener
                                          listener)
    {

        if (listener != null) {
            List var2 = this.listeners;
            synchronized(this.listeners) {
                if (this.listeners.isEmpty()) {
                    IntentFilter i$ = new IntentFilter("android.nfc.action.NDEF_DISCOVERED");
                    i$.addAction("android.nfc.action.TECH_DISCOVERED");
                    i$.addAction("android.nfc.action.TAG_DISCOVERED");
                    this.nfcIntentFilters = new IntentFilter[] {i$};
                    this.context.registerReceiver(this.nfcBroadcastReceiver, i$);
                }

                Iterator i$1 = this.listeners.iterator();

                NfcSensor.ListenerHelper helper;

                do {
                    if (!i$1.hasNext()) {
                        this.listeners.add(new NfcSensor.ListenerHelper(listener, new Handler()));
                        return;
                    }

                    helper = (NfcSensor.ListenerHelper) i$1.next();
                } while (helper.getListener() != listener);

            }
        }
    }

    public void removeOnCardboardNfcListener(NfcSensor.OnCardboardNfcListener
            listener)
    {

        if (listener != null) {
            List var2 = this.listeners;
            synchronized(this.listeners) {
                Iterator i$ = this.listeners.iterator();

                while (i$.hasNext()) {
                    NfcSensor.ListenerHelper helper = (NfcSensor.ListenerHelper) i$.next();

                    if (helper.getListener() == listener) {
                        this.listeners.remove(helper);
                        break;
                    }
                }

                if (this.nfcBroadcastReceiver != null && this.listeners.isEmpty()) {
                    this.context.unregisterReceiver(this.nfcBroadcastReceiver);
                }

            }
        }
    }

    public boolean isNfcSupported()
    {

        return this.nfcAdapter != null;
    }

    public boolean isNfcEnabled()
    {

        return this.isNfcSupported() && this.nfcAdapter.isEnabled();
    }

    public boolean isDeviceInCardboard()
    {

        Object var1 = this.tagLock;
        synchronized(this.tagLock) {
            return this.currentTagIsCardboard;
        }
    }

    public NdefMessage getTagContents()
    {

        Object var1 = this.tagLock;
        synchronized(this.tagLock) {
            return this.currentNdef != null ? this.currentNdef.getCachedNdefMessage() :
                   null;
        }
    }

    public NdefMessage getCurrentTagContents() throws TagLostException, IOException,
    FormatException {

        Object var1 = this.tagLock;
        synchronized(this.tagLock)
        {
            return this.currentNdef != null ? this.currentNdef.getNdefMessage() : null;
        }
    }

    public int getTagCapacity()
    {

        Object var1 = this.tagLock;
        synchronized(this.tagLock) {
            if (this.currentNdef == null) {
                throw new IllegalStateException("No NFC tag");
            } else {
                return this.currentNdef.getMaxSize();
            }
        }
    }

    public void writeUri(Uri uri) throws TagLostException, IOException,
    IllegalArgumentException {

        Object var2 = this.tagLock;
        synchronized(this.tagLock)
        {
            if (this.currentTag == null) {
                throw new IllegalStateException("No NFC tag found");
            } else {
                NdefMessage currentMessage = null;
                NdefMessage newMessage = null;
                NdefRecord newRecord = NdefRecord.createUri(uri);

                try {
                    currentMessage = this.getCurrentTagContents();
                } catch (Exception var15) {
                    currentMessage = this.getTagContents();
                }

                if (currentMessage != null) {
                    ArrayList ndef = new ArrayList();
                    boolean e = false;
                    NdefRecord[] arr$ = currentMessage.getRecords();
                    int len$ = arr$.length;

                    for (int i$ = 0; i$ < len$; ++i$) {
                        NdefRecord record = arr$[i$];

                        if (this.isCardboardNdefRecord(record)) {
                            if (!e) {
                                ndef.add(newRecord);
                                e = true;
                            }
                        } else {
                            ndef.add(record);
                        }
                    }

                    newMessage = new NdefMessage((NdefRecord[]) ndef.toArray(
                                                     new NdefRecord[ndef.size()]));
                }

                if (newMessage == null) {
                    newMessage = new NdefMessage(new NdefRecord[] {newRecord});
                }

                if (this.currentNdef != null) {
                    if (!this.currentNdef.isConnected()) {
                        this.currentNdef.connect();
                    }

                    if (this.currentNdef.getMaxSize() < newMessage.getByteArrayLength()) {
                        throw new IllegalArgumentException("Not enough capacity in NFC tag. Capacity: "
                                                           + this.currentNdef.getMaxSize() + " bytes, " + newMessage.getByteArrayLength() +
                                                           " required.");
                    }

                    try {
                        this.currentNdef.writeNdefMessage(newMessage);
                    } catch (FormatException var14) {
                        throw new RuntimeException("Internal error when writing to NFC tag: " +
                                                   var14.toString());
                    }
                } else {
                    NdefFormatable var17 = NdefFormatable.get(this.currentTag);

                    if (var17 == null) {
                        throw new IOException("Could not find a writable technology for the NFC tag");
                    }

                    Log.w("NfcSensor",
                          "Ndef technology not available. Falling back to NdefFormattable.");

                    try {
                        var17.connect();
                        var17.format(newMessage);
                        var17.close();
                    } catch (FormatException var13) {
                        throw new RuntimeException("Internal error when writing to NFC tag: " +
                                                   var13.toString());
                    }
                }

                this.onNewNfcTag(this.currentTag);
            }
        }
    }

    public void onResume(Activity activity)
    {

        if (this.isNfcEnabled()) {
            Intent intent = new Intent("android.nfc.action.NDEF_DISCOVERED");
            intent.setPackage(activity.getPackageName());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context, 0,
                                          intent, 0);
            this.nfcAdapter.enableForegroundDispatch(activity, pendingIntent,
                    this.nfcIntentFilters, (String[][]) null);
        }
    }

    public void onPause(Activity activity)
    {

        if (this.isNfcEnabled()) {
            this.nfcAdapter.disableForegroundDispatch(activity);
        }
    }

    public void onNfcIntent(Intent intent)
    {

        if (this.isNfcEnabled() && intent != null &&
            this.nfcIntentFilters[0].matchAction(intent.getAction())) {
            this.onNewNfcTag((Tag) intent.getParcelableExtra("android.nfc.extra.TAG"));
        }
    }

    private void onNewNfcTag(Tag nfcTag)
    {

        if (nfcTag != null) {
            Object var2 = this.tagLock;
            synchronized(this.tagLock) {
                Tag previousTag = this.currentTag;
                Ndef previousNdef = this.currentNdef;
                boolean previousTagWasCardboard = this.currentTagIsCardboard;
                this.closeCurrentNfcTag();
                this.currentTag = nfcTag;
                this.currentNdef = Ndef.get(nfcTag);

                if (this.currentNdef == null) {
                    if (previousTagWasCardboard) {
                        this.sendDisconnectionEvent();
                    }

                } else {
                    boolean isSameTag = false;

                    if (previousNdef != null) {
                        byte[] nfcTagContents = this.currentTag.getId();
                        byte[] e = previousTag.getId();
                        isSameTag = nfcTagContents != null && e != null &&
                                    Arrays.equals(nfcTagContents, e);

                        if (!isSameTag && previousTagWasCardboard) {
                            this.sendDisconnectionEvent();
                        }
                    }

                    NdefMessage nfcTagContents1;

                    try {
                        this.currentNdef.connect();
                        nfcTagContents1 = this.currentNdef.getCachedNdefMessage();
                    } catch (Exception var13) {
                        Log.e("NfcSensor", "Error reading NFC tag: " + var13.toString());

                        if (isSameTag && previousTagWasCardboard) {
                            this.sendDisconnectionEvent();
                        }

                        return;
                    }

                    this.currentTagIsCardboard = this.isCardboardNdefMessage(nfcTagContents1);

                    if (!isSameTag && this.currentTagIsCardboard) {
                        List e1 = this.listeners;
                        synchronized(this.listeners) {
                            Iterator i$ = this.listeners.iterator();

                            while (i$.hasNext()) {
                                NfcSensor.ListenerHelper listener = (NfcSensor.ListenerHelper) i$.next();
                                listener.onInsertedIntoCardboard(CardboardDeviceParams.createFromNfcContents(
                                                                     nfcTagContents1));
                            }
                        }
                    }

                    if (this.currentTagIsCardboard) {
                        this.tagConnectionFailures = 0;
                        this.nfcDisconnectTimer = new Timer("NFC disconnect timer");
                        this.nfcDisconnectTimer.schedule(new TimerTask() {
                            public void run() {
                                synchronized(NfcSensor.this.tagLock) {
                                    if (!NfcSensor.this.currentNdef.isConnected()) {

                                        //TODO 不知道干嘛用的
                                        //                                        NfcSensor.access$204(NfcSensor.this);
                                        if (NfcSensor.this.tagConnectionFailures > 1) {
                                            NfcSensor.this.closeCurrentNfcTag();
                                            NfcSensor.this.sendDisconnectionEvent();
                                        }
                                    }

                                }
                            }
                        }, 250L, 250L);
                    }

                }
            }
        }
    }

    private void closeCurrentNfcTag()
    {

        if (this.nfcDisconnectTimer != null) {
            this.nfcDisconnectTimer.cancel();
        }

        if (this.currentNdef != null) {
            try {
                this.currentNdef.close();
            } catch (IOException var2) {
                Log.w("NfcSensor", var2.toString());
            }

            this.currentTag = null;
            this.currentNdef = null;
            this.currentTagIsCardboard = false;
        }
    }

    private void sendDisconnectionEvent()
    {

        List var1 = this.listeners;
        synchronized(this.listeners) {
            Iterator i$ = this.listeners.iterator();

            while (i$.hasNext()) {
                NfcSensor.ListenerHelper listener = (NfcSensor.ListenerHelper) i$.next();
                listener.onRemovedFromCardboard();
            }

        }
    }

    private boolean isCardboardNdefMessage(NdefMessage message)
    {

        if (message == null) {
            return false;
        } else {
            NdefRecord[] arr$ = message.getRecords();
            int len$ = arr$.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                NdefRecord record = arr$[i$];

                if (this.isCardboardNdefRecord(record)) {
                    return true;
                }
            }

            return false;
        }
    }

    private boolean isCardboardNdefRecord(NdefRecord record)
    {

        if (record == null) {
            return false;
        } else {
            Uri uri = record.toUri();
            return uri != null && CardboardDeviceParams.isCardboardUri(uri);
        }
    }

    private static class ListenerHelper implements NfcSensor.OnCardboardNfcListener
    {
        private NfcSensor.OnCardboardNfcListener listener;
        private Handler handler;

        public ListenerHelper(NfcSensor.OnCardboardNfcListener listener,
                              Handler handler)
        {

            this.listener = listener;
            this.handler = handler;
        }

        public NfcSensor.OnCardboardNfcListener getListener()
        {

            return this.listener;
        }

        public void onInsertedIntoCardboard(final CardboardDeviceParams deviceParams)
        {

            this.handler.post(new Runnable() {
                public void run() {
                    ListenerHelper.this.listener.onInsertedIntoCardboard(deviceParams);
                }
            });
        }

        public void onRemovedFromCardboard()
        {

            this.handler.post(new Runnable() {
                public void run() {
                    ListenerHelper.this.listener.onRemovedFromCardboard();
                }
            });
        }
    }

    public interface OnCardboardNfcListener {
        void onInsertedIntoCardboard(CardboardDeviceParams var1);

        void onRemovedFromCardboard();
    }
}
