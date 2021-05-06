/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsAirport;

import java.util.List;

/**
 * The <b>AirportDAO</b> is an interface, which is used to access
 * Airport data from database.
 *
 * @author jianglushuang
 * @since 2020/9/23 3:28 下午
 */
public interface RdmsAirportDAO extends BaseDAO<RdmsAirport> {
    /***
     * Get records by IATA code.
     * @param iataCode IATA code.
     * @return record entries.
     */
    List<RdmsAirport> findByIataCode(String iataCode);
}
