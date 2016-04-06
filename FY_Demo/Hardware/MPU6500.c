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

int16_t mpu6050_buffer[6];      //iic读取后存放数据

S_INT16_XYZ     GYRO_OFFSET, ACC_OFFSET;        //零漂
u8                      GYRO_OFFSET_OK = 1;
u8                      ACC_OFFSET_OK = 1;
S_INT16_XYZ     MPU6050_ACC_LAST, MPU6050_GYRO_LAST;    //最新一次读取值


/*******************************************************************************
  * @brief  初始化MPU6050
  * @param  NONE
  * @retval NONE
  *****************************************************************************/
void I2C_MPU6050_Init(void)
{
    I2C_Init_Config();
    uint8_t t_cmd;
    t_cmd = 0x80;//复位MPU6050状态
    I2C_Write_NBytes(MPU_SlaveAddress, PWR_MGMT_1, 1, &t_cmd);
    delay_ms(100);//注意一定要延时

    t_cmd = 0x00;//激活MPU6050
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
    MPU_Set_Gyro_Fsr(3);                    //陀螺仪传感器,±2000dps
    MPU_Set_Accel_Fsr(0);                   //加速度传感器,±2g
    MPU_Set_Rate(200);                      //设置采样率200Hz

    I2C_Read_NBytes(MPU_SlaveAddress,DEVICE_ID_REG,1,&t_cmd);//读出ID号为104
    USART_printf("器件ID：%d\r\n",t_cmd);
    if(t_cmd == MPU_SlaveAddress)
    {
        t_cmd = 0x01;
        I2C_Write_NBytes(MPU_SlaveAddress,PWR_MGMT_1,1,&t_cmd); //设置CLKSEL,PLL X轴为参考
        t_cmd = 0x00;
        I2C_Write_NBytes(MPU_SlaveAddress,PWR_MGMT_1,1,&t_cmd); //加速度与陀螺仪都工作
        MPU_Set_Rate(200);                      //设置采样率为50Hz
    }
    */
}
/*******************************************************************************
  * @brief  设置MPU6050陀螺仪传感器满量程范围
  * @param  fsr:0,±250dps;1,±500dps;2,±1000dps;3,±2000dps
  * @retval //返回值:0,设置成功 其他,设置失败
  *****************************************************************************/
u8 MPU_Set_Gyro_Fsr(u8 fsr)
{
    uint8_t t_cmd;
    t_cmd = fsr << 3; //如3<<3 = 0x18，采样率为2000dps
    return I2C_Write_NBytes(MPU_SlaveAddress, GYRO_CONFIG, 1,
                            &t_cmd); //设置陀螺仪满量程范围
}
/*******************************************************************************
  * @brief  设置MPU6050加速度传感器满量程范围
  * @param  fsr:0,±2g;1,±4g;2,±8g;3,±16g
  * @retval //返回值:0,设置成功 其他,设置失败
  *****************************************************************************/
u8 MPU_Set_Accel_Fsr(u8 fsr)
{
    uint8_t t_cmd;
    t_cmd = fsr << 3;
    return I2C_Write_NBytes(MPU_SlaveAddress, ACCEL_CONFIG, 1,
                            &t_cmd); //设置加速度传感器满量程范围
}
/*******************************************************************************
  * @brief  设置MPU6050的数字低通滤波器
  * @param  lpf:数字低通滤波频率(Hz)
  * @retval //返回值:0,设置成功 其他,设置失败
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
                            &data); //设置数字低通滤波器
}
/*******************************************************************************
  * @brief  设置MPU6050的采样率(假定Fs=1KHz)
  * @param  rate:4~1000(Hz)
  * @retval //返回值:0,设置成功 其他,设置失败
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
    I2C_Write_NBytes(MPU_SlaveAddress, SMPLRT_DIV, 1, &data); //设置数字低通滤波器
    return MPU_Set_LPF(rate / 2); //自动设置LPF为采样率的一半
}
/*******************************************************************************
  * @brief  得到温度值
  * @param  NONE
  * @retval 温度值(扩大了100倍)
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
  * @brief  获得MPU6050加速度数据(原始值)
  * @param  用于接收数据的变量gx,gy,gz:陀螺仪x,y,z轴的原始读数(带符号)
  * @retval 返回值:0,成功   其他,错误代码
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
  * @brief  获得MPU6050加速度数据(原始值)
  * @param  用于接收数据的变量gx,gy,gz:加速度x,y,z轴的原始读数(带符号)
  * @retval 返回值:0,成功   其他,错误代码
  *****************************************************************************/
