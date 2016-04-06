#ifndef __LED_H__
#define __LED_H__

#include "stm32f0xx.h"

#define RCC_LED    RCC_AHBPeriph_GPIOA
#define LED_GPIO   GPIOA
#define LED_Pin    GPIO_Pin_2|GPIO_Pin_3



void LED_Init(void);
void LED_open(void);
void LED_close(void);
void LED_Toggle(void);

#endif

