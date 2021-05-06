package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsAircraftType;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsAircraftTypeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface RdmsAircraftTypeMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsAircraftType> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsAircraftTypeResult")
    RdmsAircraftType selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsAircraftTypeResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "iata_code", property = "iataCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "icao_code", property = "icaoCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "size_category", property = "sizeCategory", jdbcType = JdbcType.CHAR),
            @Result(column = "max_pax", property = "maxPax", jdbcType = JdbcType.SMALLINT),
            @Result(column = "max_freight_weight", property = "maxFreightWeight", jdbcType = JdbcType.DECIMAL),
            @Result(column = "max_takeoff_weight", property = "maxTakeoffWeight", jdbcType = JdbcType.DECIMAL),
            @Result(column = "aircraft_length", property = "aircraftLength", jdbcType = JdbcType.DECIMAL),
            @Result(column = "wing_span", property = "wingSpan", jdbcType = JdbcType.DECIMAL),
            @Result(column = "min_handling_time", property = "minHandlingTime", jdbcType = JdbcType.DECIMAL),
            @Result(column = "max_airbridge", property = "maxAirbridge", jdbcType = JdbcType.TINYINT),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsAircraftType> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsAircraftType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAircraftType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAircraftType)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsAircraftType record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAircraftType)
                .map(iataCode).toProperty("iataCode")
                .map(icaoCode).toProperty("icaoCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(sizeCategory).toProperty("sizeCategory")
                .map(maxPax).toProperty("maxPax")
                .map(maxFreightWeight).toProperty("maxFreightWeight")
                .map(maxTakeoffWeight).toProperty("maxTakeoffWeight")
                .map(aircraftLength).toProperty("aircraftLength")
                .map(wingSpan).toProperty("wingSpan")
                .map(minHandlingTime).toProperty("minHandlingTime")
                .map(maxAirbridge).toProperty("maxAirbridge")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsAircraftType record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAircraftType)
                .map(iataCode).toPropertyWhenPresent("iataCode", record::getIataCode)
                .map(icaoCode).toPropertyWhenPresent("icaoCode", record::getIcaoCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(sizeCategory).toPropertyWhenPresent("sizeCategory", record::getSizeCategory)
                .map(maxPax).toPropertyWhenPresent("maxPax", record::getMaxPax)
                .map(maxFreightWeight).toPropertyWhenPresent("maxFreightWeight", record::getMaxFreightWeight)
                .map(maxTakeoffWeight).toPropertyWhenPresent("maxTakeoffWeight", record::getMaxTakeoffWeight)
                .map(aircraftLength).toPropertyWhenPresent("aircraftLength", record::getAircraftLength)
                .map(wingSpan).toPropertyWhenPresent("wingSpan", record::getWingSpan)
                .map(minHandlingTime).toPropertyWhenPresent("minHandlingTime", record::getMinHandlingTime)
                .map(maxAirbridge).toPropertyWhenPresent("maxAirbridge", record::getMaxAirbridge)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAircraftType>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, iataCode, icaoCode, description, localDescription, sizeCategory, maxPax, maxFreightWeight, maxTakeoffWeight, aircraftLength, wingSpan, minHandlingTime, maxAirbridge, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAircraftType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAircraftType>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, iataCode, icaoCode, description, localDescription, sizeCategory, maxPax, maxFreightWeight, maxTakeoffWeight, aircraftLength, wingSpan, minHandlingTime, maxAirbridge, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAircraftType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsAircraftType selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, iataCode, icaoCode, description, localDescription, sizeCategory, maxPax, maxFreightWeight, maxTakeoffWeight, aircraftLength, wingSpan, minHandlingTime, maxAirbridge, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAircraftType)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsAircraftType record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAircraftType)
                .set(iataCode).equalTo(record::getIataCode)
                .set(icaoCode).equalTo(record::getIcaoCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(sizeCategory).equalTo(record::getSizeCategory)
                .set(maxPax).equalTo(record::getMaxPax)
                .set(maxFreightWeight).equalTo(record::getMaxFreightWeight)
                .set(maxTakeoffWeight).equalTo(record::getMaxTakeoffWeight)
                .set(aircraftLength).equalTo(record::getAircraftLength)
                .set(wingSpan).equalTo(record::getWingSpan)
                .set(minHandlingTime).equalTo(record::getMinHandlingTime)
                .set(maxAirbridge).equalTo(record::getMaxAirbridge)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsAircraftType record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAircraftType)
                .set(iataCode).equalToWhenPresent(record::getIataCode)
                .set(icaoCode).equalToWhenPresent(record::getIcaoCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(sizeCategory).equalToWhenPresent(record::getSizeCategory)
                .set(maxPax).equalToWhenPresent(record::getMaxPax)
                .set(maxFreightWeight).equalToWhenPresent(record::getMaxFreightWeight)
                .set(maxTakeoffWeight).equalToWhenPresent(record::getMaxTakeoffWeight)
                .set(aircraftLength).equalToWhenPresent(record::getAircraftLength)
                .set(wingSpan).equalToWhenPresent(record::getWingSpan)
                .set(minHandlingTime).equalToWhenPresent(record::getMinHandlingTime)
                .set(maxAirbridge).equalToWhenPresent(record::getMaxAirbridge)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsAircraftType record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAircraftType)
                .set(iataCode).equalTo(record::getIataCode)
                .set(icaoCode).equalTo(record::getIcaoCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(sizeCategory).equalTo(record::getSizeCategory)
                .set(maxPax).equalTo(record::getMaxPax)
                .set(maxFreightWeight).equalTo(record::getMaxFreightWeight)
                .set(maxTakeoffWeight).equalTo(record::getMaxTakeoffWeight)
                .set(aircraftLength).equalTo(record::getAircraftLength)
                .set(wingSpan).equalTo(record::getWingSpan)
                .set(minHandlingTime).equalTo(record::getMinHandlingTime)
                .set(maxAirbridge).equalTo(record::getMaxAirbridge)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsAircraftType record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAircraftType)
                .set(iataCode).equalToWhenPresent(record::getIataCode)
                .set(icaoCode).equalToWhenPresent(record::getIcaoCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(sizeCategory).equalToWhenPresent(record::getSizeCategory)
                .set(maxPax).equalToWhenPresent(record::getMaxPax)
                .set(maxFreightWeight).equalToWhenPresent(record::getMaxFreightWeight)
                .set(maxTakeoffWeight).equalToWhenPresent(record::getMaxTakeoffWeight)
                .set(aircraftLength).equalToWhenPresent(record::getAircraftLength)
                .set(wingSpan).equalToWhenPresent(record::getWingSpan)
                .set(minHandlingTime).equalToWhenPresent(record::getMinHandlingTime)
                .set(maxAirbridge).equalToWhenPresent(record::getMaxAirbridge)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}