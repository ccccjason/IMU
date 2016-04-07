//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;
import android.util.Log;

@UsedByNative
public class Eye
{
    private final int type;
    private final float[] eyeView;
    private final Viewport viewport;
    private final FieldOfView fov;
    private volatile boolean projectionChanged;
    private float[] perspective;
    private float lastZNear;
    private float lastZFar;

    public Eye(int type)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.type = type;
        this.eyeView = new float[16];
        this.viewport = new Viewport();
        this.fov = new FieldOfView();
        this.projectionChanged = true;
    }

    public int getType()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.type;
    }

    @UsedByNative
    public float[] getEyeView()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.eyeView;
    }

    public float[] getPerspective(float zNear, float zFar)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (!this.projectionChanged && this.lastZNear == zNear &&
            this.lastZFar == zFar) {
            return this.perspective;
        } else {
            if (this.perspective == null) {
                this.perspective = new float[16];
            }

            this.getFov().toPerspectiveMatrix(zNear, zFar, this.perspective, 0);
            this.lastZNear = zNear;
            this.lastZFar = zFar;
            this.projectionChanged = false;
            return this.perspective;
        }
    }

    public Viewport getViewport()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.viewport;
    }

    public FieldOfView getFov()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.fov;
    }

    public void setProjectionChanged()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.projectionChanged = true;
    }

    public boolean getProjectionChanged()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.projectionChanged;
    }

    @UsedByNative
    private void setValues(int viewportX, int viewportY, int viewportWidth,
                           int viewportHeight, float fovLeft, float fovRight, float fovBottom,
                           float fovTop)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.viewport.setViewport(viewportX, viewportY, viewportWidth, viewportHeight);
        this.fov.setAngles(fovLeft, fovRight, fovBottom, fovTop);
        this.projectionChanged = true;
    }

    public abstract static class Type
    {
        public static final int MONOCULAR = 0;
        public static final int LEFT = 1;
        public static final int RIGHT = 2;

        public Type()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        }
    }
}
