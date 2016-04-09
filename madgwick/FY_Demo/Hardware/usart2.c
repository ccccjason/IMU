/*
**
**
**Description:DMA��USARTͨ��5ΪRX��ͨ��4ΪTX
*/

#include "systick.h"
#include "usart2.h"
#include "stdarg.h"
#include "stdio.h"
#include "string.h"

//���ڷ��ͻ�����
u8 USART2_TX_BUF[USART2_MAX_SEND_LEN] = {"feiyutech"};  //���ͻ���,���USART2_MAX_SEND_LEN�ֽ�

#ifdef USART2_RX_EN                                 //���ʹ���˽���   

//���ڽ��ջ�����
u8 USART2_RX_BUF[USART2_MAX_RECV_LEN];              //���ջ���,���USART2_MAX_RECV_LEN���ֽ�.



//ͨ���жϽ�������2���ַ�֮���ʱ������10ms�������ǲ���һ������������.
//���2���ַ����ռ������10ms,����Ϊ����1����������.Ҳ���ǳ���10msû�н��յ�
//�κ�����,���ʾ�˴ν������.
//���յ�������״̬
//[15]:0,û�н��յ�����;1,���յ���һ������.
//[14:0]:���յ������ݳ���
u16 USART2_RX_STA = 0;

//��ʼ��IO ����2
//pclk1:PCLK1ʱ��Ƶ��(Mhz)
//bound:������
void USART2_Init(u32 bound)
{

    NVIC_InitTypeDef NVIC_InitStructure;
    GPIO_InitTypeDef GPIO_InitStructure;
    USART_InitTypeDef USART_InitStructure;

    RCC_AHBPeriphClockCmd(RCC_AHBPeriph_GPIOA, ENABLE); // GPIOAʱ��
    RCC_APB1PeriphClockCmd(RCC_APB1Periph_USART2, ENABLE);

    GPIO_PinAFConfig(GPIOA, GPIO_PinSource2, GPIO_AF_1);
    GPIO_PinAFConfig(GPIOA, GPIO_PinSource3, GPIO_AF_1);

    USART_DeInit(USART2);  //��λ����2
    //USART2_TX   PA.2  USART2_RX    PA.3
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_2 | GPIO_Pin_3;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_Level_2;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF;    //�����������
    GPIO_InitStructure.GPIO_OType = GPIO_OType_PP;
    GPIO_InitStructure.GPIO_PuPd = GPIO_PuPd_UP;
    GPIO_Init(GPIOA, &GPIO_InitStructure); //��ʼ��


    USART_InitStructure.USART_BaudRate = bound;//һ������Ϊ9600;
    USART_InitStructure.USART_WordLength = USART_WordLength_8b;//�ֳ�Ϊ8λ���ݸ�ʽ
    USART_InitStructure.USART_StopBits = USART_StopBits_1;//һ��ֹͣλ
    USART_InitStructure.USART_Parity = USART_Parity_No;//����żУ��λ
    USART_InitStructure.USART_HardwareFlowControl =
        USART_HardwareFlowControl_None;//��Ӳ������������
    USART_InitStructure.USART_Mode = USART_Mode_Rx | USART_Mode_Tx; //�շ�ģʽ

    USART_Init(USART2, &USART_InitStructure); //��ʼ������  2


    UART_DMA_Config(DMA1_Channel4, (u32)&USART2->TDR,
                    (u32)USART2_TX_BUF); //DMA1ͨ��7,����Ϊ����2,�洢��ΪUSART2_TX_BUF
    USART_DMACmd(USART2, USART_DMAReq_Tx, ENABLE);  //ʹ�ܴ���2��DMA����
    USART_Cmd(USART2, ENABLE);                    //ʹ�ܴ���


#ifdef USART2_RX_EN         //���ʹ���˽���
    //ʹ�ܽ����ж�
    USART_ITConfig(USART2, USART_IT_RXNE, ENABLE);//�����ж�

    NVIC_InitStructure.NVIC_IRQChannel = USART2_IRQn;
    NVIC_InitStructure.NVIC_IRQChannelPriority = 2;
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;         //IRQͨ��ʹ��
    NVIC_Init(&NVIC_InitStructure); //����ָ���Ĳ�����ʼ��VIC�Ĵ���
    TIM3_Init(99, 4799);    //10ms�ж�
    USART2_RX_STA = 0;      //����
    TIM3_Set(0);            //�رն�ʱ��4
#endif

}
//��DMA�£����Դ�������
void UART2_send_byte(uint8_t
                     byte)                                //����1�ֽ�����
{
    while (!((USART2->ISR) & (1 << 7)));

    USART2->TDR = byte;
}
void UART2_send_string(char* Data)
{
    while (*Data) {
        UART2_send_byte(*Data++);
    }
}
//����2,printf ����
//ȷ��һ�η������ݲ�����USART2_MAX_SEND_LEN�ֽ�
void USART2_printf(char* fmt, ...)
{
    va_list ap;
    va_start(ap, fmt);
    vsprintf((char*)USART2_TX_BUF, fmt, ap);
    //UART2_send_string((char*)USART2_TX_BUF);
    va_end(ap);

    while (DMA_GetCurrDataCounter(DMA1_Channel4) == 0); //�ȴ�ͨ��7�������

    UART_DMA_Enable(DMA1_Channel4,
                    strlen((const char*)USART2_TX_BUF)); //ͨ��dma���ͳ�ȥ
}

