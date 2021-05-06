/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsStatus;

import java.util.List;

/**
 * The <b>RdmsStatusDAO</b> is an interface, which is used to access
 * RdmsStatus data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public interface RdmsStatusDAO extends BaseDAO<RdmsStatus> {
    /***
     * Get RdmsStatus entity by flight status code.
     * @param code flight status code
     * @return RdmsStatus entity
     */
    List<RdmsStatus> findByStatusCode(String code);
}
