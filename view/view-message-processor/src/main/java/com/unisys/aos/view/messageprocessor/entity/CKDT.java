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
 * flight counter related information
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class CKDT {
    // sequence number
    @JacksonXmlProperty(isAttribute = true, localName = "CKNO")
    private Byte CKNO;

    //checkin counter number
    @JacksonXmlProperty(localName = "CHKC")
    private String CHKC;

    // counter class
    @JacksonXmlProperty(localName = "CCLS")
    private Character CCLS;

    // planned start time
    @JacksonXmlProperty(localName = "PCOT")
    private String PCOT;

    // planned close time
    @JacksonXmlProperty(localName = "PCCT")
    private String PCCT;

    // checkin open time
    @JacksonXmlProperty(localName = "COTM")
    private String COTM;

    // checkin close time
    @JacksonXmlProperty(localName = "CCTM")
    private String CCTM;

    // domestic/international indicator
    @JacksonXmlProperty(localName = "CTYP")
    private Character CTYP;
}
