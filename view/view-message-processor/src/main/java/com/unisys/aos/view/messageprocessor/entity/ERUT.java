/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Flight entire route information
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class ERUT {
    // route sequence number
    @JacksonXmlProperty(isAttribute = true, localName = "RTNO")
    private Byte RTNO;

    // route code
    @JacksonXmlProperty(localName = "APCD")
    private String APCD;

    // scheduled time of arrival
    @JacksonXmlProperty(localName = "SCAT")
    private String SCAT;

    /**
     * Day change factor if any for arrival.
     */
    @JacksonXmlProperty(localName = "SADC")
    private String SADC;

    // scheduled time of departure
    @JacksonXmlProperty(localName = "SCDT")
    private String SCDT;

    /**
     * Day change factor if any for departure at this stop.
     */
    @JacksonXmlProperty(localName = "SDDC")
    private String SDDC;
}
