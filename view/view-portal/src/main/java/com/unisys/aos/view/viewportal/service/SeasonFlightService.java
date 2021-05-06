package com.unisys.aos.view.viewportal.service;

import com.unisys.aos.view.viewentity.dto.SeasonFlightDTO;

import java.util.Collection;

public interface SeasonFlightService {

    /**
     * Get all flight in seasonal schedule
     *
     * @return A list of FmsFlight objects.
     */
    Collection<SeasonFlightDTO> selectAll();
}
