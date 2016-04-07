//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.util.DisplayMetrics;
import android.view.Display;

import com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams;

import java.io.InputStream;
import android.util.Log;

public class ScreenParams
{
    private static final float METERS_PER_INCH = 0.0254F;
    private static final float DEFAULT_BORDER_SIZE_METERS = 0.0030F;
    private int width;
    private int height;
    private float xMetersPerPixel;
    private float yMetersPerPixel;
    private float borderSizeMeters;

    public ScreenParams(Display display)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        DisplayMetrics metrics = new DisplayMetrics();

        try {
            display.getRealMetrics(metrics);
        } catch (NoSuchMethodError var5) {
            display.getMetrics(metrics);
        }

        this.xMetersPerPixel = 0.0254F / metrics.xdpi;
        this.yMetersPerPixel = 0.0254F / metrics.ydpi;
        this.width = metrics.widthPixels;
        this.height = metrics.heightPixels;
        this.borderSizeMeters = 0.0030F;

        //        if(this.height > this.width) {
        //            int tempPx = this.width;
        //            this.width = this.height;
        //            this.height = tempPx;
        //            float tempMetersPerPixel = this.xMetersPerPixel;
        //            this.xMetersPerPixel = this.yMetersPerPixel;
        //            this.yMetersPerPixel = tempMetersPerPixel;
        //        }

    }

    public static ScreenParams fromProto(Display display, PhoneParams params)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (params == null) {
            return null;
        } else {
            ScreenParams screenParams = new ScreenParams(display);

            if (params.hasXPpi()) {
                screenParams.xMetersPerPixel = 0.0254F / params.getXPpi();
            }

            if (params.hasYPpi()) {
                screenParams.yMetersPerPixel = 0.0254F / params.getYPpi();
            }

            if (params.hasBottomBezelHeight()) {
                screenParams.borderSizeMeters = params.getBottomBezelHeight();
            }

            return screenParams;
        }
    }

    public ScreenParams(ScreenParams params)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.width = params.width;
        this.height = params.height;
        this.xMetersPerPixel = params.xMetersPerPixel;
        this.yMetersPerPixel = params.yMetersPerPixel;
        this.borderSizeMeters = params.borderSizeMeters;
    }

    public void setWidth(int width)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.width = width;
    }

    public int getWidth()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.width;
    }

    public void setHeight(int height)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.height = height;
    }

    public int getHeight()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.height;
    }

    public float getWidthMeters()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return (float)this.width * this.xMetersPerPixel;
    }

    public float getHeightMeters()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return (float)this.height * this.yMetersPerPixel;
    }

    public void setBorderSizeMeters(float screenBorderSize)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.borderSizeMeters = screenBorderSize;
    }

    public float getBorderSizeMeters()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.borderSizeMeters;
    }

    public boolean equals(Object other)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (!(other instanceof ScreenParams)) {
            return false;
        } else {
            ScreenParams o = (ScreenParams)other;
            return this.width == o.width && this.height == o.height &&
                   this.xMetersPerPixel == o.xMetersPerPixel &&
                   this.yMetersPerPixel == o.yMetersPerPixel &&
                   this.borderSizeMeters == o.borderSizeMeters;
        }
    }

    public String toString()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return "{\n" + "  width: " + this.width + ",\n" + "  height: " + this.height +
               ",\n" + "  x_meters_per_pixel: " + this.xMetersPerPixel + ",\n" +
               "  y_meters_per_pixel: " + this.yMetersPerPixel + ",\n" +
               "  border_size_meters: " + this.borderSizeMeters + ",\n" + "}";
    }

    public static ScreenParams createFromInputStream(Display display,
            InputStream inputStream)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        PhoneParams phoneParams =
            com.google.vrtoolkit.cardboard.PhoneParams.readFromInputStream(inputStream);
        return phoneParams == null ? null : fromProto(display, phoneParams);
    }
}
