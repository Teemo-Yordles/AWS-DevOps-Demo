/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The <b>STTS</b> class is a POJO entity that represents the flight status for reference data in message.
 * This class includes all the tags for input Flight Status Status messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class STTS extends MSG {
    /**
     * The Flight Status message body list
     */
    @JacksonXmlProperty(localName = "STTS")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<StatusData> sttss;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A flight status record in the body
     */
    @Data
    @NoArgsConstructor
    public static class StatusData {
        /**
         * flight status code
         */
        @JacksonXmlProperty(localName = "STTC")
        private String STTC;
        /**
         * flight status description
         */
        @JacksonXmlProperty(localName = "STTD")
        private String STTD;
        /**
         * flight status description in Chinese
         */
        @JacksonXmlProperty(localName = "STDC")
        private String STDC;
        /**
         * If this flight status is an abnormal status?
         */
        @JacksonXmlProperty(localName = "ABNS")
        private String ABNS;
    }
}
