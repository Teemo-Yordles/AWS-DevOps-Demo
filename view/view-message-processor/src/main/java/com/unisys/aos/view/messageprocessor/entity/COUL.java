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
 * The <b>COUL</b> class is a POJO entity that represents the country for reference data in message.
 * This class includes all the tags for input COUNTRY messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class COUL extends MSG {
    /**
     * The Country message body list
     */
    @JacksonXmlProperty(localName = "COUL")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CountryData> couls;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A country record in the body
     */
    @Data
    @NoArgsConstructor
    public static class CountryData {
        // Country code
        @JacksonXmlProperty(localName = "COUC")
        private String COUC;
        // Country name
        @JacksonXmlProperty(localName = "COUN")
        private String COUN;
        // Country name in Chinese
        @JacksonXmlProperty(localName = "CNMC")
        private String CNMC;
        // Region codes
        @JacksonXmlProperty(localName = "REGC")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<String> REGC;
    }
}
