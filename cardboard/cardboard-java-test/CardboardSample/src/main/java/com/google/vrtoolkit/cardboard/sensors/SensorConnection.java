//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard.sensors;

import android.app.Activity;

import com.google.vrtoolkit.cardboard.CardboardDeviceParams;
import com.google.vrtoolkit.cardboard.sensors.MagnetSensor.OnCardboardTriggerListener;
import com.google.vrtoolkit.cardboard.sensors.NfcSensor.OnCardboardNfcListener;
import android.util.Log;

public class SensorConnection implements OnCardboardTriggerListener,
           OnCardboardNfcListener
{
    private final SensorConnection.SensorListener listener;
    private MagnetSensor magnetSensor;
    private NfcSensor nfcSensor;
    private volatile boolean magnetSensorEnabled = true;

    public SensorConnection(SensorConnection.SensorListener listener)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.listener = listener;
    }

    public void disableMagnetSensor()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.magnetSensorEnabled = false;

        if (this.magnetSensor != null) {
            this.magnetSensor.stop();
        }

    }

    public NfcSensor getNfcSensor()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.nfcSensor;
    }

    public void onCreate(Activity activity)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.magnetSensor = new MagnetSensor(activity);
        this.magnetSensor.setOnCardboardTriggerListener(this);
        this.nfcSensor = NfcSensor.getInstance(activity);
        this.nfcSensor.addOnCardboardNfcListener(this);
        this.nfcSensor.onNfcIntent(activity.getIntent());
    }

    public void onResume(Activity activity)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (this.magnetSensorEnabled) {
            this.magnetSensor.start();
        }

        this.nfcSensor.onResume(activity);
    }

    public void onPause(Activity activity)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.magnetSensor.stop();
        this.nfcSensor.onPause(activity);
    }

    public void onDestroy(Activity activity)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.nfcSensor.removeOnCardboardNfcListener(this);
    }

    public void onInsertedIntoCardboard(CardboardDeviceParams deviceParams)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.listener.onInsertedIntoCardboard(deviceParams);
    }

    public void onRemovedFromCardboard()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.listener.onRemovedFromCardboard();
    }

    public void onCardboardTrigger()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.listener.onCardboardTrigger();
    }

    public interface SensorListener {
        void onInsertedIntoCardboard(CardboardDeviceParams var1);

        void onRemovedFromCardboard();

        void onCardboardTrigger();
    }
}
