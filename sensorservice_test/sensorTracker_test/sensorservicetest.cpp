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

#define ASENSOR_TYPE_ROTATION_VECTOR 11
#define SENSOR_LOOPER_ID 20

ASensorManager* mSensorManager;
const ASensor* mOrientationSensor;
ASensorEventQueue* mSensorEventQueue;

void updateOrientation()
{
    ASensorEvent e;

    // If there are events, drain them and grab only the last one
    // If there aren't, then don't set anything
    if (ASensorEventQueue_hasEvents(mSensorEventQueue) > 0) {
        while (ASensorEventQueue_hasEvents(mSensorEventQueue) > 0) {
            ASensorEventQueue_getEvents(mSensorEventQueue, &e, 1);
            //LOGI("Sensor: %f %f %f %f", e.data[3], e.data[0], e.data[1], e.data[2]);
            printf("Sensor: %f %f %f %f\n", e.data[3], e.data[0], e.data[1], e.data[2]);
        }

        // mQuat = Quat(e.data[3], e.data[0], e.data[1], e.data[2]);
        //LOGI("Done, setting.");
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
