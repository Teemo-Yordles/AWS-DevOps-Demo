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
 * Flight vip information
 * @author LiuJ2
 * @since 2020/10/9 18:52
 */
@Data
@NoArgsConstructor
public class VIPF {
    // VIP person code
    @JacksonXmlProperty(localName = "VPCD")
    private String VPCD;

    // entourage size
    @JacksonXmlProperty(localName = "VFES")
    private Short VFES;

    // vip transactions
    @JacksonXmlProperty(localName = "VIPT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<VIPT> vipts;
}
