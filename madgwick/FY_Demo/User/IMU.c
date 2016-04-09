#include "IMU.h"
#include "MPU6500.h"
#include "math.h"
#include "timer.h"
#include "uart.h"

void IMUupdate(float gx, float gy, float gz, float ax, float ay, float az);

#define RtA         57.324841               //���ȵ��Ƕ�
#define AtR     0.0174533               //�ȵ��Ƕ�
#define Acc_G   0.0011963               //���ٶȱ��G
#define Gyro_G  0.0152672               //���ٶȱ�ɶ�
#define Gyro_Gr 0.0002663
#define FILTER_NUM 20   //20�β���ȡƽ��ֵ�����ڿ�ʹ�������㷨�����˲�


S_INT16_XYZ ACC_AVG;            //ƽ��ֵ�˲����ACC
S_FLOAT_XYZ GYRO_I;             //�����ǻ���
S_FLOAT_XYZ EXP_ANGLE;      //�����Ƕ�
S_FLOAT_XYZ DIF_ANGLE;      //�����Ƕ���ʵ�ʽǶȲ�
S_FLOAT_XYZ Q_ANGLE;            //��Ԫ��������ĽǶ�

int16_t ACC_X_BUF[FILTER_NUM], ACC_Y_BUF[FILTER_NUM], ACC_Z_BUF[FILTER_NUM];
//���ٶȻ��������˲�����

void Prepare_Data(void)
{
    static uint8_t filter_cnt = 0;
    int32_t temp1 = 0, temp2 = 0, temp3 = 0;
    uint8_t i;
    MPU6050_Read();
    MPU6050_Dataanl();
    ACC_X_BUF[filter_cnt] = MPU6050_ACC_LAST.X;
    ACC_Y_BUF[filter_cnt] = MPU6050_ACC_LAST.Y;
    ACC_Z_BUF[filter_cnt] = MPU6050_ACC_LAST.Z;

    for (i = 0; i < FILTER_NUM; i++) {
        temp1 += ACC_X_BUF[i];
        temp2 += ACC_Y_BUF[i];
        temp3 += ACC_Z_BUF[i];
    }

    ACC_AVG.X = temp1 / FILTER_NUM;
    ACC_AVG.Y = temp2 / FILTER_NUM;
    ACC_AVG.Z = temp3 / FILTER_NUM;
    filter_cnt++;

    if (filter_cnt == FILTER_NUM) {
        filter_cnt = 0;
    }

    GYRO_I.X += MPU_Get_Data(GYRO_XOUT_H) * Gyro_G *
                0.0001; //0.0001��ʱ����,����prepare��ִ������
    GYRO_I.Y += MPU_Get_Data(GYRO_YOUT_H) * Gyro_G * 0.0001; //����ʱ�䶨��1ms
    GYRO_I.Z += MPU_Get_Data(GYRO_ZOUT_H) * Gyro_G * 0.0001;
}

void Get_Attitude(void)
{

    printf("X��������%d\r\n", MPU6050_GYRO_LAST.X); //*Gyro_Gr);
    printf("Y��������%d\r\n", MPU6050_GYRO_LAST.Y); //*Gyro_Gr);
    printf("Z��������%d\r\n", MPU6050_GYRO_LAST.Z); //*Gyro_Gr);

    printf("X����ٶ�%10d\r\n", ACC_AVG.X);
    printf("Y����ٶ�%10d\r\n", ACC_AVG.Y);
    printf("Z����ٶ�%10d\r\n", ACC_AVG.Z);
    IMUupdate(MPU6050_GYRO_LAST.X * Gyro_Gr,
              MPU6050_GYRO_LAST.Y * Gyro_Gr,
              MPU6050_GYRO_LAST.Z * Gyro_Gr,
              ACC_AVG.X, ACC_AVG.Y, ACC_AVG.Z);
}
////////////////////////////////////////////////////////////////////////////////
#define Kp 10.0f                        // proportional gain governs rate of convergence to accelerometer/magnetometer
#define Ki 0.008f                          // integral gain governs rate of convergence of gyroscope biases
#define halfT 0.001f                   // half the sample period�������ڵ�һ��


