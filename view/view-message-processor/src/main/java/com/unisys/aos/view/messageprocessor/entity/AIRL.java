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
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The <b>AIRL</b> class is a POJO entity that represents the airline for reference data in message.
 * This class includes all the tags for input AIRLINE messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class AIRL extends MSG {
    /**
     * The Airline message body list
     */
    @JacksonXmlProperty(localName = "AIRL")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AirlineData> airls;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A airline record in the body
     */
    @Data
    @NoArgsConstructor
    public static class AirlineData {
        /**
         * The IATA operator code
         */
        @JacksonXmlProperty(localName = "ITOP")
        private String ITOP;
        /**
         * The ICAO operator code
         */
        @JacksonXmlProperty(localName = "ICOP")
        private String ICOP;
        /**
         * Full name of the operator
         */
        @JacksonXmlProperty(localName = "ONAM")
        private String ONAM;
        /**
         * Chinese name of airline operator
         */
        @JacksonXmlProperty(localName = "ONMC")
        private String ONMC;
        /**
         * Country code to which airline belongs
         */
        @JacksonXmlProperty(localName = "CTRY")
        private String CTRY;
        /**
         * Default operating terminal for airline. This includes two parameters:
         * MVIN = Char(1) Movement indicator, only “A” and “D” are acceptable values.
         * FLIN = Char(1) Flight Indicator, only “I” and “D” are acceptable values.
         */
        @JacksonXmlProperty(localName = "TRML")
        private Terminal terminal;
        /**
         * Name of operator group to which airline belongs
         */
        @JacksonXmlProperty(localName = "OGRP")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<String> OGRP;
        /**
         * Identifies start of one airline subcompany record.
         * Repeats for each airline subcompany record.
         */
        @JacksonXmlProperty(localName = "SUBC")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<AirlineSubcompanyData> SUBC;
        /**
         * Domestic or international of the airline
         */
        @JacksonXmlProperty(localName = "DORI")
        private String DORI;
    }

    @Data
    @NoArgsConstructor
    public static class Terminal {
        @JacksonXmlText
        private String TRML;
        /**
         * Char(1) Movement indicator, only “A” and “D” are acceptable values.
         */
        @JacksonXmlProperty(isAttribute = true, localName = "MVIN")
        private String MVIN;
        /**
         * Char(1) Flight Indicator, only “I” and “D” are acceptable values.
         */
        @JacksonXmlProperty(isAttribute = true, localName = "FLIN")
        private String FLIN;
    }
}
