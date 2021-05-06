package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsAirbridge;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
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

import static com.unisys.aos.view.viewentity.mapper.flight.FmsAirbridgeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Repository
@Mapper
public interface FmsAirbridgeMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsAirbridge> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsAirbridgeResult")
    FmsAirbridge selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FmsAirbridgeResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "flight_id", property = "flightId", jdbcType = JdbcType.BIGINT),
            @Result(column = "sequence_number", property = "sequenceNumber", jdbcType = JdbcType.TINYINT),
            @Result(column = "airbridge_code", property = "airbridgeCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "operation", property = "operation", jdbcType = JdbcType.CHAR),
            @Result(column = "operation_time", property = "operationTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<FmsAirbridge> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsAirbridge);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsAirbridge);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsAirbridge)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsAirbridge record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsAirbridge)
                .map(flightId).toProperty("flightId")
                .map(sequenceNumber).toProperty("sequenceNumber")
                .map(airbridgeCode).toProperty("airbridgeCode")
                .map(operation).toProperty("operation")
                .map(operationTime).toProperty("operationTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsAirbridge record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsAirbridge)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(sequenceNumber).toPropertyWhenPresent("sequenceNumber", record::getSequenceNumber)
                .map(airbridgeCode).toPropertyWhenPresent("airbridgeCode", record::getAirbridgeCode)
                .map(operation).toPropertyWhenPresent("operation", record::getOperation)
                .map(operationTime).toPropertyWhenPresent("operationTime", record::getOperationTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsAirbridge>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, airbridgeCode, operation, operationTime, createTime, updateTime)
                .from(fmsAirbridge);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsAirbridge>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, sequenceNumber, airbridgeCode, operation, operationTime, createTime, updateTime)
                .from(fmsAirbridge);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsAirbridge selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, sequenceNumber, airbridgeCode, operation, operationTime, createTime, updateTime)
                .from(fmsAirbridge)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsAirbridge> selectByAodbId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, airbridgeCode, operation, operationTime, createTime, updateTime)
                .from(fmsAirbridge)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsAirbridge record) {
        return UpdateDSL.updateWithMapper(this::update, fmsAirbridge)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(airbridgeCode).equalTo(record::getAirbridgeCode)
                .set(operation).equalTo(record::getOperation)
                .set(operationTime).equalTo(record::getOperationTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsAirbridge record) {
        return UpdateDSL.updateWithMapper(this::update, fmsAirbridge)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(airbridgeCode).equalToWhenPresent(record::getAirbridgeCode)
                .set(operation).equalToWhenPresent(record::getOperation)
                .set(operationTime).equalToWhenPresent(record::getOperationTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsAirbridge record) {
        return UpdateDSL.updateWithMapper(this::update, fmsAirbridge)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(airbridgeCode).equalTo(record::getAirbridgeCode)
                .set(operation).equalTo(record::getOperation)
                .set(operationTime).equalTo(record::getOperationTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsAirbridge record) {
        return UpdateDSL.updateWithMapper(this::update, fmsAirbridge)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(airbridgeCode).equalToWhenPresent(record::getAirbridgeCode)
                .set(operation).equalToWhenPresent(record::getOperation)
                .set(operationTime).equalToWhenPresent(record::getOperationTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    /**
     * Delete by flight id
     *
     * @param aodbId - flight AODB ID.
     * @return number of rows affected
     */
    default int deleteByAODBId(Long aodbId) {
        DeleteStatementProvider deleteStatement = deleteFrom(fmsAirbridge)
                .where(flightId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }
}