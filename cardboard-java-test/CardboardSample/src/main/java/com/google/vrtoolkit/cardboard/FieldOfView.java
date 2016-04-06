//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.opengl.Matrix;

public class FieldOfView
{
    private static final float DEFAULT_MAX_FOV_LEFT_RIGHT = 40.0F;
    private static final float DEFAULT_MAX_FOV_BOTTOM = 40.0F;
    private static final float DEFAULT_MAX_FOV_TOP = 40.0F;
    private float left;
    private float right;
    private float bottom;
    private float top;

    public FieldOfView()
    {
        this.left = 40.0F;
        this.right = 40.0F;
        this.bottom = 40.0F;
        this.top = 40.0F;
    }

    public FieldOfView(float left, float right, float bottom, float top)
    {
        this.setAngles(left, right, bottom, top);
    }

    public FieldOfView(FieldOfView other)
    {
        this.copy(other);
    }

    public static FieldOfView parseFromProtobuf(float[] angles)
    {
        return angles.length != 4 ? null : new FieldOfView(angles[0], angles[1],
                angles[2], angles[3]);
    }

    public float[] toProtobuf()
    {
        return new float[] {this.left, this.right, this.bottom, this.top};
    }

    public void copy(FieldOfView other)
    {
        this.left = other.left;
        this.right = other.right;
        this.bottom = other.bottom;
        this.top = other.top;
    }

    public void setAngles(float left, float right, float bottom, float top)
    {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    public void setLeft(float left)
    {
        this.left = left;
    }

    public float getLeft()
    {
        return this.left;
    }

    public void setRight(float right)
    {
        this.right = right;
    }

    public float getRight()
    {
        return this.right;
    }

    public void setBottom(float bottom)
    {
        this.bottom = bottom;
    }

    public float getBottom()
    {
        return this.bottom;
    }

    public void setTop(float top)
    {
        this.top = top;
    }

    public float getTop()
    {
        return this.top;
    }

    public void toPerspectiveMatrix(float near, float far, float[] perspective,
                                    int offset)
    {
        if (offset + 16 > perspective.length) {
            throw new IllegalArgumentException("Not enough space to write the result");
        } else {
            float l = (float)(-Math.tan(Math.toRadians((double)this.left))) * near;
            float r = (float)Math.tan(Math.toRadians((double)this.right)) * near;
            float b = (float)(-Math.tan(Math.toRadians((double)this.bottom))) * near;
            float t = (float)Math.tan(Math.toRadians((double)this.top)) * near;
            Matrix.frustumM(perspective, offset, l, r, b, t, near, far);
        }
    }

    public boolean equals(Object other)
    {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (!(other instanceof FieldOfView)) {
            return false;
        } else {
            FieldOfView o = (FieldOfView)other;
            return this.left == o.left && this.right == o.right &&
                   this.bottom == o.bottom && this.top == o.top;
        }
    }

    public String toString()
    {
        return "{\n" + "  left: " + this.left + ",\n" + "  right: " + this.right + ",\n"
               + "  bottom: " + this.bottom + ",\n" + "  top: " + this.top + ",\n" + "}";
    }
}
