package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsAirline;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsAirlineDynamicSqlSupport.*;
import static com.unisys.aos.view.viewentity.mapper.reference.RdmsAirlineSubcompanyDynamicSqlSupport.airlineIataOperatorCode;
import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionDynamicSqlSupport.expireTime;
import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionDynamicSqlSupport.inspireTime;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
@Repository
public interface RdmsAirlineMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsAirline> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsAirlineResult")
    RdmsAirline selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsAirlineResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "iata_operator_code", property = "iataOperatorCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "icao_operator_code", property = "icaoOperatorCode", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "country_code", property = "countryCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "default_terminal", property = "defaultTerminal", jdbcType = JdbcType.VARCHAR),
            @Result(column = "default_terminal_mvin", property = "defaultTerminalMvin", jdbcType = JdbcType.CHAR),
            @Result(column = "default_terminal_flin", property = "defaultTerminalFlin", jdbcType = JdbcType.CHAR),
            @Result(column = "operator_groups", property = "operatorGroups", jdbcType = JdbcType.VARCHAR),
            @Result(column = "airline_category", property = "airlineCategory", jdbcType = JdbcType.CHAR),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsAirline> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsAirline);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirline);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirline)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsAirline record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAirline)
                .map(iataOperatorCode).toProperty("iataOperatorCode")
                .map(icaoOperatorCode).toProperty("icaoOperatorCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(countryCode).toProperty("countryCode")
                .map(defaultTerminal).toProperty("defaultTerminal")
                .map(defaultTerminalMvin).toProperty("defaultTerminalMvin")
                .map(defaultTerminalFlin).toProperty("defaultTerminalFlin")
                .map(operatorGroups).toProperty("operatorGroups")
                .map(airlineCategory).toProperty("airlineCategory")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsAirline record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAirline)
                .map(iataOperatorCode).toPropertyWhenPresent("iataOperatorCode", record::getIataOperatorCode)
                .map(icaoOperatorCode).toPropertyWhenPresent("icaoOperatorCode", record::getIcaoOperatorCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(countryCode).toPropertyWhenPresent("countryCode", record::getCountryCode)
                .map(defaultTerminal).toPropertyWhenPresent("defaultTerminal", record::getDefaultTerminal)
                .map(defaultTerminalMvin).toPropertyWhenPresent("defaultTerminalMvin", record::getDefaultTerminalMvin)
                .map(defaultTerminalFlin).toPropertyWhenPresent("defaultTerminalFlin", record::getDefaultTerminalFlin)
                .map(operatorGroups).toPropertyWhenPresent("operatorGroups", record::getOperatorGroups)
                .map(airlineCategory).toPropertyWhenPresent("airlineCategory", record::getAirlineCategory)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAirline>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, iataOperatorCode, icaoOperatorCode, description, localDescription, countryCode, defaultTerminal, defaultTerminalMvin, defaultTerminalFlin, operatorGroups, airlineCategory, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirline);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAirline>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, iataOperatorCode, icaoOperatorCode, description, localDescription, countryCode, defaultTerminal, defaultTerminalMvin, defaultTerminalFlin, operatorGroups, airlineCategory, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirline);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsAirline selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, iataOperatorCode, icaoOperatorCode, description, localDescription, countryCode, defaultTerminal, defaultTerminalMvin, defaultTerminalFlin, operatorGroups, airlineCategory, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirline)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsAirline record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirline)
                .set(iataOperatorCode).equalTo(record::getIataOperatorCode)
                .set(icaoOperatorCode).equalTo(record::getIcaoOperatorCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(countryCode).equalTo(record::getCountryCode)
                .set(defaultTerminal).equalTo(record::getDefaultTerminal)
                .set(defaultTerminalMvin).equalTo(record::getDefaultTerminalMvin)
                .set(defaultTerminalFlin).equalTo(record::getDefaultTerminalFlin)
                .set(operatorGroups).equalTo(record::getOperatorGroups)
                .set(airlineCategory).equalTo(record::getAirlineCategory)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsAirline record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirline)
                .set(iataOperatorCode).equalToWhenPresent(record::getIataOperatorCode)
                .set(icaoOperatorCode).equalToWhenPresent(record::getIcaoOperatorCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(countryCode).equalToWhenPresent(record::getCountryCode)
                .set(defaultTerminal).equalToWhenPresent(record::getDefaultTerminal)
                .set(defaultTerminalMvin).equalToWhenPresent(record::getDefaultTerminalMvin)
                .set(defaultTerminalFlin).equalToWhenPresent(record::getDefaultTerminalFlin)
                .set(operatorGroups).equalToWhenPresent(record::getOperatorGroups)
                .set(airlineCategory).equalToWhenPresent(record::getAirlineCategory)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsAirline record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirline)
                .set(iataOperatorCode).equalTo(record::getIataOperatorCode)
                .set(icaoOperatorCode).equalTo(record::getIcaoOperatorCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(countryCode).equalTo(record::getCountryCode)
                .set(defaultTerminal).equalTo(record::getDefaultTerminal)
                .set(defaultTerminalMvin).equalTo(record::getDefaultTerminalMvin)
                .set(defaultTerminalFlin).equalTo(record::getDefaultTerminalFlin)
                .set(operatorGroups).equalTo(record::getOperatorGroups)
                .set(airlineCategory).equalTo(record::getAirlineCategory)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsAirline record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirline)
                .set(iataOperatorCode).equalToWhenPresent(record::getIataOperatorCode)
                .set(icaoOperatorCode).equalToWhenPresent(record::getIcaoOperatorCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(countryCode).equalToWhenPresent(record::getCountryCode)
                .set(defaultTerminal).equalToWhenPresent(record::getDefaultTerminal)
                .set(defaultTerminalMvin).equalToWhenPresent(record::getDefaultTerminalMvin)
                .set(defaultTerminalFlin).equalToWhenPresent(record::getDefaultTerminalFlin)
                .set(operatorGroups).equalToWhenPresent(record::getOperatorGroups)
                .set(airlineCategory).equalToWhenPresent(record::getAirlineCategory)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    /**
     * delete airline through Airline IATA Operator Code
     *
     * @param currentAirlineCode Airline IATA Operator Code
     * @param inspire            inspire time
     * @param expire             expire time
     */
    default int deleteByAirlineCode(String currentAirlineCode, Long inspire, Long expire) {
        //noinspection deprecation
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirline)
                .where(airlineIataOperatorCode, isEqualTo(currentAirlineCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }
}