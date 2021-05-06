/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewportal.controller;

import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.exception.ApiException;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAircraftType;
import com.unisys.aos.view.viewportal.service.AircraftTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@Slf4j
public class AircraftTypeController {
    private final AircraftTypeService aircraftTypeService;

    @Autowired
    public AircraftTypeController(AircraftTypeService aircraftTypeService) {
        this.aircraftTypeService = aircraftTypeService;
    }

    /**
     * get all aircraft type reference data
     * @return a Result object contains all aircraft type reference data
     * @throws ApiException - if any exception happens
     */
    @RequestMapping(value="/view/aircraft-types", method = RequestMethod.GET)
    @ResponseBody
    private Result<Collection<RdmsAircraftType>> getAircraftTypes() throws ApiException {
        log.debug("Getting aircraft types...");
        Collection<RdmsAircraftType> aircraftTypes = aircraftTypeService.selectAll();
        return Result.success(aircraftTypes);
    }
}
