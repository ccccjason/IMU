//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.util.Log;

public class DisplaySynchronizer implements FrameCallback
{
    private static final String TAG = DisplaySynchronizer.class.getSimpleName();
    private Choreographer choreographer;
    private final long nativeDisplaySynchronizer;

    public DisplaySynchronizer()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        System.loadLibrary("vrtoolkit");
        this.nativeDisplaySynchronizer = this.nativeInit(16666666L);
        this.choreographer = Choreographer.getInstance();
        this.choreographer.postFrameCallback(this);
    }

    protected void finalize() throws Throwable {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        try {
            this.nativeDestroy(this.nativeDisplaySynchronizer);
        } finally {
            super.finalize();
        }

    }

    public void doFrame(long vsync)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.nativeAddSyncTime(this.nativeDisplaySynchronizer, vsync);
        this.choreographer.postFrameCallback(this);
    }

    public long sync()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.nativeSync(this.nativeDisplaySynchronizer);
    }

    private native long nativeInit(long var1);

    private native void nativeDestroy(long var1);

    private native void nativeAddSyncTime(long var1, long var3);

    private native long nativeSync(long var1);
}
