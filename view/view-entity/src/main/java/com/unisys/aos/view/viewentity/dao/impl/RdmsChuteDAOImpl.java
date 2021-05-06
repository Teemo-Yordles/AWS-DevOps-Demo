/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsChuteDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsChute;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsChuteDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsChuteMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsChuteDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * 
 * The <b>ChuteDAOImpl</b> is an implementation of ChuteDAO interface,
 * which is used to access Chute data from database.
 *
 * @author jianglushuang
 * @since 2020/9/23 3:28 下午
 */
@Repository
@Slf4j
public class RdmsChuteDAOImpl extends BaseDAOImpl<RdmsChute> implements RdmsChuteDAO {

    private final RdmsChuteMapper rdmsChuteMapper;

    @Autowired
    public RdmsChuteDAOImpl(RdmsChuteMapper rdmsChuteMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsChuteMapper = rdmsChuteMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsChute insertInNewTx(RdmsChute Chute) {
        rdmsChuteMapper.insert(Chute);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(Chute), Chute);
        }
        return Chute;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsChuteMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsChute newRecord, RdmsChute oldRecord) {
        // create a temp record to store changed columns
        RdmsChute record = new RdmsChute();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("chuteCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsChuteDynamicSqlSupport.class, rdmsChute, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsChuteMapper.update(updateStatement);

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
     * @return list of RdmsChute entities.
     */
    @Override
    public Collection<RdmsChute> findAll() {
        boolean isCached = false;
        Collection<RdmsChute> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsChuteMapper.selectByExample().build().execute();
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
    public String getMapEntryKey(RdmsChute record) {
        return String.valueOf(record.getId().longValue());
    }
}
