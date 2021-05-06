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
import com.unisys.aos.view.viewentity.dao.RdmsAirportDAO;
import com.unisys.aos.view.viewentity.dao.RdmsCityDAO;
import com.unisys.aos.view.viewentity.dao.SeasonFlightDAO;
import com.unisys.aos.view.viewentity.dto.SeasonEntireRouteDTO;
import com.unisys.aos.view.viewentity.dto.SeasonFlightDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsSeasonEntireRoute;
import com.unisys.aos.view.viewentity.entity.flight.FmsSeasonFlight;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAirport;
import com.unisys.aos.view.viewentity.entity.reference.RdmsCity;
import com.unisys.aos.view.viewentity.mapper.flight.FmsSeasonEntireRouteMapper;
import com.unisys.aos.view.viewentity.mapper.flight.FmsSeasonFlightDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.flight.FmsSeasonFlightMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.unisys.aos.view.viewentity.mapper.flight.FmsFlightDynamicSqlSupport.id;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsFlightDynamicSqlSupport.updateTime;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsSeasonFlightDynamicSqlSupport.fmsSeasonFlight;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>SeasonFlightDAOImpl</b> is an implementation of SeasonFlightDAO interface,
 * which is used to access SeasonFlightDTO.
 *
 * @author Zhang Wenqiang
 * @since 2020/11/11 20:50
 */
@Repository
@Slf4j
public class SeasonFlightDAOImpl extends BaseDAOImpl<SeasonFlightDTO> implements SeasonFlightDAO {
    @Autowired
    private FmsSeasonFlightMapper fmsSeasonFlightMapper;
    @Autowired
    private FmsSeasonEntireRouteMapper fmsSeasonEntireRouteMapper;
    @Autowired
    private RdmsAirportDAO rdmsAirportDAO;
    @Autowired
    private RdmsCityDAO rdmsCityDAO;

    // following are the reference data cache
    private Map<String, RdmsAirport> airportCache;
    private Map<String, RdmsCity> cityCache;

