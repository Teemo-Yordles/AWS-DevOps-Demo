/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unisys.aos.view.common.util.ListUtil;
import com.unisys.aos.view.viewentity.entity.flight.FmsSeasonEntireRoute;
import com.unisys.aos.view.viewentity.entity.flight.FmsSeasonFlight;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * This is a SeasonFlightDTO
 *
 * @author Zhang Wenqiang
 * @since 2020/11/11 10:08
 */
@Data
@NoArgsConstructor
public class SeasonFlightDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private final long temp_id = -1L;

    private FmsSeasonFlight seasonFlight;
    private List<FmsSeasonEntireRoute> seasonEntireRoutes;

    // Additional fields for display's convenience.
    private String localLanguageFullRoute;
    private String airportCodeFullRoute;

    private List<SeasonEntireRouteDTO> entireRouteDTOList;

    /**
     * Constructor
     *
     * @param seasonFlight - FmsFlight object with basic flight information
     */
    public SeasonFlightDTO(FmsSeasonFlight seasonFlight, List<FmsSeasonEntireRoute> seasonEntireRoutes) {
        this.seasonFlight = seasonFlight;
        this.seasonEntireRoutes = seasonEntireRoutes;
    }

    /**
     * Get the id of this flight in DB
     *
     * @return long value of the flight ID in DB.
     */
    @JsonIgnore
    public long getId() {
        if (null == seasonFlight) {
            return -1L;
        }
        return seasonFlight.getId();
    }

    /**
     * Get the number of this flight
     *
     * @return number of flight
     */
    @JsonIgnore
    public String getFlightNumber() {
        if (null == seasonFlight) {
            return null;
        }
        return seasonFlight.getFlightNumber();
    }

    /**
     * set DB records' create time to new xml parsed objects
     *
     * @param createTime record create date/time
     */
    public void batchSetCreateTime(Date createTime) {
        seasonFlight.setCreateTime(createTime);
        if (null != seasonEntireRoutes) {
            seasonEntireRoutes.forEach(item -> item.setCreateTime(createTime));
        }
    }

    public void setFlightCreateTime(Date createTime) {
        seasonFlight.setCreateTime(createTime);
    }

    public void setRouteCreateTime(Date createTime) {
        if (null != seasonEntireRoutes) {
            seasonEntireRoutes.forEach(item -> item.setCreateTime(createTime));
        }
    }

    /**
     * set DB records' update time to new xml parsed objects
     *
     * @param updateTime update date/time
     */
    public void setFlightUpdateTime(Date updateTime) {
        seasonFlight.setUpdateTime(updateTime);
    }

    /**
     * set DB records' id to new xml parsed objects
     *
     * @param oldRecord old SeasonFlightDTO
     */
    public void setFlightId(SeasonFlightDTO oldRecord) {
        seasonFlight.setId(oldRecord.getSeasonFlight().getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SeasonFlightDTO)) {
            return false;
        }
        SeasonFlightDTO flightDTO = (SeasonFlightDTO) o;

        // basic flight information check
        if (!seasonFlight.equals(flightDTO.seasonFlight)) {
            return false;
        }
        seasonEntireRoutes.forEach(item -> {
            if (item.getFlightId().compareTo(temp_id) == 0) {
                item.setFlightId(seasonFlight.getId());
            }
        });

        // additional flight information check
        return ListUtil.isListEqual(seasonEntireRoutes, flightDTO.seasonEntireRoutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonFlight, seasonEntireRoutes);
    }
}
