/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsAbnormalStatusDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAbnormalStatus;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsAbnormalStatusDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsAbnormalStatusMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsAbnormalStatusDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsAbnormalStatusDAOImpl</b> is an implementation of RdmsAbnormalStatusDAO interface,
 * which is used to access RdmsAbnormalStatus data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsAbnormalStatusDAOImpl extends BaseDAOImpl<RdmsAbnormalStatus> implements RdmsAbnormalStatusDAO {

    private final RdmsAbnormalStatusMapper rdmsAbnormalStatusMapper;

    @Autowired
    public RdmsAbnormalStatusDAOImpl(RdmsAbnormalStatusMapper rdmsAbnormalStatusMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsAbnormalStatusMapper = rdmsAbnormalStatusMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsAbnormalStatus insertInNewTx(RdmsAbnormalStatus abnormalStatus) {
        rdmsAbnormalStatusMapper.insert(abnormalStatus);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(abnormalStatus), abnormalStatus);
        }
        return abnormalStatus;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsAbnormalStatusMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsAbnormalStatus newRecord, RdmsAbnormalStatus oldRecord) {
        RdmsAbnormalStatus record = new RdmsAbnormalStatus();
        record.setId(oldRecord.getId());
        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("abnormalReasonCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsAbnormalStatusDynamicSqlSupport.class, rdmsAbnormalStatus, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsAbnormalStatusMapper.update(updateStatement);

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
     * @return list of RdmsAbnormalStatus entities.
     */
    @Override
    public Collection<RdmsAbnormalStatus> findAll() {
        boolean isCached = false;
        Collection<RdmsAbnormalStatus> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsAbnormalStatusMapper.selectByExample().build().execute();
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
    public String getMapEntryKey(RdmsAbnormalStatus record) {
        return String.valueOf(record.getId().longValue());
    }
}
