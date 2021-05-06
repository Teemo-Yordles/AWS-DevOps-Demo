/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsOrganizationDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsOrganization;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsOrganizationDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsOrganizationMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsOrganizationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * The <b>RdmsOrganizationDAOImpl</b> is an implementation of RdmsOrganizationDAO interface,
 * which is used to access RdmsOrganization data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Repository
@Slf4j
public class RdmsOrganizationDAOImpl extends BaseDAOImpl<RdmsOrganization> implements RdmsOrganizationDAO {

    private final RdmsOrganizationMapper rdmsOrganizationMapper;

    @Autowired
    public RdmsOrganizationDAOImpl(RdmsOrganizationMapper rdmsOrganizationMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsOrganizationMapper = rdmsOrganizationMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsOrganization insertInNewTx(RdmsOrganization organization) {
        rdmsOrganizationMapper.insert(organization);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(organization), organization);
        }
        return organization;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsOrganizationMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsOrganization newRecord, RdmsOrganization oldRecord) {
        RdmsOrganization record = new RdmsOrganization();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("organizationIdentifier");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsOrganizationDynamicSqlSupport.class, rdmsOrganization, ignoredFieldList);
        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsOrganizationMapper.update(updateStatement);

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
     * @return list of RdmsOrganization entities.
     */
    @Override
    public Collection<RdmsOrganization> findAll() {
        boolean isCached = false;
        Collection<RdmsOrganization> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsOrganizationMapper.selectByExample().build().execute();
        }

        // cache the result if not cached and there are some records to cache
        if (null != records && !isCached) {
            cacheAll(records);
        }
        return records;
    }

    /***
     * Get RdmsOrganization entity by organization identifier
     * @param identifier organization identifier
     * @return RdmsOrganization entity
     */
    @Override
    public List<RdmsOrganization> findByOrganizationIdentifier(Integer identifier) {
        return rdmsOrganizationMapper.selectByOrganizationIdentifier(identifier);
    }

    /**
     * get map entry key string based on the record given
     *
     * @param record - map entry value to be cached
     */
    @Override
    public String getMapEntryKey(RdmsOrganization record) {
        return String.valueOf(record.getId().longValue());
    }
}
