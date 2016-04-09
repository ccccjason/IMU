#ifndef __I2C_HARD_H__
#define __I2C_HARD_H__

#include "board.h"

typedef enum {
    I2C_OK = 0,
    I2C_FAIL = 1
} I2C_Status;

#define I2C_TIMING                                0x00210507
#define I2C_TIMEOUT                               0x1000

#define RCC_I2C_GPIO    RCC_AHBPeriph_GPIOB
#define I2C_SCL         GPIO_Pin_8
#define I2C_SDA         GPIO_Pin_9
#define I2C_GPIO        GPIOB
#define I2C             I2C1
#define RCC_I2C_CLK     RCC_I2C1CLK_SYSCLK


void I2C_Init_Config(void);
I2C_Status I2C_Read_NBytes(uint8_t driver_Addr, uint8_t start_Addr,
                           uint8_t number_Bytes, uint8_t* read_Buffer);
I2C_Status I2C_Write_NBytes(uint8_t driver_Addr, uint8_t start_Addr,
                            uint8_t number_Bytes, uint8_t* write_Buffer);




#endif
