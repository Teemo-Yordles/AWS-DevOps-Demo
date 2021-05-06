/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsGate;

import java.util.List;

/**
 * The <b>RdmsGateDAO</b> is an interface, which is used to access
 * RdmsGate data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public interface RdmsGateDAO extends BaseDAO<RdmsGate> {
    /**
     * get map entries based on gate code
     *
     * @param currentGateCode gate code
     * @param inspire         inspire time
     * @param expire          expire time
     * @return map entries
     */
    List<RdmsGate> findByGateCode(String currentGateCode, Long inspire, Long expire);

    /**
     * get map entries based on terminal code
     *
     * @param currentTerminalCode terminal code
     * @param inspire             inspire time
     * @param expire              expire time
     * @return map entries
     */
    List<RdmsGate> findByTerminalCode(String currentTerminalCode, Long inspire, Long expire);

    /**
     * get map entries based on pier code
     *
     * @param currentPierCode pier code
     * @param inspire         inspire time
     * @param expire          expire time
     * @return map entries
     */
    List<RdmsGate> findByPierCode(String currentPierCode, Long inspire, Long expire);

    /***
     * Get RdmsGate entity by gate code.
     *
     * @param code gate code
     * @return RdmsGate entries
     */
    List<RdmsGate> findByGateCode(String code);
}
