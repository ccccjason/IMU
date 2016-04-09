/*******************************************************************************
*FileNaem:Get_Chip_ID.c
*description:�洢оƬΨһID�ŵĵ�ַ���ο������ֲ�
*******************************************************************************/
#include "Get_Chip_ID.h"

u32 ChipUniqueID[3];
/*******************************************************************************
*Function:���оƬΨһID��
*parm:none
*description:ʹ��һ��pָ����Խ���96λ�ĵ�ַ*p *(p+1) *(p+2)
*******************************************************************************/
void Get_Chip_ID(void)
{
    //��ַ��С����,�ȷŵ��ֽڣ��ٷŸ��ֽڣ�С��ģʽ
    //��ַ��С����,�ȷŸ��ֽڣ��ٷŵ��ֽڣ����ģʽ
    ChipUniqueID[2] = *(__IO u32*)(0X1FFFF7B4);  // ���ֽ�
    ChipUniqueID[1] = *(__IO u32*)(0X1FFFF7B0);  //
    ChipUniqueID[0] = *(__IO u32*)(0X1FFFF7AC);  // ���ֽ�
    // printf("######## оƬ��ΨһIDΪ: %X-%X-%X \r\n",
    //ChipUniqueID[0],ChipUniqueID[1],ChipUniqueID[2]);
}
/*******************************************************************************
*Function:���оƬ��Ϣ
*parm:none
*description:
*******************************************************************************/
void Get_ChipInfo(void)
{

    uint32_t ChipUniqueID[3];
    u16 FLASH_SIZE;
    ChipUniqueID[0] = *(__IO u32*)(0X1FFFF7AC);  // ���ֽ�
    ChipUniqueID[1] = *(__IO u32*)(0X1FFFF7B0);  //
    ChipUniqueID[2] = *(__IO u32*)(0X1FFFF7B4);  // ���ֽ�
    FLASH_SIZE = *(u16*)(0x1FFFF7CC);      //���������Ĵ���
    printf("rn########### оƬ��ΨһIDΪ: %X-%X-%X \r\n",
           ChipUniqueID[0], ChipUniqueID[1], ChipUniqueID[2]);
    printf("rn########### оƬflash������Ϊ: %dK \r\n", FLASH_SIZE);
    printf("rn########### ��¼����: "__DATE__" - "__TIME__"\r\n");
    //���ʹ�ù̼���汾��
    printf("rn########### ����̼���汾: V %d.%d.%d \r\n",
           __STM32F0XX_STDPERIPH_VERSION_MAIN,
           __STM32F0XX_STDPERIPH_VERSION_SUB1,
           __STM32F0XX_STDPERIPH_VERSION_SUB2);
}



