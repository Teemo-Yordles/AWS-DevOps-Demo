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
 * The <b>ORGN</b> class is a POJO entity that represents the organization for reference data in message.
 * This class includes all the tags for input ORGANIZATION messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class ORGN extends MSG {
    /**
     * The Organization message body list
     */
    @JacksonXmlProperty(localName = "ORGN")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<OrganizationData> orgns;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;

    /**
     * An organization record in the body
     */
    @Data
    @NoArgsConstructor
    public static class OrganizationData {
        /**
         * Organization Identifier
         */
        @JacksonXmlProperty(localName = "OGID")
        private Integer OGID;
        /**
         * Full name of organization
         */
        @JacksonXmlProperty(localName = "ONAM")
        private String ONAM;
        /**
         * Chinese name of organization
         */
        @JacksonXmlProperty(localName = "ONMC")
        private String ONMC;
        /**
         * Organization category.
         */
        @JacksonXmlProperty(localName = "OCAT")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<String> OCAT;
    }
}
