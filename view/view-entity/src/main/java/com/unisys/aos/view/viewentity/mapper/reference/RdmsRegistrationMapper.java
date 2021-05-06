package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsRegistration;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegistrationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface RdmsRegistrationMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsRegistration> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsRegistrationResult")
    RdmsRegistration selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsRegistrationResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "registration_code", property = "registrationCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "aircraft_type_code", property = "aircraftTypeCode", jdbcType = JdbcType.CHAR),
            @Result(column = "organization_id", property = "organizationId", jdbcType = JdbcType.INTEGER),
            @Result(column = "airline_code", property = "airlineCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "airline_subcompany_code", property = "airlineSubcompanyCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "max_pax", property = "maxPax", jdbcType = JdbcType.SMALLINT),
            @Result(column = "max_freight_weight", property = "maxFreightWeight", jdbcType = JdbcType.DECIMAL),
            @Result(column = "max_takeoff_weight", property = "maxTakeoffWeight", jdbcType = JdbcType.DECIMAL),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsRegistration> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsRegistration);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsRegistration);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsRegistration)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsRegistration record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsRegistration)
                .map(registrationCode).toProperty("registrationCode")
                .map(aircraftTypeCode).toProperty("aircraftTypeCode")
                .map(organizationId).toProperty("organizationId")
                .map(airlineCode).toProperty("airlineCode")
                .map(airlineSubcompanyCode).toProperty("airlineSubcompanyCode")
                .map(maxPax).toProperty("maxPax")
                .map(maxFreightWeight).toProperty("maxFreightWeight")
                .map(maxTakeoffWeight).toProperty("maxTakeoffWeight")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsRegistration record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsRegistration)
                .map(registrationCode).toPropertyWhenPresent("registrationCode", record::getRegistrationCode)
                .map(aircraftTypeCode).toPropertyWhenPresent("aircraftTypeCode", record::getAircraftTypeCode)
                .map(organizationId).toPropertyWhenPresent("organizationId", record::getOrganizationId)
                .map(airlineCode).toPropertyWhenPresent("airlineCode", record::getAirlineCode)
                .map(airlineSubcompanyCode).toPropertyWhenPresent("airlineSubcompanyCode", record::getAirlineSubcompanyCode)
                .map(maxPax).toPropertyWhenPresent("maxPax", record::getMaxPax)
                .map(maxFreightWeight).toPropertyWhenPresent("maxFreightWeight", record::getMaxFreightWeight)
                .map(maxTakeoffWeight).toPropertyWhenPresent("maxTakeoffWeight", record::getMaxTakeoffWeight)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsRegistration>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, registrationCode, aircraftTypeCode, organizationId, airlineCode, airlineSubcompanyCode, maxPax, maxFreightWeight, maxTakeoffWeight, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsRegistration);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsRegistration>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, registrationCode, aircraftTypeCode, organizationId, airlineCode, airlineSubcompanyCode, maxPax, maxFreightWeight, maxTakeoffWeight, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsRegistration);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsRegistration selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, registrationCode, aircraftTypeCode, organizationId, airlineCode, airlineSubcompanyCode, maxPax, maxFreightWeight, maxTakeoffWeight, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsRegistration)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsRegistration record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsRegistration)
                .set(registrationCode).equalTo(record::getRegistrationCode)
                .set(aircraftTypeCode).equalTo(record::getAircraftTypeCode)
                .set(organizationId).equalTo(record::getOrganizationId)
                .set(airlineCode).equalTo(record::getAirlineCode)
                .set(airlineSubcompanyCode).equalTo(record::getAirlineSubcompanyCode)
                .set(maxPax).equalTo(record::getMaxPax)
                .set(maxFreightWeight).equalTo(record::getMaxFreightWeight)
                .set(maxTakeoffWeight).equalTo(record::getMaxTakeoffWeight)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsRegistration record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsRegistration)
                .set(registrationCode).equalToWhenPresent(record::getRegistrationCode)
                .set(aircraftTypeCode).equalToWhenPresent(record::getAircraftTypeCode)
                .set(organizationId).equalToWhenPresent(record::getOrganizationId)
                .set(airlineCode).equalToWhenPresent(record::getAirlineCode)
                .set(airlineSubcompanyCode).equalToWhenPresent(record::getAirlineSubcompanyCode)
                .set(maxPax).equalToWhenPresent(record::getMaxPax)
                .set(maxFreightWeight).equalToWhenPresent(record::getMaxFreightWeight)
                .set(maxTakeoffWeight).equalToWhenPresent(record::getMaxTakeoffWeight)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsRegistration record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsRegistration)
                .set(registrationCode).equalTo(record::getRegistrationCode)
                .set(aircraftTypeCode).equalTo(record::getAircraftTypeCode)
                .set(organizationId).equalTo(record::getOrganizationId)
                .set(airlineCode).equalTo(record::getAirlineCode)
                .set(airlineSubcompanyCode).equalTo(record::getAirlineSubcompanyCode)
                .set(maxPax).equalTo(record::getMaxPax)
                .set(maxFreightWeight).equalTo(record::getMaxFreightWeight)
                .set(maxTakeoffWeight).equalTo(record::getMaxTakeoffWeight)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsRegistration record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsRegistration)
                .set(registrationCode).equalToWhenPresent(record::getRegistrationCode)
                .set(aircraftTypeCode).equalToWhenPresent(record::getAircraftTypeCode)
                .set(organizationId).equalToWhenPresent(record::getOrganizationId)
                .set(airlineCode).equalToWhenPresent(record::getAirlineCode)
                .set(airlineSubcompanyCode).equalToWhenPresent(record::getAirlineSubcompanyCode)
                .set(maxPax).equalToWhenPresent(record::getMaxPax)
                .set(maxFreightWeight).equalToWhenPresent(record::getMaxFreightWeight)
                .set(maxTakeoffWeight).equalToWhenPresent(record::getMaxTakeoffWeight)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}