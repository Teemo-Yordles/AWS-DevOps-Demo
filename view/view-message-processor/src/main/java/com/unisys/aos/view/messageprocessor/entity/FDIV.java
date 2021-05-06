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
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class FDIV {
    // divert airport
    @JacksonXmlProperty(isAttribute = true, localName = "DDES")
    private String DDES;

    // divert direction
    @JacksonXmlProperty(isAttribute = true, localName = "DDIR")
    private String DDIR;

    // FDIV tag value
    @JacksonXmlText
    private String value;
}
