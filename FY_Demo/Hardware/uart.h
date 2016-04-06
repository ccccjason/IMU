#ifndef __UART_H
#define __UART_H

#include "stm32f0xx.h"
#include <stdio.h>




void USART_Configuration(uint32_t BaudRate);
int fputc(int ch, FILE* f);
void UART_send_byte(uint8_t byte);
void UART_Send(uint8_t* Buffer, uint32_t Length);
void USART_printf(char* Data, ...);
//uint8_t UART_Recive(void);
//void USART1_printf(USART_TypeDef* USARTx, char *Data,...);
#endif /* __UART_H */
