#ifndef __SYSTICK_H
#define __SYSTICK_H

#include "stm32f0xx.h"

void Systick_Init(void);
void delay_us(__IO uint32_t nTime);
void TimingDelay_Decrement(void);
void delay_ms(__IO uint32_t nTime);
#endif /* __SYSTICK_H */
