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
 * @author jianglushuang
 * @since 2020/9/23 3:35 下午
 */
@Data
@NoArgsConstructor
public class FLTL extends MSG {

    @Data
    @NoArgsConstructor
    public static class FlightTypeData {
        @JacksonXmlProperty(localName = "FTYP")
        private String FTYP;
        @JacksonXmlProperty(localName = "CTYP")
        private String CTYP;
        @JacksonXmlProperty(localName = "FDES")
        private String FDES;
        @JacksonXmlProperty(localName = "FDSC")
        private String FDSC;
        @JacksonXmlProperty(localName = "FCML")
        private String FCML;
    }


    @JacksonXmlProperty(localName = "FLTL")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FLTL.FlightTypeData> flightTypes;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;
}
