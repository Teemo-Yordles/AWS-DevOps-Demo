/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsDelayTypeDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsDelayType;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsDelayTypeDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsDelayTypeMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsDelayTypeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsDelayTypeDAOImpl</b> is an implementation of RdmsDelayTypeDAO interface,
 * which is used to access RdmsDelayType data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsDelayTypeDAOImpl extends BaseDAOImpl<RdmsDelayType> implements RdmsDelayTypeDAO {

    private final RdmsDelayTypeMapper rdmsDelayTypeMapper;

    @Autowired
    public RdmsDelayTypeDAOImpl(RdmsDelayTypeMapper rdmsDelayTypeMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsDelayTypeMapper = rdmsDelayTypeMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsDelayType insertInNewTx(RdmsDelayType delayType) {
        rdmsDelayTypeMapper.insert(delayType);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(delayType), delayType);
        }
        return delayType;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsDelayTypeMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsDelayType newRecord, RdmsDelayType oldRecord) {
        RdmsDelayType record = new RdmsDelayType();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("delayTypeCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsDelayTypeDynamicSqlSupport.class, rdmsDelayType, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsDelayTypeMapper.update(updateStatement);

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
     * @return list of RdmsDelayType entities.
     */
    @Override
    public Collection<RdmsDelayType> findAll() {
        boolean isCached = false;
        Collection<RdmsDelayType> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsDelayTypeMapper.selectByExample().build().execute();
        }

        // cache the result if not cached and there are some records to cache
        if (null != records && !isCached) {
            cacheAll(records);
        }
        return records;
    }

    /***
     * Get RdmsDelayType entity by delay type code.
     * @param typeCode delay type code
     * @return RdmsDelayType entity
     */
    @Override
    public List<RdmsDelayType> findByDelayTypeCode(String typeCode) {
        return rdmsDelayTypeMapper.selectByDelayTypeCode(typeCode);
    }

    /**
     * get map entry key string based on the record given
     *
     * @param record - map entry value to be cached
     */
    @Override
    public String getMapEntryKey(RdmsDelayType record) {
        return String.valueOf(record.getId().longValue());
    }
}
