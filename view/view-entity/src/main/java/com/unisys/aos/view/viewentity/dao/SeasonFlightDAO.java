/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.dto.SeasonFlightDTO;

import java.util.Date;

/**
 * The <b>SeasonFlightDAO</b> is an interface, which is used to access SeasonFlightDTO data.
 *
 * @author Zhang Wenqiang
 * @since 2020/11/11 20:50
 */
public interface SeasonFlightDAO extends BaseDAO<SeasonFlightDTO> {
    /**
     * This is to load the reference data cache
     * used during the SCHD processing.
     */
    void loadReferenceDataCache();

    /**
     * According to the start and end dates of seasonal,
     * delete the seasonal scheduled flights that exist in database.
     *
     * @param startDate Seasonal flight schedule start date.
     * @param endDate   Seasonal flight schedule end date.
     */
    void deleteBySeasonDate(Date startDate, Date endDate);

    /***
     * Get the start date of the last seasonal flight schedule in DB.
     * @return Return seasonal start date if successfully get, otherwise return null.
     */
    Date getEarlierScheduleStartDate();

    /***
     * Delete all seasonal flight schedule before the latest two seasonal flight schedules in DB.
     * @param startDateFirst First seasonal start date.
     * @param startDateSecond Second seasonal start date.
     */
    void deleteEarlierSchedule(Date startDateFirst, Date startDateSecond);
}
