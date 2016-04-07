/*
 * GyroBiasEstimator.h
 *
 *  Created on: Feb 27, 2015
 *      Author: dantheman
 */

#pragma once
#include "ofMain.h";

enum State {
	UNCALIBRATED = 0, CALIBRATING, CALIBRATED
};

class Estimate {
public:
	State mState;
	ofVec3f mBias;
	void set(Estimate from) {
		mState = from.mState;
		mBias.set(from.mBias);
	}
};

class GyroBiasEstimator {
public:
	GyroBiasEstimator();
	virtual ~GyroBiasEstimator();

	Estimate mBiasEstimate;

	float GYRO_SMOOTHING_FACTOR;
	float ACC_SMOOTHING_FACTOR;
	float mGyroMagnitudeDiffSmoothed;
	float MIN_ACCEL_DOT_WITH_UP;
	float MIN_ACCEL_LENGTH;
	float MAX_GYRO_DIFF;

	ofVec3f UP_VECTOR;
	ofVec3f mLastGyro;
	ofVec3f mCurrGyro;
	ofVec3f mGyroDiff;
	ofVec3f mCurrAcc;
	ofVec3f mAccSmoothed;
	ofVec3f mAccNormalizedTmp;

	long mCalibrationStartTimeNs;
	long mLastGyroTimeNs;
	long mLastAccTimeNs;
	long CALIBRATION_DURATION_NS;
	long MAX_DELAY_BETWEEN_EVENTS_NS;

	void setup() {
		GYRO_SMOOTHING_FACTOR = 0.01F;
		ACC_SMOOTHING_FACTOR = 0.1F;
		UP_VECTOR.set(0.0, 1.0, 0.0);

		MIN_ACCEL_DOT_WITH_UP = (float) cos(ofDegToRad(10.0));
		MIN_ACCEL_LENGTH = 1.0E-04;
		MAX_GYRO_DIFF = 0.01;
		CALIBRATION_DURATION_NS = 5000000000;
		MAX_DELAY_BETWEEN_EVENTS_NS = 100000000;
	}

	void processGyroscope(ofVec3f gyro, long sensorTimeStamp) {
		if (mBiasEstimate.mState == CALIBRATED) {
			return;
		}
		mCurrGyro.set(gyro);

		mGyroDiff = mCurrGyro - mLastGyro;
		float mCurrDiff = (float) mGyroDiff.length();
		mGyroMagnitudeDiffSmoothed = (0.01 * mCurrDiff
				+ 0.99 * mGyroMagnitudeDiffSmoothed);

		mLastGyro.set(mCurrGyro);
		bool eventIsDelayed = sensorTimeStamp > mLastGyroTimeNs + 100000000;
		mLastGyroTimeNs = sensorTimeStamp;

		if (eventIsDelayed) {
			resetCalibration();
			return;
		}

		if ((mBiasEstimate.mState
				== CALIBRATING)
				&& (sensorTimeStamp > mCalibrationStartTimeNs + 5000000000)) {
			mBiasEstimate.mState = CALIBRATED;
			return;
		}

		if (!canCalibrateGyro()) {
			resetCalibration();
			return;
		}

		startCalibration(sensorTimeStamp);
	}

	void resetCalibration() {
		mBiasEstimate.mState = UNCALIBRATED;
		mBiasEstimate.mBias.set(0.0, 0.0, 0.0);
		mCalibrationStartTimeNs = -1;
	}

	void startCalibration(long gyroTimeStamp) {
		if (mBiasEstimate.mState
				!= CALIBRATING) {
			mBiasEstimate.mBias.set(mCurrGyro);
			mBiasEstimate.mState = CALIBRATING;
			mCalibrationStartTimeNs = gyroTimeStamp;
		} else {
			smooth(mBiasEstimate.mBias, mCurrGyro, 0.01f);
		}
	}

	void processAccelerometer(ofVec3f & acc, long & sensorTimeStamp) {
		if (mBiasEstimate.mState
				== CALIBRATED) {
			return;
		}
		mCurrAcc.set(acc);
		bool eventIsDelayed = sensorTimeStamp > mLastAccTimeNs + 100000000;
		mLastAccTimeNs = sensorTimeStamp;
		if (eventIsDelayed) {
			resetCalibration();
			return;
		}
		smooth(mAccSmoothed, mCurrAcc, 0.1f);
	}

	void getEstimate(Estimate output) {
		output.set(mBiasEstimate);
	}

	bool canCalibrateGyro() {
		if (mAccSmoothed.length() < 9.999999747378752E-05) {
			return false;
		}
		mAccNormalizedTmp.set(mAccSmoothed);
		mAccNormalizedTmp.normalize();

		if (mAccNormalizedTmp.dot(UP_VECTOR) < MIN_ACCEL_DOT_WITH_UP) {
			return false;
		}

		if (mGyroMagnitudeDiffSmoothed > 0.01) {
			return false;
		}
		return true;
	}

	void smooth(ofVec3f & smoothed, ofVec3f & newValue, float smoothingFactor) {
		smoothed.x = (smoothingFactor * newValue.x
				+ (1.0 - smoothingFactor) * smoothed.x);
		smoothed.y = (smoothingFactor * newValue.y
				+ (1.0 - smoothingFactor) * smoothed.y);
		smoothed.z = (smoothingFactor * newValue.z
				+ (1.0 - smoothingFactor) * smoothed.z);
	}
};

