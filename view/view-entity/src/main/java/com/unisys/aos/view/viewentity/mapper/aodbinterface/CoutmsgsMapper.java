package com.unisys.aos.view.viewentity.mapper.aodbinterface;

import com.unisys.aos.view.viewentity.entity.aodbinterface.Coutmsgs;
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

import static com.unisys.aos.view.viewentity.mapper.aodbinterface.CoutmsgsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface CoutmsgsMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<Coutmsgs> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("CoutmsgsResult")
    Coutmsgs selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "CoutmsgsResult", value = {
            @Result(column = "COUTMSGS_ID", property = "coutmsgsId", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "COUTMSGS_DATE_INSERTED", property = "coutmsgsDateInserted", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "COUTMSGS_DATE_SENT", property = "coutmsgsDateSent", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "ROUTINGID", property = "routingid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "COUTMSGS_TRUEFALS_GROUP", property = "coutmsgsTruefalsGroup", jdbcType = JdbcType.TINYINT),
            @Result(column = "COUTMSGS_NO_MESSAGES", property = "coutmsgsNoMessages", jdbcType = JdbcType.TINYINT),
            @Result(column = "COUTMSGS_GROUP_ORDER", property = "coutmsgsGroupOrder", jdbcType = JdbcType.TINYINT),
            @Result(column = "COUTMSGS_GROUP_ID", property = "coutmsgsGroupId", jdbcType = JdbcType.SMALLINT),
            @Result(column = "COUTMSGS_FINAL_GROUP_IND", property = "coutmsgsFinalGroupInd", jdbcType = JdbcType.TINYINT),
            @Result(column = "COUTMSGS_ACK_REQD", property = "coutmsgsAckReqd", jdbcType = JdbcType.TINYINT),
            @Result(column = "COUTMSGS_ACK_RESEND_TIMES", property = "coutmsgsAckResendTimes", jdbcType = JdbcType.TINYINT),
            @Result(column = "COUTMSGS_ACK_DATE_RECV", property = "coutmsgsAckDateRecv", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "COUTMSGS_ENCRYPT", property = "coutmsgsEncrypt", jdbcType = JdbcType.TINYINT),
            @Result(column = "COUTMSGS_ERROR", property = "coutmsgsError", jdbcType = JdbcType.TINYINT),
            @Result(column = "COUTMSGS_CLOB_MSG", property = "coutmsgsClobMsg", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<Coutmsgs> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(coutmsgs);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, coutmsgs);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long coutmsgsId_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, coutmsgs)
                .where(coutmsgsId, isEqualTo(coutmsgsId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Coutmsgs record) {
        return insert(SqlBuilder.insert(record)
                .into(coutmsgs)
                .map(coutmsgsId).toProperty("coutmsgsId")
                .map(coutmsgsDateInserted).toProperty("coutmsgsDateInserted")
                .map(coutmsgsDateSent).toProperty("coutmsgsDateSent")
                .map(routingid).toProperty("routingid")
                .map(coutmsgsTruefalsGroup).toProperty("coutmsgsTruefalsGroup")
                .map(coutmsgsNoMessages).toProperty("coutmsgsNoMessages")
                .map(coutmsgsGroupOrder).toProperty("coutmsgsGroupOrder")
                .map(coutmsgsGroupId).toProperty("coutmsgsGroupId")
                .map(coutmsgsFinalGroupInd).toProperty("coutmsgsFinalGroupInd")
                .map(coutmsgsAckReqd).toProperty("coutmsgsAckReqd")
                .map(coutmsgsAckResendTimes).toProperty("coutmsgsAckResendTimes")
                .map(coutmsgsAckDateRecv).toProperty("coutmsgsAckDateRecv")
                .map(coutmsgsEncrypt).toProperty("coutmsgsEncrypt")
                .map(coutmsgsError).toProperty("coutmsgsError")
                .map(coutmsgsClobMsg).toProperty("coutmsgsClobMsg")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Coutmsgs record) {
        return insert(SqlBuilder.insert(record)
                .into(coutmsgs)
                .map(coutmsgsId).toPropertyWhenPresent("coutmsgsId", record::getCoutmsgsId)
                .map(coutmsgsDateInserted).toPropertyWhenPresent("coutmsgsDateInserted", record::getCoutmsgsDateInserted)
                .map(coutmsgsDateSent).toPropertyWhenPresent("coutmsgsDateSent", record::getCoutmsgsDateSent)
                .map(routingid).toPropertyWhenPresent("routingid", record::getRoutingid)
                .map(coutmsgsTruefalsGroup).toPropertyWhenPresent("coutmsgsTruefalsGroup", record::getCoutmsgsTruefalsGroup)
                .map(coutmsgsNoMessages).toPropertyWhenPresent("coutmsgsNoMessages", record::getCoutmsgsNoMessages)
                .map(coutmsgsGroupOrder).toPropertyWhenPresent("coutmsgsGroupOrder", record::getCoutmsgsGroupOrder)
                .map(coutmsgsGroupId).toPropertyWhenPresent("coutmsgsGroupId", record::getCoutmsgsGroupId)
                .map(coutmsgsFinalGroupInd).toPropertyWhenPresent("coutmsgsFinalGroupInd", record::getCoutmsgsFinalGroupInd)
                .map(coutmsgsAckReqd).toPropertyWhenPresent("coutmsgsAckReqd", record::getCoutmsgsAckReqd)
                .map(coutmsgsAckResendTimes).toPropertyWhenPresent("coutmsgsAckResendTimes", record::getCoutmsgsAckResendTimes)
                .map(coutmsgsAckDateRecv).toPropertyWhenPresent("coutmsgsAckDateRecv", record::getCoutmsgsAckDateRecv)
                .map(coutmsgsEncrypt).toPropertyWhenPresent("coutmsgsEncrypt", record::getCoutmsgsEncrypt)
                .map(coutmsgsError).toPropertyWhenPresent("coutmsgsError", record::getCoutmsgsError)
                .map(coutmsgsClobMsg).toPropertyWhenPresent("coutmsgsClobMsg", record::getCoutmsgsClobMsg)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Coutmsgs>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, coutmsgsId, coutmsgsDateInserted, coutmsgsDateSent, routingid, coutmsgsTruefalsGroup, coutmsgsNoMessages, coutmsgsGroupOrder, coutmsgsGroupId, coutmsgsFinalGroupInd, coutmsgsAckReqd, coutmsgsAckResendTimes, coutmsgsAckDateRecv, coutmsgsEncrypt, coutmsgsError, coutmsgsClobMsg)
                .from(coutmsgs);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Coutmsgs>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, coutmsgsId, coutmsgsDateInserted, coutmsgsDateSent, routingid, coutmsgsTruefalsGroup, coutmsgsNoMessages, coutmsgsGroupOrder, coutmsgsGroupId, coutmsgsFinalGroupInd, coutmsgsAckReqd, coutmsgsAckResendTimes, coutmsgsAckDateRecv, coutmsgsEncrypt, coutmsgsError, coutmsgsClobMsg)
                .from(coutmsgs);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Coutmsgs selectByPrimaryKey(Long coutmsgsId_) {
        return SelectDSL.selectWithMapper(this::selectOne, coutmsgsId, coutmsgsDateInserted, coutmsgsDateSent, routingid, coutmsgsTruefalsGroup, coutmsgsNoMessages, coutmsgsGroupOrder, coutmsgsGroupId, coutmsgsFinalGroupInd, coutmsgsAckReqd, coutmsgsAckResendTimes, coutmsgsAckDateRecv, coutmsgsEncrypt, coutmsgsError, coutmsgsClobMsg)
                .from(coutmsgs)
                .where(coutmsgsId, isEqualTo(coutmsgsId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Coutmsgs record) {
        return UpdateDSL.updateWithMapper(this::update, coutmsgs)
                .set(coutmsgsId).equalTo(record::getCoutmsgsId)
                .set(coutmsgsDateInserted).equalTo(record::getCoutmsgsDateInserted)
                .set(coutmsgsDateSent).equalTo(record::getCoutmsgsDateSent)
                .set(routingid).equalTo(record::getRoutingid)
                .set(coutmsgsTruefalsGroup).equalTo(record::getCoutmsgsTruefalsGroup)
                .set(coutmsgsNoMessages).equalTo(record::getCoutmsgsNoMessages)
                .set(coutmsgsGroupOrder).equalTo(record::getCoutmsgsGroupOrder)
                .set(coutmsgsGroupId).equalTo(record::getCoutmsgsGroupId)
                .set(coutmsgsFinalGroupInd).equalTo(record::getCoutmsgsFinalGroupInd)
                .set(coutmsgsAckReqd).equalTo(record::getCoutmsgsAckReqd)
                .set(coutmsgsAckResendTimes).equalTo(record::getCoutmsgsAckResendTimes)
                .set(coutmsgsAckDateRecv).equalTo(record::getCoutmsgsAckDateRecv)
                .set(coutmsgsEncrypt).equalTo(record::getCoutmsgsEncrypt)
                .set(coutmsgsError).equalTo(record::getCoutmsgsError)
                .set(coutmsgsClobMsg).equalTo(record::getCoutmsgsClobMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Coutmsgs record) {
        return UpdateDSL.updateWithMapper(this::update, coutmsgs)
                .set(coutmsgsId).equalToWhenPresent(record::getCoutmsgsId)
                .set(coutmsgsDateInserted).equalToWhenPresent(record::getCoutmsgsDateInserted)
                .set(coutmsgsDateSent).equalToWhenPresent(record::getCoutmsgsDateSent)
                .set(routingid).equalToWhenPresent(record::getRoutingid)
                .set(coutmsgsTruefalsGroup).equalToWhenPresent(record::getCoutmsgsTruefalsGroup)
                .set(coutmsgsNoMessages).equalToWhenPresent(record::getCoutmsgsNoMessages)
                .set(coutmsgsGroupOrder).equalToWhenPresent(record::getCoutmsgsGroupOrder)
                .set(coutmsgsGroupId).equalToWhenPresent(record::getCoutmsgsGroupId)
                .set(coutmsgsFinalGroupInd).equalToWhenPresent(record::getCoutmsgsFinalGroupInd)
                .set(coutmsgsAckReqd).equalToWhenPresent(record::getCoutmsgsAckReqd)
                .set(coutmsgsAckResendTimes).equalToWhenPresent(record::getCoutmsgsAckResendTimes)
                .set(coutmsgsAckDateRecv).equalToWhenPresent(record::getCoutmsgsAckDateRecv)
                .set(coutmsgsEncrypt).equalToWhenPresent(record::getCoutmsgsEncrypt)
                .set(coutmsgsError).equalToWhenPresent(record::getCoutmsgsError)
                .set(coutmsgsClobMsg).equalToWhenPresent(record::getCoutmsgsClobMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Coutmsgs record) {
        return UpdateDSL.updateWithMapper(this::update, coutmsgs)
                .set(coutmsgsDateInserted).equalTo(record::getCoutmsgsDateInserted)
                .set(coutmsgsDateSent).equalTo(record::getCoutmsgsDateSent)
                .set(routingid).equalTo(record::getRoutingid)
                .set(coutmsgsTruefalsGroup).equalTo(record::getCoutmsgsTruefalsGroup)
                .set(coutmsgsNoMessages).equalTo(record::getCoutmsgsNoMessages)
                .set(coutmsgsGroupOrder).equalTo(record::getCoutmsgsGroupOrder)
                .set(coutmsgsGroupId).equalTo(record::getCoutmsgsGroupId)
                .set(coutmsgsFinalGroupInd).equalTo(record::getCoutmsgsFinalGroupInd)
                .set(coutmsgsAckReqd).equalTo(record::getCoutmsgsAckReqd)
                .set(coutmsgsAckResendTimes).equalTo(record::getCoutmsgsAckResendTimes)
                .set(coutmsgsAckDateRecv).equalTo(record::getCoutmsgsAckDateRecv)
                .set(coutmsgsEncrypt).equalTo(record::getCoutmsgsEncrypt)
                .set(coutmsgsError).equalTo(record::getCoutmsgsError)
                .set(coutmsgsClobMsg).equalTo(record::getCoutmsgsClobMsg)
                .where(coutmsgsId, isEqualTo(record::getCoutmsgsId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Coutmsgs record) {
        return UpdateDSL.updateWithMapper(this::update, coutmsgs)
                .set(coutmsgsDateInserted).equalToWhenPresent(record::getCoutmsgsDateInserted)
                .set(coutmsgsDateSent).equalToWhenPresent(record::getCoutmsgsDateSent)
                .set(routingid).equalToWhenPresent(record::getRoutingid)
                .set(coutmsgsTruefalsGroup).equalToWhenPresent(record::getCoutmsgsTruefalsGroup)
                .set(coutmsgsNoMessages).equalToWhenPresent(record::getCoutmsgsNoMessages)
                .set(coutmsgsGroupOrder).equalToWhenPresent(record::getCoutmsgsGroupOrder)
                .set(coutmsgsGroupId).equalToWhenPresent(record::getCoutmsgsGroupId)
                .set(coutmsgsFinalGroupInd).equalToWhenPresent(record::getCoutmsgsFinalGroupInd)
                .set(coutmsgsAckReqd).equalToWhenPresent(record::getCoutmsgsAckReqd)
                .set(coutmsgsAckResendTimes).equalToWhenPresent(record::getCoutmsgsAckResendTimes)
                .set(coutmsgsAckDateRecv).equalToWhenPresent(record::getCoutmsgsAckDateRecv)
                .set(coutmsgsEncrypt).equalToWhenPresent(record::getCoutmsgsEncrypt)
                .set(coutmsgsError).equalToWhenPresent(record::getCoutmsgsError)
                .set(coutmsgsClobMsg).equalToWhenPresent(record::getCoutmsgsClobMsg)
                .where(coutmsgsId, isEqualTo(record::getCoutmsgsId))
                .build()
                .execute();
    }
}