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
 * flight chute related information
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class CHDT {
    // sequence number
    @JacksonXmlProperty(isAttribute = true, localName = "CHNO")
    private Byte CHNO;

    // chute number
    @JacksonXmlProperty(localName = "CHUT")
    private String CHUT;

    // chute class
    @JacksonXmlProperty(localName = "CCLS")
    private Character CCLS;

    // planned start time
    @JacksonXmlProperty(localName = "PCBT")
    private String PCBT;

    // planned close time
    @JacksonXmlProperty(localName = "PCET")
    private String PCET;

    // actual start time
    @JacksonXmlProperty(localName = "CBTM")
    private String CBTM;

    // actual end time
    @JacksonXmlProperty(localName = "CETM")
    private String CETM;

    // domestic/international indicator
    @JacksonXmlProperty(localName = "CTYP")
    private Character CTYP;
}
