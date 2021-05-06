package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsStand;
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

import static com.unisys.aos.view.viewentity.mapper.flight.FmsStandDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Repository
@Mapper
public interface FmsStandMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsStand> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsStandResult")
    FmsStand selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="FmsStandResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="flight_id", property="flightId", jdbcType=JdbcType.BIGINT),
        @Result(column="sequence_number", property="sequenceNumber", jdbcType=JdbcType.TINYINT),
        @Result(column="stand_code", property="standCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="push_indicator", property="pushIndicator", jdbcType=JdbcType.VARCHAR),
        @Result(column="push_time", property="pushTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="tractor_time", property="tractorTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FmsStand> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsStand);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsStand);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsStand)
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
        DeleteStatementProvider deleteStatement = deleteFrom(fmsStand)
                .where(flightId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsStand record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsStand)
                .map(flightId).toProperty("flightId")
                .map(sequenceNumber).toProperty("sequenceNumber")
                .map(standCode).toProperty("standCode")
                .map(startTime).toProperty("startTime")
                .map(endTime).toProperty("endTime")
                .map(pushIndicator).toProperty("pushIndicator")
                .map(pushTime).toProperty("pushTime")
                .map(tractorTime).toProperty("tractorTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsStand record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsStand)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(sequenceNumber).toPropertyWhenPresent("sequenceNumber", record::getSequenceNumber)
                .map(standCode).toPropertyWhenPresent("standCode", record::getStandCode)
                .map(startTime).toPropertyWhenPresent("startTime", record::getStartTime)
                .map(endTime).toPropertyWhenPresent("endTime", record::getEndTime)
                .map(pushIndicator).toPropertyWhenPresent("pushIndicator", record::getPushIndicator)
                .map(pushTime).toPropertyWhenPresent("pushTime", record::getPushTime)
                .map(tractorTime).toPropertyWhenPresent("tractorTime", record::getTractorTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsStand>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, standCode, startTime, endTime, pushIndicator, pushTime, tractorTime, createTime, updateTime)
                .from(fmsStand);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsStand>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, sequenceNumber, standCode, startTime, endTime, pushIndicator, pushTime, tractorTime, createTime, updateTime)
                .from(fmsStand);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsStand selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, sequenceNumber, standCode, startTime, endTime, pushIndicator, pushTime, tractorTime, createTime, updateTime)
                .from(fmsStand)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsStand> selectByAodbId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, standCode, startTime, endTime, createTime, updateTime)
                .from(fmsStand)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsStand record) {
        return UpdateDSL.updateWithMapper(this::update, fmsStand)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(standCode).equalTo(record::getStandCode)
                .set(startTime).equalTo(record::getStartTime)
                .set(endTime).equalTo(record::getEndTime)
                .set(pushIndicator).equalTo(record::getPushIndicator)
                .set(pushTime).equalTo(record::getPushTime)
                .set(tractorTime).equalTo(record::getTractorTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsStand record) {
        return UpdateDSL.updateWithMapper(this::update, fmsStand)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(standCode).equalToWhenPresent(record::getStandCode)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(endTime).equalToWhenPresent(record::getEndTime)
                .set(pushIndicator).equalToWhenPresent(record::getPushIndicator)
                .set(pushTime).equalToWhenPresent(record::getPushTime)
                .set(tractorTime).equalToWhenPresent(record::getTractorTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsStand record) {
        return UpdateDSL.updateWithMapper(this::update, fmsStand)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(standCode).equalTo(record::getStandCode)
                .set(startTime).equalTo(record::getStartTime)
                .set(endTime).equalTo(record::getEndTime)
                .set(pushIndicator).equalTo(record::getPushIndicator)
                .set(pushTime).equalTo(record::getPushTime)
                .set(tractorTime).equalTo(record::getTractorTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsStand record) {
        return UpdateDSL.updateWithMapper(this::update, fmsStand)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(standCode).equalToWhenPresent(record::getStandCode)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(endTime).equalToWhenPresent(record::getEndTime)
                .set(pushIndicator).equalToWhenPresent(record::getPushIndicator)
                .set(pushTime).equalToWhenPresent(record::getPushTime)
                .set(tractorTime).equalToWhenPresent(record::getTractorTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}