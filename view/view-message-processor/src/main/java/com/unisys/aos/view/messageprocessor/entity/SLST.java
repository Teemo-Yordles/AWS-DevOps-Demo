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

import java.math.BigDecimal;
import java.util.List;

/**
 * The <b>SLST</b> class is a POJO entity that represents the stand for reference data in message.
 * This class includes all the tags for input STAND messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class SLST extends MSG {
    /**
     * The Stand message body list
     */
    @JacksonXmlProperty(localName = "SLST")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<StandData> slsts;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A stand record in the body
     */
    @Data
    @NoArgsConstructor
    public static class StandData {
        /**
         * Stand code for this stand
         */
        @JacksonXmlProperty(localName = "SCOD")
        private String SCOD;
        /**
         * Stand name
         */
        @JacksonXmlProperty(localName = "STNM")
        private String STNM;
        /**
         * Stand name in Chinese
         */
        @JacksonXmlProperty(localName = "SNMC")
        private String SNMC;
        /**
         * The biggest aircraft type category can be allocated in stand.
         */
        @JacksonXmlProperty(localName = "SATC")
        private String SATC;
        /**
         * Stand group code of this stand
         */
        @JacksonXmlProperty(localName = "STGP")
        private String STGP;
        /**
         * Width of the stand
         */
        @JacksonXmlProperty(localName = "SWID")
        private BigDecimal SWID;
        /**
         * Length of the stand
         */
        @JacksonXmlProperty(localName = "SHGT")
        private BigDecimal SHGT;
        /**
         * Terminal code
         */
        @JacksonXmlProperty(localName = "STML")
        private String STML;
        /**
         * Maximum number airbridges that can be attached on this stand.
         */
        @JacksonXmlProperty(localName = "MABS")
        private Byte MABS;
        /**
         * Default gate for international flights for this stand
         */
        @JacksonXmlProperty(localName = "DIGT")
        private String DIGT;
        /**
         * Default gate for domestic flights for this stand
         */
        @JacksonXmlProperty(localName = "DDGT")
        private String DDGT;
        /**
         * Is this a remote stand, contact stand or a bucket area stand?
         */
        @JacksonXmlProperty(localName = "REMT")
        private String REMT;
        /**
         * Does this stand have a fixed electric power unit?
         */
        @JacksonXmlProperty(localName = "FEPU")
        private String FEPU;
    }
}
