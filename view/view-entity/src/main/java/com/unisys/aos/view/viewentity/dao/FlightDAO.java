/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.entity.flight.AdditionFlightInfo;
import com.unisys.aos.view.viewentity.entity.flight.FmsFlight;
import com.unisys.aos.view.viewentity.entity.flight.FmsStand;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author LiuJ2
 * @since 2020/9/14 13:06
 */
public interface FlightDAO extends BaseDAO<FlightDTO> {
    /**
     * This is to load the reference data cache
     * used during the SCHD processing.
     */
    void loadReferenceDataCache();

    /**
     * This is to load the flight data with AODB Flight ID.
     *
     * @return Flight with flight ID specified. Null if cannot find
     */
    FlightDTO findByAodbId(Long flightId);

    /**
     * Update new flight basic information object to database.
     * NOTE: this only update the FmsFlight object to DB.
     *
     * @param newRecord - new flight record
     * @param oldFlight - current FmsFlight record
     */
    void updateFmsFlightInNewTx(FlightDTO newRecord, FmsFlight oldFlight);

    /**
     * Update new delay information object to database.
     *
     * @param newRecord - new flight record
     * @param oldRecord - current flight record
     */
    void updateFmsDelayInNewTx(FlightDTO newRecord, FlightDTO oldRecord);

    /**
     * Update new entire route information object to database.
     *
     * @param newRecord - new flight record
     * @param oldRecord - current flight record
     */
    void updateFmsEntireRouteInNewTx(FlightDTO newRecord, FlightDTO oldRecord);

    /**
     * Update new service information object to database.
     *
     * @param newRecord - new flight record
     * @param oldRecord - current flight record
     */
    void updateFmsServiceInNewTx(FlightDTO newRecord, FlightDTO oldRecord);

    /**
     * Update flight additional information and DTO cache
     * For new element in the records, need to add to the table.
     * For updated element in the records, need to update table records.
     * For extra elements in the current record list, need to delete them.
     *
     * @param flightDTO  - flightDTO that contains old records
     * @param newRecords - list of new records
     */
    void updateAdditionFlightInfo(FlightDTO flightDTO, List<? extends AdditionFlightInfo> newRecords, Type type);

    /**
     * Batch update stand in Database.
     *
     * @param stands - new flight stand information list
     */
    void updateFmsStands(List<FmsStand> stands);

    /***
     * Get flight by aodb id.
     * @param aodbId Flight aodb id
     * @return Flight
     */
    FmsFlight getByAodbId(Long aodbId);

    /***
     * Get code share flight numbers list.
     * @param currentFlight Current flight.
     * @return Code share flights string list or null.
     */
    List<String> getCodeShareFlightNumberList(FmsFlight currentFlight);

    /**
     * Cache the flight record into Redis
     *
     * @param record - record to be cached
     */
    void updateCacheRecord(FlightDTO record);
}
