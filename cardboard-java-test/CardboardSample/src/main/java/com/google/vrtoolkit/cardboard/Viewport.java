//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.opengl.GLES20;

@UsedByNative
public class Viewport
{
    public int x;
    public int y;
    public int width;
    public int height;

    public Viewport()
    {
    }

    @UsedByNative
    public void setViewport(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setGLViewport()
    {
        GLES20.glViewport(this.x, this.y, this.width, this.height);
    }

    public void setGLScissor()
    {
        GLES20.glScissor(this.x, this.y, this.width, this.height);
    }

    public void getAsArray(int[] array, int offset)
    {
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
        return "{\n" + "  x: " + this.x + ",\n" + "  y: " + this.y + ",\n" + "  width: "
               + this.width + ",\n" + "  height: " + this.height + ",\n" + "}";
    }

    public boolean equals(Object obj)
    {
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
        return Integer.valueOf(this.x).hashCode() ^ Integer.valueOf(
                   this.y).hashCode() ^ Integer.valueOf(this.width).hashCode() ^ Integer.valueOf(
                   this.height).hashCode();
    }
}
