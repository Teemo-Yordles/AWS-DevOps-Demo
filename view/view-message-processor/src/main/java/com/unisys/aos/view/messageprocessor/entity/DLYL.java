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
 * The <b>DLYL</b> class is a POJO entity that represents the delay code for reference data in message.
 * This class includes all the tags for input Delay Code messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class DLYL extends MSG {
    /**
     * The Delay Code message body list
     */
    @JacksonXmlProperty(localName = "DLYL")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DelayTypeData> dlyls;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A delay code record in the body
     */
    @Data
    @NoArgsConstructor
    public static class DelayTypeData {
        /**
         * Delay code
         */
        @JacksonXmlProperty(localName = "DCOD")
        private String DCOD;
        /**
         * Delay code as numeric
         */
        @JacksonXmlProperty(localName = "DCDN")
        private Short DCDN;
        /**
         * Associated delay description
         */
        @JacksonXmlProperty(localName = "DDES")
        private String DDES;
        /**
         * Delay description in Chinese
         */
        @JacksonXmlProperty(localName = "DDSC")
        private String DDSC;
    }
}
