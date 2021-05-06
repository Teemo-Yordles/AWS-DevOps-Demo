/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsRegionCountryAssociationDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsRegionCountryAssociation;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionCountryAssociationDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionCountryAssociationMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionCountryAssociationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsRegionCountryAssociationDAOImpl</b> is an implementation of RdmsRegionCountryAssociationDAO interface,
 * which is used to access RdmsRegionCountryAssociation data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsRegionCountryAssociationDAOImpl extends BaseDAOImpl<RdmsRegionCountryAssociation> implements RdmsRegionCountryAssociationDAO {

    private final RdmsRegionCountryAssociationMapper rdmsRegionCountryAssociationMapper;

    @Autowired
    public RdmsRegionCountryAssociationDAOImpl(RdmsRegionCountryAssociationMapper rdmsRegionCountryAssociationMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsRegionCountryAssociationMapper = rdmsRegionCountryAssociationMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsRegionCountryAssociation insertInNewTx(RdmsRegionCountryAssociation regionCountryAssociation) {
        rdmsRegionCountryAssociationMapper.insert(regionCountryAssociation);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(regionCountryAssociation), regionCountryAssociation);
        }
        return regionCountryAssociation;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsRegionCountryAssociationMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsRegionCountryAssociation newRecord, RdmsRegionCountryAssociation oldRecord) {
        RdmsRegionCountryAssociation record = new RdmsRegionCountryAssociation();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsRegionCountryAssociationDynamicSqlSupport.class, rdmsRegionCountryAssociation, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsRegionCountryAssociationMapper.update(updateStatement);

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
     * @return list of RdmsRegionCountryAssociation entities.
     */
    @Override
    public Collection<RdmsRegionCountryAssociation> findAll() {
        boolean isCached = false;
        Collection<RdmsRegionCountryAssociation> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsRegionCountryAssociationMapper.selectByExample().build().execute();
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
    public String getMapEntryKey(RdmsRegionCountryAssociation record) {
        return String.valueOf(record.getId().longValue());
    }

    /**
     * get map entries based on gate code
     *
     * @param currentRegionCode region code
     * @param inspire           inspire time
     * @param expire            expire time
     * @return map entries
     */
    @Override
    public List<RdmsRegionCountryAssociation> selectByRegionCode(String currentRegionCode, Long inspire, Long expire) {
        return rdmsRegionCountryAssociationMapper.selectByRegionCode(currentRegionCode, inspire, expire);
    }

    /**
     * get map entries based on gate code
     *
     * @param currentCountryCode country code
     * @param inspire            inspire time
     * @param expire             expire time
     * @return map entries
     */
    @Override
    public List<RdmsRegionCountryAssociation> selectByCountryCode(String currentCountryCode, Long inspire, Long expire) {
        return rdmsRegionCountryAssociationMapper.selectByCountryCode(currentCountryCode, inspire, expire);
    }

    /**
     * delete association through Country Code
     *
     * @param currentCountryCode Country Code
     * @param inspire            inspire time
     * @param expire             expire time
     * @return delete result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int deleteByCountryInNexTx(String currentCountryCode, Long inspire, Long expire) {
        return rdmsRegionCountryAssociationMapper.deleteByCountryCode(currentCountryCode, inspire, expire);
    }

    /**
     * delete association through Region Code
     *
     * @param currentRegionCode Region Code
     * @param inspire           inspire time
     * @param expire            expire time
     * @return delete result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int deleteByRegionInNexTx(String currentRegionCode, Long inspire, Long expire) {
        return rdmsRegionCountryAssociationMapper.deleteByRegionCode(currentRegionCode, inspire, expire);
    }
}
