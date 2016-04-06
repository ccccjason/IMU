/**********************************************************************************
 * 文件名  ：uart.c
 * 描述    ：串口底层驱动应用函数库
 * 硬件平台：STM32F030
 * 硬件连接 ----------------------------
 *
 *         USART1_TX -> PA9
 *         USART1_RX -> PA10
 * 库版本  STM32F0_Discovery_Library
 * 作者   谭金龙
**********************************************************************************/
#include "uart.h"
#include <stdarg.h>
#include <stdio.h>
#include "stm32f0xx_usart.h"
#ifdef __GNUC__
/*****************************************************************************
  With GCC/RAISONANCE, small printf (option LD Linker->Libraries->Small printf
   set to 'Yes') calls __io_putchar()
 ****************************************************************************/
#define PUTCHAR_PROTOTYPE int __io_putchar(int ch)
#else
#define PUTCHAR_PROTOTYPE int fputc(int ch, FILE *f)
#endif /* __GNUC__ */


/*******************************************************************************
*Function:uart configuration
*parm:none
*description:
*******************************************************************************/
void USART_Configuration(uint32_t BaudRate)//串口初始化函数
{

    GPIO_InitTypeDef  GPIO_InitStructure;
    USART_InitTypeDef USART_InitStructure;

    RCC_AHBPeriphClockCmd(RCC_AHBPeriph_GPIOA, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_USART1, ENABLE);

    GPIO_PinAFConfig(GPIOA, GPIO_PinSource9, GPIO_AF_1);
    GPIO_PinAFConfig(GPIOA, GPIO_PinSource10, GPIO_AF_1);

    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_9 | GPIO_Pin_10;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF;
    GPIO_InitStructure.GPIO_OType = GPIO_OType_PP;
    GPIO_InitStructure.GPIO_PuPd = GPIO_PuPd_UP;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_Level_3;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    USART_InitStructure.USART_BaudRate = BaudRate;            //设置串口波特率
    USART_InitStructure.USART_WordLength = USART_WordLength_8b; //设置数据位
    USART_InitStructure.USART_StopBits = USART_StopBits_1;      //设置停止位
    USART_InitStructure.USART_Parity = USART_Parity_No;         //设置效验位
    USART_InitStructure.USART_HardwareFlowControl =
        USART_HardwareFlowControl_None; //设置流控制
    USART_InitStructure.USART_Mode =
        USART_Mode_Rx | USART_Mode_Tx;//设置工作模式
    USART_Init(USART1, &USART_InitStructure);                 //配置入结构体

    USART_Cmd(USART1, ENABLE);                                   //使能串口1

}
/*******************************************************************************
*Function:uart send byte
*parm:none
*description:
*******************************************************************************/
void UART_send_byte(uint8_t byte)                                //发送1字节数据
{
    while (!((USART1->ISR) & (1 << 7)));

    USART1->TDR = byte;
}
/*******************************************************************************
*Function:uart send charater
*parm:charater buffer,charater length
*description:
*******************************************************************************/
void UART_Send(uint8_t* Buffer, uint32_t Length)
{
    while (Length != 0) {
        while (!((USART1->ISR) & (1 << 7)));                //等待发送完

        USART1->TDR = *Buffer;
        Buffer++;
        Length--;
    }
}
/*******************************************************************************
*Function:recive data
*parm:none
*description:
*******************************************************************************/
uint8_t UART_Recive(void)
{
    while (!(USART1->ISR & (1 << 5)));                      //等待接收到数据

    return (USART1->RDR);                                 //读出数据
}
/*******************************************************************************
*Function:uart send dat
*parm:none
*description:
*******************************************************************************/
PUTCHAR_PROTOTYPE {
    /* 将Printf内容发往串口 */
    USART_SendData(USART1, (uint8_t)  ch);

    while (USART_GetFlagStatus(USART1, USART_FLAG_TXE) == RESET)
    {}

    return (ch);
}
/*******************************************************************************
*Function:uart send dat
*parm:none
*description:
*******************************************************************************/
void UART_send_string(char* Data)
{
    while (*Data) {
        UART_send_byte(*Data++);

    }
}
/*******************************************************************************
*Function:USART_printf
*parm:可变参
*description:
*******************************************************************************/
void USART_printf(char* Data, ...)
{

    va_list ap;
    char buf[256];
    va_start(ap, Data);
    vsprintf(buf, Data, ap);
    UART_send_string(buf);
    va_end(ap);
}
