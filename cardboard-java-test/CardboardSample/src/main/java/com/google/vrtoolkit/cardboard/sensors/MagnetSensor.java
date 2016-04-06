//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Arrays;

public class MagnetSensor
{
    private static final String HTC_ONE_MODEL = "HTC One";
    private MagnetSensor.TriggerDetector detector;
    private Thread detectorThread;

    public MagnetSensor(Context context)
    {
        if ("HTC One".equals(Build.MODEL)) {
            this.detector = new MagnetSensor.VectorTriggerDetector(context);
        } else {
            this.detector = new MagnetSensor.ThresholdTriggerDetector(context);
        }

    }

    public void start()
    {
        this.detectorThread = new Thread(this.detector);
        this.detectorThread.start();
    }

    public void stop()
    {
        if (this.detectorThread != null) {
            this.detectorThread.interrupt();
            this.detector.stop();
        }

    }

    public void setOnCardboardTriggerListener(
        MagnetSensor.OnCardboardTriggerListener listener)
    {
        this.detector.setOnCardboardTriggerListener(listener, new Handler());
    }

    private static class VectorTriggerDetector extends MagnetSensor.TriggerDetector
    {
        private static final long NS_REFRESH_TIME = 350000000L;
        private static final long NS_THROWAWAY_SIZE = 500000000L;
        private static final long NS_WAIT_SIZE = 100000000L;
        private long lastFiring = 0L;
        private static int xThreshold;
        private static int yThreshold;
        private static int zThreshold;
        private ArrayList<float[]> sensorData = new ArrayList();
        private ArrayList<Long> sensorTimes = new ArrayList();

        public VectorTriggerDetector(Context context)
        {
            super(context);
            xThreshold = -3;
            yThreshold = 15;
            zThreshold = 6;
        }

        public VectorTriggerDetector(Context context, int xThreshold, int yThreshold,
                                     int zThreshold)
        {
            super(context);
            xThreshold = xThreshold;
            yThreshold = yThreshold;
            zThreshold = zThreshold;
        }

        private void addData(float[] values, long time)
        {
            this.sensorData.add(values);
            this.sensorTimes.add(Long.valueOf(time));

            while (((Long) this.sensorTimes.get(0)).longValue() < time - 500000000L) {
                this.sensorData.remove(0);
                this.sensorTimes.remove(0);
            }

            this.evaluateModel(time);
        }

        private void evaluateModel(long time)
        {
            if (time - this.lastFiring >= 350000000L && this.sensorData.size() >= 2) {
                int baseIndex = 0;

                for (int oldValues = 1; oldValues < this.sensorTimes.size(); ++oldValues) {
                    if (time - ((Long) this.sensorTimes.get(oldValues)).longValue() < 100000000L) {
                        baseIndex = oldValues;
                        break;
                    }
                }

                float[] var6 = (float[]) this.sensorData.get(baseIndex);
                float[] currentValues = (float[]) this.sensorData.get(this.sensorData.size() -
                                        1);

                if (currentValues[0] - var6[0] < (float) xThreshold &&
                    currentValues[1] - var6[1] > (float) yThreshold &&
                    currentValues[2] - var6[2] > (float) zThreshold) {
                    this.lastFiring = time;
                    this.handleButtonPressed();
                }

            }
        }

