/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Flight delay information
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class DELY {
    // delay code
    @JacksonXmlProperty(isAttribute = true, localName = "CODE")
    private String CODE;

    // delay start time
    @JacksonXmlProperty(isAttribute = true, localName = "STRT")
    private String STRT;

    // delay duration HHMM
    @JacksonXmlProperty(isAttribute = true, localName = "DURA")
    private String DURA;

    // DELY tag value
    @JacksonXmlText
    private String value;
}
