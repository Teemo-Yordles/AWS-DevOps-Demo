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
 * The <b>HLTR</b> class is a POJO entity that represents the handling transaction for reference data in message.
 * This class includes all the tags for input handling transaction messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class HLTR extends MSG {
    /**
     * The Handling transaction message body list
     */
    @JacksonXmlProperty(localName = "HLTR")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<HandlingAgentData> hltrs;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A handling transaction record in the body
     */
    @Data
    @NoArgsConstructor
    public static class HandlingAgentData {
        /**
         * Handling transaction code
         */
        @JacksonXmlProperty(localName = "HLTC")
        private String HLTC;
        /**
         * Handling transaction description
         */
        @JacksonXmlProperty(localName = "HLTD")
        private String HLTD;
        /**
         * Handling transaction CN description
         */
        @JacksonXmlProperty(localName = "HTDC")
        private String HTDC;
        /**
         * Handling transaction type
         */
        @JacksonXmlProperty(localName = "HLTT")
        private String HLTT;
        /**
         * Unit of measure name
         */
        @JacksonXmlProperty(localName = "UMNM")
        private String UMNM;
    }
}
