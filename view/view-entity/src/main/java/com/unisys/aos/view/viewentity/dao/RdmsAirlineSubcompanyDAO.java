/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsAirlineSubcompany;

import java.util.List;

/**
 * The <b>RdmsAirlineSubcompanyDAO</b> is an interface, which is used to access
 * RdmsAirlineSubcompany data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public interface RdmsAirlineSubcompanyDAO extends BaseDAO<RdmsAirlineSubcompany> {
    /**
     * get map entries based on Airline IATA Operator Code
     *
     * @param currentAirlineCode Airline IATA Operator Code
     * @param inspire            inspire time
     * @param expire             expire time
     * @return map entries
     */
    List<RdmsAirlineSubcompany> selectByAirlineCode(String currentAirlineCode, Long inspire, Long expire);

    /**
     * delete airline subcompany through Airline IATA Operator Code
     *
     * @param currentAirlineCode Airline IATA Operator Code
     * @param inspire            inspire time
     * @param expire             expire time
     */
    int deleteByAirlineCodeInNexTx(String currentAirlineCode, Long inspire, Long expire);
}
