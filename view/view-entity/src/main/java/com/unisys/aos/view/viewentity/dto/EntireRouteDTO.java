/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unisys.aos.view.viewentity.entity.flight.FmsEntireRoute;
import com.unisys.aos.view.viewentity.entity.flight.FmsFlight;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zhang Wenqiang
 * @since 2020/12/16 11:05
 */
@Data
@NoArgsConstructor
public class EntireRouteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private FmsEntireRoute entireRoute;

    // Additional fields for display's convenience.
    private String airportName_cn;
    private String airportName_en;

    /**
     * Constructor
     *
     * @param fmsEntireRoute - FmsEntireRoute object with basic entire route information
     */
    public EntireRouteDTO(FmsEntireRoute fmsEntireRoute){
        this.entireRoute=fmsEntireRoute;
    }

    public String getAirportName_cn() {
        return airportName_cn;
    }

    public void setAirportName_cn(String airportName_cn) {
        this.airportName_cn = airportName_cn;
    }

    public String getAirportName_en() {
        return airportName_en;
    }

    public void setAirportName_en(String airportName_en) {
        this.airportName_en = airportName_en;
    }
}
