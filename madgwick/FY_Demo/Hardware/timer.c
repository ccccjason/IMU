#include "timer.h"
#include "uart.h"

timer tim2;
timer::timer()
{
    tim2_1s = tim2_1ms = tim2_2ms = tim2_5ms = tim2_10ms = 0;
}
void TIM2_Init(uint16_t arr,
               uint16_t psc) //1000,48,48000000/48/1000=1000HZ=1ms中断
{
    NVIC_InitTypeDef NVIC_InitStructure;
    TIM_TimeBaseInitTypeDef  TIM_TimeBaseStructure;

    RCC_APB1PeriphClockCmd(RCC_APB1Periph_TIM2,
                           ENABLE); //时钟使能//TIM4时钟使能

    //定时器TIM2初始化
    TIM_TimeBaseStructure.TIM_Period =
        arr; //设置在下一个更新事件装入活动的自动重装载寄存器周期的值
    TIM_TimeBaseStructure.TIM_Prescaler = psc -
                                          1; //设置用来作为TIMx时钟频率除数的预分频值
    TIM_TimeBaseStructure.TIM_ClockDivision =
        TIM_CKD_DIV1; //设置时钟分割:TDTS = Tck_tim
    TIM_TimeBaseStructure.TIM_CounterMode = TIM_CounterMode_Up;  //TIM向上计数模式
    TIM_TimeBaseInit(TIM2,
                     &TIM_TimeBaseStructure); //根据指定的参数初始化TIMx的时间基数单位

    TIM_ITConfig(TIM2, TIM_IT_Update, ENABLE); //使能指定的TIM4中断,允许更新中断

    /*  TIM3 中断嵌套设计*/
    NVIC_InitStructure.NVIC_IRQChannel = TIM2_IRQn;
    NVIC_InitStructure.NVIC_IRQChannelPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
    NVIC_Init(&NVIC_InitStructure);

    TIM_Cmd(TIM2, ENABLE);
}

