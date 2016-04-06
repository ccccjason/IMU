#ifndef __HML5883_H__
#define __HML5883_H__

#include "board.h"

/**************************************************************
HM5883
**********************************************************/
#define         HMC5883_ADDR            0X3C    //从地址即写地址，加1为读地址

class Magnetic
{
public:
    int Mag_X;
    int Mag_Y;
    int Mag_Z;
    void HMC5883_Init(void);
    void HMC5883_Read(void);
};


extern Magnetic HMC5883     ;
extern uint8_t Mag_buf[6];
#endif

