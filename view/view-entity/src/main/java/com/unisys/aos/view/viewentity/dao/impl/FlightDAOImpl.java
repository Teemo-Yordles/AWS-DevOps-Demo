/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.common.util.ListUtil;
import com.unisys.aos.view.viewentity.config.FlightProperties;
import com.unisys.aos.view.viewentity.dao.*;
import com.unisys.aos.view.viewentity.dto.DelayInformationDTO;
import com.unisys.aos.view.viewentity.dto.EntireRouteDTO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.VipInformationDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsFlight;
import com.unisys.aos.view.viewentity.entity.flight.*;
import com.unisys.aos.view.viewentity.entity.reference.*;
import com.unisys.aos.view.viewentity.mapper.flight.*;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static com.unisys.aos.view.viewentity.mapper.flight.FmsAirbridgeDynamicSqlSupport.fmsAirbridge;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsCarouselDynamicSqlSupport.fmsCarousel;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsCheckinDeskDynamicSqlSupport.fmsCheckinDesk;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsChockDynamicSqlSupport.fmsChock;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsDelayDynamicSqlSupport.fmsDelay;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsEntireRouteDynamicSqlSupport.fmsEntireRoute;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsFlightDynamicSqlSupport.*;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsGateDynamicSqlSupport.fmsGate;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsServiceDynamicSqlSupport.fmsService;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsStandDynamicSqlSupport.fmsStand;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsVipDynamicSqlSupport.fmsVip;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author LiuJ2
 * @since 2020/9/10 12:16
 */
@Repository
@Slf4j
public class FlightDAOImpl extends BaseDAOImpl<FlightDTO> implements FlightDAO {
    @Autowired
    private FmsFlightMapper fmsFlightMapper;
    @Autowired
    private FmsAirbridgeMapper fmsAirbridgeMapper;
    @Autowired
    private FmsCarouselMapper fmsCarouselMapper;
    @Autowired
    private FmsCheckinDeskMapper fmsCheckinDeskMapper;
    @Autowired
    private FmsChockMapper fmsChockMapper;
    @Autowired
    private FmsChuteMapper fmsChuteMapper;
    @Autowired
    private FmsDelayMapper fmsDelayMapper;
    @Autowired
    private FmsEntireRouteMapper fmsEntireRouteMapper;
    @Autowired
    private FmsGateMapper fmsGateMapper;
    @Autowired
    private FmsServiceMapper fmsServiceMapper;
    @Autowired
    private FmsStandMapper fmsStandMapper;
    @Autowired
    private FmsVipMapper fmsVipMapper;
    @Autowired
    private RdmsAirportDAO rdmsAirportDAO;
    @Autowired
    private RdmsCityDAO rdmsCityDAO;
    @Autowired
    private RdmsFlightIndicatorDAO rdmsFlightIndicatorDAO;
    @Autowired
    private RdmsFlightTypeDAO rdmsFlightTypeDAO;
    @Autowired
    private RdmsStatusDAO rdmsStatusDAO;
    @Autowired
    private RdmsDelayTypeDAO rdmsDelayTypeDAO;
    @Autowired
    private RdmsOrganizationDAO rdmsOrganizationDAO;
    @Autowired
    private RdmsGateDAO rdmsGateDAO;
    @Autowired
    private RdmsVipPersonnalDAO rdmsVipPersonnalDAO;
    @Resource
    private FlightProperties flightProperties;

    // following are the reference data cache
    private Map<String, List<RdmsAirport>> airportCache;
    private Map<String, List<RdmsCity>> cityCache;
    private Map<String, List<RdmsFlightIndicator>> flightIndicatorCache;
    private Map<String, List<RdmsFlightType>> flightTypeCache;
    private Map<String, List<RdmsStatus>> flightStatusCache;
    private Map<String, List<RdmsDelayType>> delayTypeCache;
    private Map<Integer, List<RdmsOrganization>> organizationCache;
    private Map<String, List<RdmsGate>> gateCache;
    private Map<String, List<RdmsVipPersonnal>> vipCache;
    private String movement_indicator_arrival;
    private String movement_indicator_departure;
    private String currentStationIATACode;

    @Autowired
    public FlightDAOImpl(RedisUtil redisUtil) {
        super(redisUtil);
    }

    @PostConstruct
    public void init() {
        movement_indicator_arrival = flightProperties.getMovementIndicatorArrival();
        movement_indicator_departure = flightProperties.getMovementIndicatorDeparture();
        currentStationIATACode = flightProperties.getHomeAirport();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FlightDTO insertInNewTx(FlightDTO flight) {
        // insert new records to tables
        fmsFlightMapper.insert(flight.getFlight());

        if (null != flight.getEntireRoutes() && !flight.getEntireRoutes().isEmpty()) {
            for (FmsEntireRoute record : flight.getEntireRoutes()) {
                fmsEntireRouteMapper.insert(record);
            }
        }

        if (null != flight.getAirbridges() && !flight.getAirbridges().isEmpty()) {
            for (FmsAirbridge record : flight.getAirbridges()) {
                fmsAirbridgeMapper.insert(record);
            }
        }

        if (null != flight.getCarousels() && !flight.getCarousels().isEmpty()) {
            for (FmsCarousel record : flight.getCarousels()) {
                fmsCarouselMapper.insert(record);
            }
        }

        if (null != flight.getCheckinDesks() && !flight.getCheckinDesks().isEmpty()) {
            for (FmsCheckinDesk record : flight.getCheckinDesks()) {
                fmsCheckinDeskMapper.insert(record);
            }
        }

        if (null != flight.getChocks() && !flight.getChocks().isEmpty()) {
            for (FmsChock record : flight.getChocks()) {
                fmsChockMapper.insert(record);
            }
        }

        if (null != flight.getChutes() && !flight.getChutes().isEmpty()) {
            for (FmsChute record : flight.getChutes()) {
                fmsChuteMapper.insert(record);
            }
        }

        if (null != flight.getDelays() && !flight.getDelays().isEmpty()) {
            for (FmsDelay record : flight.getDelays()) {
                fmsDelayMapper.insert(record);
            }
        }

        if (null != flight.getGates() && !flight.getGates().isEmpty()) {
            for (FmsGate record : flight.getGates()) {
                fmsGateMapper.insert(record);
            }
        }

        if (null != flight.getServices() && !flight.getServices().isEmpty()) {
            for (FmsService record : flight.getServices()) {
                fmsServiceMapper.insert(record);
            }
        }

        if (null != flight.getStands() && !flight.getStands().isEmpty()) {
            for (FmsStand record : flight.getStands()) {
                fmsStandMapper.insert(record);
            }
        }

        if (null != flight.getVips() && !flight.getVips().isEmpty()) {
            for (FmsVip record : flight.getVips()) {
                fmsVipMapper.insert(record);
            }
        }

        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            fillAdditionalFields(flight);
            redisUtil.hset(HASH_KEY, getMapEntryKey(flight), flight);
        }
        return flight;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        fmsAirbridgeMapper.deleteByAODBId(id);
        fmsCarouselMapper.deleteByAODBId(id);
        fmsCheckinDeskMapper.deleteByAODBId(id);
        fmsChockMapper.deleteByAODBId(id);
        fmsChuteMapper.deleteByAODBId(id);
        fmsDelayMapper.deleteByAODBId(id);
        fmsGateMapper.deleteByAODBId(id);
        fmsServiceMapper.deleteByAODBId(id);
        fmsStandMapper.deleteByAODBId(id);
        fmsVipMapper.deleteByAODBId(id);
        fmsFlightMapper.deleteByAODBId(id);
        fmsEntireRouteMapper.deleteByAODBId(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(FlightDTO newRecord, FlightDTO oldRecord) {
        // sanity check
        if (null == newRecord || null == oldRecord) {
            log.error("Flight update [updateInNewTx] should be receives null variable.");
            return;
        }

        // check if basic flight information is different, if yes update FmsFlight object
        FmsFlight newFlightBasic = newRecord.getFlight();
        FmsFlight currentFlightBasic = oldRecord.getFlight();
        if (null != newFlightBasic && !newFlightBasic.equals(currentFlightBasic)) {
            updateFmsFlight(newFlightBasic, currentFlightBasic);
        }

        // check if routes difference and update to DB
        List<FmsEntireRoute> newRoutes = newRecord.getEntireRoutes();
        List<FmsEntireRoute> oldRoutes = oldRecord.getEntireRoutes();
        if (null != newRoutes && !ListUtil.isListEqual(newRoutes, oldRoutes)) {
            updateAdditionFlightInfo(newRoutes, oldRoutes);
        }

        // check if airbridge information difference and update to DB
        List<FmsAirbridge> newAirbridges = newRecord.getAirbridges();
        List<FmsAirbridge> oldAirbridges = oldRecord.getAirbridges();
        if (!ListUtil.isListEqual(newAirbridges, oldAirbridges)) {
            updateAdditionFlightInfo(newAirbridges, oldAirbridges);
        }

        // check if carousel information difference and update to DB
        List<FmsCarousel> newCarousels = newRecord.getCarousels();
        List<FmsCarousel> oldCarousels = oldRecord.getCarousels();
        if (!ListUtil.isListEqual(newCarousels, oldCarousels)) {
            updateAdditionFlightInfo(newCarousels, oldCarousels);
        }

        // check if chute information difference and update to DB
        List<FmsChute> newChutes = newRecord.getChutes();
        List<FmsChute> oldChutes = oldRecord.getChutes();
        if (!ListUtil.isListEqual(newChutes, oldChutes)) {
            updateAdditionFlightInfo(newChutes, oldChutes);
        }

        // check if delay information difference and update to DB
        List<FmsDelay> newDelays = newRecord.getDelays();
        List<FmsDelay> oldDelays = oldRecord.getDelays();
        if (!ListUtil.isListEqual(newDelays, oldDelays)) {
            updateAdditionFlightInfo(newDelays, oldDelays);
        }

        // check if Gate information difference and update to DB
        List<FmsGate> newGates = newRecord.getGates();
        List<FmsGate> oldGates = oldRecord.getGates();
        if (!ListUtil.isListEqual(newGates, oldGates)) {
            updateAdditionFlightInfo(newGates, oldGates);
        }

        // check if service information difference and update to DB
        List<FmsService> newServices = newRecord.getServices();
        List<FmsService> oldServices = oldRecord.getServices();
        if (!ListUtil.isListEqual(newServices, oldServices)) {
            updateAdditionFlightInfo(newServices, oldServices);
        }

        // check if Stand information difference and update to DB
        List<FmsStand> newStands = newRecord.getStands();
        List<FmsStand> oldStands = oldRecord.getStands();
        if (!ListUtil.isListEqual(newStands, oldStands)) {
            updateAdditionFlightInfo(newStands, oldStands);
        }

        // check if Stand information difference and update to DB
        List<FmsVip> newVips = newRecord.getVips();
        List<FmsVip> oldVips = oldRecord.getVips();
        if (!ListUtil.isListEqual(newVips, oldVips)) {
            updateAdditionFlightInfo(newVips, oldVips);
        }

        // update to cache
        if (redisUtil.exists(HASH_KEY)) {
            fillAdditionalFields(newRecord);
            redisUtil.hset(HASH_KEY, getMapEntryKey(newRecord), newRecord);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateFmsStands(List<FmsStand> stands) {
        for (FmsStand stand : stands) {
            fmsStandMapper.updateByPrimaryKey(stand);
        }
    }

    /**
     * Update flight additional information and DTO cache
     * For new element in the records, need to add to the table.
     * For updated element in the records, need to update table records.
     * For extra elements in the current record list, need to delete them.
     *
     * @param flightDTO  - flightDTO that contains old records
     * @param newRecords - list of new records
     */
    @Override
    public void updateAdditionFlightInfo(FlightDTO flightDTO, List<? extends AdditionFlightInfo> newRecords, Type type) {
        if (null == newRecords) {
            log.error("Error the List of  AdditionFlightInfo is null");
            return;
        }

        if (type.equals(FmsEntireRoute.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getEntireRoutes());
            flightDTO.setEntireRoutes((List<FmsEntireRoute>) newRecords);
        } else if (type.equals(FmsAirbridge.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getAirbridges());
            flightDTO.setAirbridges((List<FmsAirbridge>) newRecords);
        } else if (type.equals(FmsDelay.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getDelays());
            flightDTO.setDelays((List<FmsDelay>) newRecords);
        } else if (type.equals(FmsCheckinDesk.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getCheckinDesks());
            flightDTO.setCheckinDesks((List<FmsCheckinDesk>) newRecords);
        } else if (type.equals(FmsVip.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getVips());
            flightDTO.setVips((List<FmsVip>) newRecords);
        } else if (type.equals(FmsChock.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getChocks());
            flightDTO.setChocks((List<FmsChock>) newRecords);
        } else if (type.equals(FmsCarousel.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getCarousels());
            flightDTO.setCarousels((List<FmsCarousel>) newRecords);
        } else if (type.equals(FmsService.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getServices());
            flightDTO.setServices((List<FmsService>) newRecords);
        } else if (type.equals(FmsStand.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getStands());
            flightDTO.setStands((List<FmsStand>) newRecords);
        } else if (type.equals(FmsGate.class)) {
            updateAdditionFlightInfo(newRecords, flightDTO.getGates());
            flightDTO.setGates((List<FmsGate>) newRecords);
        }
        cacheRecord(flightDTO);
    }

    /**
     * Update flight additional information
     * For new element in the records, need to add to the table.
     * For updated element in the records, need to update table records.
     * For extra elements in the current record list, need to delete them.
     *
     * @param newRecords - list of new records
     * @param oldRecords - list of current records
     */
    private void updateAdditionFlightInfo(List<? extends AdditionFlightInfo> newRecords, List<? extends AdditionFlightInfo> oldRecords) {
        List<AdditionFlightInfo> toBeRemoved = null;
        if (null != oldRecords) {
            toBeRemoved = new ArrayList<>(oldRecords);
        }
        // new records with ID must be existing records
        // new records without ID must be new records
        // remove duplicate one from toBeRemoved, and delete all left records.
        if (null != newRecords) {
            for (AdditionFlightInfo record : newRecords) {
                AdditionFlightInfo oldRecord = null;
                if (null != toBeRemoved) {
                    Iterator<AdditionFlightInfo> iterator = toBeRemoved.iterator();
                    while (iterator.hasNext()) {
                        oldRecord = iterator.next();
                        if (oldRecord.matchFlightInfoRecord(record)) {
                            iterator.remove();
                            break;
                        }
                    }
                }
                // based on different additional information type,
                // proceed with different mapper
                if (null == record.getId() || null == oldRecord) {
                    // insert
                    if (record instanceof FmsEntireRoute) {
                        fmsEntireRouteMapper.insert((FmsEntireRoute) record);
                    } else if (record instanceof FmsAirbridge) {
                        fmsAirbridgeMapper.insert((FmsAirbridge) record);
                    } else if (record instanceof FmsDelay) {
                        fmsDelayMapper.insert((FmsDelay) record);
                    } else if (record instanceof FmsCheckinDesk) {
                        fmsCheckinDeskMapper.insert((FmsCheckinDesk) record);
                    } else if (record instanceof FmsVip) {
                        fmsVipMapper.insert((FmsVip) record);
                    } else if (record instanceof FmsChock) {
                        fmsChockMapper.insert((FmsChock) record);
                    } else if (record instanceof FmsCarousel) {
                        fmsCarouselMapper.insert((FmsCarousel) record);
                    } else if (record instanceof FmsService) {
                        fmsServiceMapper.insert((FmsService) record);
                    } else if (record instanceof FmsStand) {
                        fmsStandMapper.insert((FmsStand) record);
                    } else if (record instanceof FmsGate) {
                        fmsGateMapper.insert((FmsGate) record);
                    }
                } else {
                    // update
                    if (record instanceof FmsEntireRoute) {
                        updateFmsEntireRoute((FmsEntireRoute) record, (FmsEntireRoute) oldRecord);
                    } else if (record instanceof FmsAirbridge) {
                        updateFmsAirbridge((FmsAirbridge) record, (FmsAirbridge) oldRecord);
                    } else if (record instanceof FmsDelay) {
                        updateFmsDelay((FmsDelay) record, (FmsDelay) oldRecord);
                    } else if (record instanceof FmsCheckinDesk) {
                        updateFmsCheckinDesk((FmsCheckinDesk) record, (FmsCheckinDesk) oldRecord);
                    } else if (record instanceof FmsVip) {
                        updateFmsVip((FmsVip) record, (FmsVip) oldRecord);
                    } else if (record instanceof FmsChock) {
                        updateFmsChock((FmsChock) record, (FmsChock) oldRecord);
                    } else if (record instanceof FmsCarousel) {
                        updateFmsCarousel((FmsCarousel) record, (FmsCarousel) oldRecord);
                    } else if (record instanceof FmsService) {
                        updateFmsService((FmsService) record, (FmsService) oldRecord);
                    } else if (record instanceof FmsStand) {
                        updateFmsStand((FmsStand) record, (FmsStand) oldRecord);
                    } else if (record instanceof FmsGate) {
                        updateFmsGate((FmsGate) record, (FmsGate) oldRecord);
                    }
                }
            }
        }
        // delete
        if (null != toBeRemoved && !toBeRemoved.isEmpty()) {
            for (AdditionFlightInfo record : toBeRemoved) {
                // based on different additional information type,
                // proceed with different mapper
                if (record instanceof FmsEntireRoute) {
                    fmsEntireRouteMapper.deleteByPrimaryKey(record.getId());
                } else if (record instanceof FmsAirbridge) {
                    fmsAirbridgeMapper.deleteByPrimaryKey(record.getId());
                } else if (record instanceof FmsDelay) {
                    fmsDelayMapper.deleteByPrimaryKey(record.getId());
                } else if (record instanceof FmsCheckinDesk) {
                    fmsCheckinDeskMapper.deleteByPrimaryKey(record.getId());
                } else if (record instanceof FmsVip) {
                    fmsVipMapper.deleteByPrimaryKey(record.getId());
                } else if (record instanceof FmsChock) {
                    fmsChockMapper.deleteByPrimaryKey(record.getId());
                } else if (record instanceof FmsCarousel) {
                    fmsCarouselMapper.deleteByPrimaryKey(record.getId());
                } else if (record instanceof FmsService) {
                    fmsServiceMapper.deleteByPrimaryKey(record.getId());
                } else if (record instanceof FmsStand) {
                    fmsStandMapper.deleteByPrimaryKey(record.getId());
                } else if (record instanceof FmsGate) {
                    fmsGateMapper.deleteByPrimaryKey(record.getId());
                }
            }
        }
    }

    /**
     * Update new flight Gate information object to database.
     *
     * @param record    - new flight Gate record
     * @param oldRecord - current flight Gate record
     */
    private void updateFmsGate(FmsGate record, FmsGate oldRecord) {
        FmsGate tmpRecord = new FmsGate();
        tmpRecord.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, record, FmsGateDynamicSqlSupport.class, fmsGate, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsGateMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, record);
        }
    }

    /**
     * Update new flight Stand information object to database.
     *
     * @param record    - new flight Stand record
     * @param oldRecord - current flight Stand record
     */
    private void updateFmsStand(FmsStand record, FmsStand oldRecord) {
        FmsStand tmpRecord = new FmsStand();
        tmpRecord.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, record, FmsStandDynamicSqlSupport.class, fmsStand, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsStandMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, record);
        }
    }

