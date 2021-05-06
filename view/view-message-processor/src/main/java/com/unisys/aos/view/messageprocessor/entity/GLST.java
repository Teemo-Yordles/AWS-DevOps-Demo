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
 * The <b>GLST</b> class is a POJO entity that represents the gate for reference data in message.
 * This class includes all the tags for input GATE messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class GLST extends MSG {
    /**
     * The Gate message body list
     */
    @JacksonXmlProperty(localName = "GLST")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<GateData> glsts;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A gate record in the body
     */
    @Data
    @NoArgsConstructor
    public static class GateData {
        /**
         * Code for this gate
         */
        @JacksonXmlProperty(localName = "GCOD")
        private String GCOD;
        /**
         * Gate name/description
         */
        @JacksonXmlProperty(localName = "GTNM")
        private String GTNM;
        /**
         * Gate name/description in Chinese
         */
        @JacksonXmlProperty(localName = "GNMC")
        private String GNMC;
        /**
         * Gate category
         */
        @JacksonXmlProperty(localName = "GCAT")
        private String GCAT;
        /**
         * Terminal code for this gate
         */
        @JacksonXmlProperty(localName = "GTML")
        private String GTML;
        /**
         * Pier code for this gate
         */
        @JacksonXmlProperty(localName = "PIER")
        private String PIER;
    }
}
