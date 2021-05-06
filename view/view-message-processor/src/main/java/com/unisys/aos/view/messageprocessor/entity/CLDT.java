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
 * flight carousel related information
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class CLDT {
    // sequence number
    @JacksonXmlProperty(isAttribute = true, localName = "CLNO")
    private Byte CLNO;

    //belt number
    @JacksonXmlProperty(localName = "BELT")
    private String BELT;

    // carousel class
    @JacksonXmlProperty(localName = "BCLS")
    private Character BCLS;

    // planned start time
    @JacksonXmlProperty(localName = "PCOT")
    private String PCOT;

    // planned close time
    @JacksonXmlProperty(localName = "PCCT")
    private String PCCT;

    // first bag time
    @JacksonXmlProperty(localName = "FBAG")
    private String FBAG;

    // last bag time
    @JacksonXmlProperty(localName = "LBAG")
    private String LBAG;

    // domestic/international indicator
    @JacksonXmlProperty(localName = "BTYP")
    private Character BTYP;
}
