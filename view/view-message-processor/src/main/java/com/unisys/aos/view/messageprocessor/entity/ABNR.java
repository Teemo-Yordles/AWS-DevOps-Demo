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
 * The <b>ABNR</b> class is a POJO entity that represents the abnormal reason for reference data in message.
 * This class includes all the tags for input Abnormal reason messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class ABNR extends MSG {
    /**
     * The Abnormal reason message body list
     */
    @JacksonXmlProperty(localName = "ABNR")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AbnormalStatusData> abnrs;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A abnormal reason record in the body
     */
    @Data
    @NoArgsConstructor
    public static class AbnormalStatusData {
        /**
         * abnormal reason code
         */
        @JacksonXmlProperty(localName = "ANRC")
        private String ANRC;
        /**
         * abnormal reason description
         */
        @JacksonXmlProperty(localName = "ANRD")
        private String ANRD;
        /**
         * abnormal reason CN description
         */
        @JacksonXmlProperty(localName = "ARDC")
        private String ARDC;
    }
}
