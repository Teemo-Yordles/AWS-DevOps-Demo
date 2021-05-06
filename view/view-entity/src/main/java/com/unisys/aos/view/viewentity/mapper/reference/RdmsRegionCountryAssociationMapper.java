package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsRegionCountryAssociation;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionCountryAssociationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
@Repository
public interface RdmsRegionCountryAssociationMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsRegionCountryAssociation> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsRegionCountryAssociationResult")
    RdmsRegionCountryAssociation selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsRegionCountryAssociationResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "region_code", property = "regionCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "country_code", property = "countryCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "country_inspire_time", property = "countryInspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "country_expire_time", property = "countryExpireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "region_inspire_time", property = "regionInspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "region_expire_time", property = "regionExpireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsRegionCountryAssociation> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsRegionCountryAssociation);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsRegionCountryAssociation);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsRegionCountryAssociation)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsRegionCountryAssociation record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsRegionCountryAssociation)
                .map(regionCode).toProperty("regionCode")
                .map(countryCode).toProperty("countryCode")
                .map(countryInspireTime).toProperty("countryInspireTime")
                .map(countryExpireTime).toProperty("countryExpireTime")
                .map(regionInspireTime).toProperty("regionInspireTime")
                .map(regionExpireTime).toProperty("regionExpireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsRegionCountryAssociation record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsRegionCountryAssociation)
                .map(regionCode).toPropertyWhenPresent("regionCode", record::getRegionCode)
                .map(countryCode).toPropertyWhenPresent("countryCode", record::getCountryCode)
                .map(countryInspireTime).toPropertyWhenPresent("countryInspireTime", record::getCountryInspireTime)
                .map(countryExpireTime).toPropertyWhenPresent("countryExpireTime", record::getCountryExpireTime)
                .map(regionInspireTime).toPropertyWhenPresent("regionInspireTime", record::getRegionInspireTime)
                .map(regionExpireTime).toPropertyWhenPresent("regionExpireTime", record::getRegionExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsRegionCountryAssociation>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, regionCode, countryCode, countryInspireTime, countryExpireTime, regionInspireTime, regionExpireTime, createTime, updateTime)
                .from(rdmsRegionCountryAssociation);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsRegionCountryAssociation>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, regionCode, countryCode, countryInspireTime, countryExpireTime, regionInspireTime, regionExpireTime, createTime, updateTime)
                .from(rdmsRegionCountryAssociation);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsRegionCountryAssociation selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, regionCode, countryCode, countryInspireTime, countryExpireTime, regionInspireTime, regionExpireTime, createTime, updateTime)
                .from(rdmsRegionCountryAssociation)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsRegionCountryAssociation record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsRegionCountryAssociation)
                .set(regionCode).equalTo(record::getRegionCode)
                .set(countryCode).equalTo(record::getCountryCode)
                .set(countryInspireTime).equalTo(record::getCountryInspireTime)
                .set(countryExpireTime).equalTo(record::getCountryExpireTime)
                .set(regionInspireTime).equalTo(record::getRegionInspireTime)
                .set(regionExpireTime).equalTo(record::getRegionExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsRegionCountryAssociation record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsRegionCountryAssociation)
                .set(regionCode).equalToWhenPresent(record::getRegionCode)
                .set(countryCode).equalToWhenPresent(record::getCountryCode)
                .set(countryInspireTime).equalToWhenPresent(record::getCountryInspireTime)
                .set(countryExpireTime).equalToWhenPresent(record::getCountryExpireTime)
                .set(regionInspireTime).equalToWhenPresent(record::getRegionInspireTime)
                .set(regionExpireTime).equalToWhenPresent(record::getRegionExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsRegionCountryAssociation record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsRegionCountryAssociation)
                .set(regionCode).equalTo(record::getRegionCode)
                .set(countryCode).equalTo(record::getCountryCode)
                .set(countryInspireTime).equalTo(record::getCountryInspireTime)
                .set(countryExpireTime).equalTo(record::getCountryExpireTime)
                .set(regionInspireTime).equalTo(record::getRegionInspireTime)
                .set(regionExpireTime).equalTo(record::getRegionExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsRegionCountryAssociation record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsRegionCountryAssociation)
                .set(regionCode).equalToWhenPresent(record::getRegionCode)
                .set(countryCode).equalToWhenPresent(record::getCountryCode)
                .set(countryInspireTime).equalToWhenPresent(record::getCountryInspireTime)
                .set(countryExpireTime).equalToWhenPresent(record::getCountryExpireTime)
                .set(regionInspireTime).equalToWhenPresent(record::getRegionInspireTime)
                .set(regionExpireTime).equalToWhenPresent(record::getRegionExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    /***
     * Get RdmsRegionCountryAssociation entity by gate code.
     *
     * @param code region code
     * @param inspire inspire time
     * @param expire expire time
     * @return RdmsRegionCountryAssociation entries
     */
    default List<RdmsRegionCountryAssociation> selectByRegionCode(String code, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, regionCode, countryCode, countryInspireTime, countryExpireTime, regionInspireTime, regionExpireTime, createTime, updateTime)
                .from(rdmsRegionCountryAssociation)
                .where(regionCode, isEqualTo(code))
                .and(regionInspireTime, isLessThanOrEqualTo(inspire))
                .and(regionExpireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /***
     * Get RdmsRegionCountryAssociation entity by gate code.
     *
     * @param code country code
     * @param inspire inspire time
     * @param expire expire time
     * @return RdmsRegionCountryAssociation entries
     */
    default List<RdmsRegionCountryAssociation> selectByCountryCode(String code, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, regionCode, countryCode, countryInspireTime, countryExpireTime, regionInspireTime, regionExpireTime, createTime, updateTime)
                .from(rdmsRegionCountryAssociation)
                .where(countryCode, isEqualTo(code))
                .and(countryInspireTime, isLessThanOrEqualTo(inspire))
                .and(countryExpireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /**
     * delete association through Region Code
     *
     * @param code    Region Code
     * @param inspire inspire time
     * @param expire  expire time
     * @return delete result
     */
    default int deleteByRegionCode(String code, Long inspire, Long expire) {
        //noinspection deprecation
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsRegionCountryAssociation)
                .where(regionCode, isEqualTo(code))
                .and(regionInspireTime, isLessThanOrEqualTo(inspire))
                .and(regionExpireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /**
     * delete association through Country Code
     *
     * @param code    Country Code
     * @param inspire inspire time
     * @param expire  expire time
     * @return delete result
     */
    default int deleteByCountryCode(String code, Long inspire, Long expire) {
        //noinspection deprecation
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsRegionCountryAssociation)
                .where(countryCode, isEqualTo(code))
                .and(countryInspireTime, isLessThanOrEqualTo(inspire))
                .and(countryExpireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }
}