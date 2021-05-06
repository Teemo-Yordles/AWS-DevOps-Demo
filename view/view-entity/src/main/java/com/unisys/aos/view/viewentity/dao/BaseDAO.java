/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;

import java.util.Collection;
import java.util.List;

/**
 * @author LiuJ2
 * @since 2020/9/10 12:11
 */
public interface BaseDAO<T> {

    /**
     * Insert new record into DB with new transaction.
     *
     * @param newRecord - record to be inserted.
     * @return - new record with generated id.
     */
    T insertInNewTx(T newRecord);

    /**
     * delete a record from DB with new transaction.
     *
     * @param id - id of the record to be deleted.
     */
    void deleteInNewTx(Long id);

    /**
     * Update table record
     * Compare the columns only update the different columns
     *
     * @param newRecord - new record
     * @param oldRecord - current record in DB.
     */
    void updateInNewTx(T newRecord, T oldRecord);

    /**
     * Get all table records for a target table.
     * if no records found, empty list is returned but NOT null.
     *
     * @return list of T.
     */
    Collection<T> findAll();

    /**
     * Get records by IATA Code for a target table.
     *
     * @return list of T.
     * @param code
     */
    Collection<T> findByCode(String code);

    /**
     * Cache all table records into redis
     *
     * @param records - records to be cached.
     */
    void cacheAll(Collection<T> records);

    /**
     * get map entry key string based on the record given
     *
     * @param record - map entry value to be cached
     */
    String getMapEntryKey(T record);

    /**
     * Get cached records for a key
     *
     * @param key - hash key
     * @return list of the records stored in redis
     */
    Collection<T> getAllFromCache(String key);

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
    public UpdateDSL<UpdateModel> buildUpdateDSL(Object oldRecord, Object newRecord, Class sqlSupportClazz, SqlTable entity, List<String> ignoredFieldList);

    public boolean entityEqual(Object object1, Object object2);

}
