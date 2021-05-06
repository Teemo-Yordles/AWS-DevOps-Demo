/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsAirbridge;

import java.util.List;

/**
 * The <b>RdmsAirbridgeDAO</b> is an interface, which is used to access
 * RdmsAirbridge data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public interface RdmsAirbridgeDAO extends BaseDAO<RdmsAirbridge> {
    /**
     * get map entries based on airbridge code
     *
     * @param currentAirbridgeCode airbridge code
     * @param inspire              inspire time
     * @param expire               expire time
     * @return map entries
     */
    List<RdmsAirbridge> selectByAirbridgeCode(String currentAirbridgeCode, Long inspire, Long expire);
}
