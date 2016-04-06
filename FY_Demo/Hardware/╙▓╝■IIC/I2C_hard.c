/*******************************************************************************
  * @file
  * @author
  * @version
  * @date
  * @brief
  *****************************************************************************/
#include "I2C_hard.h"

uint32_t I2C_Timeout;


void I2C_GPIO_Config(void)
{
    GPIO_InitTypeDef GPIO_InitStruct;

    RCC_AHBPeriphClockCmd(RCC_I2C_GPIO, ENABLE);

    GPIO_InitStruct.GPIO_Pin = I2C_SCL | I2C_SDA;
    GPIO_InitStruct.GPIO_Mode = GPIO_Mode_AF;
    GPIO_InitStruct.GPIO_OType = GPIO_OType_OD;
    GPIO_InitStruct.GPIO_PuPd = GPIO_PuPd_NOPULL;
    GPIO_InitStruct.GPIO_Speed = GPIO_Speed_Level_3;
    GPIO_Init(I2C_GPIO, &GPIO_InitStruct);

    GPIO_PinAFConfig(I2C_GPIO, GPIO_PinSource8, GPIO_AF_1);
    GPIO_PinAFConfig(I2C_GPIO, GPIO_PinSource9, GPIO_AF_1);

}
/**
  * @brief  I2C�ӿڳ�ʼ��
  *         ��ʼ��ΪI2C1������7λ��ַģʽ������ģ���˲�����ͣ�������˲�����
  *         ����Ӧ��I2C�����ٶ�100KHz
  * @param  ��
  * @retval ��
  */
void I2C_Init_Config(void)
{
    I2C_GPIO_Config();
    I2C_InitTypeDef I2C_InitStructure;

    RCC_I2CCLKConfig(RCC_I2C_CLK);

    RCC_APB1PeriphClockCmd(RCC_APB1Periph_I2C1, ENABLE);

    I2C_InitStructure.I2C_Mode = I2C_Mode_I2C;
    I2C_InitStructure.I2C_AcknowledgedAddress = I2C_AcknowledgedAddress_7bit;
    I2C_InitStructure.I2C_AnalogFilter = I2C_AnalogFilter_Enable;
    I2C_InitStructure.I2C_DigitalFilter = 0x05;
    I2C_InitStructure.I2C_OwnAddress1 = 0xD0;
    I2C_InitStructure.I2C_Ack = I2C_Ack_Enable;
    I2C_InitStructure.I2C_Timing = 0x30E32E44;
    I2C_Init(I2C, &I2C_InitStructure);

    I2C_Cmd(I2C, ENABLE);
}

/**
  * @brief  ��I2C1�����ϵ�ĳһ������ĳһ��ʼ��ַ�ж�ȡһ���ֽڵ����ݵ�������
  * @param  driver_Addr��I2C������ַ
  * @param  start_Addr����ʼ�ֽڵ�ַ
  * @param  number_Bytes��Ҫ��ȡ���ֽ�������С��һҳ��
  * @param  read_Buffer����Ŷ�ȡ���ݵ�����ָ��
  * @retval �Ƿ��ȡ�ɹ�
  */
