package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsCheckinDesk;
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

import static com.unisys.aos.view.viewentity.mapper.flight.FmsCheckinDeskDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Repository
@Mapper
public interface FmsCheckinDeskMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsCheckinDesk> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsCheckinDeskResult")
    FmsCheckinDesk selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FmsCheckinDeskResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "flight_id", property = "flightId", jdbcType = JdbcType.BIGINT),
            @Result(column = "sequence_number", property = "sequenceNumber", jdbcType = JdbcType.TINYINT),
            @Result(column = "checkin_desk_code", property = "checkinDeskCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_desk_category", property = "checkinDeskCategory", jdbcType = JdbcType.CHAR),
            @Result(column = "planned_start_time", property = "plannedStartTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "planned_end_time", property = "plannedEndTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "actual_start_time", property = "actualStartTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "actual_end_time", property = "actualEndTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "checkin_indicator", property = "checkinIndicator", jdbcType = JdbcType.CHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<FmsCheckinDesk> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsCheckinDesk);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsCheckinDesk);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsCheckinDesk)
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
        DeleteStatementProvider deleteStatement = deleteFrom(fmsCheckinDesk)
                .where(flightId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsCheckinDesk record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsCheckinDesk)
                .map(flightId).toProperty("flightId")
                .map(sequenceNumber).toProperty("sequenceNumber")
                .map(checkinDeskCode).toProperty("checkinDeskCode")
                .map(checkinDeskCategory).toProperty("checkinDeskCategory")
                .map(plannedStartTime).toProperty("plannedStartTime")
                .map(plannedEndTime).toProperty("plannedEndTime")
                .map(actualStartTime).toProperty("actualStartTime")
                .map(actualEndTime).toProperty("actualEndTime")
                .map(checkinIndicator).toProperty("checkinIndicator")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsCheckinDesk record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsCheckinDesk)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(sequenceNumber).toPropertyWhenPresent("sequenceNumber", record::getSequenceNumber)
                .map(checkinDeskCode).toPropertyWhenPresent("checkinDeskCode", record::getCheckinDeskCode)
                .map(checkinDeskCategory).toPropertyWhenPresent("checkinDeskCategory", record::getCheckinDeskCategory)
                .map(plannedStartTime).toPropertyWhenPresent("plannedStartTime", record::getPlannedStartTime)
                .map(plannedEndTime).toPropertyWhenPresent("plannedEndTime", record::getPlannedEndTime)
                .map(actualStartTime).toPropertyWhenPresent("actualStartTime", record::getActualStartTime)
                .map(actualEndTime).toPropertyWhenPresent("actualEndTime", record::getActualEndTime)
                .map(checkinIndicator).toPropertyWhenPresent("checkinIndicator", record::getCheckinIndicator)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsCheckinDesk>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, checkinDeskCode, checkinDeskCategory, plannedStartTime, plannedEndTime, actualStartTime, actualEndTime, checkinIndicator, createTime, updateTime)
                .from(fmsCheckinDesk);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsCheckinDesk>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, sequenceNumber, checkinDeskCode, checkinDeskCategory, plannedStartTime, plannedEndTime, actualStartTime, actualEndTime, checkinIndicator, createTime, updateTime)
                .from(fmsCheckinDesk);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsCheckinDesk selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, sequenceNumber, checkinDeskCode, checkinDeskCategory, plannedStartTime, plannedEndTime, actualStartTime, actualEndTime, checkinIndicator, createTime, updateTime)
                .from(fmsCheckinDesk)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsCheckinDesk> selectByAodbId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, checkinDeskCode, checkinDeskCategory, plannedStartTime, plannedEndTime, actualStartTime, actualEndTime, checkinIndicator, createTime, updateTime)
                .from(fmsCheckinDesk)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsCheckinDesk record) {
        return UpdateDSL.updateWithMapper(this::update, fmsCheckinDesk)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(checkinDeskCode).equalTo(record::getCheckinDeskCode)
                .set(checkinDeskCategory).equalTo(record::getCheckinDeskCategory)
                .set(plannedStartTime).equalTo(record::getPlannedStartTime)
                .set(plannedEndTime).equalTo(record::getPlannedEndTime)
                .set(actualStartTime).equalTo(record::getActualStartTime)
                .set(actualEndTime).equalTo(record::getActualEndTime)
                .set(checkinIndicator).equalTo(record::getCheckinIndicator)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsCheckinDesk record) {
        return UpdateDSL.updateWithMapper(this::update, fmsCheckinDesk)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(checkinDeskCode).equalToWhenPresent(record::getCheckinDeskCode)
                .set(checkinDeskCategory).equalToWhenPresent(record::getCheckinDeskCategory)
                .set(plannedStartTime).equalToWhenPresent(record::getPlannedStartTime)
                .set(plannedEndTime).equalToWhenPresent(record::getPlannedEndTime)
                .set(actualStartTime).equalToWhenPresent(record::getActualStartTime)
                .set(actualEndTime).equalToWhenPresent(record::getActualEndTime)
                .set(checkinIndicator).equalToWhenPresent(record::getCheckinIndicator)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsCheckinDesk record) {
        return UpdateDSL.updateWithMapper(this::update, fmsCheckinDesk)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(checkinDeskCode).equalTo(record::getCheckinDeskCode)
                .set(checkinDeskCategory).equalTo(record::getCheckinDeskCategory)
                .set(plannedStartTime).equalTo(record::getPlannedStartTime)
                .set(plannedEndTime).equalTo(record::getPlannedEndTime)
                .set(actualStartTime).equalTo(record::getActualStartTime)
                .set(actualEndTime).equalTo(record::getActualEndTime)
                .set(checkinIndicator).equalTo(record::getCheckinIndicator)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsCheckinDesk record) {
        return UpdateDSL.updateWithMapper(this::update, fmsCheckinDesk)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(checkinDeskCode).equalToWhenPresent(record::getCheckinDeskCode)
                .set(checkinDeskCategory).equalToWhenPresent(record::getCheckinDeskCategory)
                .set(plannedStartTime).equalToWhenPresent(record::getPlannedStartTime)
                .set(plannedEndTime).equalToWhenPresent(record::getPlannedEndTime)
                .set(actualStartTime).equalToWhenPresent(record::getActualStartTime)
                .set(actualEndTime).equalToWhenPresent(record::getActualEndTime)
                .set(checkinIndicator).equalToWhenPresent(record::getCheckinIndicator)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}