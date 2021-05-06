/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewportal.service.impl;

import com.unisys.aos.view.viewentity.dao.RdmsAircraftTypeDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAircraftType;
import com.unisys.aos.view.viewportal.service.AircraftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Aircraft type service
 *
 * @author LiuJ2
 * @since 2020/10/13 10:36
 */
@Service
public class AircraftTypeServiceImpl implements AircraftTypeService {
    private final RdmsAircraftTypeDAO rdmsAircraftTypeDAO;

    /**
     * Constructor with injected bean
     * @param rdmsAircraftTypeDAO - injected bean
     */
    @Autowired
    public AircraftTypeServiceImpl(RdmsAircraftTypeDAO rdmsAircraftTypeDAO) {
        this.rdmsAircraftTypeDAO = rdmsAircraftTypeDAO;
    }


    /**
     * Get all aircraft type reference data
     *
     * @return A list of RdmsAircraftType objects.
     */
    @Override
    public Collection<RdmsAircraftType> selectAll() {
        return rdmsAircraftTypeDAO.findAll();
    }
}
