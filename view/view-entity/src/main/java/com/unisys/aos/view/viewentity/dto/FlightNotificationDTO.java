/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * This class is the payload of notification sent
 * from server side to Web client side.
 *
 * @author LiuJ2
 * @since 2020/11/20 14:21
 */
@Data
public class FlightNotificationDTO implements Serializable {
    private static final long serialVersionUID = -6788235160934687395L;
    private NotificationType type;
    private Map<String, Object> updatedFields;
    private Long flightId;
    private Collection<FlightDTO> flights;
    private Date scheduleStart;
    private Date scheduleEnd;
    private Date createTime;

    public FlightNotificationDTO(){}

    /**
     * Constructor with type
     *
     * @param type - notification type
     */
    private FlightNotificationDTO(NotificationType type) {
        this.type = type;
        this.createTime = new Date();
    }

    /**
     * This static method is to build a ADD notification
     *
     * @param flight - Flight added
     * @return - a flight notification instance.
     */
    public static FlightNotificationDTO buildAddNotification(FlightDTO flight) {
        //sanity check
        if (null == flight) {
            return null;
        }

        FlightNotificationDTO notificationDTO = new FlightNotificationDTO(NotificationType.ADD);
        notificationDTO.setFlightId(flight.getId());
        Collection<FlightDTO> flightDTOS = new ArrayList<>();
        flightDTOS.add(flight);
        notificationDTO.setFlights(flightDTOS);
        return notificationDTO;
    }

    /**
     * This static method is to build a UPDATE notification
     *
     * @param flightId      - flight id of the updated flight
     * @param updatedFields - fields updated, multiple fields could be updated at the same time
     * @return - a flight notification instance.
     */
    public static FlightNotificationDTO buildUpdateNotification(Long flightId, Map<String, Object> updatedFields) {
        //sanity check
        if (null == flightId || null == updatedFields) {
            return null;
        }

        FlightNotificationDTO notificationDTO = new FlightNotificationDTO(NotificationType.UPDATE);
        notificationDTO.setFlightId(flightId);
        notificationDTO.setUpdatedFields(updatedFields);
        return notificationDTO;
    }
    public static FlightNotificationDTO buildUpdateNotification(Long flightId){
        return buildUpdateNotification(flightId, new HashMap<>());
    }
    public FlightNotificationDTO addUpdateFields(String key, Object value){
        updatedFields.put(key,value);
        return this;
    }

    /**
     * This static method is to build a DELETE notification
     *
     * @param flightId - flight id of the deleted flight
     * @return - a flight notification instance.
     */
    public static FlightNotificationDTO buildDeleteNotification(Long flightId) {
        //sanity check
        if (null == flightId) {
            return null;
        }

        FlightNotificationDTO notificationDTO = new FlightNotificationDTO(NotificationType.DELETE);
        notificationDTO.setFlightId(flightId);
        return notificationDTO;
    }

    /**
     * This static method is to build a SCHEDULE notification
     *
     * @param flights - flight collection of the schedule
     * @param scheduleStart - start time of the schedule
     * @param scheduleEnd - end time of the schedule
     * @return - a flight notification instance.
     */
    public static FlightNotificationDTO buildScheduleNotification(Collection<FlightDTO> flights, Date scheduleStart, Date scheduleEnd) {
        //sanity check
        if (null == flights
                || flights.isEmpty()
                || null == scheduleStart
                || null == scheduleEnd) {
            return null;
        }

        FlightNotificationDTO notificationDTO = new FlightNotificationDTO(NotificationType.SCHEDULE);
        notificationDTO.setFlights(flights);
        notificationDTO.setScheduleStart(scheduleStart);
        notificationDTO.setScheduleEnd(scheduleEnd);
        return notificationDTO;
    }
}
