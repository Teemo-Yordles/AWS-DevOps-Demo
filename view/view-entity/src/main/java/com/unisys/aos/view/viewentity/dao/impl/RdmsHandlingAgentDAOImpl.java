/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsHandlingAgentDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsHandlingAgent;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsHandlingAgentDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsHandlingAgentMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsHandlingAgentDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsHandlingAgentDAOImpl</b> is an implementation of RdmsHandlingAgentDAO interface,
 * which is used to access RdmsHandlingAgent data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsHandlingAgentDAOImpl extends BaseDAOImpl<RdmsHandlingAgent> implements RdmsHandlingAgentDAO {

    private final RdmsHandlingAgentMapper rdmsHandlingAgentMapper;

    @Autowired
    public RdmsHandlingAgentDAOImpl(RdmsHandlingAgentMapper rdmsHandlingAgentMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsHandlingAgentMapper = rdmsHandlingAgentMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsHandlingAgent insertInNewTx(RdmsHandlingAgent handlingAgent) {
        rdmsHandlingAgentMapper.insert(handlingAgent);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(handlingAgent), handlingAgent);
        }
        return handlingAgent;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsHandlingAgentMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsHandlingAgent newRecord, RdmsHandlingAgent oldRecord) {
        RdmsHandlingAgent record = new RdmsHandlingAgent();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("handlingAgentCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsHandlingAgentDynamicSqlSupport.class, rdmsHandlingAgent, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsHandlingAgentMapper.update(updateStatement);

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
     * @return list of RdmsHandlingAgent entities.
     */
    @Override
    public Collection<RdmsHandlingAgent> findAll() {
        boolean isCached = false;
        Collection<RdmsHandlingAgent> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsHandlingAgentMapper.selectByExample().build().execute();
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
    public String getMapEntryKey(RdmsHandlingAgent record) {
        return String.valueOf(record.getId().longValue());
    }
}