    /**
     * Update new flight Service information object to database.
     *
     * @param record    - new flight Service record
     * @param oldRecord - current flight Service record
     */
    private void updateFmsService(FmsService record, FmsService oldRecord) {
        FmsService tmpRecord = new FmsService();
        tmpRecord.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, record, FmsServiceDynamicSqlSupport.class, fmsService, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsServiceMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, record);
        }
    }

    /**
     * Update new flight Carousel information object to database.
     *
     * @param record    - new flight Carousel record
     * @param oldRecord - current flight Carousel record
     */
    private void updateFmsCarousel(FmsCarousel record, FmsCarousel oldRecord) {
        FmsCarousel tmpRecord = new FmsCarousel();
        tmpRecord.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, record, FmsCarouselDynamicSqlSupport.class, fmsCarousel, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsCarouselMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, record);
        }
    }

    /**
     * Update new flight Chock information object to database.
     *
     * @param record    - new flight Chock record
     * @param oldRecord - current flight Chock record
     */
    private void updateFmsChock(FmsChock record, FmsChock oldRecord) {
        FmsChock tmpRecord = new FmsChock();
        tmpRecord.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, record, FmsChockDynamicSqlSupport.class, fmsChock, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsChockMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, record);
        }
    }

    /**
     * Update new flight Vip information object to database.
     *
     * @param record    - new flight Vip record
     * @param oldRecord - current flight Vip record
     */
    private void updateFmsVip(FmsVip record, FmsVip oldRecord) {
        FmsVip tmpRecord = new FmsVip();
        tmpRecord.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, record, FmsVipDynamicSqlSupport.class, fmsVip, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsVipMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, record);
        }
    }

    /**
     * Update new flight CheckinDesk information object to database.
     *
     * @param record    - new flight CheckinDesk record
     * @param oldRecord - current flight CheckinDesk record
     */
    private void updateFmsCheckinDesk(FmsCheckinDesk record, FmsCheckinDesk oldRecord) {
        FmsCheckinDesk tmpRecord = new FmsCheckinDesk();
        tmpRecord.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, record, FmsCheckinDeskDynamicSqlSupport.class, fmsCheckinDesk, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsCheckinDeskMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, record);
        }
    }

    /**
     * Update new flight Delay information object to database.
     *
     * @param record    - new flight Delay record
     * @param oldRecord - current flight Delay record
     */
    private void updateFmsDelay(FmsDelay record, FmsDelay oldRecord) {
        FmsDelay tmpRecord = new FmsDelay();
        tmpRecord.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, record, FmsDelayDynamicSqlSupport.class, fmsDelay, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsDelayMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, record);
        }
    }

    /**
     * Update new flight airbridge information object to database.
     *
     * @param record    - new flight airbridge record
     * @param oldRecord - current flight airbridge record
     */
    private void updateFmsAirbridge(FmsAirbridge record, FmsAirbridge oldRecord) {
        FmsAirbridge tmpRecord = new FmsAirbridge();
        tmpRecord.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, record, FmsAirbridgeDynamicSqlSupport.class, fmsAirbridge, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsAirbridgeMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, record);
        }
    }

    /**
     * Update new flight route information object to database.
     *
     * @param newRecord - new flight route record
     * @param oldRecord - current flight route record
     */
    private void updateFmsEntireRoute(FmsEntireRoute newRecord, FmsEntireRoute oldRecord) {
        FmsEntireRoute record = new FmsEntireRoute();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, FmsEntireRouteDynamicSqlSupport.class, fmsEntireRoute, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsEntireRouteMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, newRecord);
        }
    }

    /**
     * Update new flight basic information object to database.
     * NOTE: this also set the current time to update time.
     *
     * @param newRecord - new flight record
     * @param oldRecord - current flight record
     */
    private void updateFmsFlight(FmsFlight newRecord, FmsFlight oldRecord) {
        FmsFlight record = new FmsFlight();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, FmsFlightDynamicSqlSupport.class, fmsFlight, ignoredFieldList);
        if (null != updateDSL) {
            Date updateDateTime = new Date();
            updateDSL = updateDSL.set(updateTime).equalTo(updateDateTime);
            newRecord.setUpdateTime(updateDateTime);

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsFlightMapper.update(updateStatement);
            log.debug("FmsFlight object updated to DB.");
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, newRecord);
        }
    }

    /**
     * Update new flight basic information object to database.
     * NOTE: this only update the FmsFlight object to DB.
     *
     * @param newRecord - new flight record
     * @param oldFlight - current FmsFlight record
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateFmsFlightInNewTx(FlightDTO newRecord, FmsFlight oldFlight) {
        updateFmsFlight(newRecord.getFlight(), oldFlight);
        redisUtil.hset(HASH_KEY, getMapEntryKey(newRecord), newRecord);
        log.debug("Updated FlightDTO object updated to Redis.");
    }

    /**
     * Update new service information object to database.
     *
     * @param newRecord - new flight record
     * @param oldRecord - current flight record
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateFmsServiceInNewTx(FlightDTO newRecord, FlightDTO oldRecord) {

        // check if service information difference and update to DB
        List<FmsService> newServices = newRecord.getServices();
        List<FmsService> oldServices = oldRecord.getServices();
        if (!ListUtil.isListEqual(newServices, oldServices)) {
            updateAdditionFlightInfo(newServices, oldServices);
        }
        redisUtil.hset(HASH_KEY, getMapEntryKey(newRecord), newRecord);
        log.debug("Updated FlightDTO object updated to Redis.");
    }

    /**
     * Update new entire route information object to database.
     *
     * @param newRecord - new flight record
     * @param oldRecord - current flight record
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateFmsEntireRouteInNewTx(FlightDTO newRecord, FlightDTO oldRecord) {

        // check if entire route information difference and update to DB
        List<FmsEntireRoute> newEntireRoutes = newRecord.getEntireRoutes();
        List<FmsEntireRoute> oldEntireRoutes = oldRecord.getEntireRoutes();
        if (!ListUtil.isListEqual(newEntireRoutes, oldEntireRoutes)) {
            updateAdditionFlightInfo(newEntireRoutes, oldEntireRoutes);
        }
        redisUtil.hset(HASH_KEY, getMapEntryKey(newRecord), newRecord);
        log.debug("Updated FlightDTO object updated to Redis.");
    }

    /**
     * Update new delay information object to database.
     *
     * @param newRecord - new flight record
     * @param oldRecord - current flight record
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateFmsDelayInNewTx(FlightDTO newRecord, FlightDTO oldRecord) {

        // check if delay information difference and update to DB
        List<FmsDelay> newDelays = newRecord.getDelays();
        List<FmsDelay> oldDelays = oldRecord.getDelays();
        if (!ListUtil.isListEqual(newDelays, oldDelays)) {
            updateAdditionFlightInfo(newDelays, oldDelays);
        }
        redisUtil.hset(HASH_KEY, getMapEntryKey(newRecord), newRecord);
        log.debug("Updated FlightDTO object updated to Redis.");
    }

    /**
     * Get all table records for a target table.
     *
     * @return list of FlightDTO entities.
     */
    @Override
    public Collection<FlightDTO> findAll() {
        // double check reference loading status
        // in case reference is not loaded before the flight processing.
        if (null == airportCache || null == cityCache || null == flightIndicatorCache || null == flightTypeCache || null == flightStatusCache || null == delayTypeCache || null == organizationCache || null == gateCache || null == vipCache) {
            loadReferenceDataCache();
        }

        // try to get records from redis
        Collection<FlightDTO> records;
        if (redisUtil.exists(HASH_KEY)) {
            records = getAllFromCache(HASH_KEY);
        } else {
            records = initializeFlightCache();
        }

        return records;
    }

    private Collection<FlightDTO> initializeFlightCache() {
        // double check reference loading status
        // in case reference is not loaded before the flight processing.
        if (null == airportCache || null == cityCache || null == flightIndicatorCache || null == flightTypeCache || null == flightStatusCache || null == delayTypeCache || null == organizationCache || null == gateCache || null == vipCache) {
            loadReferenceDataCache();
        }

        Collection<FlightDTO> records = findAllFromDB();
        // cache the result if not cached and there are some records to cache
        if (null != records) {
            cacheAll(records);
        }

        return records;
    }

    /**
     * Build all FlightDTOs from database directly.
     * This is used to create flight DTOs to be cached
     * and returned back to front-end.
     *
     * @return All flight DTOs
     */
    public Collection<FlightDTO> findAllFromDB() {
        List<FmsFlight> flights = fmsFlightMapper.selectByExample().build().execute();
        if (null == flights || flights.isEmpty()) {
            //if no flight found in DB, returns empty list directly.
            return new ArrayList<>();
        }
        List<FmsAirbridge> fmsAirbridges = fmsAirbridgeMapper.selectByExample().build().execute();
        List<FmsCarousel> fmsCarousels = fmsCarouselMapper.selectByExample().build().execute();
        List<FmsCheckinDesk> fmsCheckinDesks = fmsCheckinDeskMapper.selectByExample().build().execute();
        List<FmsChock> fmsChocks = fmsChockMapper.selectByExample().build().execute();
        List<FmsChute> fmsChutes = fmsChuteMapper.selectByExample().build().execute();
        List<FmsDelay> fmsDelays = fmsDelayMapper.selectByExample().build().execute();
        List<FmsEntireRoute> fmsEntireRoutes = fmsEntireRouteMapper.selectByExample().build().execute();
        List<FmsGate> fmsGates = fmsGateMapper.selectByExample().build().execute();
        List<FmsService> fmsServices = fmsServiceMapper.selectByExample().build().execute();
        List<FmsStand> fmsStands = fmsStandMapper.selectByExample().build().execute();
        List<FmsVip> fmsVips = fmsVipMapper.selectByExample().build().execute();

        Map<Long, List<FmsAirbridge>> airbridgeMap = flightDataListToMap(fmsAirbridges, FmsAirbridge::getFlightId);
        Map<Long, List<FmsCarousel>> carouselMap = flightDataListToMap(fmsCarousels, FmsCarousel::getFlightId);
        Map<Long, List<FmsCheckinDesk>> checkinDeskMap = flightDataListToMap(fmsCheckinDesks, FmsCheckinDesk::getFlightId);
        Map<Long, List<FmsChock>> chockMap = flightDataListToMap(fmsChocks, FmsChock::getFlightId);
        Map<Long, List<FmsChute>> chuteMap = flightDataListToMap(fmsChutes, FmsChute::getFlightId);
        Map<Long, List<FmsDelay>> delayMap = flightDataListToMap(fmsDelays, FmsDelay::getFlightId);
        Map<Long, List<FmsEntireRoute>> entireRouteMap = flightDataListToMap(fmsEntireRoutes, FmsEntireRoute::getFlightId);
        Map<Long, List<FmsGate>> gateMap = flightDataListToMap(fmsGates, FmsGate::getFlightId);
        Map<Long, List<FmsService>> serviceMap = flightDataListToMap(fmsServices, FmsService::getFlightId);
        Map<Long, List<FmsStand>> standMap = flightDataListToMap(fmsStands, FmsStand::getFlightId);
        Map<Long, List<FmsVip>> vipMap = flightDataListToMap(fmsVips, FmsVip::getFlightId);

        return buildFlightDTOCollection(flights,
                airbridgeMap,
                carouselMap,
                checkinDeskMap,
                chockMap,
                chuteMap,
                delayMap,
                entireRouteMap,
                gateMap,
                serviceMap,
                standMap,
                vipMap);
    }

    /**
     * build a map <flightId, relatedFlightData> from the flight data list.
     * one flight ID could related to multiple records, so the map value is a list.
     *
     * @param flightDataList - list of specific flight data for example flight stand allocation data list.
     * @param getKey         - function to get aodb ID
     * @param <T>            - flight data class type
     * @return - a map of <aodbId,List<FlightData>>
     */
    private <T> Map<Long, List<T>> flightDataListToMap(List<T> flightDataList, Function<T, Long> getKey) {
        Map<Long, List<T>> flightDataMap = new HashMap<>();
        for (T flightData : flightDataList) {
            Long key = getKey.apply(flightData);
            if (flightDataMap.containsKey(key)) {
                flightDataMap.get(key).add(flightData);
            } else {
                List<T> groupedList = new ArrayList<>();
                groupedList.add(flightData);
                flightDataMap.put(key, groupedList);
            }
        }
        return flightDataMap;
    }

    /**
     * Build flight DTOs from flight information maps.
     */
    private Collection<FlightDTO> buildFlightDTOCollection(List<FmsFlight> flights,
                                                           Map<Long, List<FmsAirbridge>> airbridgeMap,
                                                           Map<Long, List<FmsCarousel>> carouselMap,
                                                           Map<Long, List<FmsCheckinDesk>> checkinDeskMap,
                                                           Map<Long, List<FmsChock>> chockMap,
                                                           Map<Long, List<FmsChute>> chuteMap,
                                                           Map<Long, List<FmsDelay>> delayMap,
                                                           Map<Long, List<FmsEntireRoute>> entireRouteMap,
                                                           Map<Long, List<FmsGate>> gateMap,
                                                           Map<Long, List<FmsService>> serviceMap,
                                                           Map<Long, List<FmsStand>> standMap,
                                                           Map<Long, List<FmsVip>> vipMap) {
        Collection<FlightDTO> flightDTOs = new ArrayList<>();
        if (null != flights) {
            for (FmsFlight flight : flights) {
                FlightDTO flightDTO = new FlightDTO(flight);
                Long flightId = flight.getAodbId();
                if (null != flightId && flightId >= 0) {
                    flightDTO.setAirbridges(airbridgeMap.get(flightId));
                    flightDTO.setCarousels(carouselMap.get(flightId));
                    flightDTO.setCheckinDesks(checkinDeskMap.get(flightId));
                    flightDTO.setChocks(chockMap.get(flightId));
                    flightDTO.setChutes(chuteMap.get(flightId));
                    flightDTO.setDelays(delayMap.get(flightId));
                    flightDTO.setEntireRoutes(entireRouteMap.get(flightId));
                    flightDTO.setGates(gateMap.get(flightId));
                    flightDTO.setServices(serviceMap.get(flightId));
                    flightDTO.setStands(standMap.get(flightId));
                    flightDTO.setVips(vipMap.get(flightId));

                    Long scheduleTimestamp = getScheduleTime(flight);

                    List<FmsEntireRoute> fmsEntireRoutes = entireRouteMap.get(flightId);
                    if (null != fmsEntireRoutes && !fmsEntireRoutes.isEmpty()) {
                        List<EntireRouteDTO> entireRouteDTOList = new ArrayList<>();
                        for (FmsEntireRoute entireRoute : fmsEntireRoutes) {
                            if (null != entireRoute) {
                                String airportCode = entireRoute.getAirportCode();
                                if (null != airportCode && !airportCode.isEmpty() && null != airportCache) {
                                    List<RdmsAirport> rdmsAirportList = airportCache.get(airportCode);
                                    List<RdmsAirport> currentAirportList = new ArrayList<>();
                                    if (null != scheduleTimestamp && null != rdmsAirportList && !rdmsAirportList.isEmpty()) {
                                        for (RdmsAirport airport : rdmsAirportList) {
                                            Long inspireTime = airport.getInspireTime();
                                            Long expireTime = airport.getExpireTime();
                                            if (null != inspireTime && null != expireTime) {
                                                if (scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                                                    currentAirportList.add(airport);
                                                }
                                            }
                                        }
                                    }
                                    EntireRouteDTO entireRouteDTO = new EntireRouteDTO(entireRoute);
                                    if (currentAirportList.isEmpty()) {
                                        // If this reference data does not exist in DB, set these fields to "".
                                        log.warn("The related record does not exist in the database, airport IATA code = [{}]. The airport name in entire route will be set to an empty string.", airportCode);
                                        entireRouteDTO.setAirportName_cn("");
                                        entireRouteDTO.setAirportName_en("");
                                    } else if (currentAirportList.size() > 1) {
                                        // If there are multiple reference data in DB, set these fields to iata code.
                                        log.warn("There are multiple related records in the database, airport IATA code = [{}]. The airport name in entire route will be set to it's IATA code.", airportCode);
                                        entireRouteDTO.setAirportName_cn(airportCode);
                                        entireRouteDTO.setAirportName_en(airportCode);
                                    } else {
                                        RdmsAirport airport = currentAirportList.get(0);
                                        String airPortNameCn = airport.getLocalDescription();
                                        String airPortNameEn = airport.getDescription();
                                        entireRouteDTO.setAirportName_cn(airPortNameCn);
                                        entireRouteDTO.setAirportName_en(airPortNameEn);
                                    }
                                    entireRouteDTOList.add(entireRouteDTO);
                                }
                            }
                        }
                        flightDTO.setEntireRouteDTOList(entireRouteDTOList);
                    }

                    List<FmsVip> fmsVips = vipMap.get(flightId);
                    if (null != fmsVips && !fmsVips.isEmpty()) {
                        List<VipInformationDTO> vipInformationDTOList = new ArrayList<>();
                        for (FmsVip vip : fmsVips) {
                            if (null != vip) {
                                String vipCode = vip.getVipCode();
                                if (null != vipCode && !vipCode.isEmpty() && null != vipCache) {
                                    List<RdmsVipPersonnal> rdmsVipPersonnalList = vipCache.get(vipCode);
                                    List<RdmsVipPersonnal> currentVipPersonnalList = new ArrayList<>();
                                    if (null != scheduleTimestamp && null != rdmsVipPersonnalList && !rdmsVipPersonnalList.isEmpty()) {
                                        for (RdmsVipPersonnal vipPersonnal : rdmsVipPersonnalList) {
                                            Long inspireTime = vipPersonnal.getInspireTime();
                                            Long expireTime = vipPersonnal.getExpireTime();
                                            if (null != inspireTime && null != expireTime) {
                                                if (scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                                                    currentVipPersonnalList.add(vipPersonnal);
                                                }
                                            }
                                        }
                                        VipInformationDTO vipInformationDTO = new VipInformationDTO(vip);
                                        if (currentVipPersonnalList.isEmpty()) {
                                            // If this reference data does not exist in DB, set these fields to "".
                                            log.warn("The related record does not exist in the database, vip code = [{}]. The name of vip will be set to an empty string.", vipCode);
                                            vipInformationDTO.setFirstName("");
                                            vipInformationDTO.setLastName("");

                                        } else if (currentVipPersonnalList.size() > 1) {
                                            // If there are multiple reference data in DB, set these fields to code.
                                            log.warn("There are multiple related records in the database, vip code = [{}]. The name of vip will be set to it's code.", vipCode);
                                            vipInformationDTO.setFirstName(vipCode);
                                            vipInformationDTO.setLastName(vipCode);
                                        } else {
                                            RdmsVipPersonnal rdmsVipPersonnal = currentVipPersonnalList.get(0);
                                            String firstName = rdmsVipPersonnal.getVipPersonFirstname();
                                            String lastName = rdmsVipPersonnal.getVipPersonLastname();
                                            vipInformationDTO.setFirstName(firstName);
                                            vipInformationDTO.setLastName(lastName);
                                        }
                                        vipInformationDTOList.add(vipInformationDTO);
                                    }
                                }
                            }
                        }
                        flightDTO.setVipInformationDTOList(vipInformationDTOList);
                    }

                    List<FmsDelay> fmsDelays = delayMap.get(flightId);
                    if (null != fmsDelays && !fmsDelays.isEmpty()) {
                        List<DelayInformationDTO> delayInformationDTOList = new ArrayList<>();
                        for (FmsDelay fmsDelay : fmsDelays) {
                            if (null != fmsDelay) {
                                String delayCode = fmsDelay.getDelayCode();
                                if (null != delayCode && !delayCode.isEmpty() && null != delayTypeCache) {
                                    List<RdmsDelayType> rdmsDelayTypeList = delayTypeCache.get(delayCode);
                                    List<RdmsDelayType> currentDelayTypeList = new ArrayList<>();
                                    if (null != scheduleTimestamp && null != rdmsDelayTypeList && !rdmsDelayTypeList.isEmpty()) {
                                        for (RdmsDelayType delayType : rdmsDelayTypeList) {
                                            Long inspireTime = delayType.getInspireTime();
                                            Long expireTime = delayType.getExpireTime();
                                            if (null != inspireTime && null != expireTime) {
                                                if (scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                                                    currentDelayTypeList.add(delayType);
                                                }
                                            }
                                        }
                                        DelayInformationDTO delayInformationDTO = new DelayInformationDTO(fmsDelay);
                                        if (currentDelayTypeList.isEmpty()) {
                                            // If this reference data does not exist in DB, set these fields to "".
                                            log.warn("The related record does not exist in the database, delay type code = [{}]. The description in the delay information will be set to an empty string.", delayCode);
                                            delayInformationDTO.setDelay_cn("");
                                            delayInformationDTO.setDelay_en("");
                                        } else if (currentDelayTypeList.size() > 1) {
                                            // If there are multiple reference data in DB, set these fields to code.
                                            log.warn("There are multiple related records in the database, delay type code = [{}]. The description in the delay information will be set to it's code.", delayCode);
                                            delayInformationDTO.setDelay_cn(delayCode);
                                            delayInformationDTO.setDelay_en(delayCode);
                                        } else {
                                            RdmsDelayType rdmsDelayType = currentDelayTypeList.get(0);
                                            String delayCn = rdmsDelayType.getLocalDescription();
                                            String delayEn = rdmsDelayType.getDescription();
                                            delayInformationDTO.setDelay_cn(delayCn);
                                            delayInformationDTO.setDelay_en(delayEn);
                                        }
                                        delayInformationDTOList.add(delayInformationDTO);
                                    }
                                }
                            }
                        }
                        flightDTO.setDelayInformationDTOList(delayInformationDTOList);
                    }

                }
                fillAdditionalFields(flightDTO);
                flightDTOs.add(flightDTO);
            }
        }
        return flightDTOs;
    }

    /**
     * get map entry key string based on the record given
     *
     * @param record - map entry value to be cached
     */
    @Override
    public String getMapEntryKey(FlightDTO record) {
        return String.valueOf(record.getId());
    }

    /**
     * This method is call after the flight is persisted to DB
     * before save to Redis cache.
     */
    public void fillAdditionalFields(FlightDTO record) {
        fillFullRouteFields(record);
        fillFlightIndicatorFields(record);
        fillFlightTypeFields(record);
        fillFlightStatusFields(record);
        fillFlightDelayFields(record);
        fillFlightExternalStatusFields(record);
        fillPaxAgentFields(record);
        fillFieldAgentFields(record);
        fillMaintenanceAgentFields(record);
        fillCancelFlagField(record);
        fillFlightReturnField(record);
        fillCodeShareFlightListField(record);
        fillStandListField(record);
        fillGateListField(record);
        fillCarouselListField(record);
        fillChuteListField(record);
        fillCheckinDeskListField(record);
        fillAirbridgeListField(record);
        fillPierListField(record);
        fillDivertAirportField(record);
        fillPreviousOrNextStopField(record);
        fillCityDescriptionFields(record);
        fillPreOrNextStepScheduleTimeField(record);
        fillPreOrNextStepEstimateTimeField(record);
        fillPreOrNextStepActualTimeField(record);
    }

    /**
     * Generate all full route such as "北京-成都-上海“ and "PEK-CTU-SHA"
     *
     * @param flight - FlightDTO object
     */
    private void fillFullRouteFields(FlightDTO flight) {
        // sanity check
        List<FmsEntireRoute> entireRoutes = flight.getEntireRoutes();
        if (null == entireRoutes || entireRoutes.isEmpty()) {
            return;
        }
        // sort the route based on sequence number
        Collections.sort(entireRoutes);
        // build full route
        StringBuilder codeRoute = new StringBuilder();
        for (FmsEntireRoute route : entireRoutes) {
            codeRoute.append(route.getAirportCode()).append("-");
        }
        if (codeRoute.length() > 0) {
            codeRoute.deleteCharAt(codeRoute.length() - 1);
        }
        flight.setAirportCodeFullRoute(codeRoute.toString());
    }

    /**
     * Generate flight indicator information
     *
     * @param flight - FlightDTO object
     */
    private void fillFlightIndicatorFields(FlightDTO flight) {
        // sanity check
        String flightIndicatorCode = flight.getFlight().getFlightIndicator();
        if (null == flightIndicatorCode || flightIndicatorCode.isEmpty()) {
            return;
        }
        if (null != flightIndicatorCache) {
            Long scheduleTimestamp = null;
            if (null != flight.getFlight()) {
                scheduleTimestamp = getScheduleTime(flight.getFlight());
            }
            List<RdmsFlightIndicator> flightIndicatorList = flightIndicatorCache.get(flightIndicatorCode);
            List<RdmsFlightIndicator> currentFlightIndicatorList = new ArrayList<>();
            if (null != flightIndicatorList && !flightIndicatorList.isEmpty()) {
                for (RdmsFlightIndicator flightIndicator : flightIndicatorList) {
                    Long inspireTime = flightIndicator.getInspireTime();
                    Long expireTime = flightIndicator.getExpireTime();
                    if (null != inspireTime && null != expireTime) {
                        if (null != scheduleTimestamp && scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                            currentFlightIndicatorList.add(flightIndicator);
                        }
                    }
                }
                if (currentFlightIndicatorList.isEmpty()) {
                    // If this reference data does not exist in DB, set these fields to "".
                    log.warn("The related record does not exist in the database, flight indicator code = [{}]. The description in the flight indicator information will be set to an empty string.", flightIndicatorCode);
                    flight.setFlightIndicator_cn("");
                    flight.setFlightIndicator_en("");
                } else if (currentFlightIndicatorList.size() > 1) {
                    // If there are multiple reference data in DB, set these fields to code.
                    log.warn("There are multiple related records in the database, flight indicator code = [{}]. The description in the flight indicator information will be set to it's code.", flightIndicatorCode);
                    flight.setFlightIndicator_cn(flightIndicatorCode);
                    flight.setFlightIndicator_en(flightIndicatorCode);
                } else {
                    RdmsFlightIndicator flightIndicator = currentFlightIndicatorList.get(0);
                    flight.setFlightIndicator_cn(flightIndicator.getLocalDescription());
                    flight.setFlightIndicator_en(flightIndicator.getDescription());
                }
            }
        }
    }

    /**
     * Generate flight type information
     *
     * @param flight - FlightDTO object
     */
    private void fillFlightTypeFields(FlightDTO flight) {
        // sanity check
        String flightTypeCode = flight.getFlight().getFlightType();
        if (null == flightTypeCode || flightTypeCode.isEmpty()) {
            return;
        }
        if (null != flightTypeCache) {
            Long scheduleTimestamp = null;
            if (null != flight.getFlight()) {
                scheduleTimestamp = getScheduleTime(flight.getFlight());
            }

            List<RdmsFlightType> flightTypeList = flightTypeCache.get(flightTypeCode);
            List<RdmsFlightType> currentFlightTypeList = new ArrayList<>();
            if (null != flightTypeList && !flightTypeList.isEmpty()) {
                for (RdmsFlightType flightType : flightTypeList) {
                    Long inspireTime = flightType.getInspireTime();
                    Long expireTime = flightType.getExpireTime();
                    if (null != inspireTime && null != expireTime) {
                        if (null != scheduleTimestamp && scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                            currentFlightTypeList.add(flightType);
                        }
                    }
                }
            }
            if (currentFlightTypeList.isEmpty()) {
                // If this reference data does not exist in DB, set these fields to "".
                log.warn("The related record does not exist in the database, flight type code = [{}]. The description in the flight type information will be set to an empty string.", flightTypeCode);
                flight.setFlightType_cn("");
                flight.setFlightType_en("");
            } else if (currentFlightTypeList.size() > 1) {
                // If there are multiple reference data in DB, set these fields to code.
                log.warn("There are multiple related records in the database, flight type code = [{}]. The description in the flight type information will be set to it's code.", flightTypeCode);
                flight.setFlightType_cn(flightTypeCode);
                flight.setFlightType_en(flightTypeCode);
            } else {
                RdmsFlightType flightType = currentFlightTypeList.get(0);
                flight.setFlightType_cn(flightType.getLocalDescription());
                flight.setFlightType_en(flightType.getDescription());
            }
        }
    }

    /**
     * Generate flight status information
     *
     * @param flight - FlightDTO object
     */
    private void fillFlightStatusFields(FlightDTO flight) {
        // sanity check
        String flightStatusCode = flight.getFlight().getStatusCode();
        if (null == flightStatusCode || flightStatusCode.isEmpty()) {
            return;
        }
        if (null != flightStatusCache) {
            Long scheduleTimestamp = null;
            if (null != flight.getFlight()) {
                scheduleTimestamp = getScheduleTime(flight.getFlight());
            }
            List<RdmsStatus> statusList = flightStatusCache.get(flightStatusCode);
            List<RdmsStatus> currentStatusList = new ArrayList<>();
            if (null != statusList && !statusList.isEmpty()) {
                for (RdmsStatus status : statusList) {
                    Long inspireTime = status.getInspireTime();
                    Long expireTime = status.getExpireTime();
                    if (null != inspireTime && null != expireTime) {
                        if (null != scheduleTimestamp && scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                            currentStatusList.add(status);
                        }
                    }
                }
                if (currentStatusList.isEmpty()) {
                    // If this reference data does not exist in DB, set these fields to "".
                    log.warn("The related record does not exist in the database, flight status code = [{}]. The description in the flight status information will be set to an empty string.", flightStatusCode);
                    flight.setFlightStatus_cn("");
                    flight.setFlightStatus_en("");
                } else if (currentStatusList.size() > 1) {
                    // If there are multiple reference data in DB, set these fields to code.
                    log.warn("There are multiple related records in the database, flight status code = [{}]. The description in the flight status information will be set to it's code.", flightStatusCode);
                    flight.setFlightStatus_cn(flightStatusCode);
                    flight.setFlightStatus_en(flightStatusCode);
                } else {
                    RdmsStatus status = currentStatusList.get(0);
                    flight.setFlightStatus_cn(status.getLocalDescription());
                    flight.setFlightStatus_en(status.getDescription());
                }
            }
        }
    }

    /**
     * Generate flight external status information
     *
     * @param flight - FlightDTO object
     */
    private void fillFlightExternalStatusFields(FlightDTO flight) {
        // sanity check
        String flightExternalStatusCode = flight.getFlight().getExternalStatusCode();
        if (null == flightExternalStatusCode || flightExternalStatusCode.isEmpty()) {
            return;
        }
        if (null != flightStatusCache) {
            Long scheduleTimestamp = null;
            if (null != flight.getFlight()) {
                scheduleTimestamp = getScheduleTime(flight.getFlight());
            }
            List<RdmsStatus> externalStatusList = flightStatusCache.get(flightExternalStatusCode);
            List<RdmsStatus> currentExternalStatusList = new ArrayList<>();
            if (null != externalStatusList && !externalStatusList.isEmpty()) {
                for (RdmsStatus status : externalStatusList) {
                    Long inspireTime = status.getInspireTime();
                    Long expireTime = status.getExpireTime();
                    if (null != inspireTime && null != expireTime) {
                        if (null != scheduleTimestamp && scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                            currentExternalStatusList.add(status);
                        }
                    }
                }
                if (currentExternalStatusList.isEmpty()) {
                    // If this reference data does not exist in DB, set these fields to "".
                    log.warn("The related record does not exist in the database, flight external status code = [{}]. The description in the flight external status information will be set to an empty string.", flightExternalStatusCode);
                    flight.setFlightExternalStatus_cn("");
                    flight.setFlightExternalStatus_en("");
                } else if (currentExternalStatusList.size() > 1) {
                    // If there are multiple reference data in DB, set these fields to code.
                    log.warn("There are multiple related records in the database, flight external status code = [{}]. The description in the flight external status information will be set to it's code.", flightExternalStatusCode);
                    flight.setFlightExternalStatus_cn(flightExternalStatusCode);
                    flight.setFlightExternalStatus_en(flightExternalStatusCode);
                } else {
                    RdmsStatus status = currentExternalStatusList.get(0);
                    flight.setFlightExternalStatus_cn(status.getLocalDescription());
                    flight.setFlightExternalStatus_en(status.getDescription());
                }
            }
        }
    }

    /**
     * Generate flight delay information
     *
     * @param flight - FlightDTO object
     */
    private void fillFlightDelayFields(FlightDTO flight) {
        // sanity check
        List<FmsDelay> delayList = flight.getDelays();
        if (null == delayList || delayList.isEmpty()) {
            return;
        }
        // sort delays based on sequence number
        Collections.sort(delayList);
        String delayCode = delayList.get(delayList.size() - 1).getDelayCode();
        if (null == delayCode || delayCode.isEmpty()) {
            return;
        }
        if (null != delayTypeCache) {
            Long scheduleTimestamp = null;
            if (null != flight.getFlight()) {
                scheduleTimestamp = getScheduleTime(flight.getFlight());
            }
            List<RdmsDelayType> rdmsDelayTypeList = delayTypeCache.get(delayCode);
            List<RdmsDelayType> currentDelayTypeList = new ArrayList<>();
            if (null != rdmsDelayTypeList && !rdmsDelayTypeList.isEmpty()) {
                for (RdmsDelayType delayType : rdmsDelayTypeList) {
                    Long inspireTime = delayType.getInspireTime();
                    Long expireTime = delayType.getExpireTime();
                    if (null != inspireTime && null != expireTime) {
                        if (null != scheduleTimestamp && scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                            currentDelayTypeList.add(delayType);
                        }
                    }
                }
                if (currentDelayTypeList.isEmpty()) {
                    // If this reference data does not exist in DB, set these fields to "".
                    log.warn("The related record does not exist in the database, delay type code = [{}]. The description in the delay information will be set to an empty string.", delayCode);
                    flight.setDelayDescription_cn("");
                    flight.setDelayDescription_en("");
                } else if (currentDelayTypeList.size() > 1) {
                    // If there are multiple reference data in DB, set these fields to code.
                    log.warn("There are multiple related records in the database, delay type code = [{}]. The description in the delay information will be set to it's code.", delayCode);
                    flight.setDelayDescription_cn(delayCode);
                    flight.setDelayDescription_en(delayCode);
                } else {
                    RdmsDelayType delayType = currentDelayTypeList.get(0);
                    flight.setDelayDescription_cn(delayType.getLocalDescription());
                    flight.setDelayDescription_en(delayType.getDescription());
                }
            }
        }
    }

    /**
     * Generate flight returned information
     *
     * @param flight - FlightDTO object
     */
    private void fillFlightReturnField(FlightDTO flight) {
        // sanity check
        String returnType = flight.getFlight().getRetTyp();
        if (null == returnType || returnType.isEmpty()) {
            return;
        }
        flight.setFlightReturn(returnType);
    }

    /**
     * Generate flight cancellation information
     *
     * @param flight - FlightDTO object
     */
    private void fillCancelFlagField(FlightDTO flight) {
        // sanity check
        Date cancelTime = flight.getFlight().getCancel();
        String cancelFlag = null;
        if (null != cancelTime) {
            cancelFlag = "Y";
        }
        flight.setCancelFlag(cancelFlag);
    }

    /**
     * Generate flight passenger handling agent information
     *
     * @param flight - FlightDTO object
     */
    private void fillPaxAgentFields(FlightDTO flight) {
        // sanity check
        Integer agentOrganizationCode = flight.getFlight().getPaxAgent();
        if (null == agentOrganizationCode) {
            return;
        }
        if (null != organizationCache) {
            Long scheduleTimestamp = null;
            if (null != flight.getFlight()) {
                scheduleTimestamp = getScheduleTime(flight.getFlight());
            }
            List<RdmsOrganization> organizationList = organizationCache.get(agentOrganizationCode);
            List<RdmsOrganization> currentOrganizationList = new ArrayList<>();
            if (null != organizationList && !organizationList.isEmpty()) {
                for (RdmsOrganization organization : organizationList) {
                    Long inspireTime = organization.getInspireTime();
                    Long expireTime = organization.getExpireTime();
                    if (null != inspireTime && null != expireTime) {
                        if (null != scheduleTimestamp && scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                            currentOrganizationList.add(organization);
                        }
                    }
                }
                if (currentOrganizationList.isEmpty()) {
                    // If this reference data does not exist in DB, set these fields to "".
                    log.warn("The related record does not exist in the database, passenger handling agent code = [{}]. The description in the passenger handling agent information will be set to an empty string.", agentOrganizationCode);
                    flight.setPaxAgent_cn("");
                    flight.setPaxAgent_en("");
                } else if (currentOrganizationList.size() > 1) {
                    // If there are multiple reference data in DB, set these fields to code.
                    log.warn("There are multiple related records in the database, passenger handling agent code = [{}]. The description in the passenger handling agent information will be set to it's code.", agentOrganizationCode);
                    flight.setPaxAgent_cn(agentOrganizationCode.toString());
                    flight.setPaxAgent_en(agentOrganizationCode.toString());
                } else {
                    RdmsOrganization organization = currentOrganizationList.get(0);
                    flight.setPaxAgent_cn(organization.getLocalDescription());
                    flight.setPaxAgent_en(organization.getDescription());
                }
            }
        }
    }

    /**
     * Generate flight field (or ground) handling agent information
     *
     * @param flight - FlightDTO object
     */
    private void fillFieldAgentFields(FlightDTO flight) {
        // sanity check
        Integer agentOrganizationCode = flight.getFlight().getFieldAgent();
        if (null == agentOrganizationCode) {
            return;
        }
        if (null != organizationCache) {
            Long scheduleTimestamp = null;
            if (null != flight.getFlight()) {
                scheduleTimestamp = getScheduleTime(flight.getFlight());
            }
            List<RdmsOrganization> organizationList = organizationCache.get(agentOrganizationCode);
            List<RdmsOrganization> currentOrganizationList = new ArrayList<>();
            if (null != organizationList && !organizationList.isEmpty()) {
                for (RdmsOrganization organization : organizationList) {
                    Long inspireTime = organization.getInspireTime();
                    Long expireTime = organization.getExpireTime();
                    if (null != inspireTime && null != expireTime) {
                        if (null != scheduleTimestamp && scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                            currentOrganizationList.add(organization);
                        }
                    }
                }
                if (currentOrganizationList.isEmpty()) {
                    // If this reference data does not exist in DB, set these fields to "".
                    log.warn("The related record does not exist in the database, field handling agent code = [{}]. The description in the field handling agent information will be set to an empty string.", agentOrganizationCode);
                    flight.setFieldAgent_cn("");
                    flight.setFieldAgent_en("");
                } else if (currentOrganizationList.size() > 1) {
                    // If there are multiple reference data in DB, set these fields to code.
                    log.warn("There are multiple related records in the database, field handling agent code = [{}]. The description in the field handling agent information will be set to it's code.", agentOrganizationCode);
                    flight.setFieldAgent_cn(agentOrganizationCode.toString());
                    flight.setFieldAgent_en(agentOrganizationCode.toString());
                } else {
                    RdmsOrganization organization = currentOrganizationList.get(0);
                    flight.setFieldAgent_cn(organization.getLocalDescription());
                    flight.setFieldAgent_en(organization.getDescription());
                }
            }
        }
    }

    /**
     * Generate flight maintenance handling agent information
     *
     * @param flight - FlightDTO object
     */
    private void fillMaintenanceAgentFields(FlightDTO flight) {
        // sanity check
        Integer agentOrganizationCode = flight.getFlight().getMaintenanceAgent();
        if (null == agentOrganizationCode) {
            return;
        }
        if (null != organizationCache) {
            Long scheduleTimestamp = null;
            if (null != flight.getFlight()) {
                scheduleTimestamp = getScheduleTime(flight.getFlight());
            }
            List<RdmsOrganization> organizationList = organizationCache.get(agentOrganizationCode);
            List<RdmsOrganization> currentOrganizationList = new ArrayList<>();
            if (null != organizationList && !organizationList.isEmpty()) {
                for (RdmsOrganization organization : organizationList) {
                    Long inspireTime = organization.getInspireTime();
                    Long expireTime = organization.getExpireTime();
                    if (null != inspireTime && null != expireTime) {
                        if (null != scheduleTimestamp && scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                            currentOrganizationList.add(organization);
                        }
                    }
                }
                if (currentOrganizationList.isEmpty()) {
                    // If this reference data does not exist in DB, set these fields to "".
                    log.warn("The related record does not exist in the database, maintenance handling agent code = [{}]. The description in the maintenance handling agent information will be set to an empty string.", agentOrganizationCode);
                    flight.setMaintenanceAgent_cn("");
                    flight.setMaintenanceAgent_en("");
                } else if (currentOrganizationList.size() > 1) {
                    // If there are multiple reference data in DB, set these fields to code.
                    log.warn("There are multiple related records in the database, maintenance handling agent code = [{}]. The description in the maintenance handling agent information will be set to it's code.", agentOrganizationCode);
                    flight.setMaintenanceAgent_cn(agentOrganizationCode.toString());
                    flight.setMaintenanceAgent_en(agentOrganizationCode.toString());
                } else {
                    RdmsOrganization organization = currentOrganizationList.get(0);
                    flight.setMaintenanceAgent_cn(organization.getLocalDescription());
                    flight.setMaintenanceAgent_en(organization.getDescription());
                }
            }
        }
    }

    /**
     * Generate code share flight list information
     *
     * @param flightDTO - FlightDTO object
     */
    private void fillCodeShareFlightListField(FlightDTO flightDTO) {
        // sanity check
        FmsFlight currentFlight = flightDTO.getFlight();
        List<String> flightNumberList = getCodeShareFlightNumberList(currentFlight);
        String flightNumberString = null;
        if (null != flightNumberList && !flightNumberList.isEmpty()) {
            flightNumberString = String.join(", ", flightNumberList);
        }
        flightDTO.setCodeShareFlightList(flightNumberString);
    }

    /***
     * Get code share flight numbers list.
     * @param currentFlight Current flight.
     * @return Code share flights string list or null.
     */
    @Override
    public List<String> getCodeShareFlightNumberList(FmsFlight currentFlight) {
        if (null != currentFlight) {
            List<FmsFlight> flightList = fmsFlightMapper.selectByMasterAodbId(currentFlight.getAodbId());
            List<String> flightNumberList = new ArrayList<>();
            for (FmsFlight flight : flightList) {
                String flightNum = null;
                if (null != flight) {
                    flightNum = flight.getFlightNumber();
                }
                if (null != flightNum && !flightNum.isEmpty()) {
                    flightNumberList.add(flightNum);
                }
            }
            return flightNumberList;
        }
        return null;
    }

    /**
     * Generate stand list information
     *
     * @param flight - FlightDTO object
     */
    private void fillStandListField(FlightDTO flight) {
        // sanity check
        List<FmsStand> standList = flight.getStands();
        if (null == standList || standList.isEmpty()) {
            String stand = flight.getFlight().getCurrentStand();
            if (null != stand && !stand.isEmpty()) {
                flight.setStandList(stand);
            }
            return;
        }
        // sort stands based on sequence number
        Collections.sort(standList);
        flight.setStandList(listToString(standList));
    }

    /**
     * Generate gate list information
     *
     * @param flight - FlightDTO object
     */
    private void fillGateListField(FlightDTO flight) {
        // sanity check
        List<FmsGate> gateList = flight.getGates();
        if (null == gateList || gateList.isEmpty()) {
            return;
        }
        // sort gates based on sequence number
        Collections.sort(gateList);
        flight.setGateList(listToString(gateList));
    }

    /**
     * Generate carousel list information
     *
     * @param flight - FlightDTO object
     */
    private void fillCarouselListField(FlightDTO flight) {
        // sanity check
        List<FmsCarousel> carouselList = flight.getCarousels();
        if (null == carouselList || carouselList.isEmpty()) {
            return;
        }
        // sort carousels based on sequence number
        Collections.sort(carouselList);
        flight.setCarouselList(listToString(carouselList));
    }

    /**
     * Generate chute list information
     *
     * @param flight - FlightDTO object
     */
    private void fillChuteListField(FlightDTO flight) {
        // sanity check
        List<FmsChute> chuteList = flight.getChutes();
        if (null == chuteList || chuteList.isEmpty()) {
            return;
        }
        // sort chutes based on sequence number
        Collections.sort(chuteList);
        flight.setChuteList(listToString(chuteList));
    }

    /**
     * Generate checkinDesk list information
     *
     * @param flight - FlightDTO object
     */
    private void fillCheckinDeskListField(FlightDTO flight) {
        // sanity check
        List<FmsCheckinDesk> checkinDeskList = flight.getCheckinDesks();
        if (null == checkinDeskList || checkinDeskList.isEmpty()) {
            return;
        }
        // sort checkinDesks based on sequence number
        Collections.sort(checkinDeskList);
        flight.setCheckinDeskList(listToString(checkinDeskList));
    }

    /**
     * Generate airbridge list information
     *
     * @param flight - FlightDTO object
     */
    private void fillAirbridgeListField(FlightDTO flight) {
        // sanity check
        List<FmsAirbridge> airbridgeList = flight.getAirbridges();
        if (null == airbridgeList || airbridgeList.isEmpty()) {
            return;
        }
        // sort airbridges based on sequence number
        Collections.sort(airbridgeList);
        flight.setAirbridgeList(listToString(airbridgeList));
    }

    /**
     * Generate pier list information
     *
     * @param flight - FlightDTO object
     */
    private void fillPierListField(FlightDTO flight) {
        // sanity check
        List<FmsGate> gateList = flight.getGates();
        if (null == gateList || gateList.isEmpty()) {
            return;
        }
        // sort gates based on sequence number
        Collections.sort(gateList);
        ConcurrentHashMap<String, String> gatePierMap = new ConcurrentHashMap<>();
        for (FmsGate gate : gateList) {
            String gatecode = gate.getGateCode();
            if (null != gateCache) {
                Long scheduleTimestamp = null;
                if (null != flight.getFlight()) {
                    scheduleTimestamp = getScheduleTime(flight.getFlight());
                }
                List<RdmsGate> rdmsGateList = gateCache.get(gatecode);
                List<RdmsGate> currentGateList = new ArrayList<>();
                if (null != rdmsGateList && !rdmsGateList.isEmpty()) {
                    for (RdmsGate rdmsGate : rdmsGateList) {
                        Long inspireTime = rdmsGate.getInspireTime();
                        Long expireTime = rdmsGate.getExpireTime();
                        if (null != inspireTime && null != expireTime) {
                            if (null != scheduleTimestamp && scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                                currentGateList.add(rdmsGate);
                            }
                        }
                    }
                }
                if (currentGateList.isEmpty()) {
                    log.warn("There is no associated gate in DB, gate code = [" + gatecode + "]");
                } else if (currentGateList.size() > 1) {
                    log.warn("There are multiple associated gate in DB, gate code = [" + gatecode + "]");
                } else {
                    RdmsGate rdmsGate = currentGateList.get(0);
                    String pierCode = rdmsGate.getPierCode();
                    if (null != pierCode && !pierCode.isEmpty()) {
                        gatePierMap.put(pierCode, gate.getGateCode());
                    } else {
                        log.warn("There is no pier associated with this Gate in the reference data, gate code = [" + gate.getGateCode() + "]");
                    }
                }
            }
        }
        Enumeration<String> pierList = gatePierMap.keys();

        StringBuilder piers = new StringBuilder();
        while (pierList.hasMoreElements()) {
            piers.append(pierList.nextElement()).append(", ");
        }
        // remove the last ", " symbol
        if (piers.length() > 0) {
            piers.delete(piers.length() - 2, piers.length());
        }
        flight.setPierList(piers.toString());
    }

    /**
     * Generate divert airport information
     *
     * @param flight - FlightDTO object
     */
    private void fillDivertAirportField(FlightDTO flight) {
        // sanity check
        String airportCode = flight.getFlight().getDivAirport();
        if (null == airportCode || airportCode.isEmpty()) {
            return;
        }

        flight.setGateList(airportCode);
    }

    /**
     * Generate previous or next stop information
     *
     * @param flight - FlightDTO object
     */
    private void fillPreviousOrNextStopField(FlightDTO flight) {
        // sanity check
        FmsEntireRoute stop = getPreviousOrNextStop(flight);
        if (null == stop) {
            return;
        }
        flight.setPreviousOrNextStop(stop.getAirportCode());
    }

    /**
     * Generate city description information
     *
     * @param flight - FlightDTO object
     */
    private void fillCityDescriptionFields(FlightDTO flight) {
        // sanity check
        FmsEntireRoute stop = getPreviousOrNextStop(flight);
        if (null == stop) {
            return;
        }
        // get city local description from airport iata code
        if (null != airportCache) {
            Long scheduleTimestamp = null;
            if (null != flight.getFlight()) {
                scheduleTimestamp = getScheduleTime(flight.getFlight());
            }
            List<RdmsAirport> airportList = airportCache.get(stop.getAirportCode());
            List<RdmsAirport> currentAirportList = new ArrayList<>();
            if (null != scheduleTimestamp && null != airportList && !airportList.isEmpty()) {
                for (RdmsAirport airport : airportList) {
                    Long inspireTime = airport.getInspireTime();
                    Long expireTime = airport.getExpireTime();
                    if (null != inspireTime && null != expireTime) {
                        if (scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                            currentAirportList.add(airport);
                        }
                    }
                }
            }
            if (currentAirportList.isEmpty()) {
                // If this reference data does not exist in DB, set these fields to "".
                log.warn("The related airport record does not exist in the database, airport IATA code = [{}]. The description in the city information will be set to an empty string.", stop.getAirportCode());
                flight.setCityDescription_cn("");
                flight.setCityDescription_en("");
            } else if (currentAirportList.size() > 1) {
                // If there are multiple reference data in DB, set these fields to iata code.
                log.warn("There are multiple related airport records in the database, airport IATA code = [{}]. The description in the city information will be set to it's airport IATA code.", stop.getAirportCode());
                flight.setCityDescription_cn(stop.getAirportCode());
                flight.setCityDescription_en(stop.getAirportCode());
            } else {
                RdmsAirport airport = currentAirportList.get(0);
                String cityCode = airport.getCityCode();
                if (null != cityCode && null != cityCache) {
                    List<RdmsCity> cityList = cityCache.get(cityCode);
                    List<RdmsCity> currentCityList = new ArrayList<>();
                    if (null != cityList && !cityList.isEmpty()) {
                        for (RdmsCity rdmsCity : cityList) {
                            Long inspireTime = rdmsCity.getInspireTime();
                            Long expireTime = rdmsCity.getExpireTime();
                            if (null != inspireTime && null != expireTime) {
                                if (scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                                    currentCityList.add(rdmsCity);
                                }
                            }
                        }
                        if (currentCityList.isEmpty()) {
                            // If this reference data does not exist in DB, set these fields to "".
                            log.warn("The related record does not exist in the database, city code = [{}]. The description in the city information will be set to an empty string.", cityCode);
                            flight.setCityDescription_cn("");
                            flight.setCityDescription_en("");
                        } else if (currentCityList.size() > 1) {
                            // If there are multiple reference data in DB, set these fields to iata code.
                            log.warn("There are multiple related records in the database, city code = [{}]. The description in the city information will be set to it's code.", cityCode);
                            flight.setCityDescription_cn(cityCode);
                            flight.setCityDescription_en(cityCode);
                        } else {
                            RdmsCity city = currentCityList.get(0);
                            flight.setCityDescription_cn(city.getLocalDescription());
                            flight.setCityDescription_en(city.getDescription());
                        }
                    }
                }
            }
        }
    }

    /**
     * Generate previous or next stop schedule time information
     *
     * @param flightDTO - FlightDTO object
     */
    private void fillPreOrNextStepScheduleTimeField(FlightDTO flightDTO) {
        // sanity check
        FmsFlight flight = flightDTO.getFlight();
        if (null == flight) {
            return;
        }
        String movementIndicator = flight.getMovementIndicator();
        FmsEntireRoute stop = getPreviousOrNextStop(flightDTO);
        if (null == stop) {
            return;
        }

        Date scheduleTime = null;
        if (movement_indicator_arrival.equals(movementIndicator)) {
            scheduleTime = stop.getDepartureTime();
        }
        if (movement_indicator_departure.equals(movementIndicator)) {
            scheduleTime = stop.getArrivalTime();
        }
        flightDTO.setPreOrNextStepScheduleTime(scheduleTime);
    }

    /**
     * Generate previous or next stop estimate time information
     *
     * @param flightDTO - FlightDTO object
     */
    private void fillPreOrNextStepEstimateTimeField(FlightDTO flightDTO) {
        // sanity check
        FmsFlight flight = flightDTO.getFlight();
        if (null == flight) {
            return;
        }
        String movementIndicator = flight.getMovementIndicator();

        Date estimateTime = null;
        if (movement_indicator_arrival.equals(movementIndicator)) {
            estimateTime = flight.getPedt();
        }
        if (movement_indicator_departure.equals(movementIndicator)) {
            estimateTime = flight.getNeat();
        }
        flightDTO.setPreOrNextStepEstimateTime(estimateTime);
    }

    /**
     * Generate previous or next stop actual time information
     *
     * @param flightDTO - FlightDTO object
     */
    private void fillPreOrNextStepActualTimeField(FlightDTO flightDTO) {
        // sanity check
        FmsFlight flight = flightDTO.getFlight();
        if (null == flight) {
            return;
        }
        String movementIndicator = flight.getMovementIndicator();

        Date actualTime = null;
        if (movement_indicator_arrival.equals(movementIndicator)) {
            actualTime = flight.getPadt();
        }
        if (movement_indicator_departure.equals(movementIndicator)) {
            actualTime = flight.getNaat();
        }
        flightDTO.setPreOrNextStepActualTime(actualTime);
    }

    /**
     * This is to load the reference data cache
     * used during the SCHD processing.
     */
    @Override
    public void loadReferenceDataCache() {
        // airport cache
        Collection<RdmsAirport> airports = rdmsAirportDAO.findAll();
        Set<String> airportIataCodeSet = new HashSet<>();
        if (null != airports && !airports.isEmpty()) {
            for (RdmsAirport airport : airports) {
                String iataCode = airport.getIataCode();
                if (null != iataCode) {
                    airportIataCodeSet.add(iataCode);
                }
            }
            List<RdmsAirport> rdmsAirportList;
            if (!airportIataCodeSet.isEmpty()) {
                airportCache = new ConcurrentHashMap<>();
                for (String iataCode : airportIataCodeSet) {
                    rdmsAirportList = rdmsAirportDAO.findByIataCode(iataCode);
                    if (null != rdmsAirportList && !rdmsAirportList.isEmpty()) {
                        airportCache.put(iataCode, rdmsAirportList);
                    }
                }

            }
        }
        // city cache
        Collection<RdmsCity> cities = rdmsCityDAO.findAll();
        Set<String> cityIataCodeSet = new HashSet<>();
        if (null != cities && !cities.isEmpty()) {
            for (RdmsCity city : cities) {
                String iataCode = city.getIataCityCode();
                if (null != iataCode) {
                    cityIataCodeSet.add(iataCode);
                }
            }
            List<RdmsCity> rdmsCityList;
            if (!cityIataCodeSet.isEmpty()) {
                cityCache = new ConcurrentHashMap<>();
                for (String iataCode : cityIataCodeSet) {
                    rdmsCityList = rdmsCityDAO.findByIataCityCode(iataCode);
                    if (null != rdmsCityList && !rdmsCityList.isEmpty()) {
                        cityCache.put(iataCode, rdmsCityList);
                    }
                }
            }
        }
        // flight indicator cache
        Collection<RdmsFlightIndicator> flightIndicators = rdmsFlightIndicatorDAO.findAll();
        Set<String> flightIndicatorCodeSet = new HashSet<>();
        if (null != flightIndicators && !flightIndicators.isEmpty()) {
            for (RdmsFlightIndicator rdmsFlightIndicator : flightIndicators) {
                String indicatorCode = rdmsFlightIndicator.getIndicatorCode();
                if (null != indicatorCode) {
                    flightIndicatorCodeSet.add(indicatorCode);
                }
            }
            List<RdmsFlightIndicator> rdmsFlightIndicatorList;
            if (!flightIndicatorCodeSet.isEmpty()) {
                flightIndicatorCache = new ConcurrentHashMap<>();
                for (String indicatorCode : flightIndicatorCodeSet) {
                    rdmsFlightIndicatorList = rdmsFlightIndicatorDAO.findByIndicatorCode(indicatorCode);
                    if (null != rdmsFlightIndicatorList && !rdmsFlightIndicatorList.isEmpty()) {
                        flightIndicatorCache.put(indicatorCode, rdmsFlightIndicatorList);
                    }
                }
            }
        }
        // flight type cache
        Collection<RdmsFlightType> flightTypes = rdmsFlightTypeDAO.findAll();
        Set<String> flightTypeCodeSet = new HashSet<>();
        if (null != flightTypes && !flightTypes.isEmpty()) {
            for (RdmsFlightType flightType : flightTypes) {
                String typeCode = flightType.getFlightTypeCode();
                if (null != typeCode) {
                    flightTypeCodeSet.add(typeCode);
                }
            }
            List<RdmsFlightType> rdmsFlightTypeList;
            if (!flightTypeCodeSet.isEmpty()) {
                flightTypeCache = new ConcurrentHashMap<>();
                for (String typeCode : flightTypeCodeSet) {
                    rdmsFlightTypeList = rdmsFlightTypeDAO.findByTypeCode(typeCode);
                    if (null != rdmsFlightTypeList && !rdmsFlightTypeList.isEmpty()) {
                        flightTypeCache.put(typeCode, rdmsFlightTypeList);
                    }
                }
            }
        }
        // flight status cache
        Collection<RdmsStatus> statuses = rdmsStatusDAO.findAll();
        Set<String> statusCodeSet = new HashSet<>();
        if (null != statuses && !statuses.isEmpty()) {
            for (RdmsStatus rdmsStatus : statuses) {
                String statusCode = rdmsStatus.getStatusCode();
                if (null != statusCode) {
                    statusCodeSet.add(statusCode);
                }
            }
            List<RdmsStatus> rdmsStatusList;
            if (!statusCodeSet.isEmpty()) {
                flightStatusCache = new ConcurrentHashMap<>();
                for (String statusCode : statusCodeSet) {
                    rdmsStatusList = rdmsStatusDAO.findByStatusCode(statusCode);
                    if (null != rdmsStatusList && !rdmsStatusList.isEmpty()) {
                        flightStatusCache.put(statusCode, rdmsStatusList);
                    }
                }
            }
        }
        // delay type cache
        Collection<RdmsDelayType> delayTypes = rdmsDelayTypeDAO.findAll();
        Set<String> delayTypeCodeSet = new HashSet<>();
        if (null != delayTypes && !delayTypes.isEmpty()) {
            for (RdmsDelayType rdmsDelayType : delayTypes) {
                String delayTypeCode = rdmsDelayType.getDelayTypeCode();
                if (null != delayTypeCode) {
                    delayTypeCodeSet.add(delayTypeCode);
                }
            }
            List<RdmsDelayType> rdmsDelayTypeList;
            if (!delayTypeCodeSet.isEmpty()) {
                delayTypeCache = new ConcurrentHashMap<>();
                for (String typeCode : delayTypeCodeSet) {
                    rdmsDelayTypeList = rdmsDelayTypeDAO.findByDelayTypeCode(typeCode);
                    if (null != rdmsDelayTypeList && !rdmsDelayTypeList.isEmpty()) {
                        delayTypeCache.put(typeCode, rdmsDelayTypeList);
                    }
                }
            }
        }
        // organization cache
        Collection<RdmsOrganization> organizations = rdmsOrganizationDAO.findAll();
        Set<Integer> organizationIdentifierSet = new HashSet<>();
        if (null != organizations && !organizations.isEmpty()) {
            for (RdmsOrganization organization : organizations) {
                Integer organizationIdentifier = organization.getOrganizationIdentifier();
                if (null != organizationIdentifier) {
                    organizationIdentifierSet.add(organizationIdentifier);
                }
            }
            List<RdmsOrganization> rdmsOrganizationList;
            if (!organizationIdentifierSet.isEmpty()) {
                organizationCache = new ConcurrentHashMap<>();
                for (Integer identifier : organizationIdentifierSet) {
                    rdmsOrganizationList = rdmsOrganizationDAO.findByOrganizationIdentifier(identifier);
                    if (null != rdmsOrganizationList && !rdmsOrganizationList.isEmpty()) {
                        organizationCache.put(identifier, rdmsOrganizationList);
                    }
                }
            }
        }
        // gate cache
        Collection<RdmsGate> gates = rdmsGateDAO.findAll();
        Set<String> gateCodeSet = new HashSet<>();
        if (null != gates && !gates.isEmpty()) {
            for (RdmsGate rdmsGate : gates) {
                String gateCode = rdmsGate.getGateCode();
                if (null != gateCode) {
                    gateCodeSet.add(gateCode);
                }
            }
            List<RdmsGate> rdmsGateList;
            if (!gateCodeSet.isEmpty()) {
                gateCache = new ConcurrentHashMap<>();
                for (String gateCode : gateCodeSet) {
                    rdmsGateList = rdmsGateDAO.findByGateCode(gateCode);
                    if (null != rdmsGateList && !rdmsGateList.isEmpty()) {
                        gateCache.put(gateCode, rdmsGateList);
                    }
                }
            }
        }
        // vip personnal cache
        Collection<RdmsVipPersonnal> vipPersonnals = rdmsVipPersonnalDAO.findAll();
        Set<String> vipPersonCodeSet = new HashSet<>();
        if (null != vipPersonnals && !vipPersonnals.isEmpty()) {
            for (RdmsVipPersonnal vipPersonnal : vipPersonnals) {
                String vipPersonCode = vipPersonnal.getVipPersonCode();
                if (null != vipPersonCode) {
                    vipPersonCodeSet.add(vipPersonCode);
                }
            }
            List<RdmsVipPersonnal> rdmsVipPersonnalList;
            if (!vipPersonCodeSet.isEmpty()) {
                vipCache = new ConcurrentHashMap<>();
                for (String vipPersonCode : vipPersonCodeSet) {
                    rdmsVipPersonnalList = rdmsVipPersonnalDAO.findByVipPersonCode(vipPersonCode);
                    if (null != rdmsVipPersonnalList && !rdmsVipPersonnalList.isEmpty()) {
                        vipCache.put(vipPersonCode, rdmsVipPersonnalList);
                    }
                }
            }
        }
    }

    /***
     * Get flight schedule time.
     * @param flight FmsFlight entity.
     * @return schedule time.
     */
    public Long getScheduleTime(FmsFlight flight) {
        if (null == flight) {
            return null;
        }
        Date scheduleTime = flight.getSto();
        if (null != scheduleTime) {
            return scheduleTime.getTime();
        } else {
            return null;
        }
    }

    /**
     * This is to load the flight data with AODB Flight ID.
     *
     * @param flightId - flight AODB ID, it shouldn't be null.
     */
    @Override
    public FlightDTO findByAodbId(Long flightId) {
        // sanity check
        if (null == flightId) {
            return null;
        }
        // double check reference loading status
        // in case reference is not loaded before the flight processing.
        if (null == airportCache || null == cityCache || null == flightIndicatorCache || null == flightTypeCache || null == flightStatusCache || null == delayTypeCache || null == organizationCache || null == gateCache || null == vipCache) {
            loadReferenceDataCache();
        }

        // try to get records from redis
        if (!redisUtil.exists(HASH_KEY)) {
            initializeFlightCache();
        }
        FlightDTO record = (FlightDTO) redisUtil.hget(HASH_KEY, String.valueOf(flightId));

        // if cannot find flight from redis, find it from DB
        if (null == record) {
            record = findFlightFromDB(flightId);
        }

        // cache the result if not cached and there are some records to cache
        // cache the flight that don't exit to avoid search from DB again and again
        if (null != record) {
            cacheRecord(record);
        }
        // null flight
        if (record.getFlight() == null) {
            record = null;
        }
        return record;
    }

    private void cacheRecord(FlightDTO record) {
        redisUtil.hset(HASH_KEY, String.valueOf(record.getId()), record);
    }

    /**
     * Cache the flight record into Redis
     *
     * @param record - record to be cached
     */
    @Override
    public void updateCacheRecord(FlightDTO record) {
        record = buildFlightDTO(record.getFlight(), record.getAirbridges(), record.getCarousels(), record.getCheckinDesks(), record.getChocks(), record.getChutes(), record.getDelays(), record.getEntireRoutes(), record.getGates(), record.getServices(), record.getStands(), record.getVips());
        cacheRecord(record);
    }

    /**
     * Find a single flight from DB
     *
     * @param flightId - flight AODB ID
     * @return - Object of FlightDTO
     */
    private FlightDTO findFlightFromDB(Long flightId) {
        FmsFlight flight = fmsFlightMapper.selectByAodbId(flightId);
        List<FmsAirbridge> fmsAirbridges = fmsAirbridgeMapper.selectByAodbId(flightId);
        List<FmsCarousel> fmsCarousels = fmsCarouselMapper.selectByAodbId(flightId);
        List<FmsCheckinDesk> fmsCheckinDesks = fmsCheckinDeskMapper.selectByAodbId(flightId);
        List<FmsChock> fmsChocks = fmsChockMapper.selectByAodbId(flightId);
        List<FmsChute> fmsChutes = fmsChuteMapper.selectByAodbId(flightId);
        List<FmsDelay> fmsDelays = fmsDelayMapper.selectByAodbId(flightId);
        List<FmsEntireRoute> fmsEntireRoutes = fmsEntireRouteMapper.selectByAodbId(flightId);
        List<FmsGate> fmsGates = fmsGateMapper.selectByAodbId(flightId);
        List<FmsService> fmsServices = fmsServiceMapper.selectByAodbId(flightId);
        List<FmsStand> fmsStands = fmsStandMapper.selectByAodbId(flightId);
        List<FmsVip> fmsVips = fmsVipMapper.selectByAodbId(flightId);

        return buildFlightDTO(flight,
                fmsAirbridges,
                fmsCarousels,
                fmsCheckinDesks,
                fmsChocks,
                fmsChutes,
                fmsDelays,
                fmsEntireRoutes,
                fmsGates,
                fmsServices,
                fmsStands,
                fmsVips);
    }

    /**
     * Build flight DTO from flight basic information and additional information.
     */
    private FlightDTO buildFlightDTO(FmsFlight flight,
                                     List<FmsAirbridge> fmsAirbridges,
                                     List<FmsCarousel> fmsCarousels,
                                     List<FmsCheckinDesk> fmsCheckinDesks,
                                     List<FmsChock> fmsChocks,
                                     List<FmsChute> fmsChutes,
                                     List<FmsDelay> fmsDelays,
                                     List<FmsEntireRoute> fmsEntireRoutes,
                                     List<FmsGate> fmsGates,
                                     List<FmsService> fmsServices,
                                     List<FmsStand> fmsStands,
                                     List<FmsVip> fmsVips) {
        // create FlightDTO object
        FlightDTO flightDTO = new FlightDTO(flight);

        // set additional information
        flightDTO.setAirbridges(fmsAirbridges);
        flightDTO.setCarousels(fmsCarousels);
        flightDTO.setCheckinDesks(fmsCheckinDesks);
        flightDTO.setChocks(fmsChocks);
        flightDTO.setChutes(fmsChutes);
        flightDTO.setDelays(fmsDelays);
        flightDTO.setEntireRoutes(fmsEntireRoutes);
        flightDTO.setGates(fmsGates);
        flightDTO.setServices(fmsServices);
        flightDTO.setStands(fmsStands);
        flightDTO.setVips(fmsVips);


        Long scheduleTimestamp = getScheduleTime(flight);

        if (null != fmsEntireRoutes && !fmsEntireRoutes.isEmpty()) {
            List<EntireRouteDTO> entireRouteDTOList = new ArrayList<>();
            for (FmsEntireRoute entireRoute : fmsEntireRoutes) {
                if (null != entireRoute) {
                    String airportCode = entireRoute.getAirportCode();
                    if (null != airportCode && !airportCode.isEmpty() && null != airportCache) {
                        List<RdmsAirport> rdmsAirportList = airportCache.get(airportCode);
                        List<RdmsAirport> currentAirportList = new ArrayList<>();
                        if (null != scheduleTimestamp && null != rdmsAirportList && !rdmsAirportList.isEmpty()) {
                            for (RdmsAirport airport : rdmsAirportList) {
                                Long inspireTime = airport.getInspireTime();
                                Long expireTime = airport.getExpireTime();
                                if (null != inspireTime && null != expireTime) {
                                    if (scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                                        currentAirportList.add(airport);
                                    }
                                }
                            }
                        }
                        EntireRouteDTO entireRouteDTO = new EntireRouteDTO(entireRoute);
                        if (currentAirportList.isEmpty()) {
                            // If this reference data does not exist in DB, set these fields to "".
                            log.warn("The related record does not exist in the database, airport IATA code = [{}]. The airport name in entire route will be set to an empty string.", airportCode);
                            entireRouteDTO.setAirportName_cn("");
                            entireRouteDTO.setAirportName_en("");
                        } else if (currentAirportList.size() > 1) {
                            // If there are multiple reference data in DB, set these fields to iata code.
                            log.warn("There are multiple related records in the database, airport IATA code = [{}]. The airport name in entire route will be set to it's IATA code.", airportCode);
                            entireRouteDTO.setAirportName_cn(airportCode);
                            entireRouteDTO.setAirportName_en(airportCode);
                        } else {
                            RdmsAirport airport = currentAirportList.get(0);
                            String airPortNameCn = airport.getLocalDescription();
                            String airPortNameEn = airport.getDescription();
                            entireRouteDTO.setAirportName_cn(airPortNameCn);
                            entireRouteDTO.setAirportName_en(airPortNameEn);
                        }
                        entireRouteDTOList.add(entireRouteDTO);
                    }
                }
            }
            flightDTO.setEntireRouteDTOList(entireRouteDTOList);
        }

        if (null != fmsVips && !fmsVips.isEmpty()) {
            List<VipInformationDTO> vipInformationDTOList = new ArrayList<>();
            for (FmsVip vip : fmsVips) {
                if (null != vip) {
                    String vipCode = vip.getVipCode();
                    if (null != vipCode && !vipCode.isEmpty() && null != vipCache) {
                        List<RdmsVipPersonnal> rdmsVipPersonnalList = vipCache.get(vipCode);
                        List<RdmsVipPersonnal> currentVipPersonnalList = new ArrayList<>();
                        if (null != scheduleTimestamp && null != rdmsVipPersonnalList && !rdmsVipPersonnalList.isEmpty()) {
                            for (RdmsVipPersonnal vipPersonnal : rdmsVipPersonnalList) {
                                Long inspireTime = vipPersonnal.getInspireTime();
                                Long expireTime = vipPersonnal.getExpireTime();
                                if (null != inspireTime && null != expireTime) {
                                    if (scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                                        currentVipPersonnalList.add(vipPersonnal);
                                    }
                                }
                            }
                            VipInformationDTO vipInformationDTO = new VipInformationDTO(vip);
                            if (currentVipPersonnalList.isEmpty()) {
                                // If this reference data does not exist in DB, set these fields to "".
                                log.warn("The related record does not exist in the database, vip code = [{}]. The name of vip will be set to an empty string.", vipCode);
                                vipInformationDTO.setFirstName("");
                                vipInformationDTO.setLastName("");

                            } else if (currentVipPersonnalList.size() > 1) {
                                // If there are multiple reference data in DB, set these fields to iata code.
                                log.warn("There are multiple related records in the database, vip code = [{}]. The name of vip will be set to it's code.", vipCode);
                                vipInformationDTO.setFirstName(vipCode);
                                vipInformationDTO.setLastName(vipCode);
                            } else {
                                RdmsVipPersonnal rdmsVipPersonnal = currentVipPersonnalList.get(0);
                                String firstName = rdmsVipPersonnal.getVipPersonFirstname();
                                String lastName = rdmsVipPersonnal.getVipPersonLastname();
                                vipInformationDTO.setFirstName(firstName);
                                vipInformationDTO.setLastName(lastName);
                            }
                            vipInformationDTOList.add(vipInformationDTO);
                        }
                    }
                }
            }
            flightDTO.setVipInformationDTOList(vipInformationDTOList);
        }

        if (null != fmsDelays && !fmsDelays.isEmpty()) {
            List<DelayInformationDTO> delayInformationDTOList = new ArrayList<>();
            for (FmsDelay fmsDelay : fmsDelays) {
                if (null != fmsDelay) {
                    String delayCode = fmsDelay.getDelayCode();
                    if (null != delayCode && !delayCode.isEmpty() && null != delayTypeCache) {
                        List<RdmsDelayType> rdmsDelayTypeList = delayTypeCache.get(delayCode);
                        List<RdmsDelayType> currentDelayTypeList = new ArrayList<>();
                        if (null != scheduleTimestamp && null != rdmsDelayTypeList && !rdmsDelayTypeList.isEmpty()) {
                            for (RdmsDelayType delayType : rdmsDelayTypeList) {
                                Long inspireTime = delayType.getInspireTime();
                                Long expireTime = delayType.getExpireTime();
                                if (null != inspireTime && null != expireTime) {
                                    if (scheduleTimestamp >= inspireTime && scheduleTimestamp <= expireTime) {
                                        currentDelayTypeList.add(delayType);
                                    }
                                }
                            }
                            DelayInformationDTO delayInformationDTO = new DelayInformationDTO(fmsDelay);
                            if (currentDelayTypeList.isEmpty()) {
                                // If this reference data does not exist in DB, set these fields to "".
                                log.warn("The related record does not exist in the database, delay type code = [{}]. The description in the delay information will be set to an empty string.", delayCode);
                                delayInformationDTO.setDelay_cn("");
                                delayInformationDTO.setDelay_en("");
                            } else if (currentDelayTypeList.size() > 1) {
                                // If there are multiple reference data in DB, set these fields to iata code.
                                log.warn("There are multiple related records in the database, delay type code = [{}]. The description in the delay information will be set to it's code.", delayCode);
                                delayInformationDTO.setDelay_cn(delayCode);
                                delayInformationDTO.setDelay_en(delayCode);
                            } else {
                                RdmsDelayType rdmsDelayType = currentDelayTypeList.get(0);
                                String delayCn = rdmsDelayType.getLocalDescription();
                                String delayEn = rdmsDelayType.getDescription();
                                delayInformationDTO.setDelay_cn(delayCn);
                                delayInformationDTO.setDelay_en(delayEn);
                            }
                            delayInformationDTOList.add(delayInformationDTO);
                        }
                    }
                }
            }
            flightDTO.setDelayInformationDTOList(delayInformationDTOList);
        }

        // fill additional fields
        fillAdditionalFields(flightDTO);

        return flightDTO;
    }

    /**
     * Convert the list to a string separated by ", "
     *
     * @param infoList - entity list
     * @return - a string separated by ", "
     */
    private String listToString(List<? extends AdditionFlightInfo> infoList) {
        if (null == infoList || infoList.isEmpty()) {
            return null;
        }
        StringBuilder infoString = new StringBuilder();
        for (AdditionFlightInfo info : infoList) {
            infoString.append(info.obtainCode()).append(", ");
        }
        // remove the last ", " symbol
        if (infoString.length() > 0) {
            infoString.delete(infoString.length() - 2, infoString.length());
        }
        return infoString.toString();
    }

    /**
     * Get previous or next stop information.
     *
     * @param flightDTO - current flight
     * @return - previous or next stop
     */
    private FmsEntireRoute getPreviousOrNextStop(FlightDTO flightDTO) {
        // sanity check
        if (null == flightDTO) {
            return null;
        }
        List<FmsEntireRoute> entireRoutes = flightDTO.getEntireRoutes();
        if (null == entireRoutes || entireRoutes.isEmpty()) {
            return null;
        }
        // sort the route based on sequence number
        Collections.sort(entireRoutes);
        FmsFlight flight = flightDTO.getFlight();
        if (null == flight) {
            return null;
        }
        String movementIndicator = flight.getMovementIndicator();
        Integer ctuSequence;
        if (movement_indicator_arrival.equals(movementIndicator)) {
            // For the arrival flight, look for the CTU in order from the second stop.
            ctuSequence = getCtuSequence(entireRoutes, 1);
            // If found, its previous stop is the departure airport.
            if (null != ctuSequence && ctuSequence - 1 >= 0) {
                return entireRoutes.get(ctuSequence - 1);
            }
        }
        if (movement_indicator_departure.equals(movementIndicator)) {
            // For the departure flight, look for the CTU in order from the first stop.
            ctuSequence = getCtuSequence(entireRoutes, 0);
            // If found, its next stop is the arrival airport.
            if (null != ctuSequence && ctuSequence + 1 < entireRoutes.size()) {
                return entireRoutes.get(ctuSequence + 1);
            }
        }
        return null;
    }

    /**
     * Traverse the list of routes to get sequence number of the first CTU.
     *
     * @param entireRoutes - list of routes
     * @param startIndex   - start index of the traversal
     * @return - sequence number of the first CTU
     */
    private Integer getCtuSequence(List<FmsEntireRoute> entireRoutes, int startIndex) {
        if (null == entireRoutes || entireRoutes.isEmpty() || startIndex > entireRoutes.size() - 1) {
            return null;
        }
        for (int i = startIndex; i < entireRoutes.size(); i++) {
            if (currentStationIATACode.equals(entireRoutes.get(i).getAirportCode())) {
                return i;
            }
        }
        return null;
    }

    /***
     * Get flight by aodb id.
     * @param aodbId Flight aodb id
     * @return Flight
     */
    @Override
    public FmsFlight getByAodbId(Long aodbId) {
        return fmsFlightMapper.selectByAodbId(aodbId);
    }
}
