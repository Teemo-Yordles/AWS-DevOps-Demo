package com.unisys.aos.view.viewportal.service.impl;

import com.unisys.aos.view.viewentity.dao.SeasonFlightDAO;
import com.unisys.aos.view.viewentity.dto.SeasonFlightDTO;
import com.unisys.aos.view.viewportal.service.SeasonFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SeasonFlightServiceImpl implements SeasonFlightService {

    private final SeasonFlightDAO flightDAO;

    @Autowired
    public SeasonFlightServiceImpl(SeasonFlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Get all flight in seasonal schedule
     *
     * @return A collection of FmsFlight objects.
     */
    @Override
    public Collection<SeasonFlightDTO> selectAll() {
        return flightDAO.findAll();
    }
}
