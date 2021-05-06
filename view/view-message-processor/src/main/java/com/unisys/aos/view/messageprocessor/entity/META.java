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

import java.util.Date;

/**
 * @author LiuJ2
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class META {
    @JacksonXmlProperty(localName = "SNDR")
    private String SNDR;
    @JacksonXmlProperty(localName = "SEQN")
    private long SEQN;
    @JacksonXmlProperty(localName = "DTTM")
    private Date DTTM;
    @JacksonXmlProperty(localName = "TYPE")
    private String TYPE;
    @JacksonXmlProperty(localName = "STYP")
    private String STYP;
}
