#include "led.h"

/*******************************************************************************
*Function:init the LED
*parm:none
*description:配置使用PF1作为GPIO口
*******************************************************************************/
void LED_Init(void)
{
    GPIO_InitTypeDef GPIO_InitStruct;
    RCC_AHBPeriphClockCmd(RCC_LED, ENABLE);

    GPIO_InitStruct.GPIO_Pin = LED_Pin;
    GPIO_InitStruct.GPIO_Mode = GPIO_Mode_OUT;
    GPIO_InitStruct.GPIO_OType = GPIO_OType_PP;
    GPIO_InitStruct.GPIO_Speed = GPIO_Speed_Level_3;
    GPIO_Init(LED_GPIO, &GPIO_InitStruct);
    GPIO_SetBits(LED_GPIO, LED_Pin);

}
/*******************************************************************************
*Function:open the LED
*parm:none
*description:
*******************************************************************************/
void LED_open(void)
{
    GPIO_SetBits(LED_GPIO, LED_Pin);
}
/*******************************************************************************
*Function:close the LED
*parm:none
*description:
*******************************************************************************/
void LED_close(void)
{
    GPIO_ResetBits(LED_GPIO, LED_Pin);
}
/*******************************************************************************
*Function:toggle the LED
*parm:none
*description:
*******************************************************************************/
void LED_Toggle(void)
{
    GPIO_WriteBit(LED_GPIO, LED_Pin,
                  (BitAction)((1 - GPIO_ReadOutputDataBit(LED_GPIO, LED_Pin))));
}

