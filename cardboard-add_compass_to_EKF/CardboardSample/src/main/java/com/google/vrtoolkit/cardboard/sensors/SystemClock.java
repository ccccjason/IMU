//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard.sensors;

import android.util.Log;

public class SystemClock implements Clock
{
    public SystemClock()
    {

    }

    public long nanoTime()
    {

        return System.nanoTime();
    }
}
