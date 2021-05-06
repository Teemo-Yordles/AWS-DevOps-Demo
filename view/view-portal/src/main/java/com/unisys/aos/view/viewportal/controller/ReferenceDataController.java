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
import com.unisys.aos.view.viewentity.entity.flight.FmsFlight;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAircraftType;
import com.unisys.aos.view.viewportal.service.ReferenceDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 *
 * @author jianglushuang
 * @since 2020/10/29 1:30 下午
 */
@Controller
@Slf4j
@Api(value="Reference Data Controller",tags={"Reference Data Api"})
public class ReferenceDataController {

    private ReferenceDataService referenceDataService;

    @Autowired
    public ReferenceDataController(ReferenceDataService referenceDataService){
        this.referenceDataService = referenceDataService;
    }

    /**
     * Get reference data for that type
     * @param dataType
     * @return
     * @throws ApiException
     */
    @RequestMapping(value="/view/data/reference/{dataType}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getReferenceData", notes = "Get reference data")
    private Result<Collection<Serializable>> getReferenceData(
            @ApiParam(name = "dayaType", value = "Reference Data Type") @PathVariable("dataType")String dataType) throws ApiException {
        log.debug("Select All "+ dataType +"...");
        Collection<Serializable> msgs = referenceDataService.selectAll(dataType);
        if (null == msgs){
            return Result.failed();
        }
        return Result.success(msgs);
    }

    /**
     * Get reference data by IATA code for that type
     * @param dataType
     * @return
     * @throws ApiException
     */
    @RequestMapping(value="/view/data/reference/{dataType}/{code}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getReferenceDataByCode")
    private Result<Collection<Serializable>> getReferenceDataByCode(
            @ApiParam(name = "dayaType", value = "Reference Data Type") @PathVariable("dataType")String dataType,
            @ApiParam(name = "code", value = "IATA code") @PathVariable("code")String code) throws ApiException {
        log.debug("Select "+ dataType +" by IATA Code: " + code);
        Collection<Serializable> msgs = referenceDataService.selectByCode(dataType, code);
        if (null == msgs){
            return Result.failed();
        }
        return Result.success(msgs);
    }
}