        public void onSensorChanged(SensorEvent event)
        {
            if (event.sensor.equals(this.magnetometer)) {
                float[] values = event.values;

                if (values[0] == 0.0F && values[1] == 0.0F && values[2] == 0.0F) {
                    return;
                }

                this.addData((float[]) event.values.clone(), event.timestamp);
            }

        }

        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
        }
    }

    private static class ThresholdTriggerDetector extends
        MagnetSensor.TriggerDetector
    {
        private static final long NS_SEGMENT_SIZE = 200000000L;
        private static final long NS_WINDOW_SIZE = 400000000L;
        private static final long NS_WAIT_TIME = 350000000L;
        private long lastFiring = 0L;
        private static int t1 = 30;
        private static int t2 = 130;
        private ArrayList<float[]> sensorData = new ArrayList();
        private ArrayList<Long> sensorTimes = new ArrayList();

        public ThresholdTriggerDetector(Context context)
        {
            super(context);
        }

        public ThresholdTriggerDetector(Context context, int t1, int t2)
        {
            super(context);
            t1 = t1;
            t2 = t2;
        }

        private void addData(float[] values, long time)
        {
            this.sensorData.add(values);
            this.sensorTimes.add(Long.valueOf(time));

            while (((Long) this.sensorTimes.get(0)).longValue() < time - 400000000L) {
                this.sensorData.remove(0);
                this.sensorTimes.remove(0);
            }

            this.evaluateModel(time);
        }

        private void evaluateModel(long time)
        {
            if (time - this.lastFiring >= 350000000L && this.sensorData.size() >= 2) {
                float[] baseline = (float[]) this.sensorData.get(this.sensorData.size() - 1);
                int startSecondSegment = 0;

                for (int offsets = 0; offsets < this.sensorTimes.size(); ++offsets) {
                    if (time - ((Long) this.sensorTimes.get(offsets)).longValue() < 200000000L) {
                        startSecondSegment = offsets;
                        break;
                    }
                }

                float[] var8 = new float[this.sensorData.size()];
                this.computeOffsets(var8, baseline);
                float min1 = this.computeMinimum(Arrays.copyOfRange(var8, 0,
                                                 startSecondSegment));
                float max2 = this.computeMaximum(Arrays.copyOfRange(var8, startSecondSegment,
                                                 this.sensorData.size()));

                if (min1 < (float) t1 && max2 > (float) t2) {
                    this.lastFiring = time;
                    this.handleButtonPressed();
                }

            }
        }

        private void computeOffsets(float[] offsets, float[] baseline)
        {
            for (int i = 0; i < this.sensorData.size(); ++i) {
                float[] point = (float[]) this.sensorData.get(i);
                float[] o = new float[] {point[0] - baseline[0], point[1] - baseline[1], point[2] - baseline[2]};
                float magnitude = (float) Math.sqrt((double)(o[0] * o[0] + o[1] * o[1] + o[2] *
                                                    o[2]));
                offsets[i] = magnitude;
            }

        }

        private float computeMaximum(float[] offsets)
        {
            float max = -Float.MAX_VALUE;
            float[] arr = offsets;
            int len = offsets.length;

            for (int i = 0; i < len; ++i) {
                float o = arr[i];
                max = Math.max(o, max);
            }

            return max;
        }

        private float computeMinimum(float[] offsets)
        {
            float min = Float.MAX_VALUE;
            float[] arr = offsets;
            int len$ = offsets.length;

            for (int i = 0; i < len$; ++i) {
                float o = arr[i];
                min = Math.min(o, min);
            }

            return min;
        }

        public void onSensorChanged(SensorEvent event)
        {
            if (event.sensor.equals(this.magnetometer)) {
                float[] values = event.values;

                if (values[0] == 0.0F && values[1] == 0.0F && values[2] == 0.0F) {
                    return;
                }

                this.addData((float[]) event.values.clone(), event.timestamp);
            }

        }

        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
        }
    }

    private abstract static class TriggerDetector implements Runnable,
                SensorEventListener
    {
        protected static final String TAG = "TriggerDetector";
        protected SensorManager sensorManager;
        protected Sensor magnetometer;
        protected MagnetSensor.OnCardboardTriggerListener listener;
        protected Handler handler;

        public TriggerDetector(Context context)
        {
            this.sensorManager = (SensorManager) context.getSystemService(
                                     Context.SENSOR_SERVICE);
            this.magnetometer = this.sensorManager.getDefaultSensor(2);
        }

        public synchronized void setOnCardboardTriggerListener(
            MagnetSensor.OnCardboardTriggerListener listener, Handler handler)
        {
            this.listener = listener;
            this.handler = handler;
        }

        protected void handleButtonPressed()
        {
            synchronized(this) {
                if (this.listener != null) {
                    this.handler.post(new Runnable() {
                        public void run() {
                            if (TriggerDetector.this.listener != null) {
                                TriggerDetector.this.listener.onCardboardTrigger();
                            }

                        }
                    });
                }

            }
        }

        public void run()
        {
            Looper.prepare();
            this.sensorManager.registerListener(this, this.magnetometer, 0);
            Looper.loop();
        }

        public void stop()
        {
            this.sensorManager.unregisterListener(this);
        }

        public void onSensorChanged(SensorEvent event)
        {
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
        }
    }

    public interface OnCardboardTriggerListener {
        void onCardboardTrigger();
    }
}
