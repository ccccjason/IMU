// I2C device class (I2Cdev) demonstration Arduino sketch for MPU6050 class
// 10/7/2011 by Jeff Rowberg <jeff@rowberg.net>
// Updates should (hopefully) always be available at https://github.com/jrowberg/i2cdevlib
//
// Changelog:
//      2013-05-08 - added multiple output formats
//                 - added seamless Fastwire support
//      2011-10-07 - initial release

/* ============================================
I2Cdev device library code is placed under the MIT license
Copyright (c) 2011 Jeff Rowberg

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
===============================================
*/

// I2Cdev and MPU6050 must be installed as libraries, or else the .cpp/.h files
// for both classes must be in the include path of your project
#include "I2Cdev.h"
#include "MPU6050.h"
#include "MadgwickAHRS.h" // add
// Arduino Wire library is required if I2Cdev I2CDEV_ARDUINO_WIRE implementation
// is used in I2Cdev.h
#if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
#include "Wire.h"
#endif

#define SAMPLERATE 350

Madgwick filter; // add
unsigned long microsPerReading, microsPrevious;


// class default I2C address is 0x68
// specific I2C addresses may be passed as a parameter here
// AD0 low = 0x68 (default for InvenSense evaluation board)
// AD0 high = 0x69
MPU6050 accelgyro;
//MPU6050 accelgyro(0x69); // <-- use for AD0 high

int16_t ax, ay, az;
int16_t gx, gy, gz;

static uint8_t int_state = 0;

// uncomment "OUTPUT_READABLE_ACCELGYRO" if you want to see a tab-separated
// list of the accel X/Y/Z and then gyro X/Y/Z values in decimal. Easy to read,
// not so easy to parse, and slow(er) over UART.
#define OUTPUT_READABLE_ACCELGYRO

// uncomment "OUTPUT_BINARY_ACCELGYRO" to send all 6 axes of data as 16-bit
// binary, one right after the other. This is very fast (as fast as possible
// without compression or data loss), and easy to parse, but impossible to read
// for a human.
//#define OUTPUT_BINARY_ACCELGYRO


#define LED_PIN 13
bool blinkState = false;
const int interruptNumber = 0;
const int buttonPin = 2;
static int count_int = 0;

float convertRawAcceleration(int aRaw)
{
    // since we are using 2G range
    // -2g maps to a raw value of -32768
    // +2g maps to a raw value of 32767

    float a = (aRaw * 2.0) / 32768.0;
    return a;
}

float convertRawGyro(int gRaw)
{
    // since we are using 250 degrees/seconds range
    // -250 maps to a raw value of -32768
    // +250 maps to a raw value of 32767

    float g = (gRaw * 250.0) / 32768.0;
    return g;
}


void setup()
{
    // join I2C bus (I2Cdev library doesn't do this automatically)
#if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
    Wire.begin();
#elif I2CDEV_IMPLEMENTATION == I2CDEV_BUILTIN_FASTWIRE
    Fastwire::setup(400, true);
#endif

    // initialize serial communication
    // (38400 chosen because it works as well at 8MHz as it does at 16MHz, but
    // it's really up to you depending on your project)
    Serial.begin(38400);

    // initialize device
    Serial.println("Initializing I2C devices...");
    accelgyro.initialize();
    accelgyro.setIntEnabled(0x01);
    accelgyro.setRate(4); // 1khz / (1 + 4) = 200 Hz
    accelgyro.setDLPFMode(0x03);
    // verify connection
    Serial.println("Testing device connections...");
    Serial.println(accelgyro.testConnection() ? "MPU6050 connection successful" :
                   "MPU6050 connection failed");


    filter.begin(SAMPLERATE); // add

    // initialize variables to pace updates to correct rate
    microsPerReading = 1000000 / SAMPLERATE;
    microsPrevious = micros();

    accelgyro.setXAccelOffset(-4379);
    accelgyro.setYAccelOffset(-572);
    accelgyro.setZAccelOffset(1851);
    accelgyro.setXGyroOffset(89);
    accelgyro.setYGyroOffset(23);
    accelgyro.setZGyroOffset(0);
    // use the code below to change accel/gyro offset values
    /*
    Serial.println("Updating internal sensor offsets...");
    // -76  -2359   1688    0   0   0
    Serial.print(accelgyro.getXAccelOffset()); Serial.print("\t"); // -76
    Serial.print(accelgyro.getYAccelOffset()); Serial.print("\t"); // -2359
    Serial.print(accelgyro.getZAccelOffset()); Serial.print("\t"); // 1688
    Serial.print(accelgyro.getXGyroOffset()); Serial.print("\t"); // 0
    Serial.print(accelgyro.getYGyroOffset()); Serial.print("\t"); // 0
    Serial.print(accelgyro.getZGyroOffset()); Serial.print("\t"); // 0
    Serial.print("\n");
    accelgyro.setXGyroOffset(220);
    accelgyro.setYGyroOffset(76);
    accelgyro.setZGyroOffset(-85);
    Serial.print(accelgyro.getXAccelOffset()); Serial.print("\t"); // -76
    Serial.print(accelgyro.getYAccelOffset()); Serial.print("\t"); // -2359
    Serial.print(accelgyro.getZAccelOffset()); Serial.print("\t"); // 1688
    Serial.print(accelgyro.getXGyroOffset()); Serial.print("\t"); // 0
    Serial.print(accelgyro.getYGyroOffset()); Serial.print("\t"); // 0
    Serial.print(accelgyro.getZGyroOffset()); Serial.print("\t"); // 0
    Serial.print("\n");
    */

    // configure Arduino LED for
    pinMode(LED_PIN, OUTPUT);
    pinMode(buttonPin, INPUT);
    attachInterrupt(interruptNumber, buttonStateChanged, RISING);
}

