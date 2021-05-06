/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.unisys.aos.view.common.util.ThreadLocalDateUtil;
import com.unisys.aos.view.messageprocessor.entity.*;
import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import com.unisys.aos.view.viewentity.entity.flight.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jianglushuang
 * @since 2020/11/26 8:28 下午
 */
@Slf4j
public abstract class AbsProcessor implements Processor{

    protected static final String TOPIC_FLOP = "FLOP";

    @Autowired
    RedisUtil redisUtil;

    /**
     * publish message to websocket server to notify user;
     * @param msg - message to be published
     */
    protected void publishToRedis(FlightNotificationDTO msg){
        redisUtil.publish(TOPIC_FLOP, msg);
    }

    /**
     *  Parse the XML to FLOP object
     * @param msg - message to be published
     */
    protected List<FLOP.FlopData> parserXml(String msg) throws JsonProcessingException {
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        FLOP parsedObj = objectMapper.readValue(msg, FLOP.class);
        return parsedObj.getFlops();
    }



    /**
     * parse entire route tags to map to EntireRoute entites.
     * @param routeTags - entire route tags
     * @param aodbId - flight AODB ID
     * @return FmsEntireRoute list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsEntireRoute> parseEntireRoutes(List<ERUT> routeTags, Long aodbId) throws ParseException {
        if (null == routeTags || routeTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsEntireRoute> routes = new ArrayList<>();
        for (ERUT routeTag : routeTags) {
            FmsEntireRoute route = new FmsEntireRoute();
            route.setFlightId(aodbId);
            route.setAirportCode(routeTag.getAPCD());
            route.setSequenceNumber(routeTag.getRTNO());
            if (null != routeTag.getSCAT()) {
                route.setArrivalTime(ThreadLocalDateUtil.parseXmlDate(routeTag.getSCAT()));
            }
            if (null != routeTag.getSCDT()) {
                route.setDepartureTime(ThreadLocalDateUtil.parseXmlDate(routeTag.getSCDT()));
            }
            routes.add(route);
        }
        return routes;
    }

    /**
     * parse airbridge time tags to map to FmsAirbridge entites.
     * @param airbridgeTags - airbridge time tags
     * @param aodbId - flight AODB ID
     * @return FmsAirbridge entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsAirbridge> parseAirbridges(List<ABTM> airbridgeTags, Long aodbId) throws ParseException {
        if (null == airbridgeTags || airbridgeTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsAirbridge> airbridgeUsages = new ArrayList<>();
        for (ABTM tag : airbridgeTags) {
            FmsAirbridge airbridge = new FmsAirbridge();
            airbridge.setFlightId(aodbId);
            airbridge.setAirbridgeCode(tag.getABDG());
            airbridge.setSequenceNumber(tag.getASNO());
            if (null != tag.getAOTM()) {
                airbridge.setOperationTime(ThreadLocalDateUtil.parseXmlDate(tag.getAOTM()));
            }
            airbridge.setOperation(String.valueOf(tag.getABOP()));
            airbridgeUsages.add(airbridge);
        }
        return airbridgeUsages;
    }

    /**
     * parse carousel allocation tags to map to FmsCarousel entites.
     * @param carouselTags - carousel information tags
     * @param aodbId - flight AODB ID
     * @return FmsCarousel entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsCarousel> parseCarousels(List<CLDT> carouselTags, Long aodbId) throws ParseException {
        if (null == carouselTags || carouselTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsCarousel> carousels = new ArrayList<>();
        for (CLDT tag : carouselTags) {
            FmsCarousel carousel = new FmsCarousel();
            carousel.setFlightId(aodbId);
            carousel.setCarouselCode(tag.getBELT());
            carousel.setSequenceNumber(tag.getCLNO());
            if (null != tag.getFBAG()) {
                carousel.setFirstBagTime(ThreadLocalDateUtil.parseXmlDate(tag.getFBAG()));
            }
            if (null != tag.getLBAG()) {
                carousel.setLastBagTime(ThreadLocalDateUtil.parseXmlDate(tag.getLBAG()));
            }
            if (null != tag.getPCOT()) {
                carousel.setPlannedStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getPCOT()));
            }
            if (null != tag.getPCCT()) {
                carousel.setPlannedEndTime(ThreadLocalDateUtil.parseXmlDate(tag.getPCCT()));
            }
            carousel.setCarouselIndicator(String.valueOf(tag.getBTYP()));
            carousel.setCarouselClass(String.valueOf(tag.getBCLS()));
            carousels.add(carousel);
        }
        return carousels;
    }

    /**
     * parse counter allocation tags to map to FmsCheckinDesk entites.
     * @param counterTags - counter allocation information tags
     * @param aodbId - flight AODB ID
     * @return FmsCheckinDesk entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsCheckinDesk> parseCounters(List<CKDT> counterTags, Long aodbId) throws ParseException {
        if (null == counterTags || counterTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsCheckinDesk> counters = new ArrayList<>();
        for (CKDT tag : counterTags) {
            FmsCheckinDesk counter = new FmsCheckinDesk();
            counter.setFlightId(aodbId);
            counter.setCheckinDeskCode(tag.getCHKC());
            counter.setSequenceNumber(tag.getCKNO());
            if (null != tag.getCOTM()) {
                counter.setActualStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getCOTM()));
            }
            if (null != tag.getCCTM()) {
                counter.setActualEndTime(ThreadLocalDateUtil.parseXmlDate(tag.getCCTM()));
            }
            if (null != tag.getPCOT()) {
                counter.setPlannedStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getPCOT()));
            }
            if (null != tag.getPCCT()) {
                counter.setPlannedEndTime(ThreadLocalDateUtil.parseXmlDate(tag.getPCCT()));
            }
            counter.setCheckinIndicator(String.valueOf(tag.getCTYP()));
            counter.setCheckinDeskCategory(String.valueOf(tag.getCCLS()));
            counters.add(counter);
        }
        return counters;
    }

    /**
     * parse chock on/off tags to map to FmsChock entites.
     * @param chotTags - chock information tags
     * @param aodbId - flight AODB ID
     * @return FmsChock entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsChock> parseChocks(List<CHOT> chotTags, Long aodbId) throws ParseException {
        if (null == chotTags || chotTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsChock> chocks = new ArrayList<>();
        for (CHOT tag : chotTags) {
            FmsChock chock = new FmsChock();
            chock.setFlightId(aodbId);
            chock.setChockIndicator(tag.getCHID());
            chock.setSequenceNumber(tag.getCSNO());
            chock.setStandCode(tag.getCHST());
            if (null != tag.getCHTM()) {
                chock.setChockTime(ThreadLocalDateUtil.parseXmlDate(tag.getCHTM()));
            }
            chocks.add(chock);
        }
        return chocks;
    }

    /**
     * parse chute allocation tags to map to FmsChute entites.
     *
     * @param chuteTags - chute allocation information tags
     * @param aodbId    - flight AODB ID
     * @return FmsChute entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsChute> parseChutes(List<CHDT> chuteTags, Long aodbId) throws ParseException {
        if (null == chuteTags || chuteTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsChute> chutes = new ArrayList<>();
        for (CHDT tag : chuteTags) {
            FmsChute chute = new FmsChute();
            chute.setFlightId(aodbId);
            chute.setChuteCode(tag.getCHUT());
            chute.setSequenceNumber(tag.getCHNO());
            if (null != tag.getCBTM()) {
                chute.setActualStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getCBTM()));
            }
            if (null != tag.getCETM()) {
                chute.setActualEndTime(ThreadLocalDateUtil.parseXmlDate(tag.getCETM()));
            }
            if (null != tag.getPCBT()) {
                chute.setPlannedStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getPCBT()));
            }
            if (null != tag.getPCET()) {
                chute.setPlannedEndTime(ThreadLocalDateUtil.parseXmlDate(tag.getPCET()));
            }
            chute.setChuteIndicator(String.valueOf(tag.getCTYP()));
            chute.setChuteCategory(String.valueOf(tag.getCCLS()));
            chutes.add(chute);
        }
        return chutes;
    }

    /**
     * parse delay information tags to map to FmsDelay entites.
     *
     * @param delayTags - delay information tags
     * @param aodbId    - flight AODB ID
     * @return FmsDelay entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsDelay> parseDelays(List<DELY> delayTags, Long aodbId) throws ParseException {
        if (null == delayTags || delayTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsDelay> delays = new ArrayList<>();
        for (DELY tag : delayTags) {
            FmsDelay delay = new FmsDelay();
            delay.setFlightId(aodbId);
            delay.setComments(tag.getValue());
            delay.setDelayCode(tag.getCODE());
            delay.setDuration(parseDuration(tag.getDURA()));
            if (null != tag.getSTRT()) {
                delay.setStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getSTRT()));
            }
            delays.add(delay);
        }
        return delays;
    }

    /**
     * parse duration tag to short value of minutes
     *
     * @param dura - String with HHMM format
     * @return - duration in minutes
     */
    protected Short parseDuration(String dura) {
        if (null == dura || dura.length() != 4) {
            return null;
        }
        try {
            byte hours = Byte.parseByte(dura.substring(0, 2));
            byte minutes = Byte.parseByte(dura.substring(2, 4));
            return (short) (hours * 24 + minutes);
        } catch (Exception ex) {
            log.error("failed to parse DURA tag [" + dura + "]", ex);
            return null;
        }
    }

