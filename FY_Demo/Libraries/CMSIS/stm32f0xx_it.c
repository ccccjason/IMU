/**
  ******************************************************************************
  * @file    ADC_DMA/stm32f0xx_it.c
  * @author  MCD Application Team
  * @version V1.0.0
  * @date    23-March-2012
  * @brief   Main Interrupt Service Routines.
  *          This file provides template for all exceptions handler and
  *          peripherals interrupt service routine.
  ******************************************************************************
  * @attention
  *
  * <h2><center>&copy; COPYRIGHT 2012 STMicroelectronics</center></h2>
  *
  * Licensed under MCD-ST Liberty SW License Agreement V2, (the "License");
  * You may not use this file except in compliance with the License.
  * You may obtain a copy of the License at:
  *
  *        http://www.st.com/software_license_agreement_liberty_v2
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  ******************************************************************************
  */

/* Includes ------------------------------------------------------------------*/
#include "stm32f0xx_it.h"
#include "board.h"
/** @addtogroup STM32F0_Discovery_Peripheral_Examples
  * @{
  */

/** @addtogroup ADC_DMA
  * @{
  */

/* Private typedef -----------------------------------------------------------*/
/* Private define ------------------------------------------------------------*/
/* Private macro -------------------------------------------------------------*/
/* Private variables ---------------------------------------------------------*/
/* Private function prototypes -----------------------------------------------*/
/* Private functions ---------------------------------------------------------*/

/******************************************************************************/
/*            Cortex-M0 Processor Exceptions Handlers                         */
/******************************************************************************/

/**
  * @brief  This function handles NMI exception.
  * @param  None
  * @retval None
  */
void NMI_Handler(void)
{
}

/**
  * @brief  This function handles Hard Fault exception.
  * @param  None
  * @retval None
  */
void HardFault_Handler(void)
{
    /* Go to infinite loop when Hard Fault exception occurs */
    while (1) {
    }
}

/**
  * @brief  This function handles SVCall exception.
  * @param  None
  * @retval None
  */
void SVC_Handler(void)
{
}

/**
  * @brief  This function handles PendSVC exception.
  * @param  None
  * @retval None
  */
void PendSV_Handler(void)
{
}

/**
  * @brief  This function handles SysTick Handler.
  * @param  None
  * @retval None
  */
void SysTick_Handler(void)
{

}
void EXTI4_15_IRQHandler(void)
{
    if (EXTI_GetITStatus(EXTI_Line6) != RESET) {
        LED_Toggle();
        key_tim.key_int = 1;
        EXTI_ClearITPendingBit(EXTI_Line6);
    }
}
//定时器2中断服务程序
void TIM2_IRQHandler(void)
{
    if (TIM_GetITStatus(TIM2, TIM_IT_Update) != RESET) { //是更新中断
        TIM_ClearITPendingBit(TIM2, TIM_IT_Update);    //清除TIMx更新中断标志
        tim2.tim2_1ms++;
        tim2.tim2_2ms++;
        tim2.tim2_5ms++;
        tim2.tim2_10ms++;
        tim2.tim2_1s++;

        if (1 == tim2.tim2_1ms) {
            tim2.tim2_1ms = 0;

        }

        if (2 == tim2.tim2_2ms) {
            tim2.tim2_2ms = 0;
        }

        if (5 == tim2.tim2_5ms) {
            tim2.tim2_5ms = 0;

        }

        if (10 == tim2.tim2_10ms) {
            tim2.tim2_10ms = 0;

        }

        if (1000 == tim2.tim2_1s) {
            tim2.tim2_1s = 0;
            //LED_Toggle();
            printf("1s时间到\r\n");
        }


    }
}
//定时器3中断服务程序
void TIM3_IRQHandler(void)
{
    if (TIM_GetITStatus(TIM3, TIM_IT_Update) != RESET) { //是更新中断
        USART2_RX_STA |= 1 << 15; //标记接收完成

        TIM_ClearITPendingBit(TIM3, TIM_IT_Update);    //清除TIMx更新中断标志
        TIM3_Set(0);            //关闭TIM3
    }
}
void USART2_IRQHandler(void)
{
    u8 res;

    if (USART_GetITStatus(USART2, USART_IT_RXNE) != RESET) { //接收到数据

        res = USART_ReceiveData(USART2);

        if (USART2_RX_STA < USART2_MAX_RECV_LEN) {  //还可以接收数据
            TIM_SetCounter(TIM3, 0); //计数器清空

            if (USART2_RX_STA == 0) {
                TIM3_Set(1);    //使能定时器4的中断
            }

            USART2_RX_BUF[USART2_RX_STA++] = res;   //记录接收到的值
        } else {
            USART2_RX_STA |= 1 << 15;               //强制标记接收完成
        }
    }
}
void DMA1_Channel4_5_IRQHandler(void)
{

}
/******************************************************************************/
/*                 STM32F0xx Peripherals Interrupt Handlers                   */
/*  Add here the Interrupt Handler for the used peripheral(s) (PPP), for the  */
/*  available peripheral interrupt handler's name please refer to the startup */
/*  file (startup_stm32f0xx.s).                                               */
/******************************************************************************/

/**
  * @brief  This function handles PPP interrupt request.
  * @param  None
  * @retval None
  */
/*void PPP_IRQHandler(void)
{
}*/

/**
  * @}
  */

/**
  * @}
  */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
