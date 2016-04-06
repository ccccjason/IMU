/*******************************************************************************
  * @file
  * @author
  * @version
  * @date
  * @brief
  *****************************************************************************/
#include "MPU6500.h"
#include "I2C_hard.h"
#include "uart.h"

int16_t mpu6050_buffer[6];      //iic��ȡ��������

S_INT16_XYZ     GYRO_OFFSET, ACC_OFFSET;        //��Ư
u8                      GYRO_OFFSET_OK = 1;
u8                      ACC_OFFSET_OK = 1;
S_INT16_XYZ     MPU6050_ACC_LAST, MPU6050_GYRO_LAST;    //����һ�ζ�ȡֵ


/*******************************************************************************
  * @brief  ��ʼ��MPU6050
  * @param  NONE
  * @retval NONE
  *****************************************************************************/
void I2C_MPU6050_Init(void)
{
    I2C_Init_Config();
    uint8_t t_cmd;
    t_cmd = 0x80;//��λMPU6050״̬
    I2C_Write_NBytes(MPU_SlaveAddress, PWR_MGMT_1, 1, &t_cmd);
    delay_ms(100);//ע��һ��Ҫ��ʱ

    t_cmd = 0x00;//����MPU6050
    I2C_Write_NBytes(MPU_SlaveAddress, PWR_MGMT_1, 1, &t_cmd);
    t_cmd = 0x07;//
    I2C_Write_NBytes(MPU_SlaveAddress, SMPLRT_DIV, 1, &t_cmd);
    t_cmd = 0x06;//
    I2C_Write_NBytes(MPU_SlaveAddress, CONFIG, 1, &t_cmd);
    t_cmd = 0x18;//
    I2C_Write_NBytes(MPU_SlaveAddress, GYRO_CONFIG, 1, &t_cmd);
    t_cmd = 0x01;//
    I2C_Write_NBytes(MPU_SlaveAddress, ACCEL_CONFIG, 1, &t_cmd);
    /*
    MPU_Set_Gyro_Fsr(3);                    //�����Ǵ�����,��2000dps
    MPU_Set_Accel_Fsr(0);                   //���ٶȴ�����,��2g
    MPU_Set_Rate(200);                      //���ò�����200Hz

    I2C_Read_NBytes(MPU_SlaveAddress,DEVICE_ID_REG,1,&t_cmd);//����ID��Ϊ104
    USART_printf("����ID��%d\r\n",t_cmd);
    if(t_cmd == MPU_SlaveAddress)
    {
        t_cmd = 0x01;
        I2C_Write_NBytes(MPU_SlaveAddress,PWR_MGMT_1,1,&t_cmd); //����CLKSEL,PLL X��Ϊ�ο�
        t_cmd = 0x00;
        I2C_Write_NBytes(MPU_SlaveAddress,PWR_MGMT_1,1,&t_cmd); //���ٶ��������Ƕ�����
        MPU_Set_Rate(200);                      //���ò�����Ϊ50Hz
    }
    */
}
/*******************************************************************************
  * @brief  ����MPU6050�����Ǵ����������̷�Χ
  * @param  fsr:0,��250dps;1,��500dps;2,��1000dps;3,��2000dps
  * @retval //����ֵ:0,���óɹ� ����,����ʧ��
  *****************************************************************************/
u8 MPU_Set_Gyro_Fsr(u8 fsr)
{
    uint8_t t_cmd;
    t_cmd = fsr << 3; //��3<<3 = 0x18��������Ϊ2000dps
    return I2C_Write_NBytes(MPU_SlaveAddress, GYRO_CONFIG, 1,
                            &t_cmd); //���������������̷�Χ
}
/*******************************************************************************
  * @brief  ����MPU6050���ٶȴ����������̷�Χ
  * @param  fsr:0,��2g;1,��4g;2,��8g;3,��16g
  * @retval //����ֵ:0,���óɹ� ����,����ʧ��
  *****************************************************************************/
u8 MPU_Set_Accel_Fsr(u8 fsr)
{
    uint8_t t_cmd;
    t_cmd = fsr << 3;
    return I2C_Write_NBytes(MPU_SlaveAddress, ACCEL_CONFIG, 1,
                            &t_cmd); //���ü��ٶȴ����������̷�Χ
}
/*******************************************************************************
  * @brief  ����MPU6050�����ֵ�ͨ�˲���
  * @param  lpf:���ֵ�ͨ�˲�Ƶ��(Hz)
  * @retval //����ֵ:0,���óɹ� ����,����ʧ��
  *****************************************************************************/
u8 MPU_Set_LPF(u16 lpf)
{
    u8 data = 0;

    if (lpf >= 188) {
        data = 1;
    } else if (lpf >= 98) {
        data = 2;
    } else if (lpf >= 42) {
        data = 3;
    } else if (lpf >= 20) {
        data = 4;
    } else if (lpf >= 10) {
        data = 5;
    } else {
        data = 6;
    }

    return I2C_Write_NBytes(MPU_SlaveAddress, CONFIG, 1,
                            &data); //�������ֵ�ͨ�˲���
}
/*******************************************************************************
  * @brief  ����MPU6050�Ĳ�����(�ٶ�Fs=1KHz)
  * @param  rate:4~1000(Hz)
  * @retval //����ֵ:0,���óɹ� ����,����ʧ��
  *****************************************************************************/
u8 MPU_Set_Rate(u16 rate)
{
    u8 data;

    if (rate > 1000) {
        rate = 1000;
    }

    if (rate < 4) {
        rate = 4;
    }

    data = 1000 / rate - 1;
    I2C_Write_NBytes(MPU_SlaveAddress, SMPLRT_DIV, 1, &data); //�������ֵ�ͨ�˲���
    return MPU_Set_LPF(rate / 2); //�Զ�����LPFΪ�����ʵ�һ��
}
/*******************************************************************************
  * @brief  �õ��¶�ֵ
  * @param  NONE
  * @retval �¶�ֵ(������100��)
  *****************************************************************************/
