#ifndef __BOARD_H__
#define __BOARD_H__

#include "stm32f0xx.h"
#include "core_cm0.h"
#include "string.h"

#define u8 uint8_t
#define u16 uint16_t
#define u32 uint32_t

#include "stm32f0xx_conf.h"
#include "uart.h"
#include "delay.h"
#include "led.h"
#include "key.h"
#include "usart2.h"
#include "gps.h"
#include "timer.h"
#include "HMC5883.h"
#include "MPU6500.h"
#include "I2C_hard.h"
#include "IMU.h"
#define ARMAPI extern "C"

void board_init(void);

#endif

