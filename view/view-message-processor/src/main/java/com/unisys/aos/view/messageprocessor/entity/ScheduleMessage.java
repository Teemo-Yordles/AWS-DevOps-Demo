/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The <b>SCHD</b> class is a POJO entity that represents the flight information
 * for schedule data in message.
 * This class includes all the tags for input SCHD/ADFT messages.
 *
 * The <b>SCHS</b> class is a POJO entity that represents the flight information
 * for seasonal schedule data in message.
 * This class includes all the tags for input SCHS messages.
 *
 * @author LiuJ2
 * @since 2020/10/7 9:20
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class ScheduleMessage extends MSG{
    /**
     * SCHD section.
     */
    @JacksonXmlProperty(localName = "SCHD")
    private SCHD schd;

    @JacksonXmlProperty(localName = "SCHS")
    private SCHS schs;

    @Data
    @NoArgsConstructor
    public static class SCHS {
        /**
         * Name of the season for which this seasonal schedule is applicable.
         */
        @JacksonXmlProperty(localName = "SNAM")
        private String SNAM;

        /**
         * Airport code for which this data is applicable.
         */
        @JacksonXmlProperty(localName = "APCD")
        private String APCD;

        /**
         * Starting date of the season.
         */
        @JacksonXmlProperty(localName = "SDAT")
        private String SDAT;

        /**
         * Ending date of the season.
         */
        @JacksonXmlProperty(localName = "EDAT")
        private String EDAT;

        /**
         * The record count for reference data.
         */
        @JacksonXmlProperty(localName = "RECS")
        private Integer RECS;

        /**
         * The flights record message body list
         */
        @JacksonXmlProperty(localName = "FLTR")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<FLTR> fltrs;
    }

    @Data
    @NoArgsConstructor
    public static class SCHD {
        /**
         * The record count for reference data.
         */
        @JacksonXmlProperty(localName = "RECS")
        private Integer RECS;

        /**
         * The flights record message body list
         */
        @JacksonXmlProperty(localName = "FLTR")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<FLTR> fltrs;
    }
}
