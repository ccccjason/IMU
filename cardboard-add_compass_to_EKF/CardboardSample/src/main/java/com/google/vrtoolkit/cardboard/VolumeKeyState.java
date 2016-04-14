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

        this.handler = handler;
        this.volumeKeysMode = 0;
    }

    public void onCreate()
    {

        this.volumeKeysMode = 2;
    }

    public void setVolumeKeysMode(int mode)
    {

        this.volumeKeysMode = mode;
    }

    public int getVolumeKeysMode()
    {

        return this.volumeKeysMode;
    }

    public boolean areVolumeKeysDisabled(NfcSensor nfcSensor)
    {

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

            }
        }
    }
}
