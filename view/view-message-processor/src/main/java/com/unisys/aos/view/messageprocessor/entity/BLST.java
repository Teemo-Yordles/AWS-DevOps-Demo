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
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jianglushuang
 * @since 2020/9/23 3:35 下午
 */
@Data
@NoArgsConstructor
public class BLST extends MSG {

    @Data
    @NoArgsConstructor
    public static class CarouselData {
        @JacksonXmlProperty(localName = "BCOD")
        private String BCOD;
        @JacksonXmlProperty(localName = "BTNM")
        private String BTNM;
        @JacksonXmlProperty(localName = "BNMC")
        private String BNMC;
        @JacksonXmlProperty(localName = "BCAT")
        private String BCAT;
        @JacksonXmlProperty(localName = "BTML")
        private String BTML;
    }


    @JacksonXmlProperty(localName = "BLST")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<BLST.CarouselData> carousels;
    /**
     * The record count for reference data.
     */
    @JacksonXmlProperty(localName = "RECS")
    private Integer RECS;
}
