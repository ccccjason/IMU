#ifndef __IMU_H__
#define __IMU_H__

typedef struct {
    float X;
    float Y;
    float Z;

} S_FLOAT_XYZ;

void Prepare_Data(void);
void Get_Attitude(void);

extern S_FLOAT_XYZ Q_ANGLE;//四元数计算出来的角度

#endif



