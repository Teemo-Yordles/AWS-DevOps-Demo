package com.unisys.aos.view.viewentity.mapper.flight;

import static com.unisys.aos.view.viewentity.mapper.flight.FmsSeasonEntireRouteDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.unisys.aos.view.viewentity.entity.flight.FmsSeasonEntireRoute;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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

@Repository
@Mapper
public interface FmsSeasonEntireRouteMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<FmsSeasonEntireRoute> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("FmsSeasonEntireRouteResult")
    FmsSeasonEntireRoute selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="FmsSeasonEntireRouteResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="flight_id", property="flightId", jdbcType=JdbcType.BIGINT),
        @Result(column="sequence_number", property="sequenceNumber", jdbcType=JdbcType.TINYINT),
        @Result(column="airport_code", property="airportCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="arrival_time", property="arrivalTime", jdbcType=JdbcType.CHAR),
        @Result(column="arrival_day_change", property="arrivalDayChange", jdbcType=JdbcType.CHAR),
        @Result(column="departure_time", property="departureTime", jdbcType=JdbcType.CHAR),
        @Result(column="departure_day_change", property="departureDayChange", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FmsSeasonEntireRoute> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsSeasonEntireRoute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsSeasonEntireRoute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsSeasonEntireRoute)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default int deleteByFlightId(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsSeasonEntireRoute)
                .where(flightId, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsSeasonEntireRoute record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsSeasonEntireRoute)
                .map(flightId).toProperty("flightId")
                .map(sequenceNumber).toProperty("sequenceNumber")
                .map(airportCode).toProperty("airportCode")
                .map(arrivalTime).toProperty("arrivalTime")
                .map(arrivalDayChange).toProperty("arrivalDayChange")
                .map(departureTime).toProperty("departureTime")
                .map(departureDayChange).toProperty("departureDayChange")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsSeasonEntireRoute record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsSeasonEntireRoute)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(sequenceNumber).toPropertyWhenPresent("sequenceNumber", record::getSequenceNumber)
                .map(airportCode).toPropertyWhenPresent("airportCode", record::getAirportCode)
                .map(arrivalTime).toPropertyWhenPresent("arrivalTime", record::getArrivalTime)
                .map(arrivalDayChange).toPropertyWhenPresent("arrivalDayChange", record::getArrivalDayChange)
                .map(departureTime).toPropertyWhenPresent("departureTime", record::getDepartureTime)
                .map(departureDayChange).toPropertyWhenPresent("departureDayChange", record::getDepartureDayChange)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsSeasonEntireRoute>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, airportCode, arrivalTime, arrivalDayChange, departureTime, departureDayChange, createTime, updateTime)
                .from(fmsSeasonEntireRoute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsSeasonEntireRoute>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, sequenceNumber, airportCode, arrivalTime, arrivalDayChange, departureTime, departureDayChange, createTime, updateTime)
                .from(fmsSeasonEntireRoute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsSeasonEntireRoute selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, sequenceNumber, airportCode, arrivalTime, arrivalDayChange, departureTime, departureDayChange, createTime, updateTime)
                .from(fmsSeasonEntireRoute)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsSeasonEntireRoute> selectByFlightId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, airportCode, arrivalTime, arrivalDayChange, departureTime, departureDayChange, createTime, updateTime)
                .from(fmsSeasonEntireRoute)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsSeasonEntireRoute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsSeasonEntireRoute)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(airportCode).equalTo(record::getAirportCode)
                .set(arrivalTime).equalTo(record::getArrivalTime)
                .set(arrivalDayChange).equalTo(record::getArrivalDayChange)
                .set(departureTime).equalTo(record::getDepartureTime)
                .set(departureDayChange).equalTo(record::getDepartureDayChange)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsSeasonEntireRoute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsSeasonEntireRoute)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(airportCode).equalToWhenPresent(record::getAirportCode)
                .set(arrivalTime).equalToWhenPresent(record::getArrivalTime)
                .set(arrivalDayChange).equalToWhenPresent(record::getArrivalDayChange)
                .set(departureTime).equalToWhenPresent(record::getDepartureTime)
                .set(departureDayChange).equalToWhenPresent(record::getDepartureDayChange)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsSeasonEntireRoute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsSeasonEntireRoute)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(airportCode).equalTo(record::getAirportCode)
                .set(arrivalTime).equalTo(record::getArrivalTime)
                .set(arrivalDayChange).equalTo(record::getArrivalDayChange)
                .set(departureTime).equalTo(record::getDepartureTime)
                .set(departureDayChange).equalTo(record::getDepartureDayChange)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsSeasonEntireRoute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsSeasonEntireRoute)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(airportCode).equalToWhenPresent(record::getAirportCode)
                .set(arrivalTime).equalToWhenPresent(record::getArrivalTime)
                .set(arrivalDayChange).equalToWhenPresent(record::getArrivalDayChange)
                .set(departureTime).equalToWhenPresent(record::getDepartureTime)
                .set(departureDayChange).equalToWhenPresent(record::getDepartureDayChange)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}