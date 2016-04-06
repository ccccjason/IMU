//////////////////////////////////////////////
//  * 硬件连接 ----------------------------
//  *         | PA8-CE          :24L01-CE |
//  *         | PC9-IRQ  :      24L01-IRQ |
//  *         | PC8-CS  :        24L01-CS  |
//  *         | PB13-SPI2-SCK  : 24L01-CLK |
//  *         | PB14-SPI2-MISO : 24L01-DO  |
//  *         | PB15-SPI2-MOSI : 24L01-DIO |
//  *          ----------------------------
//  * 库版本  ：ST3.0.0
//  *
////////////////////////////////////////////

#include  "rf24l01.h"

const uint8_t TX_ADDRESS[TX_ADR_WIDTH] = {0x68, 0x86, 0x66, 0x88, 0x28}; //发送地址
const uint8_t RX_ADDRESS[RX_ADR_WIDTH] = {0x68, 0x86, 0x66, 0x88, 0x28}; //发送地址
/*******************************************************************************
* Function Name  : SPI_RF_Init
* Description    : Initializes the peripherals used by the SPI FLASH driver.
* Input          : None
* Output         : None
* Return         : None
*******************************************************************************/
void SPI_RF_Init(void)
{

    GPIO_InitTypeDef  GPIO_InitStruct;
    SPI_InitTypeDef   SPI_InitStruct;

    /*!< SD_SPI_CS_GPIO, SD_SPI_MOSI_GPIO, SD_SPI_MISO_GPIO, SD_SPI_DETECT_GPIO
         and SD_SPI_SCK_GPIO Periph clock enable */
    //   RCC_AHBPeriphClockCmd(FLASH_CS_PIN_SCK|FLASH_SCK_PIN_SCK|FLASH_MISO_PIN_SCK | FLASH_MOSI_PIN_SCK, ENABLE);
    RCC_AHBPeriphClockCmd(RCC_AHBPeriph_GPIOA | RCC_AHBPeriph_GPIOB |
                          RCC_AHBPeriph_GPIOC, ENABLE);
    /*!< SD_SPI Periph clock enable */
    RCC_APB1PeriphClockCmd(RF_SPI2, ENABLE);

    /*!< Configure RF_SPI pins: SCK */
    GPIO_InitStruct.GPIO_Pin = RF_SCK_PIN;
    GPIO_InitStruct.GPIO_Mode = GPIO_Mode_AF;
    GPIO_InitStruct.GPIO_Speed = GPIO_Speed_Level_2;
    GPIO_InitStruct.GPIO_OType = GPIO_OType_PP;
    GPIO_InitStruct.GPIO_PuPd  = GPIO_PuPd_UP;
    GPIO_Init(RF_SCK_PORT, &GPIO_InitStruct);

    /*!< Configure RF_SPI pins: MISO */
    GPIO_InitStruct.GPIO_Pin = RF_MISO_PIN;
    GPIO_Init(RF_MISO_PORT, &GPIO_InitStruct);

    /*!< Configure RF_SPI pins: MOSI */
    GPIO_InitStruct.GPIO_Pin = RF_MOSI_PIN;
    GPIO_Init(RF_MOSI_PORT, &GPIO_InitStruct);

    /* Connect PXx to RF_SPI_SCK */
    GPIO_PinAFConfig(RF_SCK_PORT, RF_SCK_SOURCE, RF_SCK_AF);

    /* Connect PXx to RF_SPI_MISO */
    GPIO_PinAFConfig(RF_MISO_PORT, RF_MISO_SOURCE, RF_MISO_AF);

    /* Connect PXx to RF_SPI_MOSI */
    GPIO_PinAFConfig(RF_MOSI_PORT, RF_MOSI_SOURCE, RF_MOSI_AF);


    /*!< Configure SD_SPI_CS_PIN pin: SD Card CS pin */
    GPIO_InitStruct.GPIO_Pin = RF_CS_PIN;
    GPIO_InitStruct.GPIO_Mode = GPIO_Mode_OUT;
    GPIO_InitStruct.GPIO_OType = GPIO_OType_PP;
    GPIO_InitStruct.GPIO_PuPd = GPIO_PuPd_UP;
    GPIO_InitStruct.GPIO_Speed = GPIO_Speed_Level_2;
    GPIO_Init(RF_CS_PORT, &GPIO_InitStruct);

    GPIO_InitStruct.GPIO_Pin = RF_CE_PIN;
    GPIO_InitStruct.GPIO_Mode = GPIO_Mode_OUT;
    GPIO_InitStruct.GPIO_OType = GPIO_OType_PP;
    GPIO_InitStruct.GPIO_PuPd = GPIO_PuPd_UP;
    GPIO_InitStruct.GPIO_Speed = GPIO_Speed_Level_2;
    GPIO_Init(RF_CE_PORT, &GPIO_InitStruct);

    GPIO_InitStruct.GPIO_Pin = RF_IQR_PIN;
    GPIO_InitStruct.GPIO_Mode = GPIO_Mode_IN;
    GPIO_InitStruct.GPIO_PuPd = GPIO_PuPd_UP;
    GPIO_InitStruct.GPIO_Speed = GPIO_Speed_Level_2;
    GPIO_Init(RF_IQR_PORT , &GPIO_InitStruct);
    //  SPI_RF_CE_LOW();
    SPI_RF_CS_HIGH() ;
    /*!< SD_SPI Config */
    SPI_InitStruct.SPI_Direction = SPI_Direction_2Lines_FullDuplex;
    SPI_InitStruct.SPI_Mode = SPI_Mode_Master;
    SPI_InitStruct.SPI_DataSize = SPI_DataSize_8b;
    //   SPI_InitStruct.SPI_CPOL = SPI_CPOL_High;
    //   SPI_InitStruct.SPI_CPHA = SPI_CPHA_2Edge;
    SPI_InitStruct.SPI_CPOL =
        SPI_CPOL_Low;                       //时钟极性，空闲时为低
    SPI_InitStruct.SPI_CPHA = SPI_CPHA_1Edge;
    SPI_InitStruct.SPI_NSS = SPI_NSS_Soft;
    SPI_InitStruct.SPI_BaudRatePrescaler = SPI_BaudRatePrescaler_8;
    SPI_InitStruct.SPI_FirstBit = SPI_FirstBit_MSB;
    SPI_InitStruct.SPI_CRCPolynomial = 7;
    SPI_Init(SPI2, &SPI_InitStruct);
    SPI_RxFIFOThresholdConfig(SPI2, SPI_RxFIFOThreshold_QF);
    SPI_Cmd(SPI2, ENABLE); /*!< SD_SPI enable */

}

