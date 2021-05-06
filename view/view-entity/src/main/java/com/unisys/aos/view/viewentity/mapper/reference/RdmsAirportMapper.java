package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsAirport;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsAirportDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface RdmsAirportMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsAirport> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsAirportResult")
    RdmsAirport selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsAirportResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "iata_code", property = "iataCode", jdbcType = JdbcType.CHAR),
            @Result(column = "icao_code", property = "icaoCode", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "distance", property = "distance", jdbcType = JdbcType.BIGINT),
            @Result(column = "country_code", property = "countryCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "city_code", property = "cityCode", jdbcType = JdbcType.CHAR),
            @Result(column = "airport_category", property = "airportCategory", jdbcType = JdbcType.CHAR),
            @Result(column = "haul_category", property = "haulCategory", jdbcType = JdbcType.CHAR),
            @Result(column = "abbreviation_1", property = "abbreviation1", jdbcType = JdbcType.VARCHAR),
            @Result(column = "abbreviation_2", property = "abbreviation2", jdbcType = JdbcType.VARCHAR),
            @Result(column = "abbreviation_3", property = "abbreviation3", jdbcType = JdbcType.VARCHAR),
            @Result(column = "abbreviation_4", property = "abbreviation4", jdbcType = JdbcType.VARCHAR),
            @Result(column = "abbreviation_5", property = "abbreviation5", jdbcType = JdbcType.VARCHAR),
            @Result(column = "abbreviation_6", property = "abbreviation6", jdbcType = JdbcType.VARCHAR),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsAirport> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsAirport);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirport);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirport)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsAirport record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAirport)
                .map(iataCode).toProperty("iataCode")
                .map(icaoCode).toProperty("icaoCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(distance).toProperty("distance")
                .map(countryCode).toProperty("countryCode")
                .map(cityCode).toProperty("cityCode")
                .map(airportCategory).toProperty("airportCategory")
                .map(haulCategory).toProperty("haulCategory")
                .map(abbreviation1).toProperty("abbreviation1")
                .map(abbreviation2).toProperty("abbreviation2")
                .map(abbreviation3).toProperty("abbreviation3")
                .map(abbreviation4).toProperty("abbreviation4")
                .map(abbreviation5).toProperty("abbreviation5")
                .map(abbreviation6).toProperty("abbreviation6")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsAirport record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAirport)
                .map(iataCode).toPropertyWhenPresent("iataCode", record::getIataCode)
                .map(icaoCode).toPropertyWhenPresent("icaoCode", record::getIcaoCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(distance).toPropertyWhenPresent("distance", record::getDistance)
                .map(countryCode).toPropertyWhenPresent("countryCode", record::getCountryCode)
                .map(cityCode).toPropertyWhenPresent("cityCode", record::getCityCode)
                .map(airportCategory).toPropertyWhenPresent("airportCategory", record::getAirportCategory)
                .map(haulCategory).toPropertyWhenPresent("haulCategory", record::getHaulCategory)
                .map(abbreviation1).toPropertyWhenPresent("abbreviation1", record::getAbbreviation1)
                .map(abbreviation2).toPropertyWhenPresent("abbreviation2", record::getAbbreviation2)
                .map(abbreviation3).toPropertyWhenPresent("abbreviation3", record::getAbbreviation3)
                .map(abbreviation4).toPropertyWhenPresent("abbreviation4", record::getAbbreviation4)
                .map(abbreviation5).toPropertyWhenPresent("abbreviation5", record::getAbbreviation5)
                .map(abbreviation6).toPropertyWhenPresent("abbreviation6", record::getAbbreviation6)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAirport>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, iataCode, icaoCode, description, localDescription, distance, countryCode, cityCode, airportCategory, haulCategory, abbreviation1, abbreviation2, abbreviation3, abbreviation4, abbreviation5, abbreviation6, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirport);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAirport>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, iataCode, icaoCode, description, localDescription, distance, countryCode, cityCode, airportCategory, haulCategory, abbreviation1, abbreviation2, abbreviation3, abbreviation4, abbreviation5, abbreviation6, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirport);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsAirport selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, iataCode, icaoCode, description, localDescription, distance, countryCode, cityCode, airportCategory, haulCategory, abbreviation1, abbreviation2, abbreviation3, abbreviation4, abbreviation5, abbreviation6, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirport)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<RdmsAirport> selectByIataCode(String iataCode_) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, iataCode, icaoCode, description, localDescription, distance, countryCode, cityCode, airportCategory, haulCategory, abbreviation1, abbreviation2, abbreviation3, abbreviation4, abbreviation5, abbreviation6, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirport)
                .where(iataCode, isEqualTo(iataCode_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsAirport record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirport)
                .set(iataCode).equalTo(record::getIataCode)
                .set(icaoCode).equalTo(record::getIcaoCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(distance).equalTo(record::getDistance)
                .set(countryCode).equalTo(record::getCountryCode)
                .set(cityCode).equalTo(record::getCityCode)
                .set(airportCategory).equalTo(record::getAirportCategory)
                .set(haulCategory).equalTo(record::getHaulCategory)
                .set(abbreviation1).equalTo(record::getAbbreviation1)
                .set(abbreviation2).equalTo(record::getAbbreviation2)
                .set(abbreviation3).equalTo(record::getAbbreviation3)
                .set(abbreviation4).equalTo(record::getAbbreviation4)
                .set(abbreviation5).equalTo(record::getAbbreviation5)
                .set(abbreviation6).equalTo(record::getAbbreviation6)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsAirport record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirport)
                .set(iataCode).equalToWhenPresent(record::getIataCode)
                .set(icaoCode).equalToWhenPresent(record::getIcaoCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(distance).equalToWhenPresent(record::getDistance)
                .set(countryCode).equalToWhenPresent(record::getCountryCode)
                .set(cityCode).equalToWhenPresent(record::getCityCode)
                .set(airportCategory).equalToWhenPresent(record::getAirportCategory)
                .set(haulCategory).equalToWhenPresent(record::getHaulCategory)
                .set(abbreviation1).equalToWhenPresent(record::getAbbreviation1)
                .set(abbreviation2).equalToWhenPresent(record::getAbbreviation2)
                .set(abbreviation3).equalToWhenPresent(record::getAbbreviation3)
                .set(abbreviation4).equalToWhenPresent(record::getAbbreviation4)
                .set(abbreviation5).equalToWhenPresent(record::getAbbreviation5)
                .set(abbreviation6).equalToWhenPresent(record::getAbbreviation6)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsAirport record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirport)
                .set(iataCode).equalTo(record::getIataCode)
                .set(icaoCode).equalTo(record::getIcaoCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(distance).equalTo(record::getDistance)
                .set(countryCode).equalTo(record::getCountryCode)
                .set(cityCode).equalTo(record::getCityCode)
                .set(airportCategory).equalTo(record::getAirportCategory)
                .set(haulCategory).equalTo(record::getHaulCategory)
                .set(abbreviation1).equalTo(record::getAbbreviation1)
                .set(abbreviation2).equalTo(record::getAbbreviation2)
                .set(abbreviation3).equalTo(record::getAbbreviation3)
                .set(abbreviation4).equalTo(record::getAbbreviation4)
                .set(abbreviation5).equalTo(record::getAbbreviation5)
                .set(abbreviation6).equalTo(record::getAbbreviation6)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsAirport record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirport)
                .set(iataCode).equalToWhenPresent(record::getIataCode)
                .set(icaoCode).equalToWhenPresent(record::getIcaoCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(distance).equalToWhenPresent(record::getDistance)
                .set(countryCode).equalToWhenPresent(record::getCountryCode)
                .set(cityCode).equalToWhenPresent(record::getCityCode)
                .set(airportCategory).equalToWhenPresent(record::getAirportCategory)
                .set(haulCategory).equalToWhenPresent(record::getHaulCategory)
                .set(abbreviation1).equalToWhenPresent(record::getAbbreviation1)
                .set(abbreviation2).equalToWhenPresent(record::getAbbreviation2)
                .set(abbreviation3).equalToWhenPresent(record::getAbbreviation3)
                .set(abbreviation4).equalToWhenPresent(record::getAbbreviation4)
                .set(abbreviation5).equalToWhenPresent(record::getAbbreviation5)
                .set(abbreviation6).equalToWhenPresent(record::getAbbreviation6)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}