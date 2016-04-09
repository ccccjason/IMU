//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.opengl.GLES20;
import android.util.Log;

@UsedByNative
public class Viewport
{
    public int x;
    public int y;
    public int width;
    public int height;

    public Viewport()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

    }

    @UsedByNative
    public void setViewport(int x, int y, int width, int height)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setGLViewport()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        GLES20.glViewport(this.x, this.y, this.width, this.height);
    }

    public void setGLScissor()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        GLES20.glScissor(this.x, this.y, this.width, this.height);
    }

    public void getAsArray(int[] array, int offset)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (offset + 4 > array.length) {
            throw new IllegalArgumentException("Not enough space to write the result");
        } else {
            array[offset] = this.x;
            array[offset + 1] = this.y;
            array[offset + 2] = this.width;
            array[offset + 3] = this.height;
        }
    }

    public String toString()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return "{\n" + "  x: " + this.x + ",\n" + "  y: " + this.y + ",\n" + "  width: "
               + this.width + ",\n" + "  height: " + this.height + ",\n" + "}";
    }

    public boolean equals(Object obj)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (obj == this) {
            return true;
        } else if (!(obj instanceof Viewport)) {
            return false;
        } else {
            Viewport other = (Viewport)obj;
            return this.x == other.x && this.y == other.y && this.width == other.width &&
                   this.height == other.height;
        }
    }

    public int hashCode()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return Integer.valueOf(this.x).hashCode() ^ Integer.valueOf(
                   this.y).hashCode() ^ Integer.valueOf(this.width).hashCode() ^ Integer.valueOf(
                   this.height).hashCode();
    }
}
