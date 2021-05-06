package com.unisys.aos.view.viewentity.mapper.aodbinterface;

import static com.unisys.aos.view.viewentity.mapper.aodbinterface.CminmsgsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.unisys.aos.view.viewentity.entity.aodbinterface.Cminmsgs;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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

@Mapper
@Repository
public interface CminmsgsMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Cminmsgs> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CminmsgsResult")
    Cminmsgs selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CminmsgsResult", value = {
        @Result(column="CMINMSGS_ID", property="cminmsgsId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="CMINMSGS_DATE_RECEIVED", property="cminmsgsDateReceived", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CMINMSGS_DATE_PROCESSED", property="cminmsgsDateProcessed", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CMINMSGS_SUBSYSTEM_NAME", property="cminmsgsSubsystemName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CMINMSGS_SUBSYSTEM_SEQUENCE", property="cminmsgsSubsystemSequence", jdbcType=JdbcType.INTEGER),
        @Result(column="CMINMSGS_SUBSYSTEM_DATE_SENT", property="cminmsgsSubsystemDateSent", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CMINMSGS_SUBSYSTEM_SUBTYPE", property="cminmsgsSubsystemSubtype", jdbcType=JdbcType.VARCHAR),
        @Result(column="CMINMSGS_SUBSYSTEM_TYPE", property="cminmsgsSubsystemType", jdbcType=JdbcType.VARCHAR),
        @Result(column="CMINMSGS_STATUS", property="cminmsgsStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="CMINMSGS_CLOB_MSG", property="cminmsgsClobMsg", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Cminmsgs> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(cminmsgs);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, cminmsgs);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long cminmsgsId_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, cminmsgs)
                .where(cminmsgsId, isEqualTo(cminmsgsId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Cminmsgs record) {
        return insert(SqlBuilder.insert(record)
                .into(cminmsgs)
                .map(cminmsgsId).toProperty("cminmsgsId")
                .map(cminmsgsDateReceived).toProperty("cminmsgsDateReceived")
                .map(cminmsgsDateProcessed).toProperty("cminmsgsDateProcessed")
                .map(cminmsgsSubsystemName).toProperty("cminmsgsSubsystemName")
                .map(cminmsgsSubsystemSequence).toProperty("cminmsgsSubsystemSequence")
                .map(cminmsgsSubsystemDateSent).toProperty("cminmsgsSubsystemDateSent")
                .map(cminmsgsSubsystemSubtype).toProperty("cminmsgsSubsystemSubtype")
                .map(cminmsgsSubsystemType).toProperty("cminmsgsSubsystemType")
                .map(cminmsgsStatus).toProperty("cminmsgsStatus")
                .map(cminmsgsClobMsg).toProperty("cminmsgsClobMsg")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Cminmsgs record) {
        return insert(SqlBuilder.insert(record)
                .into(cminmsgs)
                .map(cminmsgsId).toPropertyWhenPresent("cminmsgsId", record::getCminmsgsId)
                .map(cminmsgsDateReceived).toPropertyWhenPresent("cminmsgsDateReceived", record::getCminmsgsDateReceived)
                .map(cminmsgsDateProcessed).toPropertyWhenPresent("cminmsgsDateProcessed", record::getCminmsgsDateProcessed)
                .map(cminmsgsSubsystemName).toPropertyWhenPresent("cminmsgsSubsystemName", record::getCminmsgsSubsystemName)
                .map(cminmsgsSubsystemSequence).toPropertyWhenPresent("cminmsgsSubsystemSequence", record::getCminmsgsSubsystemSequence)
                .map(cminmsgsSubsystemDateSent).toPropertyWhenPresent("cminmsgsSubsystemDateSent", record::getCminmsgsSubsystemDateSent)
                .map(cminmsgsSubsystemSubtype).toPropertyWhenPresent("cminmsgsSubsystemSubtype", record::getCminmsgsSubsystemSubtype)
                .map(cminmsgsSubsystemType).toPropertyWhenPresent("cminmsgsSubsystemType", record::getCminmsgsSubsystemType)
                .map(cminmsgsStatus).toPropertyWhenPresent("cminmsgsStatus", record::getCminmsgsStatus)
                .map(cminmsgsClobMsg).toPropertyWhenPresent("cminmsgsClobMsg", record::getCminmsgsClobMsg)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Cminmsgs>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, cminmsgsId, cminmsgsDateReceived, cminmsgsDateProcessed, cminmsgsSubsystemName, cminmsgsSubsystemSequence, cminmsgsSubsystemDateSent, cminmsgsSubsystemSubtype, cminmsgsSubsystemType, cminmsgsStatus, cminmsgsClobMsg)
                .from(cminmsgs);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Cminmsgs>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, cminmsgsId, cminmsgsDateReceived, cminmsgsDateProcessed, cminmsgsSubsystemName, cminmsgsSubsystemSequence, cminmsgsSubsystemDateSent, cminmsgsSubsystemSubtype, cminmsgsSubsystemType, cminmsgsStatus, cminmsgsClobMsg)
                .from(cminmsgs);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Cminmsgs selectByPrimaryKey(Long cminmsgsId_) {
        return SelectDSL.selectWithMapper(this::selectOne, cminmsgsId, cminmsgsDateReceived, cminmsgsDateProcessed, cminmsgsSubsystemName, cminmsgsSubsystemSequence, cminmsgsSubsystemDateSent, cminmsgsSubsystemSubtype, cminmsgsSubsystemType, cminmsgsStatus, cminmsgsClobMsg)
                .from(cminmsgs)
                .where(cminmsgsId, isEqualTo(cminmsgsId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Cminmsgs record) {
        return UpdateDSL.updateWithMapper(this::update, cminmsgs)
                .set(cminmsgsId).equalTo(record::getCminmsgsId)
                .set(cminmsgsDateReceived).equalTo(record::getCminmsgsDateReceived)
                .set(cminmsgsDateProcessed).equalTo(record::getCminmsgsDateProcessed)
                .set(cminmsgsSubsystemName).equalTo(record::getCminmsgsSubsystemName)
                .set(cminmsgsSubsystemSequence).equalTo(record::getCminmsgsSubsystemSequence)
                .set(cminmsgsSubsystemDateSent).equalTo(record::getCminmsgsSubsystemDateSent)
                .set(cminmsgsSubsystemSubtype).equalTo(record::getCminmsgsSubsystemSubtype)
                .set(cminmsgsSubsystemType).equalTo(record::getCminmsgsSubsystemType)
                .set(cminmsgsStatus).equalTo(record::getCminmsgsStatus)
                .set(cminmsgsClobMsg).equalTo(record::getCminmsgsClobMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Cminmsgs record) {
        return UpdateDSL.updateWithMapper(this::update, cminmsgs)
                .set(cminmsgsId).equalToWhenPresent(record::getCminmsgsId)
                .set(cminmsgsDateReceived).equalToWhenPresent(record::getCminmsgsDateReceived)
                .set(cminmsgsDateProcessed).equalToWhenPresent(record::getCminmsgsDateProcessed)
                .set(cminmsgsSubsystemName).equalToWhenPresent(record::getCminmsgsSubsystemName)
                .set(cminmsgsSubsystemSequence).equalToWhenPresent(record::getCminmsgsSubsystemSequence)
                .set(cminmsgsSubsystemDateSent).equalToWhenPresent(record::getCminmsgsSubsystemDateSent)
                .set(cminmsgsSubsystemSubtype).equalToWhenPresent(record::getCminmsgsSubsystemSubtype)
                .set(cminmsgsSubsystemType).equalToWhenPresent(record::getCminmsgsSubsystemType)
                .set(cminmsgsStatus).equalToWhenPresent(record::getCminmsgsStatus)
                .set(cminmsgsClobMsg).equalToWhenPresent(record::getCminmsgsClobMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Cminmsgs record) {
        return UpdateDSL.updateWithMapper(this::update, cminmsgs)
                .set(cminmsgsDateReceived).equalTo(record::getCminmsgsDateReceived)
                .set(cminmsgsDateProcessed).equalTo(record::getCminmsgsDateProcessed)
                .set(cminmsgsSubsystemName).equalTo(record::getCminmsgsSubsystemName)
                .set(cminmsgsSubsystemSequence).equalTo(record::getCminmsgsSubsystemSequence)
                .set(cminmsgsSubsystemDateSent).equalTo(record::getCminmsgsSubsystemDateSent)
                .set(cminmsgsSubsystemSubtype).equalTo(record::getCminmsgsSubsystemSubtype)
                .set(cminmsgsSubsystemType).equalTo(record::getCminmsgsSubsystemType)
                .set(cminmsgsStatus).equalTo(record::getCminmsgsStatus)
                .set(cminmsgsClobMsg).equalTo(record::getCminmsgsClobMsg)
                .where(cminmsgsId, isEqualTo(record::getCminmsgsId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Cminmsgs record) {
        return UpdateDSL.updateWithMapper(this::update, cminmsgs)
                .set(cminmsgsDateReceived).equalToWhenPresent(record::getCminmsgsDateReceived)
                .set(cminmsgsDateProcessed).equalToWhenPresent(record::getCminmsgsDateProcessed)
                .set(cminmsgsSubsystemName).equalToWhenPresent(record::getCminmsgsSubsystemName)
                .set(cminmsgsSubsystemSequence).equalToWhenPresent(record::getCminmsgsSubsystemSequence)
                .set(cminmsgsSubsystemDateSent).equalToWhenPresent(record::getCminmsgsSubsystemDateSent)
                .set(cminmsgsSubsystemSubtype).equalToWhenPresent(record::getCminmsgsSubsystemSubtype)
                .set(cminmsgsSubsystemType).equalToWhenPresent(record::getCminmsgsSubsystemType)
                .set(cminmsgsStatus).equalToWhenPresent(record::getCminmsgsStatus)
                .set(cminmsgsClobMsg).equalToWhenPresent(record::getCminmsgsClobMsg)
                .where(cminmsgsId, isEqualTo(record::getCminmsgsId))
                .build()
                .execute();
    }
}