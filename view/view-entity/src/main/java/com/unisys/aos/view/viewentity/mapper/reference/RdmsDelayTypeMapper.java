package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsDelayType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import java.util.List;

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsDelayTypeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
@Repository
public interface RdmsDelayTypeMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsDelayType> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsDelayTypeResult")
    RdmsDelayType selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsDelayTypeResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "delay_type_code", property = "delayTypeCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delay_type_code_number", property = "delayTypeCodeNumber", jdbcType = JdbcType.SMALLINT),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsDelayType> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsDelayType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsDelayType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsDelayType)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsDelayType record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsDelayType)
                .map(delayTypeCode).toProperty("delayTypeCode")
                .map(delayTypeCodeNumber).toProperty("delayTypeCodeNumber")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsDelayType record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsDelayType)
                .map(delayTypeCode).toPropertyWhenPresent("delayTypeCode", record::getDelayTypeCode)
                .map(delayTypeCodeNumber).toPropertyWhenPresent("delayTypeCodeNumber", record::getDelayTypeCodeNumber)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsDelayType>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, delayTypeCode, delayTypeCodeNumber, description, localDescription, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsDelayType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsDelayType>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, delayTypeCode, delayTypeCodeNumber, description, localDescription, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsDelayType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsDelayType selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, delayTypeCode, delayTypeCodeNumber, description, localDescription, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsDelayType)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    /***
     * Get RdmsDelayType entity by delay type code.
     * @param typeCode delay type code
     * @param inspire inspire time
     * @param expire expire time
     * @return RdmsDelayType entity
     */
    default RdmsDelayType selectByDelayTypeCode(String typeCode, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectOne, id, delayTypeCode, delayTypeCodeNumber, description, localDescription, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsDelayType)
                .where(delayTypeCode, isEqualTo(typeCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /***
     * Get RdmsDelayType entity by delay type code.
     * @param typeCode delay type code
     * @return RdmsDelayType entity
     */
    default List<RdmsDelayType> selectByDelayTypeCode(String typeCode) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, delayTypeCode, delayTypeCodeNumber, description, localDescription, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsDelayType)
                .where(delayTypeCode, isEqualTo(typeCode))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsDelayType record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsDelayType)
                .set(delayTypeCode).equalTo(record::getDelayTypeCode)
                .set(delayTypeCodeNumber).equalTo(record::getDelayTypeCodeNumber)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsDelayType record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsDelayType)
                .set(delayTypeCode).equalToWhenPresent(record::getDelayTypeCode)
                .set(delayTypeCodeNumber).equalToWhenPresent(record::getDelayTypeCodeNumber)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsDelayType record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsDelayType)
                .set(delayTypeCode).equalTo(record::getDelayTypeCode)
                .set(delayTypeCodeNumber).equalTo(record::getDelayTypeCodeNumber)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsDelayType record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsDelayType)
                .set(delayTypeCode).equalToWhenPresent(record::getDelayTypeCode)
                .set(delayTypeCodeNumber).equalToWhenPresent(record::getDelayTypeCodeNumber)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}