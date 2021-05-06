package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsSeasonFlight;
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
import java.util.Date;
import java.util.List;

import static com.unisys.aos.view.viewentity.mapper.flight.FmsSeasonFlightDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Repository
@Mapper
public interface FmsSeasonFlightMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<FmsSeasonFlight> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("FmsSeasonFlightResult")
    FmsSeasonFlight selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="FmsSeasonFlightResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="season_name", property="seasonName", jdbcType=JdbcType.VARCHAR),
        @Result(column="season_start_date", property="seasonStartDate", jdbcType=JdbcType.DATE),
        @Result(column="season_end_date", property="seasonEndDate", jdbcType=JdbcType.DATE),
        @Result(column="airline_code", property="airlineCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="flight_number", property="flightNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="movement_indicator", property="movementIndicator", jdbcType=JdbcType.CHAR),
        @Result(column="season_schedule_start_date", property="seasonScheduleStartDate", jdbcType=JdbcType.DATE),
        @Result(column="season_schedule_end_date", property="seasonScheduleEndDate", jdbcType=JdbcType.DATE),
        @Result(column="week_operate_day", property="weekOperateDay", jdbcType=JdbcType.CHAR),
        @Result(column="flight_schedule_time", property="flightScheduleTime", jdbcType=JdbcType.CHAR),
        @Result(column="flight_type", property="flightType", jdbcType=JdbcType.VARCHAR),
        @Result(column="flight_indicator", property="flightIndicator", jdbcType=JdbcType.CHAR),
        @Result(column="aircraft_type", property="aircraftType", jdbcType=JdbcType.CHAR),
        @Result(column="registration", property="registration", jdbcType=JdbcType.VARCHAR),
        @Result(column="linked_airline_code", property="linkedAirlineCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="linked_flight_number", property="linkedFlightNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="terminal", property="terminal", jdbcType=JdbcType.VARCHAR),
        @Result(column="max_pax", property="maxPax", jdbcType=JdbcType.SMALLINT),
        @Result(column="master_airline_code", property="masterAirlineCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="master_flight_number", property="masterFlightNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FmsSeasonFlight> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsSeasonFlight);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsSeasonFlight);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsSeasonFlight)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsSeasonFlight record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsSeasonFlight)
                .map(seasonName).toProperty("seasonName")
                .map(seasonStartDate).toProperty("seasonStartDate")
                .map(seasonEndDate).toProperty("seasonEndDate")
                .map(airlineCode).toProperty("airlineCode")
                .map(flightNumber).toProperty("flightNumber")
                .map(movementIndicator).toProperty("movementIndicator")
                .map(seasonScheduleStartDate).toProperty("seasonScheduleStartDate")
                .map(seasonScheduleEndDate).toProperty("seasonScheduleEndDate")
                .map(weekOperateDay).toProperty("weekOperateDay")
                .map(flightScheduleTime).toProperty("flightScheduleTime")
                .map(flightType).toProperty("flightType")
                .map(flightIndicator).toProperty("flightIndicator")
                .map(aircraftType).toProperty("aircraftType")
                .map(registration).toProperty("registration")
                .map(linkedAirlineCode).toProperty("linkedAirlineCode")
                .map(linkedFlightNumber).toProperty("linkedFlightNumber")
                .map(terminal).toProperty("terminal")
                .map(maxPax).toProperty("maxPax")
                .map(masterAirlineCode).toProperty("masterAirlineCode")
                .map(masterFlightNumber).toProperty("masterFlightNumber")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsSeasonFlight record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsSeasonFlight)
                .map(seasonName).toPropertyWhenPresent("seasonName", record::getSeasonName)
                .map(seasonStartDate).toPropertyWhenPresent("seasonStartDate", record::getSeasonStartDate)
                .map(seasonEndDate).toPropertyWhenPresent("seasonEndDate", record::getSeasonEndDate)
                .map(airlineCode).toPropertyWhenPresent("airlineCode", record::getAirlineCode)
                .map(flightNumber).toPropertyWhenPresent("flightNumber", record::getFlightNumber)
                .map(movementIndicator).toPropertyWhenPresent("movementIndicator", record::getMovementIndicator)
                .map(seasonScheduleStartDate).toPropertyWhenPresent("seasonScheduleStartDate", record::getSeasonScheduleStartDate)
                .map(seasonScheduleEndDate).toPropertyWhenPresent("seasonScheduleEndDate", record::getSeasonScheduleEndDate)
                .map(weekOperateDay).toPropertyWhenPresent("weekOperateDay", record::getWeekOperateDay)
                .map(flightScheduleTime).toPropertyWhenPresent("flightScheduleTime", record::getFlightScheduleTime)
                .map(flightType).toPropertyWhenPresent("flightType", record::getFlightType)
                .map(flightIndicator).toPropertyWhenPresent("flightIndicator", record::getFlightIndicator)
                .map(aircraftType).toPropertyWhenPresent("aircraftType", record::getAircraftType)
                .map(registration).toPropertyWhenPresent("registration", record::getRegistration)
                .map(linkedAirlineCode).toPropertyWhenPresent("linkedAirlineCode", record::getLinkedAirlineCode)
                .map(linkedFlightNumber).toPropertyWhenPresent("linkedFlightNumber", record::getLinkedFlightNumber)
                .map(terminal).toPropertyWhenPresent("terminal", record::getTerminal)
                .map(maxPax).toPropertyWhenPresent("maxPax", record::getMaxPax)
                .map(masterAirlineCode).toPropertyWhenPresent("masterAirlineCode", record::getMasterAirlineCode)
                .map(masterFlightNumber).toPropertyWhenPresent("masterFlightNumber", record::getMasterFlightNumber)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsSeasonFlight>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, seasonName, seasonStartDate, seasonEndDate, airlineCode, flightNumber, movementIndicator, seasonScheduleStartDate, seasonScheduleEndDate, weekOperateDay, flightScheduleTime, flightType, flightIndicator, aircraftType, registration, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterAirlineCode, masterFlightNumber, createTime, updateTime)
                .from(fmsSeasonFlight);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsSeasonFlight>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, seasonName, seasonStartDate, seasonEndDate, airlineCode, flightNumber, movementIndicator, seasonScheduleStartDate, seasonScheduleEndDate, weekOperateDay, flightScheduleTime, flightType, flightIndicator, aircraftType, registration, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterAirlineCode, masterFlightNumber, createTime, updateTime)
                .from(fmsSeasonFlight);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsSeasonFlight selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, seasonName, seasonStartDate, seasonEndDate, airlineCode, flightNumber, movementIndicator, seasonScheduleStartDate, seasonScheduleEndDate, weekOperateDay, flightScheduleTime, flightType, flightIndicator, aircraftType, registration, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterAirlineCode, masterFlightNumber, createTime, updateTime)
                .from(fmsSeasonFlight)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsSeasonFlight> selectEarlierScheduleByStartDate(Date startDateFirst, Date startDateSecond) {
        SelectStatementProvider selectStatement = select(id, seasonName, seasonStartDate, seasonEndDate, airlineCode, flightNumber, movementIndicator, seasonScheduleStartDate, seasonScheduleEndDate, weekOperateDay, flightScheduleTime, flightType, flightIndicator, aircraftType, registration, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterAirlineCode, masterFlightNumber, createTime, updateTime)
                .from(fmsSeasonFlight)
                .where(seasonStartDate, isNotIn(startDateFirst, startDateSecond))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return selectMany(selectStatement);
    }

    default List<FmsSeasonFlight> selectBySeasonDate(Date startDate, Date endDate) {
        SelectStatementProvider selectStatement = select(id, seasonName, seasonStartDate, seasonEndDate, airlineCode, flightNumber, movementIndicator, seasonScheduleStartDate, seasonScheduleEndDate, weekOperateDay, flightScheduleTime, flightType, flightIndicator, aircraftType, registration, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterAirlineCode, masterFlightNumber, createTime, updateTime)
                .from(fmsSeasonFlight)
                .where(seasonStartDate, isEqualTo(startDate))
                .and(seasonEndDate, isEqualTo(endDate))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return selectMany(selectStatement);
    }

    default FmsSeasonFlight selectLastSeasonSchedule() {
        SelectStatementProvider selectStatement = select(id, seasonName, seasonStartDate, seasonEndDate, airlineCode, flightNumber, movementIndicator, seasonScheduleStartDate, seasonScheduleEndDate, weekOperateDay, flightScheduleTime, flightType, flightIndicator, aircraftType, registration, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterAirlineCode, masterFlightNumber, createTime, updateTime)
                .from(fmsSeasonFlight)
                .orderBy(seasonStartDate.descending())
                .limit(1L)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return selectOne(selectStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsSeasonFlight record) {
        return UpdateDSL.updateWithMapper(this::update, fmsSeasonFlight)
                .set(seasonName).equalTo(record::getSeasonName)
                .set(seasonStartDate).equalTo(record::getSeasonStartDate)
                .set(seasonEndDate).equalTo(record::getSeasonEndDate)
                .set(airlineCode).equalTo(record::getAirlineCode)
                .set(flightNumber).equalTo(record::getFlightNumber)
                .set(movementIndicator).equalTo(record::getMovementIndicator)
                .set(seasonScheduleStartDate).equalTo(record::getSeasonScheduleStartDate)
                .set(seasonScheduleEndDate).equalTo(record::getSeasonScheduleEndDate)
                .set(weekOperateDay).equalTo(record::getWeekOperateDay)
                .set(flightScheduleTime).equalTo(record::getFlightScheduleTime)
                .set(flightType).equalTo(record::getFlightType)
                .set(flightIndicator).equalTo(record::getFlightIndicator)
                .set(aircraftType).equalTo(record::getAircraftType)
                .set(registration).equalTo(record::getRegistration)
                .set(linkedAirlineCode).equalTo(record::getLinkedAirlineCode)
                .set(linkedFlightNumber).equalTo(record::getLinkedFlightNumber)
                .set(terminal).equalTo(record::getTerminal)
                .set(maxPax).equalTo(record::getMaxPax)
                .set(masterAirlineCode).equalTo(record::getMasterAirlineCode)
                .set(masterFlightNumber).equalTo(record::getMasterFlightNumber)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsSeasonFlight record) {
        return UpdateDSL.updateWithMapper(this::update, fmsSeasonFlight)
                .set(seasonName).equalToWhenPresent(record::getSeasonName)
                .set(seasonStartDate).equalToWhenPresent(record::getSeasonStartDate)
                .set(seasonEndDate).equalToWhenPresent(record::getSeasonEndDate)
                .set(airlineCode).equalToWhenPresent(record::getAirlineCode)
                .set(flightNumber).equalToWhenPresent(record::getFlightNumber)
                .set(movementIndicator).equalToWhenPresent(record::getMovementIndicator)
                .set(seasonScheduleStartDate).equalToWhenPresent(record::getSeasonScheduleStartDate)
                .set(seasonScheduleEndDate).equalToWhenPresent(record::getSeasonScheduleEndDate)
                .set(weekOperateDay).equalToWhenPresent(record::getWeekOperateDay)
                .set(flightScheduleTime).equalToWhenPresent(record::getFlightScheduleTime)
                .set(flightType).equalToWhenPresent(record::getFlightType)
                .set(flightIndicator).equalToWhenPresent(record::getFlightIndicator)
                .set(aircraftType).equalToWhenPresent(record::getAircraftType)
                .set(registration).equalToWhenPresent(record::getRegistration)
                .set(linkedAirlineCode).equalToWhenPresent(record::getLinkedAirlineCode)
                .set(linkedFlightNumber).equalToWhenPresent(record::getLinkedFlightNumber)
                .set(terminal).equalToWhenPresent(record::getTerminal)
                .set(maxPax).equalToWhenPresent(record::getMaxPax)
                .set(masterAirlineCode).equalToWhenPresent(record::getMasterAirlineCode)
                .set(masterFlightNumber).equalToWhenPresent(record::getMasterFlightNumber)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsSeasonFlight record) {
        return UpdateDSL.updateWithMapper(this::update, fmsSeasonFlight)
                .set(seasonName).equalTo(record::getSeasonName)
                .set(seasonStartDate).equalTo(record::getSeasonStartDate)
                .set(seasonEndDate).equalTo(record::getSeasonEndDate)
                .set(airlineCode).equalTo(record::getAirlineCode)
                .set(flightNumber).equalTo(record::getFlightNumber)
                .set(movementIndicator).equalTo(record::getMovementIndicator)
                .set(seasonScheduleStartDate).equalTo(record::getSeasonScheduleStartDate)
                .set(seasonScheduleEndDate).equalTo(record::getSeasonScheduleEndDate)
                .set(weekOperateDay).equalTo(record::getWeekOperateDay)
                .set(flightScheduleTime).equalTo(record::getFlightScheduleTime)
                .set(flightType).equalTo(record::getFlightType)
                .set(flightIndicator).equalTo(record::getFlightIndicator)
                .set(aircraftType).equalTo(record::getAircraftType)
                .set(registration).equalTo(record::getRegistration)
                .set(linkedAirlineCode).equalTo(record::getLinkedAirlineCode)
                .set(linkedFlightNumber).equalTo(record::getLinkedFlightNumber)
                .set(terminal).equalTo(record::getTerminal)
                .set(maxPax).equalTo(record::getMaxPax)
                .set(masterAirlineCode).equalTo(record::getMasterAirlineCode)
                .set(masterFlightNumber).equalTo(record::getMasterFlightNumber)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsSeasonFlight record) {
        return UpdateDSL.updateWithMapper(this::update, fmsSeasonFlight)
                .set(seasonName).equalToWhenPresent(record::getSeasonName)
                .set(seasonStartDate).equalToWhenPresent(record::getSeasonStartDate)
                .set(seasonEndDate).equalToWhenPresent(record::getSeasonEndDate)
                .set(airlineCode).equalToWhenPresent(record::getAirlineCode)
                .set(flightNumber).equalToWhenPresent(record::getFlightNumber)
                .set(movementIndicator).equalToWhenPresent(record::getMovementIndicator)
                .set(seasonScheduleStartDate).equalToWhenPresent(record::getSeasonScheduleStartDate)
                .set(seasonScheduleEndDate).equalToWhenPresent(record::getSeasonScheduleEndDate)
                .set(weekOperateDay).equalToWhenPresent(record::getWeekOperateDay)
                .set(flightScheduleTime).equalToWhenPresent(record::getFlightScheduleTime)
                .set(flightType).equalToWhenPresent(record::getFlightType)
                .set(flightIndicator).equalToWhenPresent(record::getFlightIndicator)
                .set(aircraftType).equalToWhenPresent(record::getAircraftType)
                .set(registration).equalToWhenPresent(record::getRegistration)
                .set(linkedAirlineCode).equalToWhenPresent(record::getLinkedAirlineCode)
                .set(linkedFlightNumber).equalToWhenPresent(record::getLinkedFlightNumber)
                .set(terminal).equalToWhenPresent(record::getTerminal)
                .set(maxPax).equalToWhenPresent(record::getMaxPax)
                .set(masterAirlineCode).equalToWhenPresent(record::getMasterAirlineCode)
                .set(masterFlightNumber).equalToWhenPresent(record::getMasterFlightNumber)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}