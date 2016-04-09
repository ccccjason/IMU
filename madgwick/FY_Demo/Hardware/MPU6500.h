#ifndef __I2C_MPU6050_H__
#define __I2C_MPU6050_H__

#include "board.h"

/* MPU6050 Register Address ------------------------------------------------------------*/
#define SMPLRT_DIV      0x19    //陀螺仪采样率，典型值：0x07(125Hz)
#define CONFIG          0x1A    //低通滤波频率，典型值：0x06(5Hz)
#define GYRO_CONFIG     0x1B    //陀螺仪自检及测量范围，典型值：0x18(不自检，2000deg/s)
#define ACCEL_CONFIG    0x1C    //加速计自检、测量范围及高通滤波频率，典型值：0x01(不自检，2G，5Hz)
#define ACCEL_XOUT_H    0x3B
#define ACCEL_XOUT_L    0x3C
#define ACCEL_YOUT_H    0x3D
#define ACCEL_YOUT_L    0x3E
#define ACCEL_ZOUT_H    0x3F
#define ACCEL_ZOUT_L    0x40
#define TEMP_OUT_H      0x41
#define TEMP_OUT_L      0x42
#define GYRO_XOUT_H     0x43
#define GYRO_XOUT_L     0x44
#define GYRO_YOUT_H     0x45
#define GYRO_YOUT_L     0x46
#define GYRO_ZOUT_H     0x47
#define GYRO_ZOUT_L     0x48
#define PWR_MGMT_1      0x6B    //电源管理，典型值：0x00(正常启用)
#define PWR_MGMT_2      0x6C
#define DEVICE_ID_REG       0x75    //器件ID

#define MPU_ADDR        0X68    //MPU_ADDR左移1位得到0xD0即MPU_WRITE地址
#define MPU_SlaveAddress    0XD0    //IIC写入时的地址字节数据


typedef struct {
    int16_t X;
    int16_t Y;
    int16_t Z;
} S_INT16_XYZ; //用于平均滤波



u8 MPU_Set_Gyro_Fsr(u8 fsr);//设置陀螺仪采样率
u8 MPU_Set_Accel_Fsr(u8 fsr);
u8 MPU_Set_LPF(u16 lpf);
u8 MPU_Set_Rate(u16 rate);
void I2C_MPU6050_Init(void);
short MPU_Get_Temperature(void);
u8 MPU_Get_Gyroscope(short* gx, short* gy, short* gz);
u8 MPU_Get_Accelerometer(short* ax, short* ay, short* az);
uint16_t MPU_Get_Data(uint8_t REG_Address);
void MPU_6050_Printf(void);

void MPU6050_Read(void);
void MPU6050_Dataanl(void);
extern S_INT16_XYZ      MPU6050_ACC_LAST, MPU6050_GYRO_LAST;    //最新一次读取值
#endif

