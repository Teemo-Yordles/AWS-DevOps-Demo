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
import com.unisys.aos.view.common.util.ObjectUtil;
import com.unisys.aos.view.common.util.ThreadLocalDateUtil;
import com.unisys.aos.view.messageprocessor.entity.*;
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsFlight;
import com.unisys.aos.view.viewentity.entity.flight.FmsStand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * This is the processor for SCHD/ADFT/RQFD messages
 *
 * @author LiuJ2
 * @since 2020/10/1 16:32
 */
@Service("SCHD")
@Slf4j
public class SchdProcessor extends AbsProcessor {
    private static final Character CLOSE_PORT_TAG = 'C';
    private static final Character OPEN_PORT_TAG = 'O';
    private final FlightDAO flightDAO;

    @Autowired
    public SchdProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
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
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Process the message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        log.info("Start processing SCHD message");
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        ScheduleMessage parsedObj = objectMapper.readValue(msg, ScheduleMessage.class);
        String subType = parsedObj.getMeta().getSTYP();
        log.debug("message subtype [{}]", subType);
        List<FLTR> flightRecords = parsedObj.getSchd().getFltrs();

        // query DB for current entities
        // refresh reference data
        flightDAO.loadReferenceDataCache();
        Collection<FlightDTO> currentRecords = flightDAO.findAll();
        ConcurrentMap<Long, FlightDTO> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(FlightDTO::getId, (p) -> p));

        // get earlist SODT and latest SODT for DNLD/RESP
        // and filter out flights in range.
        Date earlistDate = null;
        Date latestDate = null;
        long startOfTheDay = getStartOfTheDay();
        Collection<FlightDTO> flightsInRange = new ArrayList<>();
        boolean notAdft = Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType);
        if (notAdft) {
            for (FLTR record : flightRecords) {
                try {
                    Date sodt = ThreadLocalDateUtil.parseXmlDate(record.getSODT());
                    if (null == earlistDate || earlistDate.after(sodt)) {
                        earlistDate = sodt;
                    }
                    if (null == latestDate || latestDate.before(sodt)) {
                        latestDate = sodt;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            log.info("schedule start date: [{}]", earlistDate);
            log.info("schedule end date: [{}]", latestDate);
            if (null == earlistDate || null == latestDate) {
                log.error("Failed to get earlist and latest SODT");
                return;
            }

            for (FlightDTO flightDTO : currentRecords) {
                if (earlistDate.compareTo(flightDTO.getFlight().getSto()) <= 0 &&
                        latestDate.compareTo(flightDTO.getFlight().getSto()) >= 0) {
                    flightsInRange.add(flightDTO);
                }
            }

            // validate flight SODT, if all flights before 00:00 today, ignore the list.
            if (latestDate.getTime() < startOfTheDay) {
                log.warn("All flights are earlier than today. Skip whole schedule.");
                return;
            }
        }

        // loop through all XML records
        // if flightId already exists then UPDATE otherwise INSERT
        for (FLTR record : flightRecords) {
            log.debug("processing flight record AODBID [{}]", record.getFLID());

            // create empty domain flight entity and map properties
            try {
                // check flight SODT
                Date sodt = ThreadLocalDateUtil.parseXmlDate(record.getSODT());
                if (sodt.getTime() < startOfTheDay) {
                    log.warn("Flight SODT is earlier than today. Skip this flight.");
                    continue;
                }

                FlightDTO entity = mapFlightXmlToEntity(record);
                FlightDTO currentRecord = recordMap.get(record.getFLID());
                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != currentRecord) {
                        flightDAO.deleteInNewTx(currentRecord.getId());
                        FlightNotificationDTO dto = FlightNotificationDTO.buildDeleteNotification(currentRecord.getId());
                        publishToRedis(dto);
                    }
                } else {
                    if (null == currentRecord) {
                        // this is a new flight, then create new record
                        entity.batchSetCreateTime(new Date());
                        entity = flightDAO.insertInNewTx(entity);
                        // update the map to show latest status
                        recordMap.put(entity.getId(), entity);

                        // When adding a flight, it is necessary to maintain the "codeshare" field of master flight if this is a code share flight.
                        FmsFlight flight = entity.getFlight();
                        if (null != flight) {
                            Long masterAodbId = flight.getMasterAodbId();
                            if (null != masterAodbId) {
                                if (notAdft) {
                                    FlightDTO masterFlightDTO = getMasterFlightDTO(recordMap, masterAodbId);
                                    if (null != masterFlightDTO) {
                                        String currentFlightNumber = flight.getFlightNumber();
                                        String codeShareFlightStr = masterFlightDTO.getCodeShareFlightList();
                                        if (null == codeShareFlightStr || codeShareFlightStr.isEmpty()) {
                                            codeShareFlightStr = currentFlightNumber;
                                        } else {
                                            if (!codeShareFlightStr.contains(currentFlightNumber)) {
                                                StringBuilder codeShareFlightStrBuilder = new StringBuilder(codeShareFlightStr);
                                                codeShareFlightStr = codeShareFlightStrBuilder.append(", ").append(currentFlightNumber).toString();
                                            }
                                        }
                                        updateCodeShareFlightStr(masterFlightDTO, codeShareFlightStr);
                                    }
                                } else {
                                    FlightDTO masterFlightDTO = flightDAO.findByAodbId(masterAodbId);
                                    if (null != masterFlightDTO) {
                                        List<String> codeShareFlightNumberList = flightDAO.getCodeShareFlightNumberList(masterFlightDTO.getFlight());
                                        String flightNumberString = null;
                                        if (null != codeShareFlightNumberList && !codeShareFlightNumberList.isEmpty()) {
                                            flightNumberString = String.join(", ", codeShareFlightNumberList);
                                        }
                                        FlightDTO newMasterFlightDTO = (FlightDTO) ObjectUtil.deepClone(masterFlightDTO);
                                        if (null != newMasterFlightDTO && null != newMasterFlightDTO.getFlight()) {
                                            newMasterFlightDTO.setCodeShareFlightList(flightNumberString);
                                            flightDAO.updateInNewTx(newMasterFlightDTO, masterFlightDTO);
                                            FlightNotificationDTO flightNotificationDTO = FlightNotificationDTO.buildUpdateNotification(masterAodbId).addUpdateFields("codeShareFlightList", flightNumberString);
                                            publishToRedis(flightNotificationDTO);
                                        } else {
                                            log.error("Failed to clone the old master FlightDTO object.");
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        // maintain records' create time/update time/ids
                        entity.batchSetCreateTime(currentRecord.getFlight().getCreateTime());
                        entity.batchSetUpdateTime(new Date());
                        entity.batchSetRecordId(currentRecord);
                        // not new then update record. If same ignore.
                        if (!entity.equals(currentRecord)) {
                            flightDAO.updateInNewTx(entity, currentRecord);

                            //maintain codeShare.
                            Long newMasterFlightAodbId = entity.getFlight().getMasterAodbId();
                            Long oldMasterFlightAodbId = currentRecord.getFlight().getMasterAodbId();
                            if (null == newMasterFlightAodbId) {
                                if (null != oldMasterFlightAodbId) {
                                    String currentFlightNumber = currentRecord.getFlight().getFlightNumber();
                                    removeUnusedFlightNumber(recordMap, oldMasterFlightAodbId, currentFlightNumber);
                                }
                            } else {
                                if (null == oldMasterFlightAodbId) {
                                    FlightDTO masterFlightDTO = getMasterFlightDTO(recordMap, newMasterFlightAodbId);
                                    if (null != masterFlightDTO) {
                                        String currentFlightNumber = currentRecord.getFlight().getFlightNumber();
                                        String codeShareFlightStr = masterFlightDTO.getCodeShareFlightList();
                                        if (null == codeShareFlightStr || codeShareFlightStr.isEmpty()) {
                                            codeShareFlightStr = currentFlightNumber;
                                        } else {
                                            if (!codeShareFlightStr.contains(currentFlightNumber)) {
                                                StringBuilder codeShareFlightStrBuilder = new StringBuilder(codeShareFlightStr);
                                                codeShareFlightStr = codeShareFlightStrBuilder.append(", ").append(currentFlightNumber).toString();
                                            }
                                        }
                                        updateCodeShareFlightStr(masterFlightDTO, codeShareFlightStr);
                                        // update the map to show latest status
                                        recordMap.put(masterFlightDTO.getId(), masterFlightDTO);
                                    }
                                } else {
                                    if (!newMasterFlightAodbId.equals(oldMasterFlightAodbId)) {
                                        String currentFlightNumber = currentRecord.getFlight().getFlightNumber();
                                        FlightDTO newMasterFlightDTO = getMasterFlightDTO(recordMap, newMasterFlightAodbId);
                                        if (null != newMasterFlightDTO) {
                                            String codeShareFlightStr = newMasterFlightDTO.getCodeShareFlightList();
                                            if (null == codeShareFlightStr || codeShareFlightStr.isEmpty()) {
                                                codeShareFlightStr = currentFlightNumber;
                                            } else {
                                                if (!codeShareFlightStr.contains(currentFlightNumber)) {
                                                    StringBuilder codeShareFlightStrBuilder = new StringBuilder(codeShareFlightStr);
                                                    codeShareFlightStr = codeShareFlightStrBuilder.append(", ").append(currentFlightNumber).toString();
                                                }
                                            }
                                            updateCodeShareFlightStr(newMasterFlightDTO, codeShareFlightStr);
                                            // update the map to show latest status
                                            recordMap.put(newMasterFlightDTO.getId(), newMasterFlightDTO);
                                        }

                                        removeUnusedFlightNumber(recordMap, oldMasterFlightAodbId, currentFlightNumber);
                                    }
                                }
                            }
                            flightDAO.updateCacheRecord(entity);
                            // update the map to show latest status
                            recordMap.replace(entity.getId(), entity);
                        } else {
                            log.info("Identical entity found for flight ID [{}] so skipped processing it", entity.getId());
                        }
                        flightsInRange.remove(currentRecord);
                    }
                }
            } catch (ParseException | XmlFormatException e) {
                log.error("Exception met while parsing FLTR record", e);
            }
        }
        //Notify
        Collection<FlightDTO> flights = new LinkedList<FlightDTO>();
        for(FLTR record  : flightRecords){
            flights.add(recordMap.get(record.getFLID()));
        }
        FlightNotificationDTO dto =
                FlightNotificationDTO.buildScheduleNotification(flights, earlistDate, latestDate);
        publishToRedis(dto);
        // Delete the extra record not in XML but in DB
        // if not processing ADFT
        if (notAdft) {
            log.debug("Deleting extra flight not in SCHD, total [{}] records", flightsInRange.size());
            for (FlightDTO record : flightsInRange) {
                flightDAO.deleteInNewTx(record.getId());
                log.debug("Deleted flight id[{}] ...", record.getId());
                // If the deleted flight is a code shared flight, need to maintain the 'codeShare' field in its master flight.
                FmsFlight flight = record.getFlight();
                if (null != flight) {
                    String currentFlightNumber = flight.getFlightNumber();
                    Long masterFlightAodbId = flight.getMasterAodbId();
                    if (null != masterFlightAodbId) {
                        log.debug("This flight[{}] is code shared flight, so maintain the 'codeShare' field in its master flight, the master flight aodb id = {}", record.getId(), masterFlightAodbId);
                        removeUnusedFlightNumber(recordMap, masterFlightAodbId, currentFlightNumber);
                    }
                }
            }
        }
    }

    /***
     * Update codeShareFlightList in master flight.
     * @param masterFlightDTO The original master FlightDTO.
     * @param codeShareFlightStr updated codeShareFlightList string.
     */
    private void updateCodeShareFlightStr(FlightDTO masterFlightDTO, String codeShareFlightStr) {
        if (null == masterFlightDTO) {
            log.error("Failed to clone the master FlightDTO object, the original master FlightDTO is null.");
            return;
        }
        FlightDTO newMasterFlightDTO = (FlightDTO) ObjectUtil.deepClone(masterFlightDTO);
        if (null != newMasterFlightDTO && null != newMasterFlightDTO.getFlight()) {
            newMasterFlightDTO.setCodeShareFlightList(codeShareFlightStr);
            flightDAO.updateInNewTx(newMasterFlightDTO, masterFlightDTO);
        } else {
            log.error("Failed to clone the master FlightDTO object. Master FlightDTO aodb id = [{}]", masterFlightDTO.getId());
        }
    }

    /***
     * Get master FlightDTO from cache or DB according to master flight aodb id.
     * @param recordMap Cached.
     * @param masterFlightAodbId Master flight aodb id.
     * @return Return master FlightDTO entity if it succeeds, otherwise returns null.
     */
    private FlightDTO getMasterFlightDTO(ConcurrentMap<Long, FlightDTO> recordMap, Long masterFlightAodbId) {
        if (null == recordMap) {
            log.error("Failed to get the master FlightDTO object, the recordMap is null.");
            return null;
        }
        if (null == masterFlightAodbId) {
            log.error("Failed to get the master FlightDTO object, the master flight aodb id is null.");
            return null;
        }
        FlightDTO masterFlightDTO = recordMap.get(masterFlightAodbId);
        if (null == masterFlightDTO) {
            masterFlightDTO = flightDAO.findByAodbId(masterFlightAodbId);
        }
        return masterFlightDTO;
    }

    /***
     * Maintain codeShare of master FlightDTO, delete the unused flight number. And maintain cache.
     * @param recordMap Cached.
     * @param masterFlightAodbId Master flight aodb id.
     * @param unusedFlightNumber Unused flight number.
     */
    private void removeUnusedFlightNumber(ConcurrentMap<Long, FlightDTO> recordMap, Long masterFlightAodbId, String unusedFlightNumber) {
        if (null == recordMap) {
            log.error("Failed to maintain the codeShare field of master FlightDTO object, the recordMap is null.");
            return;
        }
        if (null == masterFlightAodbId) {
            log.error("Failed to maintain the codeShare field of master FlightDTO object, the master flight aodb id is null.");
            return;
        }
        if (null == unusedFlightNumber) {
            log.error("Failed to maintain the codeShare field of master FlightDTO object, the unused flight number is null.");
            return;
        }
        if (unusedFlightNumber.isEmpty()) {
            log.error("Failed to maintain the codeShare field of master FlightDTO object, the unused flight number is empty.");
            return;
        }
        FlightDTO masterFlightDTO = getMasterFlightDTO(recordMap, masterFlightAodbId);
        if (null != masterFlightDTO) {
            String codeShareFlightStr = masterFlightDTO.getCodeShareFlightList();
            if (null != codeShareFlightStr && !codeShareFlightStr.isEmpty()) {
                if (codeShareFlightStr.contains(unusedFlightNumber)) {
                    codeShareFlightStr = codeShareFlightStr.replaceAll("(, )*" + unusedFlightNumber, "");
                    if (codeShareFlightStr.isEmpty()) {
                        codeShareFlightStr = null;
                    }
                }
            }
            updateCodeShareFlightStr(masterFlightDTO, codeShareFlightStr);
        }
    }

    /**
     * Create a FlightDTO entity based on Flight xml record.
     *
     * @param record - one FLTR record parsed from XML
     * @return - a flight object
     */
    private FlightDTO mapFlightXmlToEntity(FLTR record) throws ParseException, XmlFormatException {
        // basic flight information part
        FmsFlight fmsFlight = new FmsFlight();
        if (null == record.getACFT()) {
            throw new XmlFormatException("ACFT tag is empty");
        }
        fmsFlight.setAircraftType(record.getACFT());
        if (null == record.getALCD()) {
            throw new XmlFormatException("ALCD tag is empty");
        }
        fmsFlight.setAirlineCode(record.getALCD());
        fmsFlight.setAirlineSubcompanyCode(record.getALSC());
        if (null == record.getFLID()) {
            throw new XmlFormatException("FLID tag is empty");
        }
        fmsFlight.setAodbId(record.getFLID());
        if (null != record.getAPPT()) {
            fmsFlight.setApprovedTime(ThreadLocalDateUtil.parseXmlDate(record.getAPPT()));
        }
        if (null != record.getACTT()) {
            fmsFlight.setAto(ThreadLocalDateUtil.parseXmlDate(record.getACTT()));
        }
        if (null != record.getBOTM()) {
            fmsFlight.setBoardingOpen(ThreadLocalDateUtil.parseXmlDate(record.getBOTM()));
        }
        //TODO: process COTM
        // fmsFlight.setCalOffblock();
        //TODO: process CTOT
        // fmsFlight.setCalTakeoff();
        if (null != record.getCNCL()) {
            fmsFlight.setCancel(ThreadLocalDateUtil.parseXmlDate(record.getCNCL()));
        }
        fmsFlight.setCurrentStand(record.getSTND());
        if (null != record.getFdiv()) {
            fmsFlight.setDivAirport(record.getFdiv().getDDES());
            fmsFlight.setDivDirection(record.getFdiv().getDDIR());
            fmsFlight.setDivReason(record.getFdiv().getValue());
        }
        if (null != record.getEGST()) {
            fmsFlight.setEngineStart(ThreadLocalDateUtil.parseXmlDate(record.getEGST()));
        }
        if (null != record.getEGSR()) {
            fmsFlight.setEngineStartRequest(ThreadLocalDateUtil.parseXmlDate(record.getEGSR()));
        }
        if (null != record.getESTT()) {
            fmsFlight.setEto(ThreadLocalDateUtil.parseXmlDate(record.getESTT()));
        }
        fmsFlight.setExternalStatusCode(record.getEXSC());
        fmsFlight.setExternalStatusComment(record.getEXSR());
        fmsFlight.setFieldAgent(record.getFHAG());
        fmsFlight.setPaxAgent(record.getPHAG());
        fmsFlight.setMaintenanceAgent(record.getMHAG());
        if (null != record.getFINT()) {
            fmsFlight.setFinalTime(ThreadLocalDateUtil.parseXmlDate(record.getFINT()));
        }
        if (null == record.getFLIN()) {
            throw new XmlFormatException("FLIN tag is empty");
        }
        fmsFlight.setFlightIndicator(String.valueOf(record.getFLIN()));
        if (null == record.getFLNO()) {
            throw new XmlFormatException("FLNO tag is empty");
        }
        fmsFlight.setFlightNumber(record.getFLNO());
        if (null == record.getFLTY()) {
            throw new XmlFormatException("FLTY tag is empty");
        }
        fmsFlight.setFlightType(String.valueOf(record.getFLTY()));
        if (null != record.getFlab()) {
            fmsFlight.setLandingAbortIndicator(String.valueOf(record.getFlab().getARES()));
            fmsFlight.setLandingAbortReason(String.valueOf(record.getFlab().getValue()));
        }
        if (null != record.getLACL()) {
            fmsFlight.setLastCall(ThreadLocalDateUtil.parseXmlDate(record.getLACL()));
        }
        fmsFlight.setLinkedAirlineCode(record.getTAOP());
        fmsFlight.setLinkedAodbId(record.getTAID());
        fmsFlight.setLinkedFlightNumber(record.getTAFL());
        //TODO: get linked flight id and add to this flight
        // fmsFlight.setLinkedId();
        fmsFlight.setLocalBagCount(record.getLBNO());
        fmsFlight.setLocalBagWeight(record.getLBWT());
        fmsFlight.setMasterAirlineCode(record.getCSOP());
        fmsFlight.setMasterAodbId(record.getMAID());
        fmsFlight.setMasterFlightNumber(record.getCSFT());
        FmsFlight masterFlight = flightDAO.getByAodbId(record.getMAID());
        if (null != masterFlight) {
            Long masterId = masterFlight.getId();
            fmsFlight.setMasterId(masterId);
        }
        fmsFlight.setMaxPax(record.getMAXP());
        if (null == record.getMVIN()) {
            throw new XmlFormatException("MVIN tag is empty");
        }
        fmsFlight.setMovementIndicator(String.valueOf(record.getMVIN()));
        if (null != record.getNAAT()) {
            fmsFlight.setNaat(ThreadLocalDateUtil.parseXmlDate(record.getNAAT()));
        }
        if (null != record.getNEAT()) {
            fmsFlight.setNeat(ThreadLocalDateUtil.parseXmlDate(record.getNEAT()));
        }
        if (null != record.getPADT()) {
            fmsFlight.setPadt(ThreadLocalDateUtil.parseXmlDate(record.getPADT()));
        }
        fmsFlight.setPassengerCount(record.getPAXC());
        if (null != record.getPEDT()) {
            fmsFlight.setPedt(ThreadLocalDateUtil.parseXmlDate(record.getPEDT()));
        }
        fmsFlight.setRegistration(record.getRENO());
        fmsFlight.setRemarks(record.getREMC());
        if (null != record.getFret()) {
            fmsFlight.setRetRsn(record.getFret().getValue());
            fmsFlight.setRetTyp(String.valueOf(record.getFret().getREID()));
        }
        //TODO: add runway tag processing
        // fmsFlight.setRwyCode();
        fmsFlight.setStatusCode(record.getFTSS());
        if (null == record.getSODT()) {
            throw new XmlFormatException("SODT tag is empty");
        }
        fmsFlight.setSto(ThreadLocalDateUtil.parseXmlDate(record.getSODT()));
        fmsFlight.setTerminal(record.getTRML());
        fmsFlight.setVipCount(record.getVIPP());
        fmsFlight.setVipFlag(record.getVIPR());
        FlightDTO flightDTO = new FlightDTO(fmsFlight);

        // process PORT tags
        List<PORT> ports = record.getPorts();
        if (null == ports || ports.isEmpty()) {
            log.info("PORT tag is empty.");
        } else {
            for (PORT port : ports) {
                Character portIdentity = port.getPOID();
                String portTime = port.getPOTM();
                if (null != portIdentity) {
                    if (CLOSE_PORT_TAG.equals(portIdentity)) {
                        if (null != portTime && !portTime.isEmpty()) {
                            fmsFlight.setPortCloseTime(ThreadLocalDateUtil.parseXmlDate(portTime));
                        }
                    }
                    if (OPEN_PORT_TAG.equals(portIdentity)) {
                        if (null != portTime && !portTime.isEmpty()) {
                            fmsFlight.setPortOpenTime(ThreadLocalDateUtil.parseXmlDate(portTime));
                        }
                    }
                }
            }
        }

        // process COBT tag
        String calOffBlockTimeStr = record.getCOBT();
        fmsFlight.setCalOffblock(ThreadLocalDateUtil.parseXmlDate(calOffBlockTimeStr));

        // process TOBT tag
        String targetOffBlockTimeStr = record.getTOBT();
        fmsFlight.setTargetOffblock(ThreadLocalDateUtil.parseXmlDate(targetOffBlockTimeStr));

        // process RUNW tag
        String logicalRunway = record.getRUNW();
        fmsFlight.setLogicalRunway(logicalRunway);

        // process EOBT tag
        String estimateOffBlockTimeStr = record.getEOBT();
        fmsFlight.setEstimatedOffblock(ThreadLocalDateUtil.parseXmlDate(estimateOffBlockTimeStr));

        // process IEST tag
        String internalEstimateTimeStr = record.getIEST();
        fmsFlight.setInternalEstimated(ThreadLocalDateUtil.parseXmlDate(internalEstimateTimeStr));

        // process ERUT tags
        List<ERUT> routeTags = record.getEruts();
        flightDTO.setEntireRoutes(parseEntireRoutes(routeTags, fmsFlight.getAodbId()));

        // process ABTM tags
        List<ABTM> abtmTags = record.getAbtms();
        flightDTO.setAirbridges(parseAirbridges(abtmTags, fmsFlight.getAodbId()));

        // process CLDT tags
        List<CLDT> cldtTags = record.getCldts();
        flightDTO.setCarousels(parseCarousels(cldtTags, fmsFlight.getAodbId()));

        // process CKDT tags
        List<CKDT> ckdtTags = record.getCkdts();
        flightDTO.setCheckinDesks(parseCounters(ckdtTags, fmsFlight.getAodbId()));

        // process CHOT tags
        List<CHOT> chotTags = record.getChots();
        flightDTO.setChocks(parseChocks(chotTags, fmsFlight.getAodbId()));

        // process CHUT tags
        List<CHDT> chdtTags = record.getChdts();
        flightDTO.setChutes(parseChutes(chdtTags, fmsFlight.getAodbId()));

        // process DELY tags
        List<DELY> delyTags = record.getDelys();
        flightDTO.setDelays(parseDelays(delyTags, fmsFlight.getAodbId()));

        // process GTDT tags
        List<GTDT> gateTags = record.getGtdts();
        flightDTO.setGates(parseGates(gateTags, fmsFlight.getAodbId()));

        // process SRVT tags
        List<SRVT> srvtTags = record.getSrvts();
        flightDTO.setServices(parseServices(srvtTags, fmsFlight.getAodbId()));

        // process PSDT tags
        List<PSDT> psdtTags = record.getPsdts();
        List<FmsStand> standList = parseStands(psdtTags, fmsFlight.getAodbId());

        // process VIPF tags
        List<VIPF> vipfTags = record.getVipfs();
        flightDTO.setVips(parseVips(vipfTags, fmsFlight.getAodbId()));

        // process TIPT tag
        TIPT tractorData = record.getTipt();
        if (null == tractorData) {
            log.info("TIPT tag is empty.");
        } else {
            String againstStand = tractorData.getTRST();
            String tractorInplaceTimeStr = tractorData.getTRTM();
            if (null != againstStand) {
                if (null != standList) {
                    boolean containStand = false;
                    for (FmsStand fmsStand : standList) {
                        String standCode = fmsStand.getStandCode();
                        if (againstStand.equals(standCode)) {
                            containStand = true;
                            fmsStand.setTractorTime(ThreadLocalDateUtil.parseXmlDate(tractorInplaceTimeStr));
                        }
                    }
                    if (!containStand) {
                        log.error("This stand does not exist in the flight plan stand data, flight id = {}, plan stand list = {}, against with tractor stand = {}", record.getFLID(), standList, againstStand);
                    }
                }
            }
        }
        // process PSDT tags
        flightDTO.setStands(standList);


        return flightDTO;
    }
}

