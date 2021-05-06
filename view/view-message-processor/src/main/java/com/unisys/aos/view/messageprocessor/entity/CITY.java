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
public class CITY extends MSG {

    @Data
    @NoArgsConstructor
    public static class CityData {
        @JacksonXmlProperty(localName = "ITCD")
        private String ITCD;
        @JacksonXmlProperty(localName = "ICCD")
        private String ICCD;
        @JacksonXmlProperty(localName = "CNAM")
        private String CNAM;
        @JacksonXmlProperty(localName = "CNMC")
        private String CNMC;
        @JacksonXmlProperty(localName = "CTRY")
        private String CTRY;
        @JacksonXmlProperty(localName = "ABBR")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<CITY.AbbrData> Abbrs;
    }
    @Data
    @NoArgsConstructor
    public static class AbbrData {
        @JacksonXmlText()
        private String ABBR;
        @JacksonXmlProperty(isAttribute = false)
        private String CODE;
    }

    @JacksonXmlProperty(localName = "CITY")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CITY.CityData> citys;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;
}
