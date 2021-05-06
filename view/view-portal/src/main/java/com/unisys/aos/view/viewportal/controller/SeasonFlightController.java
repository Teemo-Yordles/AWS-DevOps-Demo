package com.unisys.aos.view.viewportal.controller;

import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.viewentity.dto.SeasonFlightDTO;
import com.unisys.aos.view.viewportal.service.SeasonFlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@Slf4j
public class SeasonFlightController {
    private final SeasonFlightService flightService;

    @Autowired
    public SeasonFlightController(SeasonFlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(value = "/view/seasonal-flights", method = RequestMethod.GET)
    @ResponseBody
    private Result<Collection<SeasonFlightDTO>> getFlights() {
        log.debug("Getting flights...");
        Collection<SeasonFlightDTO> flights = flightService.selectAll();
        return Result.success(flights);
    }
}
