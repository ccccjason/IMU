#include "board.h"
#include "key.h"


Key key_tim;//实例化一个对象


/*******************************************************************************
*Function:CPP构造函数
*parm:none
*description:
*******************************************************************************/
Key::Key()
{
    key_1s = key_3s = key_10ms = key_int = 0;
}
/*******************************************************************************
*Function:按键GPIO口初始化
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
*Function:按键,使用中断方式
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
        EXTI_Trigger_Rising;//按键按下为高电平，上升沿触发
    EXTI_InitStruct.EXTI_LineCmd = ENABLE;
    EXTI_Init(&EXTI_InitStruct);
}
#if 1
/*******************************************************************************
*Function:按键扫描
*parm:none
*description:
*******************************************************************************/
static uint8_t key_value = 0;
uint8_t Key_scan(void)
{
    if (GPIO_ReadInputDataBit(GPIO_SW_TD, Pin_SW_TD) == 1) { //有按键按下
        delay_ms(10);

        if (GPIO_ReadInputDataBit(GPIO_SW_TD, Pin_SW_TD) == 1) { //有按键按下
            LED_Toggle();

            while (GPIO_ReadInputDataBit(GPIO_SW_TD, Pin_SW_TD)); //等待按键弹起

            key_value++; //计数，按下弹起为一次
            LED_Toggle();
        }
    }

    return key_value;
}
/*******************************************************************************
*Function:按键驱动，使用中断计时10ms消抖
*parm:none
*description:
*******************************************************************************/
uint8_t key_driver(void)
{
    static uint8_t key_state = key_state_0;
    static uint16_t key_time = 0;
    uint8_t key_press, key_return = N_key;

    key_press = GPIO_ReadInputDataBit(GPIO_SW_TD, GPIO_Pin_6); // 读按键I/O电平

    switch (key_state) {
    case key_state_0:                         // 按键初始态
        if (key_press) {
            key_state = key_state_1;    // 键被按下，状态转换到按键消抖和确认状态
        }

        break;

    case key_state_1:                 // 按键消抖与确认态
        if (key_press) {
            key_time = 0;
            key_state =
                key_state_2;   // 按键仍然处于按下，消抖完成，状态转换到按下键时间的计时状态，但返回的还是无键事件
        } else {
            key_state =
                key_state_0;    // 按键已抬起，转换到按键初始态。此处完成和实现软件消抖，其实按键的按下和释放都在此消抖的。
        }

        break;

    case key_state_2:
        if (!key_press) {
            key_return = S_key;        // 此时按键释放，说明是产生一次短操作，回送S_key
            key_state = key_state_0;   // 转换到按键初始态
        } else if (++key_time >=
                   300) { // 继续按下，计时加10ms（10ms为本函数循环执行间隔）
            key_return = L_key;        // 按下时间>5000ms，此按键为长按操作，返回长键事件
            key_state = key_state_3;   // 转换到等待按键释放状态
        }

        break;

    case key_state_3:                 // 等待按键释放状态，此状态只返回无按键事件
        if (!key_press) {
            key_state = key_state_0;    //按键已释放，转换到按键初始态
        }

        break;
    }

    return key_return;
}
/*******************************************************************************
*Function:读按键值，调用以上函数一次，实现单击，双击，长按事件。
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
                0;               // 第1次单击，不返回，到下个状态判断后面是否出现双击
            key_m = key_state_1;
        } else {
            key_return = key_temp;    // 对于无键、长键，返回原事件
        }

        break;

    case key_state_1:
        if (key_temp == S_key) {           // 又一次单击（间隔肯定<1000ms）
            key_return = D_key;           // 返回双击键事件，回初始状态
            key_m = key_state_0;
        } else {
            // 这里500ms内肯定读到的都是无键事件，因为长键>2000ms，在2s前低层返回的都是无键
            if (++key_time_1 >= 100) {    //计时100ms
                key_return = S_key;      // 500ms内没有再次出现单键事件，返回上一次的单键事件
                key_m = key_state_0;     // 返回初始状态
            }
        }

        break;
    }


    return key_return;
}
#endif