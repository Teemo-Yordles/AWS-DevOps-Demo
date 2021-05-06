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
 * @author jianglushuang
 * @since 2020/9/23 3:35 下午
 */
@Data
@NoArgsConstructor
public class ARPT extends MSG {

    @Data
    @NoArgsConstructor
    public static class AirportData {
        @JacksonXmlProperty(localName = "ITCD")
        private String ITCD;
        @JacksonXmlProperty(localName = "ICCD")
        private String ICCD;
        @JacksonXmlProperty(localName = "ANAM")
        private String ANAM;
        @JacksonXmlProperty(localName = "ANMC")
        private String ANMC;
        @JacksonXmlProperty(localName = "BDIS")
        private Long BDIS;
        @JacksonXmlProperty(localName = "CTRY")
        private String CTRY;
        @JacksonXmlProperty(localName = "ACTY")
        private String ACTY;
        @JacksonXmlProperty(localName = "ATYP")
        private String ATYP;
        @JacksonXmlProperty(localName = "HAUL")
        private String HAUL;
        @JacksonXmlProperty(localName = "ABBR")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<ARPT.AbbrData> Abbrs;
    }
    @Data
    @NoArgsConstructor
    public static class AbbrData {
        @JacksonXmlText()
        private String ABBR;
        @JacksonXmlProperty(isAttribute = false)
        private String CODE;
    }

    @JacksonXmlProperty(localName = "ARPT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ARPT.AirportData> airports;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;
}
