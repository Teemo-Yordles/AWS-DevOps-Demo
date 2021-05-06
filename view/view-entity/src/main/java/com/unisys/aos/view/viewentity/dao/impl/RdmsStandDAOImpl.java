/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsStandDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsStand;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsStandDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsStandMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsStandDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsStandDAOImpl</b> is an implementation of RdmsStandDAO interface,
 * which is used to access RdmsStand data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsStandDAOImpl extends BaseDAOImpl<RdmsStand> implements RdmsStandDAO {

    private final RdmsStandMapper rdmsStandMapper;

    @Autowired
    public RdmsStandDAOImpl(RdmsStandMapper rdmsStandMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsStandMapper = rdmsStandMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsStand insertInNewTx(RdmsStand stand) {
        rdmsStandMapper.insert(stand);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(stand), stand);
        }
        return stand;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsStandMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsStand newRecord, RdmsStand oldRecord) {
        RdmsStand record = new RdmsStand();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("standCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsStandDynamicSqlSupport.class, rdmsStand, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsStandMapper.update(updateStatement);

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
     * @return list of RdmsStand entities.
     */
    @Override
    public Collection<RdmsStand> findAll() {
        boolean isCached = false;
        Collection<RdmsStand> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsStandMapper.selectByExample().build().execute();
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
    public String getMapEntryKey(RdmsStand record) {
        return String.valueOf(record.getId().longValue());
    }

    /***
     * get map entries based on stand code
     * @param currentStandCode stand code
     * @param inspire inspire time
     * @param expire expire time
     * @return map entries
     */
    @Override
    public List<RdmsStand> selectByStandCode(String currentStandCode, Long inspire, Long expire) {
        return rdmsStandMapper.selectByStandCode(currentStandCode, inspire, expire);
    }

    /**
     * get map entries based on international gate code
     *
     * @param internationalGateCode international gate code
     * @param inspire               inspire time
     * @param expire                expire time
     * @return map entries
     */
    @Override
    public List<RdmsStand> selectByInternationalGate(String internationalGateCode, Long inspire, Long expire) {
        return rdmsStandMapper.selectByStandCode(internationalGateCode, inspire, expire);

    }

    /**
     * get map entries based on domestic gate code
     *
     * @param domesticGateCode domestic gate code
     * @param inspire          inspire time
     * @param expire           expire time
     * @return map entries
     */
    @Override
    public List<RdmsStand> selectByDomesticGate(String domesticGateCode, Long inspire, Long expire) {
        return rdmsStandMapper.selectByStandCode(domesticGateCode, inspire, expire);

    }
}
