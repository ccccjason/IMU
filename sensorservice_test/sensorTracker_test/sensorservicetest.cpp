/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <android/sensor.h>
#include <android/looper.h>
#include <stdio.h>
#include <math.h>

#define ASENSOR_TYPE_ROTATION_VECTOR 11
#define SENSOR_LOOPER_ID 20
#define M_PI 3.1415926535897932384626433832795

typedef struct  Quatdata_ {
    double quat_w;
    double quat_x;
    double quat_y;
    double quat_z;
} Quatdata;

ASensorManager* mSensorManager;
const ASensor* mOrientationSensor;
ASensorEventQueue* mSensorEventQueue;

inline void QuaternionToEuler(double* data, Quatdata* q)
{
    // yaw:
    data[0] = atan2(2 * (q->quat_x * q->quat_y + q->quat_w * q->quat_z),
                    q->quat_w * q->quat_w + q->quat_x * q->quat_x - q->quat_y * q->quat_y -
                    q->quat_z * q->quat_z);
    // pitch:
    data[1] = asin(-2 * (q->quat_x * q->quat_z - q->quat_w * q->quat_y));

    // roll:
    data[2] = atan2(2 * (q->quat_y * q->quat_z + q->quat_w * q->quat_x),
                    q->quat_w * q->quat_w - q->quat_x * q->quat_x - q->quat_y * q->quat_y +
                    q->quat_z * q->quat_z);
}

void updateOrientation()
{
    ASensorEvent e;
    double pose_ypr[3] = {0};
    Quatdata quat_data;

    // If there are events, drain them and grab only the last one
    // If there aren't, then don't set anything
    if (ASensorEventQueue_hasEvents(mSensorEventQueue) > 0) {
        while (ASensorEventQueue_hasEvents(mSensorEventQueue) > 0) {
            ASensorEventQueue_getEvents(mSensorEventQueue, &e, 1);
            //LOGI("Sensor: %f %f %f %f", e.data[3], e.data[0], e.data[1], e.data[2]);
            quat_data.quat_w = e.data[3];
            quat_data.quat_x = e.data[0];
            quat_data.quat_y = e.data[1];
            quat_data.quat_z = e.data[2];
            QuaternionToEuler(pose_ypr, &quat_data);

            printf("pose_yaw=%f, pose_pitch=%f, pose_roll=%f\n",
                   (pose_ypr[0] * 180) / M_PI,
                   (pose_ypr[1] * 180) / M_PI,
                   (pose_ypr[2] * 180) / M_PI);

            // printf("Sensor: %f %f %f %f\n", e.data[3], e.data[0], e.data[1], e.data[2]);
        }

    }
}

int main(int argc, char** argv)
{
    ALooper* looper = ALooper_forThread();

    if (looper == NULL) {
        looper = ALooper_prepare(ALOOPER_PREPARE_ALLOW_NON_CALLBACKS);

    }

    mSensorManager = ASensorManager_getInstance();

    mOrientationSensor = ASensorManager_getDefaultSensor(mSensorManager,
                         ASENSOR_TYPE_ROTATION_VECTOR);
    mSensorEventQueue = ASensorManager_createEventQueue(mSensorManager, looper,
                        SENSOR_LOOPER_ID, NULL, NULL);
    ASensorEventQueue_enableSensor(mSensorEventQueue, mOrientationSensor);

#if 1
    ASensorEventQueue_setEventRate(mSensorEventQueue, mOrientationSensor,
                                   1000000 / 100); // 一秒100次??? test
#else
    ASensorEventQueue_setEventRate(mSensorEventQueue, mOrientationSensor,
                                   1000L * 100); // 100ms interval = 10 Hz (maybe)
#endif

    while (1) {
        updateOrientation();
    }

    return 0;
}
