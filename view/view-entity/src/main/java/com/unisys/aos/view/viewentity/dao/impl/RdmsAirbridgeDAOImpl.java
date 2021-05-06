/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsAirbridgeDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAirbridge;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsAirbridgeDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsAirbridgeMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsAirbridgeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsAirbridgeDAOImpl</b> is an implementation of RdmsAirbridgeDAO interface,
 * which is used to access RdmsAirbridge data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsAirbridgeDAOImpl extends BaseDAOImpl<RdmsAirbridge> implements RdmsAirbridgeDAO {

    private final RdmsAirbridgeMapper rdmsAirbridgeMapper;

    @Autowired
    public RdmsAirbridgeDAOImpl(RdmsAirbridgeMapper rdmsAirbridgeMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsAirbridgeMapper = rdmsAirbridgeMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsAirbridge insertInNewTx(RdmsAirbridge airbridge) {
        rdmsAirbridgeMapper.insert(airbridge);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(airbridge), airbridge);
        }
        return airbridge;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsAirbridgeMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsAirbridge newRecord, RdmsAirbridge oldRecord) {
        RdmsAirbridge record = new RdmsAirbridge();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("airbridgeCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsAirbridgeDynamicSqlSupport.class, rdmsAirbridge, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsAirbridgeMapper.update(updateStatement);

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
     * @return list of RdmsAirbridge entities.
     */
    @Override
    public Collection<RdmsAirbridge> findAll() {
        boolean isCached = false;
        Collection<RdmsAirbridge> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsAirbridgeMapper.selectByExample().build().execute();
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
    public String getMapEntryKey(RdmsAirbridge record) {
        return String.valueOf(record.getId().longValue());
    }

    /**
     * get map entries based on airbridge code
     *
     * @param currentAirbridgeCode airbridge code
     * @param inspire              inspire time
     * @param expire               expire time
     * @return map entries
     */
    @Override
    public List<RdmsAirbridge> selectByAirbridgeCode(String currentAirbridgeCode, Long inspire, Long expire) {
        return rdmsAirbridgeMapper.selectByAirbridgeCode(currentAirbridgeCode, inspire, expire);
    }
}
