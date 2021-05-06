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
 * Flight vip transactions
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class VIPT {
    // VIP service code
    @JacksonXmlProperty(localName = "VSCD")
    private String VSCD;

    // service quantity
    @JacksonXmlProperty(localName = "VTQY")
    private Long VTQY;

    // service start date time
    @JacksonXmlProperty(localName = "VTST")
    private String VTST;

    // service end date time
    @JacksonXmlProperty(localName = "VTET")
    private String VTET;
}
