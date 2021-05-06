package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsCarousel;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsCarouselDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface RdmsCarouselMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsCarousel> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsCarouselResult")
    RdmsCarousel selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsCarouselResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "carousel_code", property = "carouselCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "carousel_category", property = "carouselCategory", jdbcType = JdbcType.CHAR),
            @Result(column = "terminal_code", property = "terminalCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsCarousel> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsCarousel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsCarousel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsCarousel)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsCarousel record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsCarousel)
                .map(carouselCode).toProperty("carouselCode")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(carouselCategory).toProperty("carouselCategory")
                .map(terminalCode).toProperty("terminalCode")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsCarousel record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsCarousel)
                .map(carouselCode).toPropertyWhenPresent("carouselCode", record::getCarouselCode)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(carouselCategory).toPropertyWhenPresent("carouselCategory", record::getCarouselCategory)
                .map(terminalCode).toPropertyWhenPresent("terminalCode", record::getTerminalCode)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsCarousel>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, carouselCode, description, localDescription, carouselCategory, terminalCode, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsCarousel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsCarousel>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, carouselCode, description, localDescription, carouselCategory, terminalCode, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsCarousel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsCarousel selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, carouselCode, description, localDescription, carouselCategory, terminalCode, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsCarousel)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsCarousel record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsCarousel)
                .set(carouselCode).equalTo(record::getCarouselCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(carouselCategory).equalTo(record::getCarouselCategory)
                .set(terminalCode).equalTo(record::getTerminalCode)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsCarousel record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsCarousel)
                .set(carouselCode).equalToWhenPresent(record::getCarouselCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(carouselCategory).equalToWhenPresent(record::getCarouselCategory)
                .set(terminalCode).equalToWhenPresent(record::getTerminalCode)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsCarousel record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsCarousel)
                .set(carouselCode).equalTo(record::getCarouselCode)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(carouselCategory).equalTo(record::getCarouselCategory)
                .set(terminalCode).equalTo(record::getTerminalCode)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsCarousel record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsCarousel)
                .set(carouselCode).equalToWhenPresent(record::getCarouselCode)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(carouselCategory).equalToWhenPresent(record::getCarouselCategory)
                .set(terminalCode).equalToWhenPresent(record::getTerminalCode)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}