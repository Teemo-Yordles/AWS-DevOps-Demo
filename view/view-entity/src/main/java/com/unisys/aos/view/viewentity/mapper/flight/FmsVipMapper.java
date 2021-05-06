package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsVip;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
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

import static com.unisys.aos.view.viewentity.mapper.flight.FmsVipDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface FmsVipMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsVip> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsVipResult")
    FmsVip selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FmsVipResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "flight_id", property = "flightId", jdbcType = JdbcType.BIGINT),
            @Result(column = "vip_code", property = "vipCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "entourage_size", property = "entourageSize", jdbcType = JdbcType.SMALLINT),
            @Result(column = "service_code", property = "serviceCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "service_quantity", property = "serviceQuantity", jdbcType = JdbcType.BIGINT),
            @Result(column = "service_start_time", property = "serviceStartTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "service_end_time", property = "serviceEndTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<FmsVip> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsVip);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsVip);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsVip)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    /**
     * Delete by flight id
     *
     * @param aodbId - flight AODB ID.
     * @return number of rows affected
     */
    default int deleteByAODBId(Long aodbId) {
        DeleteStatementProvider deleteStatement = deleteFrom(fmsVip)
                .where(flightId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsVip record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsVip)
                .map(flightId).toProperty("flightId")
                .map(vipCode).toProperty("vipCode")
                .map(entourageSize).toProperty("entourageSize")
                .map(serviceCode).toProperty("serviceCode")
                .map(serviceQuantity).toProperty("serviceQuantity")
                .map(serviceStartTime).toProperty("serviceStartTime")
                .map(serviceEndTime).toProperty("serviceEndTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsVip record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsVip)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(vipCode).toPropertyWhenPresent("vipCode", record::getVipCode)
                .map(entourageSize).toPropertyWhenPresent("entourageSize", record::getEntourageSize)
                .map(serviceCode).toPropertyWhenPresent("serviceCode", record::getServiceCode)
                .map(serviceQuantity).toPropertyWhenPresent("serviceQuantity", record::getServiceQuantity)
                .map(serviceStartTime).toPropertyWhenPresent("serviceStartTime", record::getServiceStartTime)
                .map(serviceEndTime).toPropertyWhenPresent("serviceEndTime", record::getServiceEndTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsVip>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, vipCode, entourageSize, serviceCode, serviceQuantity, serviceStartTime, serviceEndTime, createTime, updateTime)
                .from(fmsVip);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsVip>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, vipCode, entourageSize, serviceCode, serviceQuantity, serviceStartTime, serviceEndTime, createTime, updateTime)
                .from(fmsVip);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsVip selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, vipCode, entourageSize, serviceCode, serviceQuantity, serviceStartTime, serviceEndTime, createTime, updateTime)
                .from(fmsVip)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsVip> selectByAodbId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, vipCode, entourageSize, serviceCode, serviceQuantity, serviceStartTime, serviceEndTime, createTime, updateTime)
                .from(fmsVip)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsVip record) {
        return UpdateDSL.updateWithMapper(this::update, fmsVip)
                .set(flightId).equalTo(record::getFlightId)
                .set(vipCode).equalTo(record::getVipCode)
                .set(entourageSize).equalTo(record::getEntourageSize)
                .set(serviceCode).equalTo(record::getServiceCode)
                .set(serviceQuantity).equalTo(record::getServiceQuantity)
                .set(serviceStartTime).equalTo(record::getServiceStartTime)
                .set(serviceEndTime).equalTo(record::getServiceEndTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsVip record) {
        return UpdateDSL.updateWithMapper(this::update, fmsVip)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(vipCode).equalToWhenPresent(record::getVipCode)
                .set(entourageSize).equalToWhenPresent(record::getEntourageSize)
                .set(serviceCode).equalToWhenPresent(record::getServiceCode)
                .set(serviceQuantity).equalToWhenPresent(record::getServiceQuantity)
                .set(serviceStartTime).equalToWhenPresent(record::getServiceStartTime)
                .set(serviceEndTime).equalToWhenPresent(record::getServiceEndTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsVip record) {
        return UpdateDSL.updateWithMapper(this::update, fmsVip)
                .set(flightId).equalTo(record::getFlightId)
                .set(vipCode).equalTo(record::getVipCode)
                .set(entourageSize).equalTo(record::getEntourageSize)
                .set(serviceCode).equalTo(record::getServiceCode)
                .set(serviceQuantity).equalTo(record::getServiceQuantity)
                .set(serviceStartTime).equalTo(record::getServiceStartTime)
                .set(serviceEndTime).equalTo(record::getServiceEndTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsVip record) {
        return UpdateDSL.updateWithMapper(this::update, fmsVip)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(vipCode).equalToWhenPresent(record::getVipCode)
                .set(entourageSize).equalToWhenPresent(record::getEntourageSize)
                .set(serviceCode).equalToWhenPresent(record::getServiceCode)
                .set(serviceQuantity).equalToWhenPresent(record::getServiceQuantity)
                .set(serviceStartTime).equalToWhenPresent(record::getServiceStartTime)
                .set(serviceEndTime).equalToWhenPresent(record::getServiceEndTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}