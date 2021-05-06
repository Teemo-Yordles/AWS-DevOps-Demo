package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsCity;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsCityDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
@Repository
public interface RdmsCityMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsCity> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsCityResult")
    RdmsCity selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsCityResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "iata_city_code", property = "iataCityCode", jdbcType = JdbcType.CHAR),
            @Result(column = "icao_city_code", property = "icaoCityCode", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "country_code", property = "countryCode", jdbcType = JdbcType.VARCHAR),
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
    List<RdmsCity> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsCity);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsCity);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsCity)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsCity record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsCity)
                .map(iataCityCode).toProperty("iataCityCode")
                .map(icaoCityCode).toProperty("icaoCityCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(countryCode).toProperty("countryCode")
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
    default int insertSelective(RdmsCity record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsCity)
                .map(iataCityCode).toPropertyWhenPresent("iataCityCode", record::getIataCityCode)
                .map(icaoCityCode).toPropertyWhenPresent("icaoCityCode", record::getIcaoCityCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(countryCode).toPropertyWhenPresent("countryCode", record::getCountryCode)
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
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsCity>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, iataCityCode, icaoCityCode, description, localDescription, countryCode, abbreviation1, abbreviation2, abbreviation3, abbreviation4, abbreviation5, abbreviation6, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsCity);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsCity>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, iataCityCode, icaoCityCode, description, localDescription, countryCode, abbreviation1, abbreviation2, abbreviation3, abbreviation4, abbreviation5, abbreviation6, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsCity);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsCity selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, iataCityCode, icaoCityCode, description, localDescription, countryCode, abbreviation1, abbreviation2, abbreviation3, abbreviation4, abbreviation5, abbreviation6, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsCity)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    /***
     * Get RdmsCity entity by city iata code.
     * @param iataCode city iata code
     * @param inspire inspire time
     * @param expire expire time
     * @return RdmsCity entity
     */
    default RdmsCity selectByIataCityCode(String iataCode, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectOne, id, iataCityCode, icaoCityCode, description, localDescription, countryCode, abbreviation1, abbreviation2, abbreviation3, abbreviation4, abbreviation5, abbreviation6, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsCity)
                .where(iataCityCode, isEqualTo(iataCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /***
     * Get RdmsCity entities by city iata code.
     * @param iataCode city iata code
     * @return RdmsCity entities
     */
    default List<RdmsCity> selectByIataCityCode(String iataCode) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, iataCityCode, icaoCityCode, description, localDescription, countryCode, abbreviation1, abbreviation2, abbreviation3, abbreviation4, abbreviation5, abbreviation6, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsCity)
                .where(iataCityCode, isEqualTo(iataCode))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsCity record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsCity)
                .set(iataCityCode).equalTo(record::getIataCityCode)
                .set(icaoCityCode).equalTo(record::getIcaoCityCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(countryCode).equalTo(record::getCountryCode)
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
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsCity record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsCity)
                .set(iataCityCode).equalToWhenPresent(record::getIataCityCode)
                .set(icaoCityCode).equalToWhenPresent(record::getIcaoCityCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(countryCode).equalToWhenPresent(record::getCountryCode)
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
    default int updateByPrimaryKey(RdmsCity record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsCity)
                .set(iataCityCode).equalTo(record::getIataCityCode)
                .set(icaoCityCode).equalTo(record::getIcaoCityCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(countryCode).equalTo(record::getCountryCode)
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
    default int updateByPrimaryKeySelective(RdmsCity record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsCity)
                .set(iataCityCode).equalToWhenPresent(record::getIataCityCode)
                .set(icaoCityCode).equalToWhenPresent(record::getIcaoCityCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(countryCode).equalToWhenPresent(record::getCountryCode)
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