    /**
     * parse gate allocation tags to map to FmsGate entites.
     * @param gateTags - gate allocation information tags
     * @param aodbId - flight AODB ID
     * @return FmsGate entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsGate> parseGates(List<GTDT> gateTags, Long aodbId) throws ParseException {
        if (null == gateTags || gateTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsGate> gates = new ArrayList<>();
        for (GTDT tag : gateTags) {
            FmsGate gate = new FmsGate();
            gate.setFlightId(aodbId);
            gate.setGateCode(tag.getGATE());
            gate.setSequenceNumber(tag.getGTNO());
            if (null != tag.getGOTM()) {
                gate.setActualStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getGOTM()));
            }
            if (null != tag.getGCTM()) {
                gate.setActualEndTime(ThreadLocalDateUtil.parseXmlDate(tag.getGCTM()));
            }
            if (null != tag.getPGOT()) {
                gate.setPlannedStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getPGOT()));
            }
            if (null != tag.getPGCT()) {
                gate.setPlannedEndTime(ThreadLocalDateUtil.parseXmlDate(tag.getPGCT()));
            }
            gate.setGateIndicator(String.valueOf(tag.getGTYP()));
            gates.add(gate);
        }
        return gates;
    }

    /**
     * parse service information tags to map to FmsService entites.
     * @param srvtTags - service information tags
     * @param aodbId - flight AODB ID
     * @return FmsService entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsService> parseServices(List<SRVT> srvtTags, Long aodbId) throws ParseException {
        if (null == srvtTags || srvtTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsService> services = new ArrayList<>();
        for (SRVT tag : srvtTags) {
            FmsService service = new FmsService();
            service.setFlightId(aodbId);
            service.setServiceCode(tag.getSRTC());
            service.setServiceQuantity(tag.getSRQT());
            if (null != tag.getSRST()) {
                service.setStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getSRST()));
            }
            if (null != tag.getSRET()) {
                service.setEndTime(ThreadLocalDateUtil.parseXmlDate(tag.getSRET()));
            }
            service.setServiceProvider(tag.getSRPR());
            service.setAbnormalReason(tag.getSANR());
            service.setAbnormalRemark(tag.getSARR());
            services.add(service);
        }
        return services;
    }

    /**
     * parse stand allocation tags to map to FmsStand entites.
     * @param psdtTags - stand allocation information tags
     * @param aodbId - flight AODB ID
     * @return FmsStand entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsStand> parseStands(List<PSDT> psdtTags, Long aodbId) throws ParseException {
        if (null == psdtTags || psdtTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsStand> stands = new ArrayList<>();
        for (PSDT tag : psdtTags) {
            FmsStand stand = new FmsStand();
            stand.setFlightId(aodbId);
            stand.setStandCode(tag.getPSST());
            stand.setSequenceNumber(tag.getPSNO());
            if (null != tag.getSTST()) {
                stand.setStartTime(ThreadLocalDateUtil.parseXmlDate(tag.getSTST()));
            }
            if (null != tag.getSTET()) {
                stand.setEndTime(ThreadLocalDateUtil.parseXmlDate(tag.getSTET()));
            }
            stands.add(stand);
        }
        return stands;
    }

    protected List<FmsStand> parseStandUpdate(List<PSHT> pshtTags, Long aodbId) throws ParseException {
        List<FmsStand> stands = new ArrayList<>();
        for (PSHT tag : pshtTags){
            FmsStand stand = new FmsStand();
            stand.setFlightId(aodbId);
            stand.setStandCode(tag.getPUST());
            stand.setPushIndicator(tag.getPSID());
            if (null != tag.getPSTM()){
                try {
                    stand.setPushTime(ThreadLocalDateUtil.parseXmlDate(tag.getPSTM()));
                } catch (ParseException e) {
                    log.error("pase error [{}] PSTM: ", tag.getPSTM());
                    throw e;
                }
            }
            stands.add(stand);
        }
        return stands;
    }

    protected List<FmsStand> parseStandTractorUpdate(List<TIPT> pshtTags, Long aodbId) throws ParseException {
        List<FmsStand> stands = new ArrayList<>();
        for (TIPT tag : pshtTags){
            FmsStand stand = new FmsStand();
            stand.setFlightId(aodbId);
            stand.setStandCode(tag.getTRST());
            if (null != tag.getTRTM()){
                try {
                    stand.setTractorTime(ThreadLocalDateUtil.parseXmlDate(tag.getTRTM()));
                } catch (ParseException e) {
                    log.error("pase error [{}] PSTM: ", tag.getTRTM());
                    throw e;
                }
            }
            stands.add(stand);
        }
        return stands;
    }

    /**
     * parse vip tags to map to FmsVip entites.
     * @param vipTags - vip information tags
     * @param aodbId - flight AODB ID
     * @return FmsVip entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsVip> parseVips(List<VIPF> vipTags, Long aodbId) throws ParseException {
        if (null == vipTags || vipTags.isEmpty() || null == aodbId) {
            return null;
        }

        List<FmsVip> vips = new ArrayList<>();
        for (VIPF tag : vipTags) {
            List<FmsVip> vipTransactions = parseVipTransactions(tag.getVPCD(), tag.getVFES(), aodbId, tag.getVipts());
            vips.addAll(vipTransactions);
        }
        return vips;
    }

    /**
     * parse VIP transactions
     * @param vpcd - VIP conde
     * @param vfes - VIP Entourage Size
     * @param vipts - VIP transactions
     * @return FmsVip entity list
     * @throws ParseException - xml date parse exception
     */
    protected List<FmsVip> parseVipTransactions(String vpcd, Short vfes, Long aodbId, List<VIPT> vipts) throws ParseException {
        List<FmsVip> vips = new ArrayList<>();
        if (null == vipts || vipts.isEmpty()) {
            FmsVip vip = new FmsVip();
            vip.setVipCode(vpcd);
            vip.setEntourageSize(vfes);
            vip.setFlightId(aodbId);
            vips.add(vip);
            return vips;
        }
        for(VIPT vipt : vipts) {
            FmsVip vip = new FmsVip();
            vip.setVipCode(vpcd);
            vip.setEntourageSize(vfes);
            vip.setFlightId(aodbId);
            vip.setServiceCode(vipt.getVSCD());
            vip.setServiceQuantity(vipt.getVTQY());
            if (null != vipt.getVTST()) {
                vip.setServiceStartTime(ThreadLocalDateUtil.parseXmlDate(vipt.getVTST()));
            }
            if (null != vipt.getVTET()) {
                vip.setServiceEndTime(ThreadLocalDateUtil.parseXmlDate(vipt.getVTET()));
            }
            vips.add(vip);
        }
        return vips;
    }


}
