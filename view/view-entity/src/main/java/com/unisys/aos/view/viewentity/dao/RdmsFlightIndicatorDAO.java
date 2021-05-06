/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsFlightIndicator;

import java.util.List;

/**
 * The <b>RdmsFlightIndicatorDAO</b> is an interface, which is used to access
 * RdmsFlightIndicator data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/12/07 15:12
 */
public interface RdmsFlightIndicatorDAO extends BaseDAO<RdmsFlightIndicator> {
    /***
     * Get map entries based on indicator code
     * @param instanceCode flight indicator code
     * @return map entries
     */
    List<RdmsFlightIndicator> findByIndicatorCode(String instanceCode);
}
