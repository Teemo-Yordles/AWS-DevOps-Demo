package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsGate;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsGateDynamicSqlSupport.*;
import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionDynamicSqlSupport.expireTime;
import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionDynamicSqlSupport.inspireTime;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
@Repository
public interface RdmsGateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsGate> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsGateResult")
    RdmsGate selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsGateResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "gate_code", property = "gateCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gate_category", property = "gateCategory", jdbcType = JdbcType.CHAR),
            @Result(column = "terminal_code", property = "terminalCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "pier_code", property = "pierCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsGate> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsGate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsGate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsGate)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsGate record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsGate)
                .map(gateCode).toProperty("gateCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(gateCategory).toProperty("gateCategory")
                .map(terminalCode).toProperty("terminalCode")
                .map(pierCode).toProperty("pierCode")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsGate record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsGate)
                .map(gateCode).toPropertyWhenPresent("gateCode", record::getGateCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(gateCategory).toPropertyWhenPresent("gateCategory", record::getGateCategory)
                .map(terminalCode).toPropertyWhenPresent("terminalCode", record::getTerminalCode)
                .map(pierCode).toPropertyWhenPresent("pierCode", record::getPierCode)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsGate>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, gateCode, description, localDescription, gateCategory, terminalCode, pierCode, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsGate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsGate>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, gateCode, description, localDescription, gateCategory, terminalCode, pierCode, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsGate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsGate selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, gateCode, description, localDescription, gateCategory, terminalCode, pierCode, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsGate)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    /***
     * Get RdmsGate entity by gate code.
     *
     * @param code gate code
     * @param inspire inspire time
     * @param expire expire time
     * @return RdmsGate entries
     */
    default List<RdmsGate> selectByGateCode(String code, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, gateCode, description, localDescription, gateCategory, terminalCode, pierCode, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsGate)
                .where(gateCode, isEqualTo(code))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /***
     * Get RdmsGate entity by gate code.
     *
     * @param code gate code
     * @return RdmsGate entries
     */
    default List<RdmsGate> selectByGateCode(String code) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, gateCode, description, localDescription, gateCategory, terminalCode, pierCode, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsGate)
                .where(gateCode, isEqualTo(code))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsGate record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsGate)
                .set(gateCode).equalTo(record::getGateCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(gateCategory).equalTo(record::getGateCategory)
                .set(terminalCode).equalTo(record::getTerminalCode)
                .set(pierCode).equalTo(record::getPierCode)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsGate record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsGate)
                .set(gateCode).equalToWhenPresent(record::getGateCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(gateCategory).equalToWhenPresent(record::getGateCategory)
                .set(terminalCode).equalToWhenPresent(record::getTerminalCode)
                .set(pierCode).equalToWhenPresent(record::getPierCode)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsGate record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsGate)
                .set(gateCode).equalTo(record::getGateCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(gateCategory).equalTo(record::getGateCategory)
                .set(terminalCode).equalTo(record::getTerminalCode)
                .set(pierCode).equalTo(record::getPierCode)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsGate record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsGate)
                .set(gateCode).equalToWhenPresent(record::getGateCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(gateCategory).equalToWhenPresent(record::getGateCategory)
                .set(terminalCode).equalToWhenPresent(record::getTerminalCode)
                .set(pierCode).equalToWhenPresent(record::getPierCode)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    /**
     * get map entries based on terminal code
     *
     * @param currentTerminalCode terminal code
     * @param inspire             inspire time
     * @param expire              expire time
     * @return map entries
     */
    default List<RdmsGate> selectByTerminalCode(String currentTerminalCode, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, gateCode, description, localDescription, gateCategory, terminalCode, pierCode, inspireTime, expireTime, createTime, updateTime).from(rdmsGate)
                .where(terminalCode, isEqualTo(currentTerminalCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }

    /**
     * get map entries based on pier code
     *
     * @param currentPierCode pier code
     * @param inspire         inspire time
     * @param expire          expire time
     * @return map entries
     */
    default List<RdmsGate> selectByPierCode(String currentPierCode, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, gateCode, description, localDescription, gateCategory, terminalCode, pierCode, inspireTime, expireTime, createTime, updateTime).from(rdmsGate)
                .where(pierCode, isEqualTo(currentPierCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }
}