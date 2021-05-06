package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsService;
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

import static com.unisys.aos.view.viewentity.mapper.flight.FmsServiceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Repository
@Mapper
public interface FmsServiceMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsService> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsServiceResult")
    FmsService selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FmsServiceResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "flight_id", property = "flightId", jdbcType = JdbcType.BIGINT),
            @Result(column = "service_code", property = "serviceCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "service_quantity", property = "serviceQuantity", jdbcType = JdbcType.TINYINT),
            @Result(column = "start_time", property = "startTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "end_time", property = "endTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "service_provider", property = "serviceProvider", jdbcType = JdbcType.INTEGER),
            @Result(column = "abnormal_reason", property = "abnormalReason", jdbcType = JdbcType.VARCHAR),
            @Result(column = "abnormal_remark", property = "abnormalRemark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<FmsService> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsService);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsService);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsService)
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
        DeleteStatementProvider deleteStatement = deleteFrom(fmsService)
                .where(flightId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsService record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsService)
                .map(flightId).toProperty("flightId")
                .map(serviceCode).toProperty("serviceCode")
                .map(serviceQuantity).toProperty("serviceQuantity")
                .map(startTime).toProperty("startTime")
                .map(endTime).toProperty("endTime")
                .map(serviceProvider).toProperty("serviceProvider")
                .map(abnormalReason).toProperty("abnormalReason")
                .map(abnormalRemark).toProperty("abnormalRemark")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsService record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsService)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(serviceCode).toPropertyWhenPresent("serviceCode", record::getServiceCode)
                .map(serviceQuantity).toPropertyWhenPresent("serviceQuantity", record::getServiceQuantity)
                .map(startTime).toPropertyWhenPresent("startTime", record::getStartTime)
                .map(endTime).toPropertyWhenPresent("endTime", record::getEndTime)
                .map(serviceProvider).toPropertyWhenPresent("serviceProvider", record::getServiceProvider)
                .map(abnormalReason).toPropertyWhenPresent("abnormalReason", record::getAbnormalReason)
                .map(abnormalRemark).toPropertyWhenPresent("abnormalRemark", record::getAbnormalRemark)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsService>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, serviceCode, serviceQuantity, startTime, endTime, serviceProvider, abnormalReason, abnormalRemark, createTime, updateTime)
                .from(fmsService);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsService>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, serviceCode, serviceQuantity, startTime, endTime, serviceProvider, abnormalReason, abnormalRemark, createTime, updateTime)
                .from(fmsService);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsService selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, serviceCode, serviceQuantity, startTime, endTime, serviceProvider, abnormalReason, abnormalRemark, createTime, updateTime)
                .from(fmsService)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsService> selectByAodbId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, serviceCode, serviceQuantity, startTime, endTime, serviceProvider, abnormalReason, abnormalRemark, createTime, updateTime)
                .from(fmsService)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsService record) {
        return UpdateDSL.updateWithMapper(this::update, fmsService)
                .set(flightId).equalTo(record::getFlightId)
                .set(serviceCode).equalTo(record::getServiceCode)
                .set(serviceQuantity).equalTo(record::getServiceQuantity)
                .set(startTime).equalTo(record::getStartTime)
                .set(endTime).equalTo(record::getEndTime)
                .set(serviceProvider).equalTo(record::getServiceProvider)
                .set(abnormalReason).equalTo(record::getAbnormalReason)
                .set(abnormalRemark).equalTo(record::getAbnormalRemark)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsService record) {
        return UpdateDSL.updateWithMapper(this::update, fmsService)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(serviceCode).equalToWhenPresent(record::getServiceCode)
                .set(serviceQuantity).equalToWhenPresent(record::getServiceQuantity)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(endTime).equalToWhenPresent(record::getEndTime)
                .set(serviceProvider).equalToWhenPresent(record::getServiceProvider)
                .set(abnormalReason).equalToWhenPresent(record::getAbnormalReason)
                .set(abnormalRemark).equalToWhenPresent(record::getAbnormalRemark)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsService record) {
        return UpdateDSL.updateWithMapper(this::update, fmsService)
                .set(flightId).equalTo(record::getFlightId)
                .set(serviceCode).equalTo(record::getServiceCode)
                .set(serviceQuantity).equalTo(record::getServiceQuantity)
                .set(startTime).equalTo(record::getStartTime)
                .set(endTime).equalTo(record::getEndTime)
                .set(serviceProvider).equalTo(record::getServiceProvider)
                .set(abnormalReason).equalTo(record::getAbnormalReason)
                .set(abnormalRemark).equalTo(record::getAbnormalRemark)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsService record) {
        return UpdateDSL.updateWithMapper(this::update, fmsService)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(serviceCode).equalToWhenPresent(record::getServiceCode)
                .set(serviceQuantity).equalToWhenPresent(record::getServiceQuantity)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(endTime).equalToWhenPresent(record::getEndTime)
                .set(serviceProvider).equalToWhenPresent(record::getServiceProvider)
                .set(abnormalReason).equalToWhenPresent(record::getAbnormalReason)
                .set(abnormalRemark).equalToWhenPresent(record::getAbnormalRemark)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}