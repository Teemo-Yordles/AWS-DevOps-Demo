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
 * Flight airbridge usage
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class ABTM {
    // sequence number
    @JacksonXmlProperty(isAttribute = true, localName = "ASNO")
    private Byte ASNO;

    // airbridge code
    @JacksonXmlProperty(localName = "ABDG")
    private String ABDG;

    // attach or detach
    @JacksonXmlProperty(localName = "ABOP")
    private Character ABOP;

    // operation time
    @JacksonXmlProperty(localName = "AOTM")
    private String AOTM;
}
