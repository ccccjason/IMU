#include "systick.h"

static __IO uint32_t TimingDelay;

void delay_ms(__IO uint32_t nTime)
{
    TimingDelay = nTime;

    while (TimingDelay != 0);
}
//����ʹ��systick��ʱ������1us�ж�һ�Σ��������������ֻ����Ϊ10us����ʱʹ�ô���ʱ
void delay_us(__IO uint32_t nTime)
{
    while (nTime--) {
        uint16_t i = 5;

        while (i--);
    }
}

/*******************************************************************************
* @function :
* @brief : Decrements the TimingDelay variable.
* @param : None
* @retval:None
  *****************************************************************************/
void TimingDelay_Decrement(void)
{
    if (TimingDelay != 0x00) {
        TimingDelay--;
    }
}
/*******************************************************************************
* @function : init systick
* @brief : Decrements the TimingDelay variable.
* @param : None
* @retval:None
  *****************************************************************************/
void Systick_Init(void)
{
    if (SysTick_Config(SystemCoreClock / 1000)) { //
        /* Capture error */
        while (1);
    }
}
