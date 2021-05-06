/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsRegion;

import java.util.List;

/**
 * The <b>RdmsRegionDAO</b> is an interface, which is used to access
 * RdmsRegion data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public interface RdmsRegionDAO extends BaseDAO<RdmsRegion> {
    /**
     * get map entries based on region code
     *
     * @param currentRegionCode region code
     * @param inspire           inspire time
     * @param expire            expire time
     * @return map entries
     */
    List<RdmsRegion> selectByRegionCode(String currentRegionCode, Long inspire, Long expire);
}
