#include "HMC5883.h"
#include "math.h"
Magnetic    HMC5883;

uint8_t Mag_buf[6];

void Magnetic::HMC5883_Init(void)
{
    uint8_t t_cmd;
    t_cmd = 0x14;
    I2C_Write_NBytes(HMC5883_ADDR, 0x00, 1, &t_cmd);
    t_cmd = 0x00;
    I2C_Write_NBytes(HMC5883_ADDR, 0x02, 1, &t_cmd);
}
void Magnetic::HMC5883_Read(void)
{

    uint8_t t_cmd;
    t_cmd = 0x14;
    I2C_Write_NBytes(HMC5883_ADDR, 0x00, 1, &t_cmd);
    t_cmd = 0x00;
    I2C_Write_NBytes(HMC5883_ADDR, 0x02, 1, &t_cmd);

    for (int i = 0; i < 6; i++) {
        I2C_Read_NBytes(HMC5883_ADDR, 0x03 + i, 6, &Mag_buf[i]);
    }

    Mag_X = (Mag_buf[1] << 8) | Mag_buf[0];
    Mag_Z = (Mag_buf[3] << 8) | Mag_buf[2];
    Mag_Y = (Mag_buf[5] << 8) | Mag_buf[4];
    USART_printf("m_x:%10d m_y:%10d m_z:%10d\r\n",
                 Mag_X, Mag_X, Mag_X);
    /*

      if(HMC5883.x>0X7FFF)HMC5883.x -= 0XFFFF;
      if(HMC5883.y>0X7FFF)HMC5883.y -= 0XFFFF;

    angle = atan2((double)HMC5883.y,(double)HMC5883.x) * (180 / 3.14159265) + 180;
              // angle in degrees

    */
}