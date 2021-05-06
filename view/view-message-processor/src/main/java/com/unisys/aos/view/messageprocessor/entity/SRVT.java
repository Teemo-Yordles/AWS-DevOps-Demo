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
 * Flight services
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class SRVT {
    // service transaction code
    @JacksonXmlProperty(localName = "SRTC")
    private String SRTC;

    // service quantity
    @JacksonXmlProperty(localName = "SRQT")
    private Byte SRQT;

    // service start date time
    @JacksonXmlProperty(localName = "SRST")
    private String SRST;

    // service end date time
    @JacksonXmlProperty(localName = "SRET")
    private String SRET;

    // service provider
    @JacksonXmlProperty(localName = "SRPR")
    private Integer SRPR;

    // service transaction abnormal reason
    @JacksonXmlProperty(localName = "SANR")
    private String SANR;

    // service transaction abnormal remark
    @JacksonXmlProperty(localName = "SARR")
    private String SARR;
}
