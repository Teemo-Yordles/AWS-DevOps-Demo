package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsCarousel;
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

import static com.unisys.aos.view.viewentity.mapper.flight.FmsCarouselDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface FmsCarouselMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsCarousel> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsCarouselResult")
    FmsCarousel selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FmsCarouselResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "flight_id", property = "flightId", jdbcType = JdbcType.BIGINT),
            @Result(column = "sequence_number", property = "sequenceNumber", jdbcType = JdbcType.TINYINT),
            @Result(column = "carousel_code", property = "carouselCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "planned_start_time", property = "plannedStartTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "planned_end_time", property = "plannedEndTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "first_bag_time", property = "firstBagTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "last_bag_time", property = "lastBagTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "carousel_indicator", property = "carouselIndicator", jdbcType = JdbcType.CHAR),
            @Result(column = "carousel_class", property = "carouselClass", jdbcType = JdbcType.CHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<FmsCarousel> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsCarousel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsCarousel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsCarousel)
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
        DeleteStatementProvider deleteStatement = deleteFrom(fmsCarousel)
                .where(flightId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsCarousel record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsCarousel)
                .map(flightId).toProperty("flightId")
                .map(sequenceNumber).toProperty("sequenceNumber")
                .map(carouselCode).toProperty("carouselCode")
                .map(plannedStartTime).toProperty("plannedStartTime")
                .map(plannedEndTime).toProperty("plannedEndTime")
                .map(firstBagTime).toProperty("firstBagTime")
                .map(lastBagTime).toProperty("lastBagTime")
                .map(carouselIndicator).toProperty("carouselIndicator")
                .map(carouselClass).toProperty("carouselClass")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsCarousel record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsCarousel)
                .map(flightId).toPropertyWhenPresent("flightId", record::getFlightId)
                .map(sequenceNumber).toPropertyWhenPresent("sequenceNumber", record::getSequenceNumber)
                .map(carouselCode).toPropertyWhenPresent("carouselCode", record::getCarouselCode)
                .map(plannedStartTime).toPropertyWhenPresent("plannedStartTime", record::getPlannedStartTime)
                .map(plannedEndTime).toPropertyWhenPresent("plannedEndTime", record::getPlannedEndTime)
                .map(firstBagTime).toPropertyWhenPresent("firstBagTime", record::getFirstBagTime)
                .map(lastBagTime).toPropertyWhenPresent("lastBagTime", record::getLastBagTime)
                .map(carouselIndicator).toPropertyWhenPresent("carouselIndicator", record::getCarouselIndicator)
                .map(carouselClass).toPropertyWhenPresent("carouselClass", record::getCarouselClass)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsCarousel>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, carouselCode, plannedStartTime, plannedEndTime, firstBagTime, lastBagTime, carouselIndicator, carouselClass, createTime, updateTime)
                .from(fmsCarousel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsCarousel>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, flightId, sequenceNumber, carouselCode, plannedStartTime, plannedEndTime, firstBagTime, lastBagTime, carouselIndicator, carouselClass, createTime, updateTime)
                .from(fmsCarousel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsCarousel selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, flightId, sequenceNumber, carouselCode, plannedStartTime, plannedEndTime, firstBagTime, lastBagTime, carouselIndicator, carouselClass, createTime, updateTime)
                .from(fmsCarousel)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    default List<FmsCarousel> selectByAodbId(Long flightId_) {
        return SelectDSL.selectWithMapper(this::selectMany, id, flightId, sequenceNumber, carouselCode, plannedStartTime, plannedEndTime, firstBagTime, lastBagTime, carouselIndicator, carouselClass, createTime, updateTime)
                .from(fmsCarousel)
                .where(flightId, isEqualTo(flightId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsCarousel record) {
        return UpdateDSL.updateWithMapper(this::update, fmsCarousel)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(carouselCode).equalTo(record::getCarouselCode)
                .set(plannedStartTime).equalTo(record::getPlannedStartTime)
                .set(plannedEndTime).equalTo(record::getPlannedEndTime)
                .set(firstBagTime).equalTo(record::getFirstBagTime)
                .set(lastBagTime).equalTo(record::getLastBagTime)
                .set(carouselIndicator).equalTo(record::getCarouselIndicator)
                .set(carouselClass).equalTo(record::getCarouselClass)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsCarousel record) {
        return UpdateDSL.updateWithMapper(this::update, fmsCarousel)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(carouselCode).equalToWhenPresent(record::getCarouselCode)
                .set(plannedStartTime).equalToWhenPresent(record::getPlannedStartTime)
                .set(plannedEndTime).equalToWhenPresent(record::getPlannedEndTime)
                .set(firstBagTime).equalToWhenPresent(record::getFirstBagTime)
                .set(lastBagTime).equalToWhenPresent(record::getLastBagTime)
                .set(carouselIndicator).equalToWhenPresent(record::getCarouselIndicator)
                .set(carouselClass).equalToWhenPresent(record::getCarouselClass)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsCarousel record) {
        return UpdateDSL.updateWithMapper(this::update, fmsCarousel)
                .set(flightId).equalTo(record::getFlightId)
                .set(sequenceNumber).equalTo(record::getSequenceNumber)
                .set(carouselCode).equalTo(record::getCarouselCode)
                .set(plannedStartTime).equalTo(record::getPlannedStartTime)
                .set(plannedEndTime).equalTo(record::getPlannedEndTime)
                .set(firstBagTime).equalTo(record::getFirstBagTime)
                .set(lastBagTime).equalTo(record::getLastBagTime)
                .set(carouselIndicator).equalTo(record::getCarouselIndicator)
                .set(carouselClass).equalTo(record::getCarouselClass)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsCarousel record) {
        return UpdateDSL.updateWithMapper(this::update, fmsCarousel)
                .set(flightId).equalToWhenPresent(record::getFlightId)
                .set(sequenceNumber).equalToWhenPresent(record::getSequenceNumber)
                .set(carouselCode).equalToWhenPresent(record::getCarouselCode)
                .set(plannedStartTime).equalToWhenPresent(record::getPlannedStartTime)
                .set(plannedEndTime).equalToWhenPresent(record::getPlannedEndTime)
                .set(firstBagTime).equalToWhenPresent(record::getFirstBagTime)
                .set(lastBagTime).equalToWhenPresent(record::getLastBagTime)
                .set(carouselIndicator).equalToWhenPresent(record::getCarouselIndicator)
                .set(carouselClass).equalToWhenPresent(record::getCarouselClass)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}