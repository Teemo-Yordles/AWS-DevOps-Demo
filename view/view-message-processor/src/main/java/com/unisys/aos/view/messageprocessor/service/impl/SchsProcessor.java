/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.unisys.aos.view.common.exception.XmlFormatException;
import com.unisys.aos.view.common.util.ThreadLocalDateUtil;
import com.unisys.aos.view.messageprocessor.entity.ERUT;
import com.unisys.aos.view.messageprocessor.entity.FLTR;
import com.unisys.aos.view.messageprocessor.entity.ScheduleMessage;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.dao.SeasonFlightDAO;
import com.unisys.aos.view.viewentity.dto.SeasonFlightDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsSeasonEntireRoute;
import com.unisys.aos.view.viewentity.entity.flight.FmsSeasonFlight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This is the processor for SCHS messages
 *
 * @author Zhang Wenqiang
 * @since 2020/11/12 17:10
 */
@Service("SCHS")
@Slf4j
public class SchsProcessor implements Processor {
    private final SeasonFlightDAO seasonFlightDAO;

    @Autowired
    public SchsProcessor(SeasonFlightDAO seasonFlightDAO) {
        this.seasonFlightDAO = seasonFlightDAO;
    }

    /**
     * Get the millisecond of the 00:00:00 of the day
     *
     * @return - 00:00:00 of the day in millisecond
     */
    private static long getStartOfTheDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTimeInMillis();
    }

    /**
     * Process the message.
     *
     * @param scheduleMsg - Message to be processed
     */
    @Override
    public void process(String scheduleMsg) throws JsonProcessingException {
        // Parse the XML to object
        log.info("Start processing SCHS message");
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        ScheduleMessage parsedObj = objectMapper.readValue(scheduleMsg, ScheduleMessage.class);
        String subType = parsedObj.getMeta().getSTYP();
        log.debug("message subtype [{}]", subType);
        long startOfTheDay = getStartOfTheDay();
        String seasonName = parsedObj.getSchs().getSNAM();
        if (null == seasonName || seasonName.isEmpty()) {
            log.error("Failed to get season name.");
            return;
        }
        Date seasonStartDate;
        Date seasonEndDate;
        try {
            seasonStartDate = ThreadLocalDateUtil.parseXmlDate(parsedObj.getSchs().getSDAT());
        } catch (ParseException e) {
            log.error("Failed to get season flight schedule start time.");
            return;
        }
        try {
            seasonEndDate = ThreadLocalDateUtil.parseXmlDate(parsedObj.getSchs().getEDAT());
        } catch (ParseException e) {
            log.error("Failed to get season flight schedule end time.");
            return;
        }

        // DELETE if this seasonal flights schedule already exists.
        seasonFlightDAO.deleteBySeasonDate(seasonStartDate,seasonEndDate);
        Date earlierScheduleStartDate = seasonFlightDAO.getEarlierScheduleStartDate();

        List<FLTR> newFlightRecords = parsedObj.getSchs().getFltrs();

        // validate flight EDAT, if all flights before 00:00 today, ignore the list.
        if (seasonEndDate.getTime() < startOfTheDay) {
            log.warn("Seasonal flight schedule EDAT is earlier than today. Skip whole schedule.");
            return;
        }

        // loop through all XML records
        if (null != newFlightRecords) {
            log.info("Processing Season flight schedule, season name: [{}]", seasonName);
            log.info("season schedule start date: [{}]", seasonStartDate);
            log.info("season schedule end date: [{}]", seasonEndDate);
            boolean processSuccessful = true;
            for (FLTR record : newFlightRecords) {
                log.info("flight number [{}]", record.getFLNO());
                try {
                    Date eddt = ThreadLocalDateUtil.parseXmlDate(record.getEDDT());
                    if (eddt.getTime() < startOfTheDay) {
                        log.warn("Seasonal flight schedule EDDT is earlier than today. Skip this flight.");
                        continue;
                    }
                    SeasonFlightDTO newFlightDTO = mapFlightXmlToEntity(record, seasonName, seasonStartDate, seasonEndDate);
                    newFlightDTO.batchSetCreateTime(new Date());
                    seasonFlightDAO.insertInNewTx(newFlightDTO);
                } catch (ParseException | XmlFormatException e) {
                    processSuccessful = false;
                    log.error("Exception met while parsing FLTR record", e);
                }
            }
            if (processSuccessful) {
                if (null != seasonStartDate && null != earlierScheduleStartDate) {
                    // Delete all seasonal flight schedule before the latest two seasonal flight schedules in DB.
                    seasonFlightDAO.deleteEarlierSchedule(seasonStartDate, earlierScheduleStartDate);
                }
            }
        }
    }

    /**
     * Create a FmsSeasonFlight entity based on Season Flight schedule xml record.
     *
     * @param record          - one FLTR record parsed from XML
     * @param seasonName      - season name, obtained from SCHS record parsed from XML
     * @param seasonStartDate - season start date, obtained from SCHS record parsed from XML
     * @param seasonEndDate   - season end date, obtained from SCHS record parsed from XML
     * @return - a SeasonFlightDTO object
     */
    private SeasonFlightDTO mapFlightXmlToEntity(FLTR record, String seasonName, Date seasonStartDate, Date seasonEndDate) throws ParseException, XmlFormatException {
        // basic flight information part
        FmsSeasonFlight seasonFlight = new FmsSeasonFlight();
        long temp_id = -1L;
        seasonFlight.setId(temp_id);
        if (null == seasonName || seasonName.isEmpty()) {
            throw new XmlFormatException("SNAM tag in SCHS is empty");
        }
        seasonFlight.setSeasonName(seasonName);
        if (null == seasonStartDate) {
            throw new XmlFormatException("SDAT tag in SCHS is empty");
        }
        seasonFlight.setSeasonStartDate(seasonStartDate);
        if (null == seasonEndDate) {
            throw new XmlFormatException("EDAT tag in SCHS is empty");
        }
        seasonFlight.setSeasonEndDate(seasonEndDate);
        if (null == record.getALCD()) {
            throw new XmlFormatException("ALCD tag is empty");
        }
        seasonFlight.setAirlineCode(record.getALCD());
        if (null == record.getFLNO()) {
            throw new XmlFormatException("FLNO tag is empty");
        }
        seasonFlight.setFlightNumber(record.getFLNO());
        if (null == record.getMVIN()) {
            throw new XmlFormatException("MVIN tag is empty");
        }
        seasonFlight.setMovementIndicator(String.valueOf(record.getMVIN()));
        if (null == record.getSTDT()) {
            throw new XmlFormatException("STDT tag is empty");
        }
        seasonFlight.setSeasonScheduleStartDate(ThreadLocalDateUtil.parseXmlDate(record.getSTDT()));
        if (null == record.getEDDT()) {
            throw new XmlFormatException("EDDT tag is empty");
        }
        seasonFlight.setSeasonScheduleEndDate(ThreadLocalDateUtil.parseXmlDate(record.getEDDT()));
        if (null == record.getDYOP()) {
            throw new XmlFormatException("DYOP tag is empty");
        }
        seasonFlight.setWeekOperateDay(record.getDYOP());
        if (null == record.getSCHT()) {
            throw new XmlFormatException("SCHT tag is empty");
        } else if (!checkSCHTValue(record.getSCHT())) {
            throw new XmlFormatException("The value of the SCHT tag is invalid.");
        }
        seasonFlight.setFlightScheduleTime(parseXmlTime(record.getSCHT()));
        if (null == record.getFLTY()) {
            throw new XmlFormatException("FLTY tag is empty");
        }
        seasonFlight.setFlightType(String.valueOf(record.getFLTY()));
        if (null == record.getFLIN()) {
            throw new XmlFormatException("FLIN tag is empty");
        }
        seasonFlight.setFlightIndicator(String.valueOf(record.getFLIN()));
        if (null == record.getACFT()) {
            throw new XmlFormatException("ACFT tag is empty");
        }
        seasonFlight.setAircraftType(record.getACFT());
        seasonFlight.setRegistration(record.getRENO());
        seasonFlight.setLinkedAirlineCode(record.getTAOP());
        seasonFlight.setLinkedFlightNumber(record.getTAFL());
        seasonFlight.setTerminal(record.getTRML());
        seasonFlight.setMaxPax(record.getMAXP());
        seasonFlight.setMasterAirlineCode(record.getCSOP());
        seasonFlight.setMasterFlightNumber(record.getCSFT());

        // process ERUT tags
        List<ERUT> routeTags = record.getEruts();

        return new SeasonFlightDTO(seasonFlight, parseEntireRoutes(routeTags, seasonFlight.getId()));
    }

    /**
     * parse entire route tags to map to EntireRoute entites.
     *
     * @param routeTags - entire route tags
     * @param flightId  - flight ID
     * @return FmsEntireRoute list
     */
    private List<FmsSeasonEntireRoute> parseEntireRoutes(List<ERUT> routeTags, Long flightId) {
        if (null == routeTags || routeTags.isEmpty() || null == flightId) {
            return null;
        }

        List<FmsSeasonEntireRoute> routes = new ArrayList<>();
        for (ERUT routeTag : routeTags) {
            FmsSeasonEntireRoute route = new FmsSeasonEntireRoute();
            route.setFlightId(flightId);
            route.setAirportCode(routeTag.getAPCD());
            route.setSequenceNumber(routeTag.getRTNO());
            if (null != routeTag.getSCAT()) {
                route.setArrivalTime(parseXmlTime(routeTag.getSCAT()));
            }
            if (null != routeTag.getSADC()) {
                route.setArrivalDayChange(routeTag.getSADC());
            }
            if (null != routeTag.getSCDT()) {
                route.setDepartureTime(parseXmlTime(routeTag.getSCDT()));
            }
            if (null != routeTag.getSDDC()) {
                route.setDepartureDayChange(routeTag.getSDDC());
            }
            routes.add(route);
        }
        return routes;
    }

    /**
     * Check whether the string value of SCHT is a correct 24-hour format time.
     *
     * @param scht the string value of SCHT
     * @return return true if it is a correct 24-hour format time, otherwise return false.
     */
    private boolean checkSCHTValue(String scht) {
        int scht_length = 4;
        if (null == scht || scht.length() != scht_length) {
            return false;
        }
        String regex = "[0-6][0-9][0-6][0-9]";
        return Pattern.matches(regex, scht);
    }

    /**
     * Parse the 24-hour format time string, HHMM -> HH:MM
     *
     * @param xmlTime HHMM
     * @return HH:MM or null(if parsing failed).
     */
    private String parseXmlTime(String xmlTime) {
        if (checkSCHTValue(xmlTime)) {
            return xmlTime.substring(0, 2) + ":" + xmlTime.substring(2, 4);
        }
        return null;
    }
}