short MPU_Get_Temperature(void)
{

    uint8_t buf[2];
    short raw;
    float temp;

    I2C_Read_NBytes(MPU_SlaveAddress, TEMP_OUT_H, 2, buf);

    raw = ((u16)buf[0] << 8) | buf[1];
    temp = 36.53 + ((double)raw) / 340;
    return short(temp * 100);
}
/*******************************************************************************
  * @brief  ���MPU6050���ٶ�����(ԭʼֵ)
  * @param  ���ڽ������ݵı���gx,gy,gz:������x,y,z���ԭʼ����(������)
  * @retval ����ֵ:0,�ɹ�   ����,�������
  *****************************************************************************/
u8 MPU_Get_Gyroscope(short* gx, short* gy, short* gz)
{
    u8 buf[6], res;
    res = I2C_Read_NBytes(MPU_SlaveAddress, GYRO_XOUT_H, 6, buf);

    if (res == 0) {
        *gx = ((u16)buf[0] << 8) | buf[1];
        *gy = ((u16)buf[2] << 8) | buf[3];
        *gz = ((u16)buf[4] << 8) | buf[5];
    }

    return res;;
}
/*******************************************************************************
  * @brief  ���MPU6050���ٶ�����(ԭʼֵ)
  * @param  ���ڽ������ݵı���gx,gy,gz:���ٶ�x,y,z���ԭʼ����(������)
  * @retval ����ֵ:0,�ɹ�   ����,�������
  *****************************************************************************/
u8 MPU_Get_Accelerometer(short* ax, short* ay, short* az)
{
    u8 buf[6], res;
    res = I2C_Read_NBytes(MPU_SlaveAddress, ACCEL_XOUT_H, 6,
                          buf); //��X�Ὺʼ�ĵ�ַ��

    if (res == 0) {
        *ax = ((u16)buf[0] << 8) | buf[1];
        *ay = ((u16)buf[2] << 8) | buf[3];
        *az = ((u16)buf[4] << 8) | buf[5];
    }

    return res;;
}
/*******************************************************************************
  * @brief  ���MPU6050
  * @param  �Ĵ�����ַ��
  * @retval NONE
  *****************************************************************************/
uint16_t MPU_Get_Data(uint8_t REG_Address)
{
    uint8_t t_buf[2];
    I2C_Read_NBytes(MPU_SlaveAddress, REG_Address, 2, t_buf);
    return (t_buf[0] | t_buf[1] << 8); //�ϳ�����
}
/**************************ʵ�ֺ���********************************************
//��iic��ȡ�������ݷֲ�,������Ӧ�Ĵ���,����MPU6050_Last
*******************************************************************************/
void MPU6050_Read(void)
{
    for (int i = 0; i < 3; i++) {
        mpu6050_buffer[i] = MPU_Get_Data(ACCEL_XOUT_H + i);
    }

    for (int i = 0; i < 3; i++) {
        mpu6050_buffer[i + 3] = MPU_Get_Data(GYRO_XOUT_H + i);
    }
}
void MPU6050_Dataanl(void)//�ǵ�Ҫ����mpu6050_buffer����Ϣ
{
    MPU6050_ACC_LAST.X = mpu6050_buffer[0] - ACC_OFFSET.X;
    MPU6050_ACC_LAST.Y = mpu6050_buffer[1] - ACC_OFFSET.Y;
    MPU6050_ACC_LAST.Z = mpu6050_buffer[2] - ACC_OFFSET.Z;
    //�����¶�ADC
    MPU6050_GYRO_LAST.X = mpu6050_buffer[3] - GYRO_OFFSET.X;
    MPU6050_GYRO_LAST.Y = mpu6050_buffer[4] - GYRO_OFFSET.Y;
    MPU6050_GYRO_LAST.Z = mpu6050_buffer[5] - GYRO_OFFSET.Z;


    printf("X����ٶ�%10d\r\n", MPU6050_ACC_LAST.X);
    printf("Y����ٶ�%10d\r\n", MPU6050_ACC_LAST.Y);
    printf("Z����ٶ�%10d\r\n", MPU6050_ACC_LAST.Z);
    printf("X��������%10d\r\n", MPU6050_GYRO_LAST.X);
    printf("Y��������%10d\r\n", MPU6050_GYRO_LAST.Y);
    printf("Z��������%10d\r\n", MPU6050_GYRO_LAST.Z);
    printf("�¶�:%d\r\n", MPU_Get_Temperature());

}
/*******************************************************************************
  * @brief  ���MPU6050
  * @param  NONE
  * @retval NONE
  *****************************************************************************/
void MPU_6050_Printf(void)
{
    USART_printf("���ٶ�ԭʼ�ٶ�	X��:		Y��		Z��\r\n");
    USART_printf("			%10d%10d%10d\r\n",
                 MPU_Get_Data(ACCEL_XOUT_H),
                 MPU_Get_Data(ACCEL_YOUT_H),
                 MPU_Get_Data(ACCEL_ZOUT_H));
    USART_printf("������ԭʼ�ٶ�	X��:		Y��		Z��\r\n");
    USART_printf("			%10d%10d%10d\r\n",
                 MPU_Get_Data(GYRO_XOUT_H),
                 MPU_Get_Data(GYRO_YOUT_H),
                 MPU_Get_Data(GYRO_ZOUT_H));
    USART_printf("�¶ȣ�%0.2f\r\n", MPU_Get_Temperature());
}