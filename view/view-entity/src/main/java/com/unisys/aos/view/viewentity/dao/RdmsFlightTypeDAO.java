/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsFlightType;

import java.util.List;

/**
 * The <b>RdmsFlightTypeDAO</b> is an interface, which is used to access
 * RdmsFlightType data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public interface RdmsFlightTypeDAO extends BaseDAO<RdmsFlightType> {
    /***
     * Get RdmsFlightType entity by flight type code.
     * @param typeCode flight type code
     * @return RdmsFlightType entity
     */
    List<RdmsFlightType> findByTypeCode(String typeCode);
}
