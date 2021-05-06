package com.unisys.aos.view.viewportal.service;

import com.unisys.aos.view.viewentity.dto.FlightDTO;

import java.util.Collection;
import java.util.List;

public interface FlightService {

    /**
     * Get all flight in daily schedule
     * @return A list of FmsFlight objects.
     */
    Collection<FlightDTO> selectAll();
}
