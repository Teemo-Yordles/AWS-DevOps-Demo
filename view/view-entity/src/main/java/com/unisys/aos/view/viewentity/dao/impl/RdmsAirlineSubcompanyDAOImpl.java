/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsAirlineSubcompanyDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAirlineSubcompany;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsAirlineSubcompanyDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsAirlineSubcompanyMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsAirlineSubcompanyDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsAirlineSubcompanyDAOImpl</b> is an implementation of RdmsAirlineSubcompanyDAO interface,
 * which is used to access RdmsAirlineSubcompany data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsAirlineSubcompanyDAOImpl extends BaseDAOImpl<RdmsAirlineSubcompany> implements RdmsAirlineSubcompanyDAO {

    private final RdmsAirlineSubcompanyMapper rdmsAirlineSubcompanyMapper;

    @Autowired
    public RdmsAirlineSubcompanyDAOImpl(RdmsAirlineSubcompanyMapper rdmsAirlineSubcompanyMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsAirlineSubcompanyMapper = rdmsAirlineSubcompanyMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsAirlineSubcompany insertInNewTx(RdmsAirlineSubcompany airlineSubcompany) {
        rdmsAirlineSubcompanyMapper.insert(airlineSubcompany);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(airlineSubcompany), airlineSubcompany);
        }
        return airlineSubcompany;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsAirlineSubcompanyMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsAirlineSubcompany newRecord, RdmsAirlineSubcompany oldRecord) {
        RdmsAirlineSubcompany record = new RdmsAirlineSubcompany();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("airlineSubcompanyCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsAirlineSubcompanyDynamicSqlSupport.class, rdmsAirlineSubcompany, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsAirlineSubcompanyMapper.update(updateStatement);

            // update to cache
            if (redisUtil.exists(HASH_KEY)) {
                redisUtil.hset(HASH_KEY, getMapEntryKey(newRecord), newRecord);
            }
        } else {
            log.error("Update failed, old record[{}], new record[{}]", oldRecord, newRecord);
        }
    }

    /**
     * Get all table records for a target table.
     *
     * @return list of RdmsAirlineSubcompany entities.
     */
    @Override
    public Collection<RdmsAirlineSubcompany> findAll() {
        boolean isCached = false;
        Collection<RdmsAirlineSubcompany> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsAirlineSubcompanyMapper.selectByExample().build().execute();
        }

        // cache the result if not cached and there are some records to cache
        if (null != records && !isCached) {
            cacheAll(records);
        }
        return records;
    }

    public Collection<RdmsAirlineSubcompany> findByCode(String code, Long inspire, Long expire) {
        return selectByAirlineCode(code, inspire, expire);
    }

    /**
     * get map entry key string based on the record given
     *
     * @param record - map entry value to be cached
     */
    @Override
    public String getMapEntryKey(RdmsAirlineSubcompany record) {
        return String.valueOf(record.getId().longValue());
    }

    /**
     * get map entries based on Airline IATA Operator Code
     *
     * @param currentAirlineCode Airline IATA Operator Code
     * @param inspire            inspire time
     * @param expire             expire time
     * @return map entries
     */
    @Override
    public List<RdmsAirlineSubcompany> selectByAirlineCode(String currentAirlineCode, Long inspire, Long expire) {
        return rdmsAirlineSubcompanyMapper.selectByAirlineCode(currentAirlineCode, inspire, expire);
    }

    /**
     * delete airline subcompany through Airline IATA Operator Code
     *
     * @param currentAirlineCode Airline IATA Operator Code
     * @param inspire            inspire time
     * @param expire             expire time
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int deleteByAirlineCodeInNexTx(String currentAirlineCode, Long inspire, Long expire) {
        return rdmsAirlineSubcompanyMapper.deleteByAirlineCode(currentAirlineCode, inspire, expire);
    }
}
