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
public class VIPL extends MSG {

    @Data
    @NoArgsConstructor
    public static class VipPersonnalData {
        @JacksonXmlProperty(localName = "VPCD")
        private String VPCD;
        @JacksonXmlProperty(localName = "VPNC")
        private String VPNC;
        @JacksonXmlProperty(localName = "VFNM")
        private String VFNM;
        @JacksonXmlProperty(localName = "VLNM")
        private String VLNM;
        @JacksonXmlProperty(localName = "VDES")
        private String VDES;
        @JacksonXmlProperty(localName = "VDSC")
        private String VDSC;
        @JacksonXmlProperty(localName = "VPON")
        private String VPON;
        @JacksonXmlProperty(localName = "VRNK")
        private String VRNK;
        @JacksonXmlProperty(localName = "VPTC")
        private String VPTC;
        @JacksonXmlProperty(localName = "VCTN")
        private String VCTN;
        @JacksonXmlProperty(localName = "VRMK")
        private String VRMK;
        @JacksonXmlProperty(localName = "VCMN")
        private String VCMN;
        @JacksonXmlProperty(localName = "VPWU")
        private String VPWU;
        @JacksonXmlProperty(localName = "VPPS")
        private String VPPS;
    }


    @JacksonXmlProperty(localName = "VIPL")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<VIPL.VipPersonnalData> vipPersonnals;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;
}
