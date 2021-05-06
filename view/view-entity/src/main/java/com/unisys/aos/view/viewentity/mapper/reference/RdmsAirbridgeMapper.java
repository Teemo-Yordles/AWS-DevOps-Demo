package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsAirbridge;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsAirbridgeDynamicSqlSupport.*;
import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionDynamicSqlSupport.expireTime;
import static com.unisys.aos.view.viewentity.mapper.reference.RdmsRegionDynamicSqlSupport.inspireTime;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
@Repository
public interface RdmsAirbridgeMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsAirbridge> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsAirbridgeResult")
    RdmsAirbridge selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsAirbridgeResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "airbridge_code", property = "airbridgeCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "max_height", property = "maxHeight", jdbcType = JdbcType.DECIMAL),
            @Result(column = "terminal_code", property = "terminalCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "support_a380", property = "supportA380", jdbcType = JdbcType.BIT),
            @Result(column = "associated_stands", property = "associatedStands", jdbcType = JdbcType.VARCHAR),
            @Result(column = "associated_gates", property = "associatedGates", jdbcType = JdbcType.VARCHAR),
            @Result(column = "arm_number", property = "armNumber", jdbcType = JdbcType.TINYINT),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsAirbridge> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsAirbridge);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirbridge);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsAirbridge)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsAirbridge record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAirbridge)
                .map(airbridgeCode).toProperty("airbridgeCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(maxHeight).toProperty("maxHeight")
                .map(terminalCode).toProperty("terminalCode")
                .map(supportA380).toProperty("supportA380")
                .map(associatedStands).toProperty("associatedStands")
                .map(associatedGates).toProperty("associatedGates")
                .map(armNumber).toProperty("armNumber")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsAirbridge record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsAirbridge)
                .map(airbridgeCode).toPropertyWhenPresent("airbridgeCode", record::getAirbridgeCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(maxHeight).toPropertyWhenPresent("maxHeight", record::getMaxHeight)
                .map(terminalCode).toPropertyWhenPresent("terminalCode", record::getTerminalCode)
                .map(supportA380).toPropertyWhenPresent("supportA380", record::getSupportA380)
                .map(associatedStands).toPropertyWhenPresent("associatedStands", record::getAssociatedStands)
                .map(associatedGates).toPropertyWhenPresent("associatedGates", record::getAssociatedGates)
                .map(armNumber).toPropertyWhenPresent("armNumber", record::getArmNumber)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAirbridge>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, airbridgeCode, description, localDescription, maxHeight, terminalCode, supportA380, associatedStands, associatedGates, armNumber, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirbridge);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsAirbridge>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, airbridgeCode, description, localDescription, maxHeight, terminalCode, supportA380, associatedStands, associatedGates, armNumber, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirbridge);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsAirbridge selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, airbridgeCode, description, localDescription, maxHeight, terminalCode, supportA380, associatedStands, associatedGates, armNumber, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirbridge)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsAirbridge record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirbridge)
                .set(airbridgeCode).equalTo(record::getAirbridgeCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(maxHeight).equalTo(record::getMaxHeight)
                .set(terminalCode).equalTo(record::getTerminalCode)
                .set(supportA380).equalTo(record::getSupportA380)
                .set(associatedStands).equalTo(record::getAssociatedStands)
                .set(associatedGates).equalTo(record::getAssociatedGates)
                .set(armNumber).equalTo(record::getArmNumber)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsAirbridge record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirbridge)
                .set(airbridgeCode).equalToWhenPresent(record::getAirbridgeCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(maxHeight).equalToWhenPresent(record::getMaxHeight)
                .set(terminalCode).equalToWhenPresent(record::getTerminalCode)
                .set(supportA380).equalToWhenPresent(record::getSupportA380)
                .set(associatedStands).equalToWhenPresent(record::getAssociatedStands)
                .set(associatedGates).equalToWhenPresent(record::getAssociatedGates)
                .set(armNumber).equalToWhenPresent(record::getArmNumber)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsAirbridge record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirbridge)
                .set(airbridgeCode).equalTo(record::getAirbridgeCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(maxHeight).equalTo(record::getMaxHeight)
                .set(terminalCode).equalTo(record::getTerminalCode)
                .set(supportA380).equalTo(record::getSupportA380)
                .set(associatedStands).equalTo(record::getAssociatedStands)
                .set(associatedGates).equalTo(record::getAssociatedGates)
                .set(armNumber).equalTo(record::getArmNumber)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsAirbridge record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsAirbridge)
                .set(airbridgeCode).equalToWhenPresent(record::getAirbridgeCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(maxHeight).equalToWhenPresent(record::getMaxHeight)
                .set(terminalCode).equalToWhenPresent(record::getTerminalCode)
                .set(supportA380).equalToWhenPresent(record::getSupportA380)
                .set(associatedStands).equalToWhenPresent(record::getAssociatedStands)
                .set(associatedGates).equalToWhenPresent(record::getAssociatedGates)
                .set(armNumber).equalToWhenPresent(record::getArmNumber)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    /**
     * get map entries based on airbridge code
     *
     * @param currentAirbridgeCode airbridge code
     * @param inspire              inspire time
     * @param expire               expire time
     * @return map entries
     */
    default List<RdmsAirbridge> selectByAirbridgeCode(String currentAirbridgeCode, Long inspire, Long expire) {
        //noinspection deprecation
        return SelectDSL.selectWithMapper(this::selectMany, id, airbridgeCode, description, localDescription, maxHeight, terminalCode, supportA380, associatedStands, associatedGates, armNumber, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsAirbridge)
                .where(airbridgeCode, isEqualTo(currentAirbridgeCode))
                .and(inspireTime, isLessThanOrEqualTo(inspire))
                .and(expireTime, isGreaterThanOrEqualTo(expire))
                .build()
                .execute();
    }
}