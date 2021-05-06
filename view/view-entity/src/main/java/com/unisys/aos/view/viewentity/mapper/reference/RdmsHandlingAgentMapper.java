package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsHandlingAgent;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsHandlingAgentDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
@Repository
public interface RdmsHandlingAgentMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsHandlingAgent> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsHandlingAgentResult")
    RdmsHandlingAgent selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsHandlingAgentResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "handling_agent_code", property = "handlingAgentCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handling_agent_category", property = "handlingAgentCategory", jdbcType = JdbcType.CHAR),
            @Result(column = "unit_measure", property = "unitMeasure", jdbcType = JdbcType.VARCHAR),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsHandlingAgent> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsHandlingAgent);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsHandlingAgent);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsHandlingAgent)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsHandlingAgent record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsHandlingAgent)
                .map(handlingAgentCode).toProperty("handlingAgentCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(handlingAgentCategory).toProperty("handlingAgentCategory")
                .map(unitMeasure).toProperty("unitMeasure")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsHandlingAgent record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsHandlingAgent)
                .map(handlingAgentCode).toPropertyWhenPresent("handlingAgentCode", record::getHandlingAgentCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(handlingAgentCategory).toPropertyWhenPresent("handlingAgentCategory", record::getHandlingAgentCategory)
                .map(unitMeasure).toPropertyWhenPresent("unitMeasure", record::getUnitMeasure)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsHandlingAgent>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, handlingAgentCode, description, localDescription, handlingAgentCategory, unitMeasure, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsHandlingAgent);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsHandlingAgent>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, handlingAgentCode, description, localDescription, handlingAgentCategory, unitMeasure, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsHandlingAgent);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsHandlingAgent selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, handlingAgentCode, description, localDescription, handlingAgentCategory, unitMeasure, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsHandlingAgent)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    /***
     * Get RdmsHandlingAgent entity by handling agent code.
     * @param agentCode handling agent code
     * @param inspire inspire time
     * @param expire expire time
     * @return RdmsHandlingAgent entity
     */
    default RdmsHandlingAgent selectByAgentCode(String agentCode, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectOne, id, handlingAgentCode, description, localDescription, handlingAgentCategory, unitMeasure, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsHandlingAgent)
                .where(handlingAgentCode, isEqualTo(agentCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsHandlingAgent record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsHandlingAgent)
                .set(handlingAgentCode).equalTo(record::getHandlingAgentCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(handlingAgentCategory).equalTo(record::getHandlingAgentCategory)
                .set(unitMeasure).equalTo(record::getUnitMeasure)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsHandlingAgent record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsHandlingAgent)
                .set(handlingAgentCode).equalToWhenPresent(record::getHandlingAgentCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(handlingAgentCategory).equalToWhenPresent(record::getHandlingAgentCategory)
                .set(unitMeasure).equalToWhenPresent(record::getUnitMeasure)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsHandlingAgent record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsHandlingAgent)
                .set(handlingAgentCode).equalTo(record::getHandlingAgentCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(handlingAgentCategory).equalTo(record::getHandlingAgentCategory)
                .set(unitMeasure).equalTo(record::getUnitMeasure)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsHandlingAgent record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsHandlingAgent)
                .set(handlingAgentCode).equalToWhenPresent(record::getHandlingAgentCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(handlingAgentCategory).equalToWhenPresent(record::getHandlingAgentCategory)
                .set(unitMeasure).equalToWhenPresent(record::getUnitMeasure)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}