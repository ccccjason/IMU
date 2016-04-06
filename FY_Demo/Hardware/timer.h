#ifndef __TIMER_H__
#define __TIMER_H__

#include "stm32f0xx.h"

class timer
{
public:
    timer();
    uint16_t tim2_1ms, tim2_2ms, tim2_5ms, tim2_10ms, tim2_1s;
};

void TIM2_Init(uint16_t arr, uint16_t psc);
extern timer tim2;
#endif

