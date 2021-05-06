/**
 * The <b>RdmsTerminalDAOImpl</b> is an implementation of RdmsTerminalDAO interface,
 * which is used to access RdmsTerminal data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.RdmsTerminalDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsTerminal;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsTerminalDynamicSqlSupport;
import com.unisys.aos.view.viewentity.mapper.reference.RdmsTerminalMapper;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsTerminalDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author LiuJ2
 * @since 2020/9/10 12:16
 */
@Repository
@Slf4j
public class RdmsTerminalDAOImpl extends BaseDAOImpl<RdmsTerminal> implements RdmsTerminalDAO {

    private final RdmsTerminalMapper rdmsTerminalMapper;

    @Autowired
    public RdmsTerminalDAOImpl(RdmsTerminalMapper rdmsTerminalMapper, RedisUtil redisUtil) {
        super(redisUtil);
        this.rdmsTerminalMapper = rdmsTerminalMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RdmsTerminal insertInNewTx(RdmsTerminal terminal) {
        rdmsTerminalMapper.insert(terminal);
        // insert to cache
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hset(HASH_KEY, getMapEntryKey(terminal), terminal);
        }
        return terminal;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteInNewTx(Long id) {
        rdmsTerminalMapper.deleteByPrimaryKey(id);
        if (redisUtil.exists(HASH_KEY)) {
            redisUtil.hdel(HASH_KEY, String.valueOf(id.longValue()));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInNewTx(RdmsTerminal newRecord, RdmsTerminal oldRecord) {
        RdmsTerminal record = new RdmsTerminal();
        record.setId(oldRecord.getId());

        List<String> ignoredFieldList = new ArrayList<>();
        ignoredFieldList.add("id");
        ignoredFieldList.add("terminalCode");
        ignoredFieldList.add("createTime");
        ignoredFieldList.add("updateTime");

        UpdateDSL<UpdateModel> updateDSL = buildUpdateDSL(oldRecord, newRecord, RdmsTerminalDynamicSqlSupport.class, rdmsTerminal, ignoredFieldList);

        if (null != updateDSL) {
            updateDSL = updateDSL.set(updateTime).equalTo(new Date());

            // update to DB
            UpdateStatementProvider updateStatement = updateDSL.where(id, isEqualTo(record::getId)).build().render(RenderingStrategies.MYBATIS3);
            rdmsTerminalMapper.update(updateStatement);

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
     * @return list of RdmsTerminal entities.
     */
    @Override
    public Collection<RdmsTerminal> findAll() {
        boolean isCached = false;
        Collection<RdmsTerminal> records = null;

        // try to get records from redis
        if (redisUtil.exists(HASH_KEY)) {
            isCached = true;
            records = getAllFromCache(HASH_KEY);
        }

        // if cannot find in cache then query from DB
        if (null == records || records.isEmpty()) {
            records = rdmsTerminalMapper.selectByExample().build().execute();
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
    public String getMapEntryKey(RdmsTerminal record) {
        return String.valueOf(record.getId().longValue());
    }
}