    @Autowired
    public SeasonFlightDAOImpl(RedisUtil redisUtil) {
        super(redisUtil);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SeasonFlightDTO insertInNewTx(SeasonFlightDTO seasonFlightDTO) {
        if (null == seasonFlightDTO) {
            return null;
        }
        fmsSeasonFlightMapper.insert(seasonFlightDTO.getSeasonFlight());

        if (null != seasonFlightDTO.getSeasonEntireRoutes() && !seasonFlightDTO.getSeasonEntireRoutes().isEmpty()) {
            for (FmsSeasonEntireRoute record : seasonFlightDTO.getSeasonEntireRoutes()) {
                record.setFlightId(seasonFlightDTO.getId());
                fmsSeasonEntireRouteMapper.insert(record);
            }
        }

        // insert to cache
        fillAdditionalFields(seasonFlightDTO);
        redisUtil.hset(HASH_KEY, getMapEntryKey(seasonFlightDTO), seasonFlightDTO);

        return seasonFlightDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        fmsSeasonFlightMapper.deleteByPrimaryKey(id);
        fmsSeasonEntireRouteMapper.deleteByFlightId(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteBySeasonDate(Date startDate, Date endDate) {
        List<FmsSeasonFlight> seasonFlightList = fmsSeasonFlightMapper.selectBySeasonDate(startDate, endDate);
        deleteSeasonFlight(seasonFlightList);
    }

    private void deleteSeasonFlight(List<FmsSeasonFlight> seasonFlightList) {
        if (null != seasonFlightList && !seasonFlightList.isEmpty()) {
            for (FmsSeasonFlight seasonFlight : seasonFlightList) {
                Long seasonFlightId = seasonFlight.getId();
                if (null != seasonFlightId) {
                    fmsSeasonFlightMapper.deleteByPrimaryKey(seasonFlightId);
                    fmsSeasonEntireRouteMapper.deleteByFlightId(seasonFlightId);
                    if (redisUtil.exists(HASH_KEY)) {
                        redisUtil.hdel(HASH_KEY, String.valueOf(seasonFlightId.longValue()));
                    }
                }
            }
        } else {
            log.error("Failed to delete earlier seasonal flight schedule, the parameter provided is null.");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Date getEarlierScheduleStartDate() {
        FmsSeasonFlight fmsSeasonFlight = fmsSeasonFlightMapper.selectLastSeasonSchedule();
        if (null != fmsSeasonFlight) {
            return fmsSeasonFlight.getSeasonStartDate();
        } else {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteEarlierSchedule(Date startDateFirst, Date startDateSecond) {
        if (null == startDateFirst || null == startDateSecond) {
            log.error("Failed to delete earlier seasonal flight schedule, since there is null in parameters, startDateFirst = {}, startDateSecond = {}.", startDateFirst, startDateSecond);
            return;
        }
        List<FmsSeasonFlight> earlierScheduleList = fmsSeasonFlightMapper.selectEarlierScheduleByStartDate(startDateFirst, startDateSecond);
        deleteSeasonFlight(earlierScheduleList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(SeasonFlightDTO newRecord, SeasonFlightDTO oldRecord) {
        // sanity check
        if (null == newRecord || null == oldRecord) {
            log.error("Flight update [updateInNewTx] should be receives null variable.");
            return;
        }

        // check if basic flight information is different, if yes update FmsFlight object
        FmsSeasonFlight newFlightBasic = newRecord.getSeasonFlight();
        FmsSeasonFlight currentFlightBasic = oldRecord.getSeasonFlight();
        if (null != newFlightBasic && !newFlightBasic.equals(currentFlightBasic)) {
            updateFmsSeasonFlight(newFlightBasic, currentFlightBasic);
        }

        // check if routes difference and update to DB
        List<FmsSeasonEntireRoute> newRoutes = newRecord.getSeasonEntireRoutes();
        List<FmsSeasonEntireRoute> oldRoutes = oldRecord.getSeasonEntireRoutes();
        if (null != newRoutes && !ListUtil.isListEqual(newRoutes, oldRoutes)) {
            newRecord.setSeasonEntireRoutes(updateAdditionFlightInfo(newRoutes, oldRoutes));
        }

        // update to cache
        if (redisUtil.exists(HASH_KEY)) {
            fillAdditionalFields(newRecord);
            redisUtil.hset(HASH_KEY, getMapEntryKey(newRecord), newRecord);
        }
    }

    /**
     * Update flight additional information
     * For new element in the records, need to add to the table.
     * For updated element in the records, need to update table records.
     * For extra elements in the current record list, need to delete them.
     *
     * @param newRecords - list of new records
     * @param oldRecords - list of current records
     * @return updated route list
     */
    private List<FmsSeasonEntireRoute> updateAdditionFlightInfo(List<FmsSeasonEntireRoute> newRecords, List<FmsSeasonEntireRoute> oldRecords) {
        List<FmsSeasonEntireRoute> toBeRemoved = null;
        List<FmsSeasonEntireRoute> updatedRoutes = new ArrayList<>();
        if (null != oldRecords) {
            toBeRemoved = new ArrayList<>(oldRecords);
        }
        // new records with ID must be existing records
        // new records without ID must be new records
        // remove duplicate one from toBeRemoved, and delete all left records.
        if (null != newRecords) {
            for (FmsSeasonEntireRoute record : newRecords) {
                FmsSeasonEntireRoute oldRecord;
                boolean exist = false;
                if (null != toBeRemoved) {
                    Iterator<FmsSeasonEntireRoute> iterator = toBeRemoved.iterator();
                    while (iterator.hasNext()) {
                        oldRecord = iterator.next();
                        if (oldRecord.equals(record)) {
                            iterator.remove();
                            updatedRoutes.add(oldRecord);
                            exist = true;
                            break;
                        }
                    }
                }
                if (!exist) {
                    record.setCreateTime(new Date());
                    fmsSeasonEntireRouteMapper.insert(record);
                    updatedRoutes.add(record);
                }

            }
        }
        // delete
        if (null != toBeRemoved && !toBeRemoved.isEmpty()) {
            for (FmsSeasonEntireRoute record : toBeRemoved) {
                fmsSeasonEntireRouteMapper.deleteByPrimaryKey(record.getId());
            }
        }
        return updatedRoutes;
    }

    /**
     * Update new flight basic information object to database.
     *
     * @param newRecord - new season flight record
     * @param oldRecord - current season flight record
     */
    private void updateFmsSeasonFlight(FmsSeasonFlight newRecord, FmsSeasonFlight oldRecord) {
        FmsSeasonFlight record = new FmsSeasonFlight();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, FmsSeasonFlightDynamicSqlSupport.class, fmsSeasonFlight, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            fmsSeasonFlightMapper.update(updateStatement);
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, newRecord);
        }
    }

    /**
     * Get all table records for a target table.
     *
     * @return list of SeasonFlightDTO entities.
     */
    @Override
    public Collection<SeasonFlightDTO> findAll() {
        // double check reference loading status
        // in case reference is not loaded before the flight processing.
        if (null == airportCache || null == cityCache) {
            loadReferenceDataCache();
        }

        boolean isCached = false;
        Collection<SeasonFlightDTO> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = findAllFromDB();
        }

        // cache the result if not cached and there are some records to cache
        if (null != records && !isCached) {
            cacheAll(records);
        }
        return records;
    }

    /**
     * Build all SeasonFlightDTOs from database directly.
     * This is used to create season flight DTOs to be cached
     * and returned back to front-end.
     *
     * @return All season flight DTOs
     */
    public Collection<SeasonFlightDTO> findAllFromDB() {
        List<FmsSeasonFlight> flights = fmsSeasonFlightMapper.selectByExample().build().execute();
        List<SeasonFlightDTO> seasonFlightDTOList = new ArrayList<>();
        flights.forEach(item -> {
            Long flightId = item.getId();
            List<FmsSeasonEntireRoute> fmsSeasonEntireRoutes = fmsSeasonEntireRouteMapper.selectByFlightId(flightId);

            SeasonFlightDTO seasonFlightDTO=new SeasonFlightDTO(item, fmsSeasonEntireRoutes);
            fillEntireRouteDTOList(seasonFlightDTO);
            seasonFlightDTOList.add(seasonFlightDTO);
        });

        return seasonFlightDTOList;
    }


    /**
     * build a map <flightId, relatedFlightData> from the season flight data list.
     * one season flight ID could related to multiple records, so the map value is a list.
     *
     * @param flightDataList - list of specific flight data for example flight stand allocation data list.
     * @param getKey         - function to get flight ID
     * @param <T>            - flight data class type
     * @return - a map of <Id,List<FlightData>>
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
     * get map entry key string based on the record given
     *
     * @param record - map entry value to be cached
     */
    @Override
    public String getMapEntryKey(SeasonFlightDTO record) {
        return String.valueOf(record.getId());
    }

    /**
     * This method is call after the flight is persisted to DB
     * before save to Redis cache.
     */
    public void fillAdditionalFields(SeasonFlightDTO record) {
        fillFullRouteFields(record);
        fillEntireRouteDTOList(record);
    }

    /**
     * Generate all full route such as "北京-成都-上海" and "PEK-CTU-SHA"
     *
     * @param seasonFlight - SeasonFlightDTO object
     */
    private void fillFullRouteFields(SeasonFlightDTO seasonFlight) {
        // sanity check
        List<FmsSeasonEntireRoute> entireRoutes = seasonFlight.getSeasonEntireRoutes();
        if (null == entireRoutes || entireRoutes.isEmpty()) {
            return;
        }
        // sort the route based on sequence number
        Collections.sort(entireRoutes);
        // build full route
        StringBuilder localLangRoute = new StringBuilder();
        StringBuilder codeRoute = new StringBuilder();
        for (FmsSeasonEntireRoute route : entireRoutes) {
            String iataCode = route.getAirportCode();
            codeRoute.append(iataCode).append("-");
            String localCityName = iataCode;
            // get city local description from airport iata code
            if (null != airportCache) {
                RdmsAirport airport = airportCache.get(iataCode);
                if (null != airport) {
                    String cityCode = airport.getCityCode();
                    if (null != cityCode && null != cityCache) {
                        RdmsCity city = cityCache.get(cityCode);
                        if (null != city && null != city.getLocalDescription()) {
                            localCityName = city.getLocalDescription();
                        }
                    }
                }
            }
            localLangRoute.append(localCityName).append("-");
        }
        // remove the last "-" symbol
        if (localLangRoute.length() > 0) {
            localLangRoute.deleteCharAt(localLangRoute.length() - 1);
        }
        if (codeRoute.length() > 0) {
            codeRoute.deleteCharAt(codeRoute.length() - 1);
        }
        seasonFlight.setLocalLanguageFullRoute(localLangRoute.toString());
        seasonFlight.setAirportCodeFullRoute(codeRoute.toString());
    }

    /**
     * This is to load the reference data cache
     * used during the SCHS processing.
     */
    @Override
    public void loadReferenceDataCache() {
        Collection<RdmsAirport> airports = rdmsAirportDAO.findAll();
        if (null != airports && !airports.isEmpty()) {
            airportCache = airports.stream()
                    .collect(Collectors.toConcurrentMap(RdmsAirport::getIataCode, (p) -> p));
        }
        Collection<RdmsCity> cities = rdmsCityDAO.findAll();
        if (null != cities && !cities.isEmpty()) {
            cityCache = cities.stream()
                    .collect(Collectors.toConcurrentMap(RdmsCity::getIataCityCode, (p) -> p));
        }
    }

    public void fillEntireRouteDTOList(SeasonFlightDTO seasonFlightDTO){
        List<FmsSeasonEntireRoute> entireRouteList = seasonFlightDTO.getSeasonEntireRoutes();
        List<SeasonEntireRouteDTO> seasonEntireRouteDTOList=new ArrayList<>();
        if(null != entireRouteList && !entireRouteList.isEmpty()){
            for (FmsSeasonEntireRoute entireRoute:entireRouteList) {
                if(null != entireRoute){
                    String airportCode = entireRoute.getAirportCode();
                    if(null != airportCode && !airportCode.isEmpty()){
                        if(null == airportCache){
                            loadReferenceDataCache();
                        }
                        if(null != airportCache){
                            RdmsAirport airport = airportCache.get(airportCode);
                            if(null != airport){
                                String airPortNameCn = airport.getLocalDescription();
                                String airPortNameEn = airport.getDescription();
                                SeasonEntireRouteDTO seasonEntireRouteDTO=new SeasonEntireRouteDTO(entireRoute);
                                seasonEntireRouteDTO.setAirportName_cn(airPortNameCn);
                                seasonEntireRouteDTO.setAirportName_en(airPortNameEn);
                                seasonEntireRouteDTOList.add(seasonEntireRouteDTO);
                            }
                        }
                    }
                }
            }
        }
        seasonFlightDTO.setEntireRouteDTOList(seasonEntireRouteDTOList);
    }
}
