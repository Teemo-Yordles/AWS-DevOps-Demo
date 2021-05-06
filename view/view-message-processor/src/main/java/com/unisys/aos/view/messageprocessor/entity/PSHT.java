/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jianglushuang
 * @since 2020/12/3 11:08 下午
 */
@Data
@NoArgsConstructor
public class PSHT {
    // sequence number
    @JacksonXmlProperty(isAttribute = true, localName = "PSHN")
    private Byte PSHN;

    // Push time.
    @JacksonXmlProperty(localName = "PSTM")
    private String PSTM;

    // Push indicator
    @JacksonXmlProperty(localName = "PSID")
    private String PSID;

    // The push stand against which the push time is reported by ACDM
    @JacksonXmlProperty(localName = "PUST")
    private String PUST;
}
