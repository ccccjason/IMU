//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard.sensors;

import android.app.Activity;

import com.google.vrtoolkit.cardboard.CardboardDeviceParams;
import com.google.vrtoolkit.cardboard.sensors.MagnetSensor.OnCardboardTriggerListener;
import com.google.vrtoolkit.cardboard.sensors.NfcSensor.OnCardboardNfcListener;

public class SensorConnection implements OnCardboardTriggerListener,
           OnCardboardNfcListener
{
    private final SensorConnection.SensorListener listener;
    private MagnetSensor magnetSensor;
    private NfcSensor nfcSensor;
    private volatile boolean magnetSensorEnabled = true;

    public SensorConnection(SensorConnection.SensorListener listener)
    {
        this.listener = listener;
    }

    public void disableMagnetSensor()
    {
        this.magnetSensorEnabled = false;

        if (this.magnetSensor != null) {
            this.magnetSensor.stop();
        }

    }

    public NfcSensor getNfcSensor()
    {
        return this.nfcSensor;
    }

    public void onCreate(Activity activity)
    {
        this.magnetSensor = new MagnetSensor(activity);
        this.magnetSensor.setOnCardboardTriggerListener(this);
        this.nfcSensor = NfcSensor.getInstance(activity);
        this.nfcSensor.addOnCardboardNfcListener(this);
        this.nfcSensor.onNfcIntent(activity.getIntent());
    }

    public void onResume(Activity activity)
    {
        if (this.magnetSensorEnabled) {
            this.magnetSensor.start();
        }

        this.nfcSensor.onResume(activity);
    }

    public void onPause(Activity activity)
    {
        this.magnetSensor.stop();
        this.nfcSensor.onPause(activity);
    }

    public void onDestroy(Activity activity)
    {
        this.nfcSensor.removeOnCardboardNfcListener(this);
    }

    public void onInsertedIntoCardboard(CardboardDeviceParams deviceParams)
    {
        this.listener.onInsertedIntoCardboard(deviceParams);
    }

    public void onRemovedFromCardboard()
    {
        this.listener.onRemovedFromCardboard();
    }

    public void onCardboardTrigger()
    {
        this.listener.onCardboardTrigger();
    }

    public interface SensorListener {
        void onInsertedIntoCardboard(CardboardDeviceParams var1);

        void onRemovedFromCardboard();

        void onCardboardTrigger();
    }
}
