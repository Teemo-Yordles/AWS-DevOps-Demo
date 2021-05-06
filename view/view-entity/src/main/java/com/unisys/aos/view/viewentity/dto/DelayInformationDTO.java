/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unisys.aos.view.viewentity.entity.flight.FmsDelay;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zhang Wenqiang
 * @since 2020/12/16 11:05
 */
@Data
@NoArgsConstructor
public class DelayInformationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private FmsDelay delay;

    // Additional fields for display's convenience.
    private String delay_cn;
    private String delay_en;

    /**
     * Constructor
     *
     * @param fmsDelay - FmsDelay object with delay information
     */
    public DelayInformationDTO(FmsDelay fmsDelay){
        this.delay=fmsDelay;
    }

    public String getDelay_cn() {
        return delay_cn;
    }

    public void setDelay_cn(String delay_cn) {
        this.delay_cn = delay_cn;
    }

    public String getDelay_en() {
        return delay_en;
    }

    public void setDelay_en(String delay_en) {
        this.delay_en = delay_en;
    }
}
