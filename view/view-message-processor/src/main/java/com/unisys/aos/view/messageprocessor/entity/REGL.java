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
 * The <b>REGL</b> class is a POJO entity that represents the region for reference data in message.
 * This class includes all the tags for input REGION messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class REGL extends MSG {
    /**
     * The Region message body list
     */
    @JacksonXmlProperty(localName = "REGL")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<RegionData> regls;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * A region record in the body
     */
    @Data
    @NoArgsConstructor
    public static class RegionData {
        // Region code
        @JacksonXmlProperty(localName = "REGC")
        private String REGC;
        // Region name
        @JacksonXmlProperty(localName = "REGN")
        private String REGN;
        // Region name in Chinese
        @JacksonXmlProperty(localName = "RGNC")
        private String RGNC;
    }
}
