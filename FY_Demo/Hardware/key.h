#ifndef __KEY_H__
#define __KEY_H__

/*****************����IO��***************************************************/

#define RCC_SW_TD   RCC_AHBPeriph_GPIOF
#define GPIO_SW_TD  GPIOF
#define Pin_SW_TD   GPIO_Pin_6


#define N_key    0             //�޼�   
#define S_key    1             //����   
#define D_key    2             //˫��  
#define L_key    3             //����   

#define T_key    4             //����3��
#define F_key    5             //����4��
#define V_key    6             //����5��

#define key_state_0 0
#define key_state_1 1
#define key_state_2 2
#define key_state_3 3
#define key_state_4 4
class Key
{

public:
    Key();
    u16 key_1s, key_3s ;
    u8 key_10ms;

    u8 key_int;
    u16 key_500ms, key_int_2s;
};

extern Key key_tim ;
void Key_Init(void);
void EXIT_KEY_Init(void);
#if 1
uint8_t Key_scan(void);
uint8_t key_read(void) ;
uint8_t key_driver(void);
#endif

#endif
