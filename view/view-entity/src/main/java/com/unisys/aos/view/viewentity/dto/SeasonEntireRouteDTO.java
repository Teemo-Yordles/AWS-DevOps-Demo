/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dto;

import com.unisys.aos.view.viewentity.entity.flight.FmsSeasonEntireRoute;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zhang Wenqiang
 * @since 2020/12/30 16:05
 */
@Data
@NoArgsConstructor
public class SeasonEntireRouteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private FmsSeasonEntireRoute seasonEntireRoute;

    // Additional fields for display's convenience.
    private String airportName_cn;
    private String airportName_en;

    /**
     * Constructor
     *
     * @param seasonEntireRoute - FmsEntireRoute object with basic entire route information
     */
    public SeasonEntireRouteDTO(FmsSeasonEntireRoute seasonEntireRoute){
        this.seasonEntireRoute =seasonEntireRoute;
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
