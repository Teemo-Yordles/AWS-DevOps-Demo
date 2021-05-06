package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsStand;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsStandDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
@Repository
public interface RdmsStandMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsStand> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsStandResult")
    RdmsStand selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsStandResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "stand_code", property = "standCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "allocate_big_aircraft", property = "allocateBigAircraft", jdbcType = JdbcType.CHAR),
            @Result(column = "stand_group_code", property = "standGroupCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "width", property = "width", jdbcType = JdbcType.DECIMAL),
            @Result(column = "length", property = "length", jdbcType = JdbcType.DECIMAL),
            @Result(column = "terminal_code", property = "terminalCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "attache_max_airbridge", property = "attacheMaxAirbridge", jdbcType = JdbcType.TINYINT),
            @Result(column = "international_gate", property = "internationalGate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "domestic_gate", property = "domesticGate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "stand_type", property = "standType", jdbcType = JdbcType.CHAR),
            @Result(column = "fixed_electric", property = "fixedElectric", jdbcType = JdbcType.BIT),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsStand> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsStand);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsStand);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsStand)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsStand record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsStand)
                .map(standCode).toProperty("standCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(allocateBigAircraft).toProperty("allocateBigAircraft")
                .map(standGroupCode).toProperty("standGroupCode")
                .map(width).toProperty("width")
                .map(length).toProperty("length")
                .map(terminalCode).toProperty("terminalCode")
                .map(attacheMaxAirbridge).toProperty("attacheMaxAirbridge")
                .map(internationalGate).toProperty("internationalGate")
                .map(domesticGate).toProperty("domesticGate")
                .map(standType).toProperty("standType")
                .map(fixedElectric).toProperty("fixedElectric")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsStand record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsStand)
                .map(standCode).toPropertyWhenPresent("standCode", record::getStandCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(allocateBigAircraft).toPropertyWhenPresent("allocateBigAircraft", record::getAllocateBigAircraft)
                .map(standGroupCode).toPropertyWhenPresent("standGroupCode", record::getStandGroupCode)
                .map(width).toPropertyWhenPresent("width", record::getWidth)
                .map(length).toPropertyWhenPresent("length", record::getLength)
                .map(terminalCode).toPropertyWhenPresent("terminalCode", record::getTerminalCode)
                .map(attacheMaxAirbridge).toPropertyWhenPresent("attacheMaxAirbridge", record::getAttacheMaxAirbridge)
                .map(internationalGate).toPropertyWhenPresent("internationalGate", record::getInternationalGate)
                .map(domesticGate).toPropertyWhenPresent("domesticGate", record::getDomesticGate)
                .map(standType).toPropertyWhenPresent("standType", record::getStandType)
                .map(fixedElectric).toPropertyWhenPresent("fixedElectric", record::getFixedElectric)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsStand>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, standCode, description, localDescription, allocateBigAircraft, standGroupCode, width, length, terminalCode, attacheMaxAirbridge, internationalGate, domesticGate, standType, fixedElectric, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsStand);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsStand>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, standCode, description, localDescription, allocateBigAircraft, standGroupCode, width, length, terminalCode, attacheMaxAirbridge, internationalGate, domesticGate, standType, fixedElectric, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsStand);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsStand selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, standCode, description, localDescription, allocateBigAircraft, standGroupCode, width, length, terminalCode, attacheMaxAirbridge, internationalGate, domesticGate, standType, fixedElectric, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsStand)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsStand record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsStand)
                .set(standCode).equalTo(record::getStandCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(allocateBigAircraft).equalTo(record::getAllocateBigAircraft)
                .set(standGroupCode).equalTo(record::getStandGroupCode)
                .set(width).equalTo(record::getWidth)
                .set(length).equalTo(record::getLength)
                .set(terminalCode).equalTo(record::getTerminalCode)
                .set(attacheMaxAirbridge).equalTo(record::getAttacheMaxAirbridge)
                .set(internationalGate).equalTo(record::getInternationalGate)
                .set(domesticGate).equalTo(record::getDomesticGate)
                .set(standType).equalTo(record::getStandType)
                .set(fixedElectric).equalTo(record::getFixedElectric)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsStand record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsStand)
                .set(standCode).equalToWhenPresent(record::getStandCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(allocateBigAircraft).equalToWhenPresent(record::getAllocateBigAircraft)
                .set(standGroupCode).equalToWhenPresent(record::getStandGroupCode)
                .set(width).equalToWhenPresent(record::getWidth)
                .set(length).equalToWhenPresent(record::getLength)
                .set(terminalCode).equalToWhenPresent(record::getTerminalCode)
                .set(attacheMaxAirbridge).equalToWhenPresent(record::getAttacheMaxAirbridge)
                .set(internationalGate).equalToWhenPresent(record::getInternationalGate)
                .set(domesticGate).equalToWhenPresent(record::getDomesticGate)
                .set(standType).equalToWhenPresent(record::getStandType)
                .set(fixedElectric).equalToWhenPresent(record::getFixedElectric)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsStand record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsStand)
                .set(standCode).equalTo(record::getStandCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(allocateBigAircraft).equalTo(record::getAllocateBigAircraft)
                .set(standGroupCode).equalTo(record::getStandGroupCode)
                .set(width).equalTo(record::getWidth)
                .set(length).equalTo(record::getLength)
                .set(terminalCode).equalTo(record::getTerminalCode)
                .set(attacheMaxAirbridge).equalTo(record::getAttacheMaxAirbridge)
                .set(internationalGate).equalTo(record::getInternationalGate)
                .set(domesticGate).equalTo(record::getDomesticGate)
                .set(standType).equalTo(record::getStandType)
                .set(fixedElectric).equalTo(record::getFixedElectric)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsStand record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsStand)
                .set(standCode).equalToWhenPresent(record::getStandCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(allocateBigAircraft).equalToWhenPresent(record::getAllocateBigAircraft)
                .set(standGroupCode).equalToWhenPresent(record::getStandGroupCode)
                .set(width).equalToWhenPresent(record::getWidth)
                .set(length).equalToWhenPresent(record::getLength)
                .set(terminalCode).equalToWhenPresent(record::getTerminalCode)
                .set(attacheMaxAirbridge).equalToWhenPresent(record::getAttacheMaxAirbridge)
                .set(internationalGate).equalToWhenPresent(record::getInternationalGate)
                .set(domesticGate).equalToWhenPresent(record::getDomesticGate)
                .set(standType).equalToWhenPresent(record::getStandType)
                .set(fixedElectric).equalToWhenPresent(record::getFixedElectric)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    /***
     * get map entries based on stand code
     * @param currentStandCode stand code
     * @param inspire inspire time
     * @param expire expire time
     * @return map entries
     */
    @SuppressWarnings("deprecation")
    default List<RdmsStand> selectByStandCode(String currentStandCode, Long inspire, Long expire) {
        return SelectDSL.selectWithMapper(this::selectMany, id, standCode, description, localDescription, allocateBigAircraft, standGroupCode, width, length, terminalCode, attacheMaxAirbridge, internationalGate, domesticGate, standType, fixedElectric, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsStand)
                .where(standCode, isEqualTo(currentStandCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /**
     * get map entries based on international gate code
     *
     * @param internationalGateCode international gate code
     * @param inspire               inspire time
     * @param expire                expire time
     * @return map entries
     */
    @SuppressWarnings("deprecation")
    default List<RdmsStand> selectByInternationalGate(String internationalGateCode, Long inspire, Long expire) {
        return SelectDSL.selectWithMapper(this::selectMany, id, standCode, description, localDescription, allocateBigAircraft, standGroupCode, width, length, terminalCode, attacheMaxAirbridge, internationalGate, domesticGate, standType, fixedElectric, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsStand)
                .where(internationalGate, isEqualTo(internationalGateCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /**
     * get map entries based on domestic gate code
     *
     * @param domesticGateCode domestic gate code
     * @param inspire          inspire time
     * @param expire           expire time
     * @return map entries
     */
    @SuppressWarnings("deprecation")
    default List<RdmsStand> selectByDomesticGate(String domesticGateCode, Long inspire, Long expire) {
        return SelectDSL.selectWithMapper(this::selectMany, id, standCode, description, localDescription, allocateBigAircraft, standGroupCode, width, length, terminalCode, attacheMaxAirbridge, internationalGate, domesticGate, standType, fixedElectric, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsStand)
                .where(domesticGate, isEqualTo(domesticGateCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }
}