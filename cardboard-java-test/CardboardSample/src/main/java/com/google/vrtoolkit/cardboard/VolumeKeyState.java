//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import com.google.vrtoolkit.cardboard.sensors.NfcSensor;
import android.util.Log;

final class VolumeKeyState
{
    private final VolumeKeyState.Handler handler;
    private int volumeKeysMode;

    public VolumeKeyState(VolumeKeyState.Handler handler)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.handler = handler;
        this.volumeKeysMode = 0;
    }

    public void onCreate()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.volumeKeysMode = 2;
    }

    public void setVolumeKeysMode(int mode)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.volumeKeysMode = mode;
    }

    public int getVolumeKeysMode()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.volumeKeysMode;
    }

    public boolean areVolumeKeysDisabled(NfcSensor nfcSensor)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        switch (this.volumeKeysMode) {
        case 0:
            return false;

        case 1:
            return true;

        case 2:
            return nfcSensor.isDeviceInCardboard();

        default:
            throw new IllegalStateException("Invalid volume keys mode " +
                                            this.volumeKeysMode);
        }
    }

    public boolean onKey(int keyCode)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return (keyCode == 24 || keyCode == 25) && this.handler.areVolumeKeysDisabled();
    }

    public interface Handler {
        boolean areVolumeKeysDisabled();

        public abstract static class VolumeKeys {
            public static final int NOT_DISABLED = 0;
            public static final int DISABLED = 1;
            public static final int DISABLED_WHILE_IN_CARDBOARD = 2;

            public VolumeKeys()
            {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            }
        }
    }
}
