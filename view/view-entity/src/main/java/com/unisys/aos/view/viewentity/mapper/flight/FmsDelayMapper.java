package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsDelay;
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

import static com.unisys.aos.view.viewentity.mapper.flight.FmsDelayDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface FmsDelayMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsDelay> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsDelayResult")
    FmsDelay selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FmsDelayResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "flight_id", property = "flightId", jdbcType = JdbcType.BIGINT),
            @Result(column = "delay_code", property = "delayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_time", property = "startTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "duration", property = "duration", jdbcType = JdbcType.SMALLINT),
            @Result(column = "comments", property = "comments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<FmsDelay> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsDelay);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsDelay);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsDelay)
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
        DeleteStatementProvider deleteStatement = deleteFrom(fmsDelay)
                .where(flightId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsDelay record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsDelay)
                .map(flightId).toProperty("flightId")
                .map(delayCode).toProperty("delayCode")
                .map(startTime).toProperty("startTime")
                .map(duration).toProperty("duration")
                .map(comments).toProperty("comments")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsDelay record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsDelay)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(delayCode).toPropertyWhenPresent("delayCode", record::getDelayCode)
                .map(startTime).toPropertyWhenPresent("startTime", record::getStartTime)
                .map(duration).toPropertyWhenPresent("duration", record::getDuration)
                .map(comments).toPropertyWhenPresent("comments", record::getComments)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsDelay>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, delayCode, startTime, duration, comments, createTime, updateTime)
                .from(fmsDelay);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsDelay>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, delayCode, startTime, duration, comments, createTime, updateTime)
                .from(fmsDelay);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsDelay selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, delayCode, startTime, duration, comments, createTime, updateTime)
                .from(fmsDelay)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsDelay> selectByAodbId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, delayCode, startTime, duration, comments, createTime, updateTime)
                .from(fmsDelay)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsDelay record) {
        return UpdateDSL.updateWithMapper(this::update, fmsDelay)
                .set(flightId).equalTo(record::getFlightId)
                .set(delayCode).equalTo(record::getDelayCode)
                .set(startTime).equalTo(record::getStartTime)
                .set(duration).equalTo(record::getDuration)
                .set(comments).equalTo(record::getComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsDelay record) {
        return UpdateDSL.updateWithMapper(this::update, fmsDelay)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(delayCode).equalToWhenPresent(record::getDelayCode)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(duration).equalToWhenPresent(record::getDuration)
                .set(comments).equalToWhenPresent(record::getComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsDelay record) {
        return UpdateDSL.updateWithMapper(this::update, fmsDelay)
                .set(flightId).equalTo(record::getFlightId)
                .set(delayCode).equalTo(record::getDelayCode)
                .set(startTime).equalTo(record::getStartTime)
                .set(duration).equalTo(record::getDuration)
                .set(comments).equalTo(record::getComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsDelay record) {
        return UpdateDSL.updateWithMapper(this::update, fmsDelay)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(delayCode).equalToWhenPresent(record::getDelayCode)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(duration).equalToWhenPresent(record::getDuration)
                .set(comments).equalToWhenPresent(record::getComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}