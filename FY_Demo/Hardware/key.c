#include "board.h"
#include "key.h"


Key key_tim;//ʵ����һ������


/*******************************************************************************
*Function:CPP���캯��
*parm:none
*description:
*******************************************************************************/
Key::Key()
{
    key_1s = key_3s = key_10ms = key_int = 0;
}
/*******************************************************************************
*Function:����GPIO�ڳ�ʼ��
*parm:none
*description:
*******************************************************************************/
void Key_Init(void)
{
    GPIO_InitTypeDef GPIO_InitStruct;
    RCC_AHBPeriphClockCmd(RCC_SW_TD, ENABLE);

    GPIO_InitStruct.GPIO_Pin = Pin_SW_TD;
    GPIO_InitStruct.GPIO_Mode = GPIO_Mode_IN;
    GPIO_InitStruct.GPIO_PuPd = GPIO_PuPd_UP;
    GPIO_InitStruct.GPIO_Speed = GPIO_Speed_Level_2;
    GPIO_Init(GPIO_SW_TD, &GPIO_InitStruct);
}
/*******************************************************************************
*Function:����,ʹ���жϷ�ʽ
*parm:none
*description:
  PA0
  PB0
  PC0-----EXTI0
  ...
  PF0
*******************************************************************************/
void EXIT_KEY_Init(void)
{
    Key_Init();

    EXTI_InitTypeDef EXTI_InitStruct;
    NVIC_InitTypeDef NVIC_InitStruct;

    RCC_APB2PeriphClockCmd(RCC_APB2Periph_SYSCFG, ENABLE);

    NVIC_InitStruct.NVIC_IRQChannel = EXTI4_15_IRQn;//PF6
    NVIC_InitStruct.NVIC_IRQChannelPriority = 0x00;
    NVIC_InitStruct.NVIC_IRQChannelCmd = ENABLE;
    NVIC_Init(&NVIC_InitStruct);

    SYSCFG_EXTILineConfig(EXTI_PortSourceGPIOF, EXTI_PinSource6);
    EXTI_InitStruct.EXTI_Line = EXTI_Line6;
    EXTI_InitStruct.EXTI_Mode = EXTI_Mode_Interrupt;
    EXTI_InitStruct.EXTI_Trigger =
        EXTI_Trigger_Rising;//��������Ϊ�ߵ�ƽ�������ش���
    EXTI_InitStruct.EXTI_LineCmd = ENABLE;
    EXTI_Init(&EXTI_InitStruct);
}
#if 1
/*******************************************************************************
*Function:����ɨ��
*parm:none
*description:
*******************************************************************************/
static uint8_t key_value = 0;
uint8_t Key_scan(void)
{
    if (GPIO_ReadInputDataBit(GPIO_SW_TD, Pin_SW_TD) == 1) { //�а�������
        delay_ms(10);

        if (GPIO_ReadInputDataBit(GPIO_SW_TD, Pin_SW_TD) == 1) { //�а�������
            LED_Toggle();

            while (GPIO_ReadInputDataBit(GPIO_SW_TD, Pin_SW_TD)); //�ȴ���������

            key_value++; //���������µ���Ϊһ��
            LED_Toggle();
        }
    }

    return key_value;
}
/*******************************************************************************
*Function:����������ʹ���жϼ�ʱ10ms����
*parm:none
*description:
*******************************************************************************/
uint8_t key_driver(void)
{
    static uint8_t key_state = key_state_0;
    static uint16_t key_time = 0;
    uint8_t key_press, key_return = N_key;

    key_press = GPIO_ReadInputDataBit(GPIO_SW_TD, GPIO_Pin_6); // ������I/O��ƽ

    switch (key_state) {
    case key_state_0:                         // ������ʼ̬
        if (key_press) {
            key_state = key_state_1;    // �������£�״̬ת��������������ȷ��״̬
        }

        break;

    case key_state_1:                 // ����������ȷ��̬
        if (key_press) {
            key_time = 0;
            key_state =
                key_state_2;   // ������Ȼ���ڰ��£�������ɣ�״̬ת�������¼�ʱ��ļ�ʱ״̬�������صĻ����޼��¼�
        } else {
            key_state =
                key_state_0;    // ������̧��ת����������ʼ̬���˴���ɺ�ʵ�������������ʵ�����İ��º��ͷŶ��ڴ������ġ�
        }

        break;

    case key_state_2:
        if (!key_press) {
            key_return = S_key;        // ��ʱ�����ͷţ�˵���ǲ���һ�ζ̲���������S_key
            key_state = key_state_0;   // ת����������ʼ̬
        } else if (++key_time >=
                   300) { // �������£���ʱ��10ms��10msΪ������ѭ��ִ�м����
            key_return = L_key;        // ����ʱ��>5000ms���˰���Ϊ�������������س����¼�
            key_state = key_state_3;   // ת�����ȴ������ͷ�״̬
        }

        break;

    case key_state_3:                 // �ȴ������ͷ�״̬����״ֻ̬�����ް����¼�
        if (!key_press) {
            key_state = key_state_0;    //�������ͷţ�ת����������ʼ̬
        }

        break;
    }

    return key_return;
}
/*******************************************************************************
*Function:������ֵ���������Ϻ���һ�Σ�ʵ�ֵ�����˫���������¼���
*parm:none
*description:
*******************************************************************************/
uint8_t key_read(void)
{
    static uint8_t key_m = key_state_0, key_time_1 = 0;
    uint8_t key_return = N_key, key_temp;

    key_temp = key_driver();


    switch (key_m) {
    case key_state_0:
        if (key_temp == S_key) {
            key_time_1 =
                0;               // ��1�ε����������أ����¸�״̬�жϺ����Ƿ����˫��
            key_m = key_state_1;
        } else {
            key_return = key_temp;    // �����޼�������������ԭ�¼�
        }

        break;

    case key_state_1:
        if (key_temp == S_key) {           // ��һ�ε���������϶�<1000ms��
            key_return = D_key;           // ����˫�����¼����س�ʼ״̬
            key_m = key_state_0;
        } else {
            // ����500ms�ڿ϶������Ķ����޼��¼�����Ϊ����>2000ms����2sǰ�Ͳ㷵�صĶ����޼�
            if (++key_time_1 >= 100) {    //��ʱ100ms
                key_return = S_key;      // 500ms��û���ٴγ��ֵ����¼���������һ�εĵ����¼�
                key_m = key_state_0;     // ���س�ʼ״̬
            }
        }

        break;
    }


    return key_return;
}
#endif