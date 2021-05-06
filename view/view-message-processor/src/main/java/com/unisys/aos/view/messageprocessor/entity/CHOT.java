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
 * chocks time information
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class CHOT {
    // sequence number
    @JacksonXmlProperty(isAttribute = true, localName = "CSNO")
    private Byte CSNO;

    // chocks time
    @JacksonXmlProperty(localName = "CHTM")
    private String CHTM;

    // Chocks indicator
    @JacksonXmlProperty(localName = "CHID")
    private String CHID;

    // stand code
    @JacksonXmlProperty(localName = "CHST")
    private String CHST;
}
