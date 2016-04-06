/**********************************************************************************
 * �ļ���  ��uart.c
 * ����    �����ڵײ�����Ӧ�ú�����
 * Ӳ��ƽ̨��STM32F030
 * Ӳ������ ----------------------------
 *
 *         USART1_TX -> PA9
 *         USART1_RX -> PA10
 * ��汾  STM32F0_Discovery_Library
 * ����   ̷����
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
void USART_Configuration(uint32_t BaudRate)//���ڳ�ʼ������
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

    USART_InitStructure.USART_BaudRate = BaudRate;            //���ô��ڲ�����
    USART_InitStructure.USART_WordLength = USART_WordLength_8b; //��������λ
    USART_InitStructure.USART_StopBits = USART_StopBits_1;      //����ֹͣλ
    USART_InitStructure.USART_Parity = USART_Parity_No;         //����Ч��λ
    USART_InitStructure.USART_HardwareFlowControl =
        USART_HardwareFlowControl_None; //����������
    USART_InitStructure.USART_Mode =
        USART_Mode_Rx | USART_Mode_Tx;//���ù���ģʽ
    USART_Init(USART1, &USART_InitStructure);                 //������ṹ��

    USART_Cmd(USART1, ENABLE);                                   //ʹ�ܴ���1

}
/*******************************************************************************
*Function:uart send byte
*parm:none
*description:
*******************************************************************************/
void UART_send_byte(uint8_t byte)                                //����1�ֽ�����
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
        while (!((USART1->ISR) & (1 << 7)));                //�ȴ�������

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
    while (!(USART1->ISR & (1 << 5)));                      //�ȴ����յ�����

    return (USART1->RDR);                                 //��������
}
/*******************************************************************************
*Function:uart send dat
*parm:none
*description:
*******************************************************************************/
PUTCHAR_PROTOTYPE {
    /* ��Printf���ݷ������� */
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
*parm:�ɱ��
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
