/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A airline subcompany record in <b>AIRL</b>.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class AirlineSubcompanyData extends MSG {
    /**
     * Subcompany code
     */
    @JacksonXmlProperty(localName = "CODE")
    private String CODE;
    /**
     * Subcompany name
     */
    @JacksonXmlProperty(localName = "NAME")
    private String NAME;
    /**
     * Subcompany name in alternative language
     */
    @JacksonXmlProperty(localName = "NAMC")
    private String NAMC;
}
