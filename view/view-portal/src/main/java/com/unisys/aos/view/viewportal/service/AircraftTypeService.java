/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewportal.service;

import com.unisys.aos.view.viewentity.entity.reference.RdmsAircraftType;

import java.util.Collection;

public interface AircraftTypeService {

    /**
     * Get all aircraft type reference data
     * @return A list of RdmsAircraftType objects.
     */
    Collection<RdmsAircraftType> selectAll();
}
