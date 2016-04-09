#ifndef __I2C_MPU6050_H__
#define __I2C_MPU6050_H__

#include "board.h"

/* MPU6050 Register Address ------------------------------------------------------------*/
#define SMPLRT_DIV      0x19    //�����ǲ����ʣ�����ֵ��0x07(125Hz)
#define CONFIG          0x1A    //��ͨ�˲�Ƶ�ʣ�����ֵ��0x06(5Hz)
#define GYRO_CONFIG     0x1B    //�������Լ켰������Χ������ֵ��0x18(���Լ죬2000deg/s)
#define ACCEL_CONFIG    0x1C    //���ټ��Լ졢������Χ����ͨ�˲�Ƶ�ʣ�����ֵ��0x01(���Լ죬2G��5Hz)
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
#define PWR_MGMT_1      0x6B    //��Դ��������ֵ��0x00(��������)
#define PWR_MGMT_2      0x6C
#define DEVICE_ID_REG       0x75    //����ID

#define MPU_ADDR        0X68    //MPU_ADDR����1λ�õ�0xD0��MPU_WRITE��ַ
#define MPU_SlaveAddress    0XD0    //IICд��ʱ�ĵ�ַ�ֽ�����


typedef struct {
    int16_t X;
    int16_t Y;
    int16_t Z;
} S_INT16_XYZ; //����ƽ���˲�



u8 MPU_Set_Gyro_Fsr(u8 fsr);//���������ǲ�����
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
extern S_INT16_XYZ      MPU6050_ACC_LAST, MPU6050_GYRO_LAST;    //����һ�ζ�ȡֵ
#endif

