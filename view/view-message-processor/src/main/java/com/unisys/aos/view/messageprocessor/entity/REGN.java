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
 * The <b>REGN</b> class is a POJO entity that represents the registration for reference data in message.
 * This class includes all the tags for input REGISTRATION messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class REGN extends MSG {
    /**
     * The Registration message body list
     */
    @JacksonXmlProperty(localName = "REGN")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<RegistrationData> regns;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A registration record in the body
     */
    @Data
    @NoArgsConstructor
    public static class RegistrationData {
        /**
         * Registration Number
         */
        @JacksonXmlProperty(localName = "RNUM")
        private String RNUM;
        /**
         * The IATA code for aircraft type
         */
        @JacksonXmlProperty(localName = "ITAT")
        private String ITAT;
        /**
         * Organization ID of owner of aircraft
         */
        @JacksonXmlProperty(localName = "OWID")
        private Integer OWID;
        /**
         * Aircraft owner’s airline code
         */
        @JacksonXmlProperty(localName = "ACAL")
        private String ACAL;
        /**
         * Aircraft owner’s airline subcompany code
         */
        @JacksonXmlProperty(localName = "ALSC")
        private String ALSC;
        /**
         * Maximum passengers this particular registration can carry, overrides the value in the aircraft type.
         */
        @JacksonXmlProperty(localName = "MAXP")
        private Short MAXP;
        /**
         * Maximum freight weight for this particular registration, overrides the value in the aircraft type.
         */
        @JacksonXmlProperty(localName = "MFWT")
        private BigDecimal MFWT;
        /**
         * Maximum freight weight for this particular registration, overrides the value in the aircraft type.
         */
        @JacksonXmlProperty(localName = "MTWT")
        private BigDecimal MTWT;
    }
}