I2C_Status I2C_Read_NBytes(uint8_t driver_Addr, uint8_t start_Addr,
                           uint8_t number_Bytes, uint8_t* read_Buffer)
{
    uint8_t read_Num;

    I2C_Timeout = I2C_TIMEOUT;

    while (I2C_GetFlagStatus(I2C1, I2C_FLAG_BUSY) != RESET) {
        if ((I2C_Timeout--) == 0) {
            return I2C_FAIL;
        }
    }

    driver_Addr = driver_Addr + 1;
    I2C_TransferHandling(I2C, driver_Addr, 1, I2C_SoftEnd_Mode,
                         I2C_Generate_Start_Write);

    I2C_Timeout = I2C_TIMEOUT;

    while (I2C_GetFlagStatus(I2C, I2C_FLAG_TXIS) == RESET) {
        if ((I2C_Timeout--) == 0) {
            return I2C_FAIL;
        }
    }

    I2C_SendData(I2C, start_Addr);

    I2C_Timeout = I2C_TIMEOUT;

    while (I2C_GetFlagStatus(I2C, I2C_FLAG_TC) == RESET) {
        if ((I2C_Timeout--) == 0) {
            return I2C_FAIL;
        }
    }

    I2C_TransferHandling(I2C, driver_Addr, number_Bytes,  I2C_AutoEnd_Mode,
                         I2C_Generate_Start_Read);

    for (read_Num = 0; read_Num < number_Bytes; read_Num++) {
        I2C_Timeout = I2C_TIMEOUT;

        while (I2C_GetFlagStatus(I2C, I2C_FLAG_RXNE) == RESET) {
            if ((I2C_Timeout--) == 0) {
                return I2C_FAIL;
            }
        }

        read_Buffer[read_Num] = I2C_ReceiveData(I2C1);
    }

    I2C_Timeout = I2C_TIMEOUT;

    while (I2C_GetFlagStatus(I2C, I2C_FLAG_STOPF) == RESET) {
        if ((I2C_Timeout--) == 0) {
            return I2C_FAIL;
        }
    }

    return I2C_OK;
}

/*******************************************************************************
  * @brief  ��I2C1�������ϵ�ĳһ������ĳһ��ʼ��ַ�ж�ȡһ���ֽڵ����ݵ�������
  * @param  driver_Addr��I2C������ַ
  * @param  start_Addr����ʼ�ֽڵ�ַ
  * @param  number_Bytes��Ҫ��ȡ���ֽ�������С��һҳ��
  * @param  write_Buffer����Ŷ�ȡ���ݵ�����ָ��
  * @retval �Ƿ��ȡ�ɹ�
  *****************************************************************************/
I2C_Status I2C_Write_NBytes(uint8_t driver_Addr, uint8_t start_Addr,
                            uint8_t number_Bytes, uint8_t* write_Buffer)
{
    uint8_t write_Num;

    I2C_Timeout = I2C_TIMEOUT;

    while (I2C_GetFlagStatus(I2C, I2C_FLAG_BUSY) != RESET) {
        if ((I2C_Timeout--) == 0) {
            return I2C_FAIL;
        }
    }

    I2C_TransferHandling(I2C, driver_Addr, 1, I2C_Reload_Mode,
                         I2C_Generate_Start_Write);

    I2C_Timeout = I2C_TIMEOUT;

    while (I2C_GetFlagStatus(I2C, I2C_FLAG_TXIS) == RESET) {
        if ((I2C_Timeout--) == 0) {
            return I2C_FAIL;
        }
    }

    I2C_SendData(I2C, start_Addr);

    I2C_Timeout = I2C_TIMEOUT;

    while (I2C_GetFlagStatus(I2C, I2C_FLAG_TCR) == RESET) {
        if ((I2C_Timeout--) == 0) {
            return I2C_FAIL;
        }
    }

    I2C_TransferHandling(I2C, driver_Addr, number_Bytes, I2C_AutoEnd_Mode,
                         I2C_No_StartStop);

    for (write_Num = 0; write_Num < number_Bytes; write_Num++) {
        I2C_Timeout = I2C_TIMEOUT;

        while (I2C_GetFlagStatus(I2C, I2C_FLAG_TXIS) == RESET) {
            if ((I2C_Timeout--) == 0) {
                return I2C_FAIL;
            }
        }

        I2C_SendData(I2C, write_Buffer[write_Num]);
    }

    I2C_Timeout = I2C_TIMEOUT;

    while (I2C_GetFlagStatus(I2C, I2C_FLAG_STOPF) == RESET) {
        if ((I2C_Timeout--) == 0) {
            return I2C_FAIL;
        }
    }


    return I2C_OK;
}