/*********************************************/
/* 函数功能：给24L01的寄存器写值（一个字节） */
/* 入口参数：reg   要写的寄存器地址          */
/*           value 给寄存器写的值            */
/* 出口参数：status 状态值                   */
/*********************************************/
uint8_t NRF24L01_Write_Reg(uint8_t reg, uint8_t value)
{
    uint8_t status;

    SPI_RF_CS_LOW() ;    //CSN=0;
    status = SPI_RF_SendByte(reg);//发送寄存器地址,并读取状态值
    SPI_RF_SendByte(value);
    SPI_RF_CS_HIGH();   //CSN=1;
    return status;
}

/*************************************************/
/* 函数功能：读24L01的寄存器值 （一个字节）      */
/* 入口参数：reg  要读的寄存器地址               */
/* 出口参数：value 读出寄存器的值                */
/*************************************************/
uint8_t NRF24L01_Read_Reg(uint8_t reg)
{
    uint8_t value;

    SPI_RF_CS_LOW() ; //CSN=0;
    SPI_RF_SendByte(reg);//发送寄存器值(位置),并读取状态值
    value = SPI_RF_SendByte(NOP);
    SPI_RF_CS_HIGH();  //CSN=1;
    return value;
}

/*********************************************/
/* 函数功能：读24L01的寄存器值（多个字节）   */
/* 入口参数：reg   寄存器地址                */
/*           *pBuf 读出寄存器值的存放数组    */
/*           len   数组字节长度              */
/* 出口参数：status 状态值                   */
/*********************************************/
uint8_t NRF24L01_Read_Buf(uint8_t reg, uint8_t* pBuf, uint8_t len)
{
    uint8_t status, u8_ctr;
    SPI_RF_CS_LOW() ;//CSN=0
    status = SPI_RF_SendByte(reg); //发送寄存器地址,并读取状态值

    for (u8_ctr = 0; u8_ctr < len; u8_ctr++) {
        pBuf[u8_ctr] = SPI_RF_SendByte(0XFF);    //读出数据
    }

    SPI_RF_CS_HIGH(); //CSN=1
    return status;        //返回读到的状态值
}
/**********************************************/
/* 函数功能：给24L01的寄存器写值（多个字节）  */
/* 入口参数：reg  要写的寄存器地址            */
/*           *pBuf 值的存放数组               */
/*           len   数组字节长度               */
/**********************************************/
uint8_t NRF24L01_Write_Buf(uint8_t reg, uint8_t* pBuf, uint8_t len)
{
    uint8_t status, u8_ctr;
    SPI_RF_CS_LOW() ;
    status = SPI_RF_SendByte(reg);//发送寄存器值(位置),并读取状态值

    for (u8_ctr = 0; u8_ctr < len; u8_ctr++) {
        SPI_RF_SendByte(*pBuf++);    //写入数据
    }

    SPI_RF_CS_HIGH();
    return status;          //返回读到的状态值
}
/********************************************/
/* 函数功能：检测24L01是否存在              */
/* 返回值；  0  存在                        */
/*           1  不存在                      */
/********************************************/
uint8_t NRF24L01_Check(void)
{
    uint8_t check_in_buf[5] = {0x11, 0x22, 0x33, 0x44, 0x55};
    uint8_t check_out_buf[5] = {0x00};

    NRF24L01_Write_Buf(WRITE_REG + TX_ADDR, check_in_buf, 5);

    NRF24L01_Read_Buf(READ_REG + TX_ADDR, check_out_buf, 5);

    if ((check_out_buf[0] == 0x11) && \
        (check_out_buf[1] == 0x22) && \
        (check_out_buf[2] == 0x33) && \
        (check_out_buf[3] == 0x44) && \
        (check_out_buf[4] == 0x55)) {
        return 0;
    } else {
        return 1;
    }
}
/*********************************************/
/* 函数功能：设置24L01为接收模式             */
/*********************************************/
void NRF24L01_RX_Mode(void)
{

    SPI_RF_CE_LOW() ;   //CE拉低，使能24L01配置

    NRF24L01_Write_Buf(WRITE_REG + RX_ADDR_P0, (uint8_t*)RX_ADDRESS,
                       RX_ADR_WIDTH); //写RX接收地址

    NRF24L01_Write_Reg(WRITE_REG + EN_AA, 0x01); //开启通道0自动应答
    NRF24L01_Write_Reg(WRITE_REG + EN_RXADDR, 0x01); //通道0接收允许
    NRF24L01_Write_Reg(WRITE_REG + RF_CH, 40);   //设置RF工作通道频率
    NRF24L01_Write_Reg(WRITE_REG + RX_PW_P0,
                       RX_PLOAD_WIDTH); //选择通道0的有效数据宽度
    NRF24L01_Write_Reg(WRITE_REG + RF_SETUP,
                       0x0f); //设置TX发射参数,0db增益,2Mbps,低噪声增益开启
    NRF24L01_Write_Reg(WRITE_REG + CONFIG,
                       0x0f); //配置基本工作模式的参数;PWR_UP,EN_CRC,16BIT_CRC,接收模式
    NRF24L01_Write_Reg(FLUSH_RX, 0xff); //清除RX FIFO寄存器
    SPI_RF_CE_HIGH();   //CE置高，使能接收
}
/*********************************************/
/* 函数功能：设置24L01为发送模式             */
/*********************************************/
void NRF24L01_TX_Mode(void)
{
    SPI_RF_CE_LOW() ;   //CE拉低，使能24L01配置
    NRF24L01_Write_Buf(WRITE_REG + TX_ADDR, (uint8_t*)TX_ADDRESS,
                       TX_ADR_WIDTH); //写TX节点地址
    NRF24L01_Write_Buf(WRITE_REG + RX_ADDR_P0, (uint8_t*)RX_ADDRESS,
                       RX_ADR_WIDTH); //设置TX节点地址,主要为了使能ACK
    NRF24L01_Write_Reg(WRITE_REG + EN_AA, 0x01);  //使能通道0的自动应答
    NRF24L01_Write_Reg(WRITE_REG + EN_RXADDR, 0x01); //使能通道0的接收地址
    NRF24L01_Write_Reg(WRITE_REG + SETUP_RETR,
                       0x1a); //设置自动重发间隔时间:500us + 86us;最大自动重发次数:10次
    NRF24L01_Write_Reg(WRITE_REG + RF_CH, 40);    //设置RF通道为40
    NRF24L01_Write_Reg(WRITE_REG + RF_SETUP,
                       0x0f); //设置TX发射参数,0db增益,2Mbps,低噪声增益开启
    NRF24L01_Write_Reg(WRITE_REG + CONFIG,
                       0x0e); //配置基本工作模式的参数;PWR_UP,EN_CRC,16BIT_CRC,接收模式,开启所有中断
    SPI_RF_CE_HIGH(); //CE置高，使能发送
}
/*********************************************/
/* 函数功能：24L01接收数据                   */
/* 入口参数：rxbuf 接收数据数组              */
/* 返回值： 0   成功收到数据                 */
/*          1   没有收到数据                 */
/*********************************************/
uint8_t NRF24L01_RxPacket(uint8_t* rxbuf)
{
    uint8_t state;

    state = NRF24L01_Read_Reg(STATUS); //读取状态寄存器的值
    NRF24L01_Write_Reg(WRITE_REG + STATUS, state); //清除TX_DS或MAX_RT中断标志

    if (state & RX_OK) { //接收到数据
        NRF24L01_Read_Buf(RD_RX_PLOAD, rxbuf, RX_PLOAD_WIDTH); //读取数据
        NRF24L01_Write_Reg(FLUSH_RX, 0xff); //清除RX FIFO寄存器
        return 0;
    }

    return 1;//没收到任何数据
}
/**********************************************/
/* 函数功能：设置24L01为发送模式              */
/* 入口参数：txbuf  发送数据数组              */
/* 返回值； 0x10    达到最大重发次数，发送失败*/
/*          0x20    成功发送完成              */
/*          0xff    发送失败                  */
/**********************************************/
uint8_t NRF24L01_TxPacket(uint8_t* txbuf)
{
    uint8_t state;

    SPI_RF_CE_LOW() ;   //CE拉低，使能24L01配置
    NRF24L01_Write_Buf(WR_TX_PLOAD, txbuf,
                       TX_PLOAD_WIDTH); //写数据到TX BUF  32个字节
    SPI_RF_CE_HIGH();   //CE置高，使能发送

    while (SPI_RF_IRQ() != 0); //等待发送完成

    state = NRF24L01_Read_Reg(STATUS); //读取状态寄存器的值
    NRF24L01_Write_Reg(WRITE_REG + STATUS, state); //清除TX_DS或MAX_RT中断标志
    NRF24L01_Write_Reg(FLUSH_TX, 0xff); //清除TX FIFO寄存器

    if (state & MAX_TX) { //达到最大重发次数

        return MAX_TX;
    }

    if (state & TX_OK) { //发送完成
        return TX_OK;
    }

    return 0xff;//发送失败
}


/*******************************************************************************
* Function Name  : SPI_FLASH_SendByte
* Description    : Sends a byte through the SPI interface and return the byte
*                  received from the SPI bus.
* Input          : byte : byte to send.
* Output         : None
* Return         : The value of the received byte.
*******************************************************************************/
uint8_t SPI_RF_SendByte(uint8_t byte)
{
    /* Loop while DR register in not emplty */
    while (SPI_I2S_GetFlagStatus(SPI2, SPI_I2S_FLAG_TXE) == RESET);

    /* Send byte through the SPI1 peripheral */
    SPI_SendData8(SPI2, byte);

    /* Wait to receive a byte */
    while (SPI_I2S_GetFlagStatus(SPI2, SPI_I2S_FLAG_RXNE) == RESET);

    /* Return the byte read from the SPI bus */
    return SPI_ReceiveData8(SPI2);
}
