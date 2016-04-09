package com.google.vrtoolkit.cardboard.sensors;

import android.hardware.SensorEventListener;

public abstract interface SensorEventProvider {
    public abstract void start();

    public abstract void stop();

    public abstract void registerListener(SensorEventListener paramSensorEventListener);

    public abstract void unregisterListener(SensorEventListener paramSensorEventListener);
}

/* Location:           /Users/mayuhan/laboratory/cardboardSrc/
 * Qualified Name:     com.google.vrtoolkit.cardboard.sensors.SensorEventProvider
 * JD-Core Version:    0.6.2
 */
