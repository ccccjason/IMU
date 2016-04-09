/*******************************************************************************
*FileNaem:Get_Chip_ID.c
*description:存储芯片唯一ID号的地址，参考数据手册
*******************************************************************************/
#include "Get_Chip_ID.h"

u32 ChipUniqueID[3];
/*******************************************************************************
*Function:获得芯片唯一ID号
*parm:none
*description:使用一个p指针可以接收96位的地址*p *(p+1) *(p+2)
*******************************************************************************/
void Get_Chip_ID(void)
{
    //地址从小到大,先放低字节，再放高字节：小端模式
    //地址从小到大,先放高字节，再放低字节：大端模式
    ChipUniqueID[2] = *(__IO u32*)(0X1FFFF7B4);  // 低字节
    ChipUniqueID[1] = *(__IO u32*)(0X1FFFF7B0);  //
    ChipUniqueID[0] = *(__IO u32*)(0X1FFFF7AC);  // 高字节
    // printf("######## 芯片的唯一ID为: %X-%X-%X \r\n",
    //ChipUniqueID[0],ChipUniqueID[1],ChipUniqueID[2]);
}
/*******************************************************************************
*Function:获得芯片信息
*parm:none
*description:
*******************************************************************************/
void Get_ChipInfo(void)
{

    uint32_t ChipUniqueID[3];
    u16 FLASH_SIZE;
    ChipUniqueID[0] = *(__IO u32*)(0X1FFFF7AC);  // 高字节
    ChipUniqueID[1] = *(__IO u32*)(0X1FFFF7B0);  //
    ChipUniqueID[2] = *(__IO u32*)(0X1FFFF7B4);  // 低字节
    FLASH_SIZE = *(u16*)(0x1FFFF7CC);      //闪存容量寄存器
    printf("rn########### 芯片的唯一ID为: %X-%X-%X \r\n",
           ChipUniqueID[0], ChipUniqueID[1], ChipUniqueID[2]);
    printf("rn########### 芯片flash的容量为: %dK \r\n", FLASH_SIZE);
    printf("rn########### 烧录日期: "__DATE__" - "__TIME__"\r\n");
    //输出使用固件库版本号
    printf("rn########### 代码固件库版本: V %d.%d.%d \r\n",
           __STM32F0XX_STDPERIPH_VERSION_MAIN,
           __STM32F0XX_STDPERIPH_VERSION_SUB1,
           __STM32F0XX_STDPERIPH_VERSION_SUB2);
}



