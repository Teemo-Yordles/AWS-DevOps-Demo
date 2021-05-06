/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsCountry;

import java.util.List;

/**
 * The <b>RdmsCountryDAO</b> is an interface, which is used to access
 * RdmsCountry data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public interface RdmsCountryDAO extends BaseDAO<RdmsCountry> {
    /**
     * get map entries based on country code
     *
     * @param currentCountryCode country code
     * @param inspire            inspire time
     * @param expire             expire time
     * @return map entries
     */
    List<RdmsCountry> selectByCountryCode(String currentCountryCode, Long inspire, Long expire);
}
