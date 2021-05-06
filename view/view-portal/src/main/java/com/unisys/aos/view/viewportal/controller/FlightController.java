package com.unisys.aos.view.viewportal.controller;

import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewportal.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@Slf4j
public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(value="/view/flights", method = RequestMethod.GET)
    @ResponseBody
    private Result<Collection<FlightDTO>> getFlights() {
        log.debug("Getting flights...");
        Collection<FlightDTO> flights = flightService.selectAll();
        return Result.success(flights);
    }
}