void buttonStateChanged()
{

    count_int++;
    /*
    int_state = accelgyro.getIntStatus();
    if(int_state & 0x01) {
    count_int++;
    }
    */
}

void loop()
{

    Serial.print("count: ");
    Serial.print("\t");
    Serial.println(count_int);
    count_int = 0;
    delay(1000);
#if 0
    // read raw accel/gyro measurements from device
    // accelgyro.getMotion6(&ax, &ay, &az, &gx, &gy, &gz);

    // these methods (and a few others) are also available
    //accelgyro.getAcceleration(&ax, &ay, &az);
    //accelgyro.getRotation(&gx, &gy, &gz);

    float roll, pitch, heading;
    float aax, aay, aaz;
    float ggx, ggy, ggz;
    unsigned long microsNow;

    // check if it's time to read data and update the filter
    microsNow = micros();
    //  if (microsNow - microsPrevious >= microsPerReading) {
    accelgyro.getMotion6(&ax, &ay, &az, &gx, &gy, &gz);

    // convert from raw data to gravity and degrees/second units
    aax = convertRawAcceleration(ax);
    aay = convertRawAcceleration(ay);
    aaz = convertRawAcceleration(az);
    ggx = convertRawGyro(gx);
    ggy = convertRawGyro(gy);
    ggz = convertRawGyro(gz);

    // update the filter, which computes orientation
    filter.updateIMU(ggx, ggy, ggz, aax, aay, aaz);

    // print the heading, pitch and roll
    roll = filter.getRoll();
    pitch = filter.getPitch();
    heading = filter.getYaw();

    Serial.print("Orientation: ");
    Serial.print(heading);
    Serial.print(" ");
    Serial.print(pitch);
    Serial.print(" ");
    Serial.println(roll);

    // increment previous time, so we keep proper pace
    microsPrevious = microsPrevious + microsPerReading;
#endif
    //}

    /*
        #ifdef OUTPUT_READABLE_ACCELGYRO
            // display tab-separated accel/gyro x/y/z values
            Serial.print("a/g:\t");
            Serial.print(ax); Serial.print("\t");
            Serial.print(ay); Serial.print("\t");
            Serial.print(az); Serial.print("\t");
            Serial.print(gx); Serial.print("\t");
            Serial.print(gy); Serial.print("\t");
            Serial.println(gz);
        #endif

        #ifdef OUTPUT_BINARY_ACCELGYRO
            Serial.write((uint8_t)(ax >> 8)); Serial.write((uint8_t)(ax & 0xFF));
            Serial.write((uint8_t)(ay >> 8)); Serial.write((uint8_t)(ay & 0xFF));
            Serial.write((uint8_t)(az >> 8)); Serial.write((uint8_t)(az & 0xFF));
            Serial.write((uint8_t)(gx >> 8)); Serial.write((uint8_t)(gx & 0xFF));
            Serial.write((uint8_t)(gy >> 8)); Serial.write((uint8_t)(gy & 0xFF));
            Serial.write((uint8_t)(gz >> 8)); Serial.write((uint8_t)(gz & 0xFF));
        #endif
    */
    // blink LED to indicate activity
    blinkState = !blinkState;
    digitalWrite(LED_PIN, blinkState);
}
