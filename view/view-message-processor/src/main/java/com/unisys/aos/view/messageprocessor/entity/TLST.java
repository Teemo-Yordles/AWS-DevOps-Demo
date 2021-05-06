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
 * The <b>TLST</b> class is a POJO entity that represents the terminal for reference data in message.
 * This class includes all the tags for input Terminal messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class TLST extends MSG {
    /**
     * The Terminal message body list
     */
    @JacksonXmlProperty(localName = "TLST")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<TerminalData> tlsts;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A terminal record in the body
     */
    @Data
    @NoArgsConstructor
    public static class TerminalData {
        /**
         * Code for this terminal
         */
        @JacksonXmlProperty(localName = "TCOD")
        private String TCOD;
        /**
         * Terminal name/description
         */
        @JacksonXmlProperty(localName = "TNAM")
        private String TNAM;
        /**
         * Terminal name/description in Chinese
         */
        @JacksonXmlProperty(localName = "TNMC")
        private String TNMC;
        /**
         * Terminal category
         */
        @JacksonXmlProperty(localName = "TCAT")
        private String TCAT;
        /**
         * Is this a passenger terminal?
         */
        @JacksonXmlProperty(localName = "TPAX")
        private String TPAX;
    }
}