float q0 = 1, q1 = 0, q2 = 0,
      q3 = 0;    // quaternion elements representing the estimated orientation
float exInt = 0, eyInt = 0, ezInt = 0;    // scaled integral error

void IMUupdate(float gx, float gy, float gz, float ax, float ay, float az)
{
    float norm;
    //  float hx, hy, hz, bx, bz;
    float vx, vy, vz;// wx, wy, wz;
    float ex, ey, ez;

    // �Ȱ���Щ�õõ���ֵ���
    float q0q0 = q0 * q0;
    float q0q1 = q0 * q1;
    float q0q2 = q0 * q2;
    //  float q0q3 = q0*q3;
    float q1q1 = q1 * q1;
    //  float q1q2 = q1*q2;
    float q1q3 = q1 * q3;
    float q2q2 = q2 * q2;
    float q2q3 = q2 * q3;
    float q3q3 = q3 * q3;

    if (ax * ay * az == 0) {
        return;
    }

    norm = sqrt(ax * ax + ay * ay + az * az); //acc���ݹ�һ��
    ax = ax / norm;
    ay = ay / norm;
    az = az / norm;

    // estimated direction of gravity and flux (v and w)              �����������������/��Ǩ
    vx = 2 * (q1q3 -
              q0q2);                                           //��Ԫ����xyz�ı�ʾ
    vy = 2 * (q0q1 + q2q3);
    vz = q0q0 - q1q1 - q2q2 + q3q3 ;

    // error is sum of cross product between reference direction of fields and direction measured by sensors
    ex = (ay * vz - az * vy)
         ;                                         //�������������õ���־������
    ey = (az * vx - ax * vz) ;
    ez = (ax * vy - ay * vx) ;

    exInt = exInt + ex * Ki;                                //�������л���
    eyInt = eyInt + ey * Ki;
    ezInt = ezInt + ez * Ki;

    // adjusted gyroscope measurements
    gx = gx + Kp * ex +
         exInt;                                            //�����PI�󲹳��������ǣ����������Ư��
    gy = gy + Kp * ey + eyInt;
    gz = gz + Kp * ez +
         ezInt;                                        //�����gz����û�й۲��߽��н��������Ư�ƣ����ֳ����ľ��ǻ����������Լ�

    // integrate quaternion rate and normalise                           //��Ԫ�ص�΢�ַ���
    q0 = q0 + (-q1 * gx - q2 * gy - q3 * gz) * halfT;
    q1 = q1 + (q0 * gx + q2 * gz - q3 * gy) * halfT;
    q2 = q2 + (q0 * gy - q1 * gz + q3 * gx) * halfT;
    q3 = q3 + (q0 * gz + q1 * gy - q2 * gx) * halfT;

    // normalise quaternion
    norm = sqrt(q0 * q0 + q1 * q1 + q2 * q2 + q3 * q3);
    q0 = q0 / norm;
    q1 = q1 / norm;
    q2 = q2 / norm;
    q3 = q3 / norm;

    Q_ANGLE.Z = atan2(2 * q1 * q2 + 2 * q0 * q3,
                      q0 * q0 + q1 * q1 - q2 * q2 - q3 * q3) * 57.3; // yaw
    Q_ANGLE.Y  = asin(-2 * q1 * q3 + 2 * q0 * q2) * 57.3; // pitch
    Q_ANGLE.X = atan2(2 * q2 * q3 + 2 * q0 * q1,
                      -2 * q1 * q1 - 2 * q2 * q2 + 1) * 57.3; // roll
    printf("YAW:%.2f\r\n", Q_ANGLE.Z * 10);
    printf("pitch:%.2f\r\n", Q_ANGLE.Y * 10);
    printf("roll:%.2f\r\n", Q_ANGLE.X * 10);
    //UART1_ReportIMU(Q_ANGLE.Z*10,Q_ANGLE.Y*10,Q_ANGLE.X*10,1,1,1,100);

}
