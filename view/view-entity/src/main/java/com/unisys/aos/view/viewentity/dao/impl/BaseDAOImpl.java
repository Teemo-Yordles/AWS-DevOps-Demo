/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dao.BaseDAO;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

import static org.mybatis.dynamic.sql.update.UpdateDSL.update;

/**
 * @author LiuJ2
 * @since 2020/9/14 12:42
 */
@Slf4j
public abstract class BaseDAOImpl<T> implements BaseDAO<T> {
    private static final HashSet<String> ignoredFields = new HashSet<String>(3) {{
        add("id");
        add("createTime");
        add("updateTime");
    }};
    // get name for the redis hashmap for RdmsAircraftType entities.
    protected final String HASH_KEY;
    protected final RedisUtil redisUtil;

    @Autowired
    public BaseDAOImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
        // Get the generic type class name as HashMap key
        Class clazz = this.getClass();
        Type genType = clazz.getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        this.HASH_KEY = ((Class) params[0]).getName();
    }

    @Override
    public Collection<T> findByCode(String code) {
        return null;
    }

    /**
     * Cache all table records into redis
     *
     * @param records - records to be cached.
     */
    @Override
    public void cacheAll(Collection<T> records) {
        Map<String, T> recordMap = new HashMap<>();
        for (T record : records) {
            String key = getMapEntryKey(record);
            recordMap.put(key, record);
            log.info("[REDIS] Cached all records for entity [{}]", HASH_KEY);
        }
        if (!redisUtil.hmset(HASH_KEY, (Map<String, Object>) recordMap)) {
            log.error("Failed to cache records [" + HASH_KEY + "]");
        }
    }

    /**
     * Get cached records for a key
     *
     * @param key - hash key
     * @return list of the records stored in redis
     */
    @Override
    public Collection<T> getAllFromCache(String key) {
        Map recordMap = redisUtil.hmget(key);
        if (null != recordMap) {
            log.info("[REDIS] Got all records for entity [{}]", HASH_KEY);
            return recordMap.values();
        }
        return null;
    }

    @Override
    public boolean entityEqual(Object object1, Object object2) {
        if (null == object1) {
            return null == object2;
        }
        if (null == object2) {
            return false;
        }

        Class clazz = object1.getClass();
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String propertyName = property.getName();
                if (ignoredFields != null && ignoredFields.contains(propertyName)) {
                    continue;
                }
                Method readMethod = property.getReadMethod();
                Object v1 = readMethod.invoke(object1);
                Object v2 = readMethod.invoke(object2);
                if (null == v1) {
                    if (null != v2) {
                        return false;
                    } else {
                        continue;
                    }
                }
                if (null == v2) {
                    return false;
                }
                if (v1 != v2) {
                    if (property.getPropertyType() == BigDecimal.class) {
                        BigDecimal bv1 = (BigDecimal) v1;
                        BigDecimal bv2 = (BigDecimal) v2;
                        if (0 != bv1.compareTo(bv2)) {
                            return false;
                        }
                    } else {
                        if (!v1.equals(v2)) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to compare two entity", e);
            return false;
        }
        return true;
    }

    /**
     * Build update statement, assign different values in old records and old record to old record.
     *
     * @param oldRecord        Old record
     * @param newRecord        New record
     * @param sqlSupportClazz  Dynamic sql support class
     * @param entity           Static inner class in DynamicSqlSupport
     * @param ignoredFieldList Ignored field List
     * @return Update statement
     */
    @Override
    public UpdateDSL<UpdateModel> buildUpdateDSL(Object oldRecord, Object newRecord, Class sqlSupportClazz, SqlTable entity, List<String> ignoredFieldList) {
        if (null == oldRecord || null == newRecord || null == sqlSupportClazz || null == entity || oldRecord.getClass() != newRecord.getClass()) {
            return null;
        }
        UpdateDSL<UpdateModel> updateDSL = update(entity);
        try {
            Class clazz = oldRecord.getClass();
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String propertyName = property.getName();
                if (ignoredFieldList != null && ignoredFieldList.contains(propertyName)) {
                    continue;
                }
                Method readMethod = property.getReadMethod();
                Object oldPropertyValue = readMethod.invoke(oldRecord);
                Object newPropertyValue = readMethod.invoke(newRecord);
                if (null == newPropertyValue) {
                    if (null != oldPropertyValue) {
                        Field field = getFieldByFieldName(propertyName, sqlSupportClazz);
                        if (null != field && field.getType() == SqlColumn.class) {
                            updateDSL = buildFieldUpdate(propertyName, sqlSupportClazz, updateDSL, null);
                        }
                    }
                } else {
                    if (null == oldPropertyValue) {
                        updateDSL = buildFieldUpdate(propertyName, sqlSupportClazz, updateDSL, newPropertyValue);
                    } else {
                        if (property.getPropertyType() == BigDecimal.class) {
                            BigDecimal newValue = (BigDecimal) newPropertyValue;
                            BigDecimal oldValue = (BigDecimal) oldPropertyValue;
                            if (0 != newValue.compareTo(oldValue)) {
                                updateDSL = buildFieldUpdate(propertyName, sqlSupportClazz, updateDSL, newPropertyValue);
                            }
                        } else {
                            if (!(newPropertyValue.equals(oldPropertyValue))) {
                                updateDSL = buildFieldUpdate(propertyName, sqlSupportClazz, updateDSL, newPropertyValue);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to update the old record. ", e);
            return null;
        }
        return updateDSL;
    }

    /**
     * Build update statement.
     *
     * @param propertyName    Property name
     * @param sqlSupportClazz Dynamic sql support class
     * @param updateDSL       updateDSL
     * @param propertyValue   Property value
     * @return Return updateDSL. If field is not null and its class is SqlColumn.class, then updateDSL is updated DSL, otherwise updateDSL is not changed.
     * @throws Exception Including but not limited to IllegalAccessException
     */
    private UpdateDSL<UpdateModel> buildFieldUpdate(String propertyName, Class sqlSupportClazz, UpdateDSL<UpdateModel> updateDSL, Object propertyValue) throws Exception {
        Field field = getFieldByFieldName(propertyName, sqlSupportClazz);
        if (null != field && field.getType() == SqlColumn.class && null != updateDSL) {
            updateDSL = updateDSL.set((SqlColumn) field.get(sqlSupportClazz)).equalTo(propertyValue);
        }
        return updateDSL;
    }

    /**
     * Get field by field name.
     *
     * @param fieldName Field name
     * @param clazz     Class
     * @return Field
     */
    private Field getFieldByFieldName(String fieldName, Class clazz) {
        if (null == fieldName || null == clazz) {
            return null;
        }
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }


}
