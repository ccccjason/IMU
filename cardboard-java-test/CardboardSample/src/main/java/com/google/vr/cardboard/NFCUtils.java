/*    */ package com.google.vr.cardboard;
/*    */
/*    */ import android.app.Activity;
/*    */ import android.app.PendingIntent;
/*    */ import android.content.BroadcastReceiver;
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import android.content.IntentFilter;
/*    */ import android.nfc.NfcAdapter;
/*    */ import android.nfc.Tag;
/*    */ import android.util.Log;
/*    */
/*    */
/*    */
/*    */
/*    */ public class NFCUtils
/*    */
{
    /* 18 */   private static final String TAG = NFCUtils.class.getSimpleName();
    /*    */   Context context;
    /*    */   NfcAdapter nfcAdapter;
    /*    */   BroadcastReceiver nfcBroadcastReceiver;
    /*    */   IntentFilter[] nfcIntentFilters;
    /*    */
    /*    */   public void onCreate(Activity activity)
    /*    */
    {
        /* 26 */     this.context = activity.getApplicationContext();
        /*    */
        /* 28 */     this.nfcAdapter = NfcAdapter.getDefaultAdapter(this.context);
        /*    */
        /* 30 */     this.nfcBroadcastReceiver = new BroadcastReceiver()
        /*    */     {
            /*    */       public void onReceive(Context context, Intent intent) {
                /* 33 */         Log.i(NFCUtils.TAG, "Got an NFC tag!");
                /* 34 */         NFCUtils.this.onNFCTagDetected((Tag)
                        intent.getParcelableExtra("android.nfc.extra.TAG"));
                /*    */
            }
            /*    */
            /* 37 */
        };
        /* 38 */     IntentFilter originalIntent = createNfcIntentFilter();
        /* 39 */     originalIntent.addDataScheme("cardboard");
        /*    */
        /* 41 */     IntentFilter shortenedIntent = createNfcIntentFilter();
        /* 42 */     shortenedIntent.addDataScheme("http");
        /* 43 */     shortenedIntent.addDataAuthority("goo.gl", null);
        /*    */
        /* 45 */     IntentFilter explicitIntent = createNfcIntentFilter();
        /* 46 */     explicitIntent.addDataScheme("http");
        /* 47 */     explicitIntent.addDataAuthority("google.com", null);
        /* 48 */     explicitIntent.addDataPath("/cardboard/cfg.*", 2);
        /*    */
        /* 50 */     this.nfcIntentFilters = new IntentFilter[] { originalIntent, shortenedIntent, explicitIntent };
        /*    */
    }
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */   public void onResume(Activity activity)
    /*    */
    {
        /* 58 */     activity.registerReceiver(this.nfcBroadcastReceiver,
                                               createNfcIntentFilter());
        /* 59 */     Intent intent = new Intent("android.nfc.action.NDEF_DISCOVERED");
        /* 60 */     intent.setPackage(activity.getPackageName());
        /* 61 */     PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this.context, 0, intent, 0);

        /* 62 */     if (isNFCEnabled()) {
            /* 63 */       this.nfcAdapter.enableForegroundDispatch(activity, pendingIntent,
                    this.nfcIntentFilters, null);
            /*    */
        }

        /*    */
    }
    /*    */
    /*    */   public void onPause(Activity activity)
    {
        /* 68 */     if (isNFCEnabled()) {
            /* 69 */       this.nfcAdapter.disableForegroundDispatch(activity);
            /*    */
        }

        /* 71 */     activity.unregisterReceiver(this.nfcBroadcastReceiver);
        /*    */
    }
    /*    */
    /*    */   protected boolean isNFCEnabled()
    {
        /* 75 */     return (this.nfcAdapter != null) && (this.nfcAdapter.isEnabled());
        /*    */
    }
    /*    */
    /*    */
    /*    */   protected void onNFCTagDetected(Tag tag) {}
    /*    */
    /*    */   private IntentFilter createNfcIntentFilter()
    /*    */
    {
        /* 83 */     IntentFilter intentFilter = new IntentFilter();
        /* 84 */     intentFilter.addAction("android.nfc.action.NDEF_DISCOVERED");
        /* 85 */     intentFilter.addAction("android.nfc.action.TECH_DISCOVERED");
        /* 86 */     intentFilter.addAction("android.nfc.action.TAG_DISCOVERED");
        /* 87 */     return intentFilter;
        /*    */
    }
    /*    */
}


/* Location:              /home/jason_yao/cardboard/cardboard-java/CardboardSample/libs/cardboard.jar!/com/google/vr/cardboard/NFCUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */