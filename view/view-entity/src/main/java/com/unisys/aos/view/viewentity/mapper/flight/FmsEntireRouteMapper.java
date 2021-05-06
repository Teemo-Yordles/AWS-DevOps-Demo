package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsEntireRoute;
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

import static com.unisys.aos.view.viewentity.mapper.flight.FmsDelayDynamicSqlSupport.flightId;
import static com.unisys.aos.view.viewentity.mapper.flight.FmsEntireRouteDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Repository
@Mapper
public interface FmsEntireRouteMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsEntireRoute> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsEntireRouteResult")
    FmsEntireRoute selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FmsEntireRouteResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "flight_id", property = "flightId", jdbcType = JdbcType.BIGINT),
            @Result(column = "sequence_number", property = "sequenceNumber", jdbcType = JdbcType.TINYINT),
            @Result(column = "airport_code", property = "airportCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "arrival_time", property = "arrivalTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "departure_time", property = "departureTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<FmsEntireRoute> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsEntireRoute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsEntireRoute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsEntireRoute)
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
        DeleteStatementProvider deleteStatement = deleteFrom(fmsEntireRoute)
                .where(flightId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsEntireRoute record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsEntireRoute)
                .map(flightId).toProperty("flightId")
                .map(sequenceNumber).toProperty("sequenceNumber")
                .map(airportCode).toProperty("airportCode")
                .map(arrivalTime).toProperty("arrivalTime")
                .map(departureTime).toProperty("departureTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsEntireRoute record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsEntireRoute)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(sequenceNumber).toPropertyWhenPresent("sequenceNumber", record::getSequenceNumber)
                .map(airportCode).toPropertyWhenPresent("airportCode", record::getAirportCode)
                .map(arrivalTime).toPropertyWhenPresent("arrivalTime", record::getArrivalTime)
                .map(departureTime).toPropertyWhenPresent("departureTime", record::getDepartureTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsEntireRoute>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, airportCode, arrivalTime, departureTime, createTime, updateTime)
                .from(fmsEntireRoute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsEntireRoute>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, sequenceNumber, airportCode, arrivalTime, departureTime, createTime, updateTime)
                .from(fmsEntireRoute);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsEntireRoute selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, sequenceNumber, airportCode, arrivalTime, departureTime, createTime, updateTime)
                .from(fmsEntireRoute)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsEntireRoute> selectByAodbId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, airportCode, arrivalTime, departureTime, createTime, updateTime)
                .from(fmsEntireRoute)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsEntireRoute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsEntireRoute)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(airportCode).equalTo(record::getAirportCode)
                .set(arrivalTime).equalTo(record::getArrivalTime)
                .set(departureTime).equalTo(record::getDepartureTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsEntireRoute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsEntireRoute)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(airportCode).equalToWhenPresent(record::getAirportCode)
                .set(arrivalTime).equalToWhenPresent(record::getArrivalTime)
                .set(departureTime).equalToWhenPresent(record::getDepartureTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsEntireRoute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsEntireRoute)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(airportCode).equalTo(record::getAirportCode)
                .set(arrivalTime).equalTo(record::getArrivalTime)
                .set(departureTime).equalTo(record::getDepartureTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsEntireRoute record) {
        return UpdateDSL.updateWithMapper(this::update, fmsEntireRoute)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(airportCode).equalToWhenPresent(record::getAirportCode)
                .set(arrivalTime).equalToWhenPresent(record::getArrivalTime)
                .set(departureTime).equalToWhenPresent(record::getDepartureTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}