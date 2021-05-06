/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsPierDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsPier;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsPierDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsPierMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsPierDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 *
 * The <b>PierDAOImpl</b> is an implementation of PierDAO interface,
 * which is used to access Pier data from database.
 *
 * @author jianglushuang
 * @since 2020/9/23 3:28 下午
 */
@Repository
@Slf4j
public class RdmsPierDAOImpl extends BaseDAOImpl<RdmsPier> implements RdmsPierDAO {

    private final RdmsPierMapper rdmsPierMapper;

    @Autowired
    public RdmsPierDAOImpl(RdmsPierMapper rdmsPierMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsPierMapper = rdmsPierMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsPier insertInNewTx(RdmsPier Pier) {
        rdmsPierMapper.insert(Pier);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(Pier), Pier);
        }
        return Pier;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsPierMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsPier newRecord, RdmsPier oldRecord) {
        // create a temp record to store changed columns
        RdmsPier record = new RdmsPier();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("pierCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsPierDynamicSqlSupport.class, rdmsPier, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsPierMapper.update(updateStatement);

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
     * @return list of RdmsPier entities.
     */
    @Override
    public Collection<RdmsPier> findAll() {
        boolean isCached = false;
        Collection<RdmsPier> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsPierMapper.selectByExample().build().execute();
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
    public String getMapEntryKey(RdmsPier record) {
        return String.valueOf(record.getId().longValue());
    }
}
