package com.unisys.aos.view.viewportal.service.impl;

import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsFlight;
import com.unisys.aos.view.viewentity.mapper.flight.FmsFlightMapper;
import com.unisys.aos.view.viewportal.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightDAO flightDAO;

    @Autowired
    public FlightServiceImpl(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }
    /**
     * Get all flight in daily schedule
     *
     * @return A collection of FmsFlight objects.
     */
    @Override
    public Collection<FlightDTO> selectAll() {
        return flightDAO.findAll();
    }
}
