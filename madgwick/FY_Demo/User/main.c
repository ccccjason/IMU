/*******************************************************************************
  * @file    myProject/main.c
  * @author  tanjinlong guiinfeiyutech
  * @version V1.0.0
  * @date    2-7-2015
  * @brief   Main program body
  *****************************************************************************/

#include "stm32f0xx.h"
#include "board.h"


int main(void)
{
    board_init();
    TIM2_Init(1000, 48);
    I2C_MPU6050_Init();
    HMC5883.HMC5883_Init();

    USART_printf("云台定位系统，测试程序调试\r\n");

    while (1) {
        Prepare_Data();
        Get_Attitude();
        //MPU_6050_Printf();
        //HMC5883.HMC5883_Read();
        delay_ms(500);
    }
}



