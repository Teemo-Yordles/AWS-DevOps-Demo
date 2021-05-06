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
import com.unisys.aos.view.viewentity.entity.flight.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * This is a FlightDTO
 *
 * @author LiuJ2
 * @since 2020/10/12 11:03
 */
@Data
@NoArgsConstructor
public class FlightDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private FmsFlight flight;
    private List<FmsAirbridge> airbridges;
    private List<FmsChute> chutes;
    private List<FmsChock> chocks;
    private List<FmsCheckinDesk> checkinDesks;
    private List<FmsCarousel> carousels;
    private List<FmsDelay> delays;
    private List<FmsEntireRoute> entireRoutes;
    private List<FmsGate> gates;
    private List<FmsStand> stands;
    private List<FmsService> services;
    private List<FmsVip> vips;

    private List<EntireRouteDTO> entireRouteDTOList;
    private List<VipInformationDTO> vipInformationDTOList;
    private List<DelayInformationDTO> delayInformationDTOList;

    private String airportCodeFullRoute;

    private String flightIndicator_cn;
    private String flightIndicator_en;

    private String flightType_cn;
    private String flightType_en;

    private String flightStatus_cn;
    private String flightStatus_en;

    private String flightExternalStatus_cn;
    private String flightExternalStatus_en;

    private String cityDescription_cn;
    private String cityDescription_en;

    private String delayDescription_cn;
    private String delayDescription_en;

    private String paxAgent_cn;
    private String paxAgent_en;

    private String fieldAgent_cn;
    private String fieldAgent_en;

    private String maintenanceAgent_cn;
    private String maintenanceAgent_en;

    private Date preOrNextStepScheduleTime;
    private Date preOrNextStepEstimateTime;
    private Date preOrNextStepActualTime;

    private String codeShareFlightList;
    private String standList;
    private String gateList;
    private String carouselList;
    private String checkinDeskList;
    private String chuteList;
    private String airbridgeList;
    private String pierList;
    private String cancelFlag;
    private String flightReturn;
    private String previousOrNextStop;

    /**
     * Constructor
     *
     * @param flight - FmsFlight object with basic flight information
     */
    public FlightDTO(FmsFlight flight) {
        this.flight = flight;
    }

    /**
     * Get the aodb id for this flight
     *
     * @return long value of the AODB ID.
     */
    @JsonIgnore
    public long getId() {
        if (null == flight) {
            return -1L;
        }
        return flight.getAodbId();
    }

    /**
     * set DB records' create time to new xml parsed objects
     *
     * @param createTime record create date/time
     */
    public void batchSetCreateTime(Date createTime) {
        flight.setCreateTime(createTime);
        if (null != entireRoutes) {
            entireRoutes.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != airbridges) {
            airbridges.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != chutes) {
            chutes.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != chocks) {
            chocks.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != checkinDesks) {
            checkinDesks.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != carousels) {
            carousels.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != delays) {
            delays.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != gates) {
            gates.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != stands) {
            stands.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != services) {
            services.forEach(item -> item.setCreateTime(createTime));
        }
        if (null != vips) {
            vips.forEach(item -> item.setCreateTime(createTime));
        }
    }

    /**
     * set DB records' update time to new xml parsed objects
     *
     * @param updateTime update date/time
     */
    public void batchSetUpdateTime(Date updateTime) {
        flight.setUpdateTime(updateTime);
        if (null != entireRoutes) {
            entireRoutes.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != airbridges) {
            airbridges.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != chutes) {
            chutes.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != chocks) {
            chocks.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != checkinDesks) {
            checkinDesks.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != carousels) {
            carousels.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != delays) {
            delays.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != gates) {
            gates.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != stands) {
            stands.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != services) {
            services.forEach(item -> item.setUpdateTime(updateTime));
        }
        if (null != vips) {
            vips.forEach(item -> item.setUpdateTime(updateTime));
        }
    }

    /**
     * set DB records' id to new xml parsed objects
     *
     * @param currentRecord Current flight Dto.
     */
    public void batchSetRecordId(FlightDTO currentRecord) {
        flight.setId(currentRecord.getFlight().getId());
        setRecordIdForList(this.getEntireRoutes(), currentRecord.getEntireRoutes());
        setRecordIdForList(this.getAirbridges(), currentRecord.getAirbridges());
        setRecordIdForList(this.getCarousels(), currentRecord.getCarousels());
        setRecordIdForList(this.getCheckinDesks(), currentRecord.getCheckinDesks());
        setRecordIdForList(this.getChocks(), currentRecord.getChocks());
        setRecordIdForList(this.getChutes(), currentRecord.getChutes());
        setRecordIdForList(this.getDelays(), currentRecord.getDelays());
        setRecordIdForList(this.getGates(), currentRecord.getGates());
        setRecordIdForList(this.getServices(), currentRecord.getServices());
        setRecordIdForList(this.getStands(), currentRecord.getStands());
        setRecordIdForList(this.getVips(), currentRecord.getVips());
    }

    /**
     * Match the records and set the current records' ids to new records.
     *
     * @param newRecords     - list of new records
     * @param currentRecords - list of current records
     */
    private void setRecordIdForList(List<? extends AdditionFlightInfo> newRecords, List<? extends AdditionFlightInfo> currentRecords) {
        // sanity check
        if (null == newRecords || newRecords.isEmpty() || null == currentRecords || currentRecords.isEmpty()) {
            return;
        }

        for (AdditionFlightInfo newRecord : newRecords) {
            for (AdditionFlightInfo currentRecord : currentRecords) {
                if (newRecord.matchFlightInfoRecord(currentRecord)) {
                    newRecord.setId(currentRecord.getId());
                    break;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlightDTO)) {
            return false;
        }
        FlightDTO flightDTO = (FlightDTO) o;

        // basic flight information check
        if (!flight.equals(flightDTO.flight)) {
            return false;
        }

        // additional flight information check
        if (!ListUtil.isListEqual(entireRoutes, flightDTO.entireRoutes)) {
            return false;
        } else if (!ListUtil.isListEqual(airbridges, flightDTO.airbridges)) {
            return false;
        } else if (!ListUtil.isListEqual(chutes, flightDTO.chutes)) {
            return false;
        } else if (!ListUtil.isListEqual(chocks, flightDTO.chocks)) {
            return false;
        } else if (!ListUtil.isListEqual(checkinDesks, flightDTO.checkinDesks)) {
            return false;
        } else if (!ListUtil.isListEqual(carousels, flightDTO.carousels)) {
            return false;
        } else if (!ListUtil.isListEqual(delays, flightDTO.delays)) {
            return false;
        } else if (!ListUtil.isListEqual(gates, flightDTO.gates)) {
            return false;
        } else if (!ListUtil.isListEqual(stands, flightDTO.stands)) {
            return false;
        } else if (!ListUtil.isListEqual(services, flightDTO.services)) {
            return false;
        } else return ListUtil.isListEqual(vips, flightDTO.vips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flight, airbridges, chutes, chocks, checkinDesks, carousels, delays, entireRoutes, gates, stands, services, vips);
    }
}
