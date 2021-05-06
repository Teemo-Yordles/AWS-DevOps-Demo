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
 * flight stand allocation related information
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class PSDT {
    // sequence number
    @JacksonXmlProperty(isAttribute = true, localName = "PSNO")
    private Byte PSNO;

    // stand code
    @JacksonXmlProperty(localName = "PSST")
    private String PSST;

    // planned start time
    @JacksonXmlProperty(localName = "STST")
    private String STST;

    // planned close time
    @JacksonXmlProperty(localName = "STET")
    private String STET;
}
