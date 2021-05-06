package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsChute;
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

import static com.unisys.aos.view.viewentity.mapper.flight.FmsChuteDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Repository
@Mapper
public interface FmsChuteMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsChute> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsChuteResult")
    FmsChute selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FmsChuteResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "flight_id", property = "flightId", jdbcType = JdbcType.BIGINT),
            @Result(column = "sequence_number", property = "sequenceNumber", jdbcType = JdbcType.TINYINT),
            @Result(column = "chute_code", property = "chuteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chute_category", property = "chuteCategory", jdbcType = JdbcType.CHAR),
            @Result(column = "planned_start_time", property = "plannedStartTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "planned_end_time", property = "plannedEndTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "actual_start_time", property = "actualStartTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "actual_end_time", property = "actualEndTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "chute_indicator", property = "chuteIndicator", jdbcType = JdbcType.CHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<FmsChute> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsChute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsChute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsChute)
                .where(id, isEqualTo(id_))
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
        DeleteStatementProvider deleteStatement = deleteFrom(fmsChute)
                .where(flightId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsChute record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsChute)
                .map(flightId).toProperty("flightId")
                .map(sequenceNumber).toProperty("sequenceNumber")
                .map(chuteCode).toProperty("chuteCode")
                .map(chuteCategory).toProperty("chuteCategory")
                .map(plannedStartTime).toProperty("plannedStartTime")
                .map(plannedEndTime).toProperty("plannedEndTime")
                .map(actualStartTime).toProperty("actualStartTime")
                .map(actualEndTime).toProperty("actualEndTime")
                .map(chuteIndicator).toProperty("chuteIndicator")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsChute record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsChute)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(sequenceNumber).toPropertyWhenPresent("sequenceNumber", record::getSequenceNumber)
                .map(chuteCode).toPropertyWhenPresent("chuteCode", record::getChuteCode)
                .map(chuteCategory).toPropertyWhenPresent("chuteCategory", record::getChuteCategory)
                .map(plannedStartTime).toPropertyWhenPresent("plannedStartTime", record::getPlannedStartTime)
                .map(plannedEndTime).toPropertyWhenPresent("plannedEndTime", record::getPlannedEndTime)
                .map(actualStartTime).toPropertyWhenPresent("actualStartTime", record::getActualStartTime)
                .map(actualEndTime).toPropertyWhenPresent("actualEndTime", record::getActualEndTime)
                .map(chuteIndicator).toPropertyWhenPresent("chuteIndicator", record::getChuteIndicator)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsChute>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, chuteCode, chuteCategory, plannedStartTime, plannedEndTime, actualStartTime, actualEndTime, chuteIndicator, createTime, updateTime)
                .from(fmsChute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsChute>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, sequenceNumber, chuteCode, chuteCategory, plannedStartTime, plannedEndTime, actualStartTime, actualEndTime, chuteIndicator, createTime, updateTime)
                .from(fmsChute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsChute selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, sequenceNumber, chuteCode, chuteCategory, plannedStartTime, plannedEndTime, actualStartTime, actualEndTime, chuteIndicator, createTime, updateTime)
                .from(fmsChute)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsChute> selectByAodbId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, chuteCode, chuteCategory, plannedStartTime, plannedEndTime, actualStartTime, actualEndTime, chuteIndicator, createTime, updateTime)
                .from(fmsChute)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsChute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsChute)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(chuteCode).equalTo(record::getChuteCode)
                .set(chuteCategory).equalTo(record::getChuteCategory)
                .set(plannedStartTime).equalTo(record::getPlannedStartTime)
                .set(plannedEndTime).equalTo(record::getPlannedEndTime)
                .set(actualStartTime).equalTo(record::getActualStartTime)
                .set(actualEndTime).equalTo(record::getActualEndTime)
                .set(chuteIndicator).equalTo(record::getChuteIndicator)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsChute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsChute)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(chuteCode).equalToWhenPresent(record::getChuteCode)
                .set(chuteCategory).equalToWhenPresent(record::getChuteCategory)
                .set(plannedStartTime).equalToWhenPresent(record::getPlannedStartTime)
                .set(plannedEndTime).equalToWhenPresent(record::getPlannedEndTime)
                .set(actualStartTime).equalToWhenPresent(record::getActualStartTime)
                .set(actualEndTime).equalToWhenPresent(record::getActualEndTime)
                .set(chuteIndicator).equalToWhenPresent(record::getChuteIndicator)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsChute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsChute)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(chuteCode).equalTo(record::getChuteCode)
                .set(chuteCategory).equalTo(record::getChuteCategory)
                .set(plannedStartTime).equalTo(record::getPlannedStartTime)
                .set(plannedEndTime).equalTo(record::getPlannedEndTime)
                .set(actualStartTime).equalTo(record::getActualStartTime)
                .set(actualEndTime).equalTo(record::getActualEndTime)
                .set(chuteIndicator).equalTo(record::getChuteIndicator)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsChute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsChute)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(chuteCode).equalToWhenPresent(record::getChuteCode)
                .set(chuteCategory).equalToWhenPresent(record::getChuteCategory)
                .set(plannedStartTime).equalToWhenPresent(record::getPlannedStartTime)
                .set(plannedEndTime).equalToWhenPresent(record::getPlannedEndTime)
                .set(actualStartTime).equalToWhenPresent(record::getActualStartTime)
                .set(actualEndTime).equalToWhenPresent(record::getActualEndTime)
                .set(chuteIndicator).equalToWhenPresent(record::getChuteIndicator)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}