u8 MPU_Get_Accelerometer(short* ax, short* ay, short* az)
{
    u8 buf[6], res;
    res = I2C_Read_NBytes(MPU_SlaveAddress, ACCEL_XOUT_H, 6,
                          buf); //从X轴开始的地址读

    if (res == 0) {
        *ax = ((u16)buf[0] << 8) | buf[1];
        *ay = ((u16)buf[2] << 8) | buf[3];
        *az = ((u16)buf[4] << 8) | buf[5];
    }

    return res;;
}
/*******************************************************************************
  * @brief  获得MPU6050
  * @param  寄存器地址，
  * @retval NONE
  *****************************************************************************/
uint16_t MPU_Get_Data(uint8_t REG_Address)
{
    uint8_t t_buf[2];
    I2C_Read_NBytes(MPU_SlaveAddress, REG_Address, 2, t_buf);
    return (t_buf[0] | t_buf[1] << 8); //合成数据
}
/**************************实现函数********************************************
//将iic读取到得数据分拆,放入相应寄存器,更新MPU6050_Last
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
void MPU6050_Dataanl(void)//记得要更新mpu6050_buffer的信息
{
    MPU6050_ACC_LAST.X = mpu6050_buffer[0] - ACC_OFFSET.X;
    MPU6050_ACC_LAST.Y = mpu6050_buffer[1] - ACC_OFFSET.Y;
    MPU6050_ACC_LAST.Z = mpu6050_buffer[2] - ACC_OFFSET.Z;
    //跳过温度ADC
    MPU6050_GYRO_LAST.X = mpu6050_buffer[3] - GYRO_OFFSET.X;
    MPU6050_GYRO_LAST.Y = mpu6050_buffer[4] - GYRO_OFFSET.Y;
    MPU6050_GYRO_LAST.Z = mpu6050_buffer[5] - GYRO_OFFSET.Z;


    printf("X轴加速度%10d\r\n", MPU6050_ACC_LAST.X);
    printf("Y轴加速度%10d\r\n", MPU6050_ACC_LAST.Y);
    printf("Z轴加速度%10d\r\n", MPU6050_ACC_LAST.Z);
    printf("X轴陀螺仪%10d\r\n", MPU6050_GYRO_LAST.X);
    printf("Y轴陀螺仪%10d\r\n", MPU6050_GYRO_LAST.Y);
    printf("Z轴陀螺仪%10d\r\n", MPU6050_GYRO_LAST.Z);
    printf("温度:%d\r\n", MPU_Get_Temperature());

}
/*******************************************************************************
  * @brief  输出MPU6050
  * @param  NONE
  * @retval NONE
  *****************************************************************************/
void MPU_6050_Printf(void)
{
    USART_printf("加速度原始速度	X轴:		Y轴		Z轴\r\n");
    USART_printf("			%10d%10d%10d\r\n",
                 MPU_Get_Data(ACCEL_XOUT_H),
                 MPU_Get_Data(ACCEL_YOUT_H),
                 MPU_Get_Data(ACCEL_ZOUT_H));
    USART_printf("陀螺仪原始速度	X轴:		Y轴		Z轴\r\n");
    USART_printf("			%10d%10d%10d\r\n",
                 MPU_Get_Data(GYRO_XOUT_H),
                 MPU_Get_Data(GYRO_YOUT_H),
                 MPU_Get_Data(GYRO_ZOUT_H));
    USART_printf("温度：%0.2f\r\n", MPU_Get_Temperature());
}