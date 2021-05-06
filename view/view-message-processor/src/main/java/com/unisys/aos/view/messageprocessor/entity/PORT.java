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
 * @author jianglushuang
 * @since 2020/12/7 4:22 下午
 */
@Data
@NoArgsConstructor
public class PORT {

    //Port time.
    @JacksonXmlProperty(localName = "POTM")
    private String POTM;
    //Port indicator
    @JacksonXmlProperty(localName = "POID")
    private Character POID;
}
