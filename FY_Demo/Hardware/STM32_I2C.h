#ifndef __MYIIC_H
#define __MYIIC_H
#include "board.h"
#include "stdint.h"


#define BYTE0(dwTemp)       (*(char *)(&dwTemp))
#define BYTE1(dwTemp)       (*((char *)(&dwTemp) + 1))
#define BYTE2(dwTemp)       (*((char *)(&dwTemp) + 2))
#define BYTE3(dwTemp)       (*((char *)(&dwTemp) + 3))

#define true 1
#define false 0
#define bool  uint8_t

#define TRUE  0
#define FALSE -1

//0表示写
//#define   I2C_Direction_Transmitter   0
//１表示读
//#define   I2C_Direction_Receiver      1
/*====================================================================================================*/
/*====================================================================================================*/
bool i2cWriteBuffer(uint8_t addr_, uint8_t reg_, uint8_t len_, uint8_t* data);
bool i2cWrite(uint8_t addr_, uint8_t reg_, uint8_t data);
bool i2cRead(uint8_t addr_, uint8_t reg_, uint8_t len, uint8_t* buf);
void i2cInit(void);
static void i2cUnstick(void);

int8_t i2cwrite(uint8_t addr, uint8_t reg, uint8_t len, uint8_t* data);
int8_t i2cread(uint8_t addr, uint8_t reg, uint8_t len, uint8_t* buf);
/*====================================================================================================*/
/*====================================================================================================*/


#endif
















