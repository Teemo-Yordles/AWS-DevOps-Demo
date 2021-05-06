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
 * @author jianglushuang
 * @since 2020/9/23 3:35 下午
 */
@Data
@NoArgsConstructor
public class VIPS extends MSG {

    @Data
    @NoArgsConstructor
    public static class VipServiceData {
        @JacksonXmlProperty(localName = "VSCD")
        private String VSCD;
        @JacksonXmlProperty(localName = "VSDS")
        private String VSDS;
        @JacksonXmlProperty(localName = "VSDC")
        private String VSDC;
        @JacksonXmlProperty(localName = "VSQC")
        private Byte VSQC;
        @JacksonXmlProperty(localName = "VSUC")
        private String VSUC;
    }


    @JacksonXmlProperty(localName = "VIPS")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<VIPS.VipServiceData> vipServices;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;
}
