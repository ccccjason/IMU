#ifndef __SPI_H
#define __SPI_H
#include "stm32f0xx.h"

#define RCC_SPI1_CLK       RCC_APB2Periph_SPI1
#define SPI1_GPIO_PORT     GPIOA
#define RCC_SPI1_PORT      RCC_AHBPeriph_GPIOA
#define SPI1_SCK           GPIO_Pin_5
#define SPI1_MISO          GPIO_Pin_6
#define SPI1_MOSI          GPIO_Pin_7
#define SPI1_SCK_SOURCE             GPIO_PinSource5
#define SPI1_MISO_SOURCE            GPIO_PinSource6
#define SPI1_MOSI_SOURCE            GPIO_PinSource7


#define MOSI_H GPIO_SetBits(GPIOA, GPIO_Pin_7)
#define MOSI_L GPIO_ResetBits(GPIOA, GPIO_Pin_7)
#define SCLK_H GPIO_SetBits(GPIOA, GPIO_Pin_5)
#define SCLK_L GPIO_ResetBits(GPIOA, GPIO_Pin_5)
#define MISO GPIO_ReadInputDataBit(GPIOA, GPIO_Pin_6)


void SPI1_Init_Anolog(void);
void SPI1_Init(void);            //初始化SPI口
uint8_t SPI1_ReadWriteByte(uint16_t TxData);//SPI总线读写一个字节

#endif

