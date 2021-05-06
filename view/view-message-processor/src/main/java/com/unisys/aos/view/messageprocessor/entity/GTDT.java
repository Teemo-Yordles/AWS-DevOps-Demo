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
 * flight gate related information
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class GTDT {
    // sequence number
    @JacksonXmlProperty(isAttribute = true, localName = "GTNO")
    private Byte GTNO;

    // gate number
    @JacksonXmlProperty(localName = "GATE")
    private String GATE;

    // planned start time
    @JacksonXmlProperty(localName = "PGOT")
    private String PGOT;

    // planned close time
    @JacksonXmlProperty(localName = "PGCT")
    private String PGCT;

    // actual start time
    @JacksonXmlProperty(localName = "GOTM")
    private String GOTM;

    // actual end time
    @JacksonXmlProperty(localName = "GCTM")
    private String GCTM;

    // domestic/international indicator
    @JacksonXmlProperty(localName = "GTYP")
    private Character GTYP;
}
