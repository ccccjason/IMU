//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard.sensors.internal;

import java.util.concurrent.TimeUnit;
import android.util.Log;

public class LowPassFilter
{
    private static final double NANOS_TO_SECONDS;
    private final double timeConstantSecs;
    private final Vector3d filteredData = new Vector3d();
    private long lastTimestampNs;
    private int numSamples;
    private final Vector3d temp = new Vector3d();

    public LowPassFilter(double cutoffFrequency)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.timeConstantSecs = 1.0D / (6.283185307179586D * cutoffFrequency);
    }

    public int getNumSamples()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.numSamples;
    }

    public void addSample(Vector3d sampleData, long timestampNs)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.addWeightedSample(sampleData, timestampNs, 1.0D);
    }

    public void addWeightedSample(Vector3d sampleData, long timestampNs,
                                  double weight)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        ++this.numSamples;

        if (this.numSamples == 1) {
            this.filteredData.set(sampleData);
            this.lastTimestampNs = timestampNs;
        } else {
            double weightedDeltaSecs = weight * (double)(timestampNs - this.lastTimestampNs)
                                       * NANOS_TO_SECONDS;
            double alpha = weightedDeltaSecs / (this.timeConstantSecs + weightedDeltaSecs);
            this.filteredData.scale(1.0D - alpha);
            this.temp.set(sampleData);
            this.temp.scale(alpha);
            Vector3d.add(this.temp, this.filteredData, this.filteredData);
            this.lastTimestampNs = timestampNs;
        }
    }

    public Vector3d getFilteredData()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.filteredData;
    }

    static {
        NANOS_TO_SECONDS = 1.0D / (double)TimeUnit.NANOSECONDS.convert(1L, TimeUnit.SECONDS);
    }
}
