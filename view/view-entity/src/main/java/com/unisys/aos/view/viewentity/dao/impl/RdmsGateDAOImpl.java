/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsGateDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsGate;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsGateDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsGateMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsGateDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsGateDAOImpl</b> is an implementation of RdmsGateDAO interface,
 * which is used to access RdmsGate data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsGateDAOImpl extends BaseDAOImpl<RdmsGate> implements RdmsGateDAO {

    private final RdmsGateMapper rdmsGateMapper;

    @Autowired
    public RdmsGateDAOImpl(RdmsGateMapper rdmsGateMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsGateMapper = rdmsGateMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsGate insertInNewTx(RdmsGate gate) {
        rdmsGateMapper.insert(gate);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(gate), gate);
        }
        return gate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsGateMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsGate newRecord, RdmsGate oldRecord) {
        RdmsGate record = new RdmsGate();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("gateCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsGateDynamicSqlSupport.class, rdmsGate, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsGateMapper.update(updateStatement);

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
     * @return list of RdmsGate entities.
     */
    @Override
    public Collection<RdmsGate> findAll() {
        boolean isCached = false;
        Collection<RdmsGate> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsGateMapper.selectByExample().build().execute();
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
    public String getMapEntryKey(RdmsGate record) {
        return String.valueOf(record.getId().longValue());
    }

    /**
     * get map entries based on gate code
     *
     * @param currentGateCode gate code
     * @param inspire         inspire time
     * @param expire          expire time
     * @return map entries
     */
    @Override
    public List<RdmsGate> findByGateCode(String currentGateCode, Long inspire, Long expire) {
        return rdmsGateMapper.selectByGateCode(currentGateCode, inspire, expire);
    }

    /**
     * get map entries based on terminal code
     *
     * @param currentTerminalCode terminal code
     * @param inspire             inspire time
     * @param expire              expire time
     * @return map entries
     */
    @Override
    public List<RdmsGate> findByTerminalCode(String currentTerminalCode, Long inspire, Long expire) {
        return rdmsGateMapper.selectByTerminalCode(currentTerminalCode, inspire, expire);
    }

    /**
     * get map entries based on pier code
     *
     * @param currentPierCode pier code
     * @param inspire         inspire time
     * @param expire          expire time
     * @return map entries
     */
    @Override
    public List<RdmsGate> findByPierCode(String currentPierCode, Long inspire, Long expire) {
        return rdmsGateMapper.selectByPierCode(currentPierCode, inspire, expire);
    }

    /***
     * Get RdmsGate entity by gate code.
     *
     * @param code gate code
     * @return RdmsGate entries
     */
    @Override
    public List<RdmsGate> findByGateCode(String code) {
        return rdmsGateMapper.selectByGateCode(code);
    }
}
