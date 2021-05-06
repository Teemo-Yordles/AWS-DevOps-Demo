/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsCity;

import java.util.List;

/**
 * The <b>CityDAO</b> is an interface, which is used to access
 * City data from database.
 *
 * @author jianglushuang
 * @since 2020/9/23 3:28 下午
 */
public interface RdmsCityDAO extends BaseDAO<RdmsCity> {
    /***
     * Get RdmsCity entities by city iata code.
     * @param iataCode city iata code
     * @return RdmsCity entities
     */
    List<RdmsCity> findByIataCityCode(String iataCode);
}
