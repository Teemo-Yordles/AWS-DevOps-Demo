/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsFlightIndicatorDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsFlightIndicator;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsFlightIndicatorDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsFlightIndicatorMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsFlightIndicatorDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsFlightIndicatorDAOImpl</b> is an implementation of RdmsFlightIndicatorDAO interface,
 * which is used to access RdmsFlightIndicator data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/12/07 15:12
 */
@Repository
@Slf4j
public class RdmsFlightIndicatorDAOImpl extends BaseDAOImpl<RdmsFlightIndicator> implements RdmsFlightIndicatorDAO {

    private final RdmsFlightIndicatorMapper rdmsFlightIndicatorMapper;

    @Autowired
    public RdmsFlightIndicatorDAOImpl(RdmsFlightIndicatorMapper rdmsFlightIndicatorMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsFlightIndicatorMapper = rdmsFlightIndicatorMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsFlightIndicator insertInNewTx(RdmsFlightIndicator status) {
        rdmsFlightIndicatorMapper.insert(status);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(status), status);
        }
        return status;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsFlightIndicatorMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsFlightIndicator newRecord, RdmsFlightIndicator oldRecord) {
        RdmsFlightIndicator record = new RdmsFlightIndicator();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("indicatorCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsFlightIndicatorDynamicSqlSupport.class, rdmsFlightIndicator, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsFlightIndicatorMapper.update(updateStatement);

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
     * @return list of RdmsFlightIndicator entities.
     */
    @Override
    public Collection<RdmsFlightIndicator> findAll() {
        boolean isCached = false;
        Collection<RdmsFlightIndicator> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsFlightIndicatorMapper.selectByExample().build().execute();
        }

        // cache the result if not cached and there are some records to cache
        if (null != records && !isCached) {
            cacheAll(records);
        }
        return records;
    }

    /***
     * Get map entries based on indicator code
     * @param instanceCode flight indicator code
     * @return map entries
     */
    @Override
    public List<RdmsFlightIndicator> findByIndicatorCode(String instanceCode) {
        return rdmsFlightIndicatorMapper.selectByIndicatorCode(instanceCode);
    }

    /**
     * get map entry key string based on the record given
     *
     * @param record - map entry value to be cached
     */
    @Override
    public String getMapEntryKey(RdmsFlightIndicator record) {
        return String.valueOf(record.getId().longValue());
    }
}
