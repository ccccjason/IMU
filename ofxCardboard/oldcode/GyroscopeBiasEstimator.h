/*
 * GyroscopeBiasEstimator.h
 *
 *  Created on: Feb 28, 2015
 *      Author: dantheman
 */
#pragma once
#include "ofMain.h"
#include "LowPassFilter.h"
class IsStaticCounter
{
public:
    int minStaticFrames;
    int consecutiveIsStatic;

    void setStaticCounter(int frame)
    {
        consecutiveIsStatic = 0;
        minStaticFrames = frame;
    }

    void appendFrame(bool isStatic)
    {
        if (!isStatic) {
            consecutiveIsStatic = 0;
        } else {
            consecutiveIsStatic += 1;
        }

    }

    bool isRecentlyStatic()
    {
        return consecutiveIsStatic >= minStaticFrames;
    }
};

class GyroscopeBiasEstimator
{
public:
    GyroscopeBiasEstimator();
    virtual ~GyroscopeBiasEstimator();

    LowPassFilter accelLowPass;
    LowPassFilter gyroLowPass;
    LowPassFilter gyroBiasLowPass;
    ofVec3f smoothedGyroDiff;
    ofVec3f smoothedAccelDiff;
    float GYRO_FOR_BIAS_THRESHOLD;
    IsStaticCounter isAccelStatic;
    IsStaticCounter isGyroStatic;
    ofVec3f accelDiff;

    void reset()
    {
        accelLowPass.setCutoffFrequency(1.0);
        gyroLowPass.setCutoffFrequency(10.00);
        gyroBiasLowPass.setCutoffFrequency(0.15);
        isAccelStatic.setStaticCounter(3);
        isGyroStatic.setStaticCounter(3);
        smoothedGyroDiff.set(0, 0, 0);
        smoothedAccelDiff.set(0, 0, 0);
    }

    void processGyroscope(ofVec3f gyro, long sensorTimestampNs)
    {
        gyroLowPass.addSample(gyro, sensorTimestampNs);
        smoothedGyroDiff = gyro - gyroLowPass.getFilteredData();

        isGyroStatic.appendFrame(smoothedGyroDiff.length() < 0.08);

        if ((isGyroStatic.isRecentlyStatic()) && (isAccelStatic.isRecentlyStatic())) {
            updateGyroBias(gyro, sensorTimestampNs);
        }
    }

    void processAccelerometer(ofVec3f accel, long sensorTimestampNs)
    {
        accelLowPass.addSample(accel, sensorTimestampNs);
        smoothedAccelDiff = accel - accelLowPass.getFilteredData();
        isAccelStatic.appendFrame(smoothedAccelDiff.length() < 0.5);
    }

    ofVec3f getGyroBias()
    {
        ofVec3f result;

        if (gyroBiasLowPass.getNumSamples() < 30) {
            result.set(0, 0, 0);
        } else {
            result.set(gyroBiasLowPass.getFilteredData());
            //ofLog()<<gyroBiasLowPass.getNumSamples()<<endl;
            float rampUpRatio = min(1.0, (gyroBiasLowPass.getNumSamples() - 30) / 100.0);
            result.scale(rampUpRatio);
        }

        return result;
    }

    void updateGyroBias(ofVec3f gyro, long sensorTimestampNs)
    {
        //ofLog()<<"updateGyroBias length "<<gyro.length()<<endl;
        if (gyro.length() >= 0.25) {
            ofLog() << "updateGyroBias too long " << gyro.length() << endl;
            return;
        }

        float updateWeight = max(0.0, 1.0 - gyro.length() / 0.25);
        updateWeight *= updateWeight;
        gyroBiasLowPass.addWeightedSample(gyroLowPass.getFilteredData(),
                                          sensorTimestampNs, updateWeight);
    }
};