//����TIM4�Ŀ���
//sta:0���ر�;1,����;
void TIM3_Set(u8 sta)
{
    if (sta) {
        TIM_SetCounter(TIM3, 0); //���������
        TIM_Cmd(TIM3, ENABLE);  //ʹ��TIMx
    } else {
        TIM_Cmd(TIM3, DISABLE);    //�رն�ʱ��4
    }
}

void TIM3_Init(u16 arr, u16 psc) //1000,48,48000000/48/1000=1000HZ=1ms�ж�
{
    NVIC_InitTypeDef NVIC_InitStructure;
    TIM_TimeBaseInitTypeDef  TIM_TimeBaseStructure;

    RCC_APB1PeriphClockCmd(RCC_APB1Periph_TIM3,
                           ENABLE); //ʱ��ʹ��//TIM4ʱ��ʹ��

    //��ʱ��TIM3��ʼ��
    TIM_TimeBaseStructure.TIM_Period =
        arr; //��������һ�������¼�װ�����Զ���װ�ؼĴ������ڵ�ֵ
    TIM_TimeBaseStructure.TIM_Prescaler = psc -
                                          1; //����������ΪTIMxʱ��Ƶ�ʳ�����Ԥ��Ƶֵ
    TIM_TimeBaseStructure.TIM_ClockDivision =
        TIM_CKD_DIV1; //����ʱ�ӷָ�:TDTS = Tck_tim
    TIM_TimeBaseStructure.TIM_CounterMode = TIM_CounterMode_Up;  //TIM���ϼ���ģʽ
    TIM_TimeBaseInit(TIM3,
                     &TIM_TimeBaseStructure); //����ָ���Ĳ�����ʼ��TIMx��ʱ�������λ

    TIM_ITConfig(TIM3, TIM_IT_Update, ENABLE); //ʹ��ָ����TIM4�ж�,��������ж�


    /*  TIM3 �ж�Ƕ�����*/
    NVIC_InitStructure.NVIC_IRQChannel = TIM3_IRQn;
    NVIC_InitStructure.NVIC_IRQChannelPriority = 1;
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
    NVIC_Init(&NVIC_InitStructure);
}
#endif
///////////////////////////////////////USART2 DMA�������ò���//////////////////////////////////
//DMA1�ĸ�ͨ������
//����Ĵ�����ʽ�ǹ̶���,���Ҫ���ݲ�ͬ��������޸�
//�Ӵ洢��->����ģʽ/8λ���ݿ��/�洢������ģʽ
//DMA_CHx:DMAͨ��CHx
//cpar:�����ַ
//cmar:�洢����ַ
void UART_DMA_Config(DMA_Channel_TypeDef* DMA_CHx, u32 cpar, u32 cmar)
{

    NVIC_InitTypeDef NVIC_InitStructure;
    //DMA���ȼ�

    NVIC_InitStructure.NVIC_IRQChannel = DMA1_Channel4_5_IRQn;
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
    NVIC_InitStructure.NVIC_IRQChannelPriority = 0x00;
    NVIC_Init(&NVIC_InitStructure);

    DMA_InitTypeDef DMA_InitStructure;
    RCC_AHBPeriphClockCmd(RCC_AHBPeriph_DMA1, ENABLE);  //ʹ��DMA����
    DMA_DeInit(DMA_CHx);   //��DMA��ͨ��1�Ĵ�������Ϊȱʡֵ
    DMA_InitStructure.DMA_PeripheralBaseAddr = cpar;  //DMA�������ַ
    DMA_InitStructure.DMA_MemoryBaseAddr = cmar;  //DMA�ڴ����ַ
    DMA_InitStructure.DMA_DIR =
        DMA_DIR_PeripheralDST;  //���ݴ��䷽�򣬴��ڴ��ȡ���͵�����
    DMA_InitStructure.DMA_BufferSize = 200;  //DMAͨ����DMA����Ĵ�С
    DMA_InitStructure.DMA_PeripheralInc =
        DMA_PeripheralInc_Disable;  //�����ַ�Ĵ�������
    DMA_InitStructure.DMA_MemoryInc = DMA_MemoryInc_Enable;  //�ڴ��ַ�Ĵ�������
    DMA_InitStructure.DMA_PeripheralDataSize =
        DMA_PeripheralDataSize_Byte;  //���ݿ��Ϊ8λ
    DMA_InitStructure.DMA_MemoryDataSize = DMA_MemoryDataSize_Byte; //���ݿ��Ϊ8λ
    DMA_InitStructure.DMA_Mode = DMA_Mode_Circular;  //��������������ģʽ
    DMA_InitStructure.DMA_Priority = DMA_Priority_Medium; //DMAͨ�� xӵ�������ȼ�
    DMA_InitStructure.DMA_M2M = DMA_M2M_Disable;  //DMAͨ��xû������Ϊ�ڴ浽�ڴ洫��
    DMA_Init(DMA_CHx,
             &DMA_InitStructure);  //����DMA_InitStruct��ָ���Ĳ�����ʼ��DMA��ͨ��USART1_Tx_DMA_Channel����ʶ�ļĴ���
}
//����һ��DMA����
void UART_DMA_Enable(DMA_Channel_TypeDef* DMA_CHx, u8 len)
{
    DMA_Cmd(DMA_CHx, DISABLE);   //�ر� ָʾ��ͨ��
    DMA_SetCurrDataCounter(DMA_CHx, len); //DMAͨ����DMA����Ĵ�С
    DMA_Cmd(DMA_CHx, ENABLE);           //����DMA����
}

