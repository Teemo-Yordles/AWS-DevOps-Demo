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
 * The <b>ABDG</b> class is a POJO entity that represents the airbridge for reference data in message.
 * This class includes all the tags for input AIRBRIDGE messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class ABDG extends MSG {
    /**
     * The Airbridge message body list
     */
    @JacksonXmlProperty(localName = "ABDG")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AirbridgeData> abdgs;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A airbridge record in the body
     */
    @Data
    @NoArgsConstructor
    public static class AirbridgeData {
        /**
         * The code for airbridge
         */
        @JacksonXmlProperty(localName = "ABCD")
        private String ABCD;
        /**
         * Full description of the airbridge code
         */
        @JacksonXmlProperty(localName = "DESC")
        private String DESC;
        /**
         * Chinese description of the airbridge code
         */
        @JacksonXmlProperty(localName = "CDSC")
        private String CDSC;
        /**
         * Maximum height of the airbridge
         */
        @JacksonXmlProperty(localName = "AHGT")
        private BigDecimal AHGT;
        /**
         * Terminal code for this airbridge
         */
        @JacksonXmlProperty(localName = "ATML")
        private String ATML;
        /**
         * Whether A380 is supported on this airbridge or not
         */
        @JacksonXmlProperty(localName = "ATHE")
        private String ATHE;
        /**
         * Stand associated with this airbridge.
         */
        @JacksonXmlProperty(localName = "STND")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<String> STND;
        /**
         * Gate associated with this airbridge.
         */
        @JacksonXmlProperty(localName = "GATE")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<String> GATE;
        /**
         * Number of arms of the airbridge
         */
        @JacksonXmlProperty(localName = "AMNO")
        private Byte AMNO;
    }
}
