//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;
import android.util.Log;

public class HeadMountedDisplay
{
    private ScreenParams screen;
    private CardboardDeviceParams cardboardDevice;

    public HeadMountedDisplay(ScreenParams screenParams,
                              CardboardDeviceParams cardboardDevice)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.screen = screenParams;
        this.cardboardDevice = cardboardDevice;
    }

    public HeadMountedDisplay(HeadMountedDisplay hmd)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.screen = new ScreenParams(hmd.screen);
        this.cardboardDevice = new CardboardDeviceParams(hmd.cardboardDevice);
    }

    public void setScreenParams(ScreenParams screen)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.screen = new ScreenParams(screen);
    }

    public ScreenParams getScreenParams()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.screen;
    }

    public void setCardboardDeviceParams(CardboardDeviceParams
                                         cardboardDeviceParams)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardDevice = new CardboardDeviceParams(cardboardDeviceParams);
    }

    public CardboardDeviceParams getCardboardDeviceParams()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardDevice;
    }

    public boolean equals(Object other)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (!(other instanceof HeadMountedDisplay)) {
            return false;
        } else {
            HeadMountedDisplay o = (HeadMountedDisplay)other;
            return this.screen.equals(o.screen) &&
                   this.cardboardDevice.equals(o.cardboardDevice);
        }
    }
}
