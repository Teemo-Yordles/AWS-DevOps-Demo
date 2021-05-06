/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsCountryDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsCountry;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsCountryDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsCountryMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsCountryDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsCountryDAOImpl</b> is an implementation of RdmsCountryDAO interface,
 * which is used to access RdmsCountry data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsCountryDAOImpl extends BaseDAOImpl<RdmsCountry> implements RdmsCountryDAO {

    private final RdmsCountryMapper rdmsCountryMapper;

    @Autowired
    public RdmsCountryDAOImpl(RdmsCountryMapper rdmsCountryMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsCountryMapper = rdmsCountryMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsCountry insertInNewTx(RdmsCountry country) {
        rdmsCountryMapper.insert(country);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(country), country);
        }
        return country;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsCountryMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsCountry newRecord, RdmsCountry oldRecord) {
        RdmsCountry record = new RdmsCountry();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("countryCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsCountryDynamicSqlSupport.class, rdmsCountry, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsCountryMapper.update(updateStatement);

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
     * @return list of RdmsCountry entities.
     */
    @Override
    public Collection<RdmsCountry> findAll() {
        boolean isCached = false;
        Collection<RdmsCountry> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsCountryMapper.selectByExample().build().execute();
        }

        // cache the result if not cached and there are some records to cache
        if (null != records && !isCached) {
            cacheAll(records);
        }
        return records;
    }

    /**
     * get map entry key string based on the record given
     *
     * @param record - map entry value to be cached
     */
    @Override
    public String getMapEntryKey(RdmsCountry record) {
        return String.valueOf(record.getId().longValue());
    }

    /**
     * get map entries based on country code
     *
     * @param currentCountryCode country code
     * @param inspire            inspire time
     * @param expire             expire time
     * @return map entries
     */
    @Override
    public List<RdmsCountry> selectByCountryCode(String currentCountryCode, Long inspire, Long expire) {
        return rdmsCountryMapper.selectByCountryCode(currentCountryCode, inspire, expire);
    }
}
