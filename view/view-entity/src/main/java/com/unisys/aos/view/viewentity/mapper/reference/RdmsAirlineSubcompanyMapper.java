package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsAirlineSubcompany;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsAirlineSubcompanyDynamicSqlSupport.*;
import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionDynamicSqlSupport.expireTime;
import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionDynamicSqlSupport.inspireTime;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
@Repository
public interface RdmsAirlineSubcompanyMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsAirlineSubcompany> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsAirlineSubcompanyResult")
    RdmsAirlineSubcompany selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsAirlineSubcompanyResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "airline_iata_operator_code", property = "airlineIataOperatorCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "airline_subcompany_code", property = "airlineSubcompanyCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_company_inspire_time", property = "parentCompanyInspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "parent_company_expire_time", property = "parentCompanyExpireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsAirlineSubcompany> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsAirlineSubcompany);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirlineSubcompany);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirlineSubcompany)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsAirlineSubcompany record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAirlineSubcompany)
                .map(airlineIataOperatorCode).toProperty("airlineIataOperatorCode")
                .map(airlineSubcompanyCode).toProperty("airlineSubcompanyCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(parentCompanyInspireTime).toProperty("parentCompanyInspireTime")
                .map(parentCompanyExpireTime).toProperty("parentCompanyExpireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsAirlineSubcompany record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAirlineSubcompany)
                .map(airlineIataOperatorCode).toPropertyWhenPresent("airlineIataOperatorCode", record::getAirlineIataOperatorCode)
                .map(airlineSubcompanyCode).toPropertyWhenPresent("airlineSubcompanyCode", record::getAirlineSubcompanyCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(parentCompanyInspireTime).toPropertyWhenPresent("parentCompanyInspireTime", record::getParentCompanyInspireTime)
                .map(parentCompanyExpireTime).toPropertyWhenPresent("parentCompanyExpireTime", record::getParentCompanyExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAirlineSubcompany>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, airlineIataOperatorCode, airlineSubcompanyCode, description, localDescription, parentCompanyInspireTime, parentCompanyExpireTime, createTime, updateTime)
                .from(rdmsAirlineSubcompany);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAirlineSubcompany>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, airlineIataOperatorCode, airlineSubcompanyCode, description, localDescription, parentCompanyInspireTime, parentCompanyExpireTime, createTime, updateTime)
                .from(rdmsAirlineSubcompany);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsAirlineSubcompany selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, airlineIataOperatorCode, airlineSubcompanyCode, description, localDescription, parentCompanyInspireTime, parentCompanyExpireTime, createTime, updateTime)
                .from(rdmsAirlineSubcompany)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsAirlineSubcompany record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirlineSubcompany)
                .set(airlineIataOperatorCode).equalTo(record::getAirlineIataOperatorCode)
                .set(airlineSubcompanyCode).equalTo(record::getAirlineSubcompanyCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(parentCompanyInspireTime).equalTo(record::getParentCompanyInspireTime)
                .set(parentCompanyExpireTime).equalTo(record::getParentCompanyExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsAirlineSubcompany record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirlineSubcompany)
                .set(airlineIataOperatorCode).equalToWhenPresent(record::getAirlineIataOperatorCode)
                .set(airlineSubcompanyCode).equalToWhenPresent(record::getAirlineSubcompanyCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(parentCompanyInspireTime).equalToWhenPresent(record::getParentCompanyInspireTime)
                .set(parentCompanyExpireTime).equalToWhenPresent(record::getParentCompanyExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsAirlineSubcompany record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirlineSubcompany)
                .set(airlineIataOperatorCode).equalTo(record::getAirlineIataOperatorCode)
                .set(airlineSubcompanyCode).equalTo(record::getAirlineSubcompanyCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(parentCompanyInspireTime).equalTo(record::getParentCompanyInspireTime)
                .set(parentCompanyExpireTime).equalTo(record::getParentCompanyExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsAirlineSubcompany record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirlineSubcompany)
                .set(airlineIataOperatorCode).equalToWhenPresent(record::getAirlineIataOperatorCode)
                .set(airlineSubcompanyCode).equalToWhenPresent(record::getAirlineSubcompanyCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(parentCompanyInspireTime).equalToWhenPresent(record::getParentCompanyInspireTime)
                .set(parentCompanyExpireTime).equalToWhenPresent(record::getParentCompanyExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    /**
     * get map entries based on Airline IATA Operator Code
     *
     * @param currentAirlineCode Airline IATA Operator Code
     * @param inspire            inspire time
     * @param expire             expire time
     * @return map entries
     */
    default List<RdmsAirlineSubcompany> selectByAirlineCode(String currentAirlineCode, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, airlineIataOperatorCode, airlineSubcompanyCode, description, localDescription, parentCompanyInspireTime, parentCompanyExpireTime, createTime, updateTime).from(rdmsAirlineSubcompany)
                .where(airlineIataOperatorCode, isEqualTo(currentAirlineCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /**
     * delete airline subcompany through Airline IATA Operator Code
     *
     * @param currentAirlineCode Airline IATA Operator Code
     * @param inspire            inspire time
     * @param expire             expire time
     */
    default int deleteByAirlineCode(String currentAirlineCode, Long inspire, Long expire) {
        //noinspection deprecation
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirlineSubcompany)
                .where(airlineIataOperatorCode, isEqualTo(currentAirlineCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }
}