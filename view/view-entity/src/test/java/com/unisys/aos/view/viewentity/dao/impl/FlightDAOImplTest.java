package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsEntireRoute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LiuJ2
 * @since 2020/10/28 11:39
 */
class FlightDAOImplTest {
    private static FlightDTO flight1;

    @BeforeAll
    public static void init() {
        flight1 = new FlightDTO();
        List<FmsEntireRoute> routes = new ArrayList<>();
        FmsEntireRoute route1 = new FmsEntireRoute();
        route1.setFlightId(1L);
        route1.setDepartureTime(new Date());
        route1.setSequenceNumber((byte) 2);
        route1.setArrivalTime(new Date());
        route1.setAirportCode("CTU");
        FmsEntireRoute route2 = new FmsEntireRoute();
        route2.setFlightId(1L);
        route2.setDepartureTime(new Date());
        route2.setSequenceNumber((byte) 1);
        route2.setArrivalTime(new Date());
        route2.setAirportCode("PEK");
        FmsEntireRoute route3 = new FmsEntireRoute();
        route3.setFlightId(1L);
        route3.setDepartureTime(new Date());
        route3.setSequenceNumber((byte) 3);
        route3.setArrivalTime(new Date());
        route3.setAirportCode("SHA");
        routes.add(route1);
        routes.add(route2);
        routes.add(route3);
        flight1.setEntireRoutes(routes);
    }
}