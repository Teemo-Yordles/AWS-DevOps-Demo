/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unisys.aos.view.viewentity.entity.flight.FmsVip;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zhang Wenqiang
 * @since 2020/12/16 11:05
 */
@Data
@NoArgsConstructor
public class VipInformationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private FmsVip vip;

    // Additional fields for display's convenience.
    private String firstName;
    private String lastName;


    /**
     * Constructor
     *
     * @param fmsVip - FmsVip object with vip information
     */
    public VipInformationDTO(FmsVip fmsVip){
        this.vip=fmsVip;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
