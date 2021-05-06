package com.unisys.aos.view.viewentity.mapper.reference;

import com.unisys.aos.view.viewentity.entity.reference.RdmsVipPersonnal;
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

import static com.unisys.aos.view.viewentity.mapper.reference.RdmsVipPersonnalDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface RdmsVipPersonnalMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<RdmsVipPersonnal> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RdmsVipPersonnalResult")
    RdmsVipPersonnal selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RdmsVipPersonnalResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "vip_person_code", property = "vipPersonCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_person_number", property = "vipPersonNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_person_firstname", property = "vipPersonFirstname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_person_lastname", property = "vipPersonLastname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_person_position", property = "vipPersonPosition", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_person_ranking", property = "vipPersonRanking", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "local_description", property = "localDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_contact_person", property = "vipContactPerson", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_contact_telephone_number", property = "vipContactTelephoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_contact_mobile_number", property = "vipContactMobileNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_person_work_unit", property = "vipPersonWorkUnit", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vip_person_remarks", property = "vipPersonRemarks", jdbcType = JdbcType.VARCHAR),
            @Result(column = "inspire_time", property = "inspireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "expire_time", property = "expireTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RdmsVipPersonnal> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rdmsVipPersonnal);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsVipPersonnal);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rdmsVipPersonnal)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RdmsVipPersonnal record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsVipPersonnal)
                .map(vipPersonCode).toProperty("vipPersonCode")
                .map(vipPersonNumber).toProperty("vipPersonNumber")
                .map(vipPersonFirstname).toProperty("vipPersonFirstname")
                .map(vipPersonLastname).toProperty("vipPersonLastname")
                .map(vipPersonPosition).toProperty("vipPersonPosition")
                .map(vipPersonRanking).toProperty("vipPersonRanking")
                .map(description).toProperty("description")
                .map(localDescription).toProperty("localDescription")
                .map(vipContactPerson).toProperty("vipContactPerson")
                .map(vipContactTelephoneNumber).toProperty("vipContactTelephoneNumber")
                .map(vipContactMobileNumber).toProperty("vipContactMobileNumber")
                .map(vipPersonWorkUnit).toProperty("vipPersonWorkUnit")
                .map(vipPersonRemarks).toProperty("vipPersonRemarks")
                .map(inspireTime).toProperty("inspireTime")
                .map(expireTime).toProperty("expireTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RdmsVipPersonnal record) {
        return insert(SqlBuilder.insert(record)
                .into(rdmsVipPersonnal)
                .map(vipPersonCode).toPropertyWhenPresent("vipPersonCode", record::getVipPersonCode)
                .map(vipPersonNumber).toPropertyWhenPresent("vipPersonNumber", record::getVipPersonNumber)
                .map(vipPersonFirstname).toPropertyWhenPresent("vipPersonFirstname", record::getVipPersonFirstname)
                .map(vipPersonLastname).toPropertyWhenPresent("vipPersonLastname", record::getVipPersonLastname)
                .map(vipPersonPosition).toPropertyWhenPresent("vipPersonPosition", record::getVipPersonPosition)
                .map(vipPersonRanking).toPropertyWhenPresent("vipPersonRanking", record::getVipPersonRanking)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(localDescription).toPropertyWhenPresent("localDescription", record::getLocalDescription)
                .map(vipContactPerson).toPropertyWhenPresent("vipContactPerson", record::getVipContactPerson)
                .map(vipContactTelephoneNumber).toPropertyWhenPresent("vipContactTelephoneNumber", record::getVipContactTelephoneNumber)
                .map(vipContactMobileNumber).toPropertyWhenPresent("vipContactMobileNumber", record::getVipContactMobileNumber)
                .map(vipPersonWorkUnit).toPropertyWhenPresent("vipPersonWorkUnit", record::getVipPersonWorkUnit)
                .map(vipPersonRemarks).toPropertyWhenPresent("vipPersonRemarks", record::getVipPersonRemarks)
                .map(inspireTime).toPropertyWhenPresent("inspireTime", record::getInspireTime)
                .map(expireTime).toPropertyWhenPresent("expireTime", record::getExpireTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsVipPersonnal>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, vipPersonCode, vipPersonNumber, vipPersonFirstname, vipPersonLastname, vipPersonPosition, vipPersonRanking, description, localDescription, vipContactPerson, vipContactTelephoneNumber, vipContactMobileNumber, vipPersonWorkUnit, vipPersonRemarks, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsVipPersonnal);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RdmsVipPersonnal>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, vipPersonCode, vipPersonNumber, vipPersonFirstname, vipPersonLastname, vipPersonPosition, vipPersonRanking, description, localDescription, vipContactPerson, vipContactTelephoneNumber, vipContactMobileNumber, vipPersonWorkUnit, vipPersonRemarks, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsVipPersonnal);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RdmsVipPersonnal selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, vipPersonCode, vipPersonNumber, vipPersonFirstname, vipPersonLastname, vipPersonPosition, vipPersonRanking, description, localDescription, vipContactPerson, vipContactTelephoneNumber, vipContactMobileNumber, vipPersonWorkUnit, vipPersonRemarks, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsVipPersonnal)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    /***
     * Get RdmsVipPersonnal entity by vip person code.
     * @param code vip person code
     * @return RdmsVipPersonnal entity
     */
    default List<RdmsVipPersonnal> selectByVipPersonCode(String code) {
        return SelectDSL.selectWithMapper(this::selectMany, id, vipPersonCode, vipPersonNumber, vipPersonFirstname, vipPersonLastname, vipPersonPosition, vipPersonRanking, description, localDescription, vipContactPerson, vipContactTelephoneNumber, vipContactMobileNumber, vipPersonWorkUnit, vipPersonRemarks, inspireTime, expireTime, createTime, updateTime)
                .from(rdmsVipPersonnal)
                .where(vipPersonCode, isEqualTo(code))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RdmsVipPersonnal record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsVipPersonnal)
                .set(vipPersonCode).equalTo(record::getVipPersonCode)
                .set(vipPersonNumber).equalTo(record::getVipPersonNumber)
                .set(vipPersonFirstname).equalTo(record::getVipPersonFirstname)
                .set(vipPersonLastname).equalTo(record::getVipPersonLastname)
                .set(vipPersonPosition).equalTo(record::getVipPersonPosition)
                .set(vipPersonRanking).equalTo(record::getVipPersonRanking)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(vipContactPerson).equalTo(record::getVipContactPerson)
                .set(vipContactTelephoneNumber).equalTo(record::getVipContactTelephoneNumber)
                .set(vipContactMobileNumber).equalTo(record::getVipContactMobileNumber)
                .set(vipPersonWorkUnit).equalTo(record::getVipPersonWorkUnit)
                .set(vipPersonRemarks).equalTo(record::getVipPersonRemarks)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RdmsVipPersonnal record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsVipPersonnal)
                .set(vipPersonCode).equalToWhenPresent(record::getVipPersonCode)
                .set(vipPersonNumber).equalToWhenPresent(record::getVipPersonNumber)
                .set(vipPersonFirstname).equalToWhenPresent(record::getVipPersonFirstname)
                .set(vipPersonLastname).equalToWhenPresent(record::getVipPersonLastname)
                .set(vipPersonPosition).equalToWhenPresent(record::getVipPersonPosition)
                .set(vipPersonRanking).equalToWhenPresent(record::getVipPersonRanking)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(vipContactPerson).equalToWhenPresent(record::getVipContactPerson)
                .set(vipContactTelephoneNumber).equalToWhenPresent(record::getVipContactTelephoneNumber)
                .set(vipContactMobileNumber).equalToWhenPresent(record::getVipContactMobileNumber)
                .set(vipPersonWorkUnit).equalToWhenPresent(record::getVipPersonWorkUnit)
                .set(vipPersonRemarks).equalToWhenPresent(record::getVipPersonRemarks)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RdmsVipPersonnal record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsVipPersonnal)
                .set(vipPersonCode).equalTo(record::getVipPersonCode)
                .set(vipPersonNumber).equalTo(record::getVipPersonNumber)
                .set(vipPersonFirstname).equalTo(record::getVipPersonFirstname)
                .set(vipPersonLastname).equalTo(record::getVipPersonLastname)
                .set(vipPersonPosition).equalTo(record::getVipPersonPosition)
                .set(vipPersonRanking).equalTo(record::getVipPersonRanking)
                .set(description).equalTo(record::getDescription)
                .set(localDescription).equalTo(record::getLocalDescription)
                .set(vipContactPerson).equalTo(record::getVipContactPerson)
                .set(vipContactTelephoneNumber).equalTo(record::getVipContactTelephoneNumber)
                .set(vipContactMobileNumber).equalTo(record::getVipContactMobileNumber)
                .set(vipPersonWorkUnit).equalTo(record::getVipPersonWorkUnit)
                .set(vipPersonRemarks).equalTo(record::getVipPersonRemarks)
                .set(inspireTime).equalTo(record::getInspireTime)
                .set(expireTime).equalTo(record::getExpireTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RdmsVipPersonnal record) {
        return UpdateDSL.updateWithMapper(this::update, rdmsVipPersonnal)
                .set(vipPersonCode).equalToWhenPresent(record::getVipPersonCode)
                .set(vipPersonNumber).equalToWhenPresent(record::getVipPersonNumber)
                .set(vipPersonFirstname).equalToWhenPresent(record::getVipPersonFirstname)
                .set(vipPersonLastname).equalToWhenPresent(record::getVipPersonLastname)
                .set(vipPersonPosition).equalToWhenPresent(record::getVipPersonPosition)
                .set(vipPersonRanking).equalToWhenPresent(record::getVipPersonRanking)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(localDescription).equalToWhenPresent(record::getLocalDescription)
                .set(vipContactPerson).equalToWhenPresent(record::getVipContactPerson)
                .set(vipContactTelephoneNumber).equalToWhenPresent(record::getVipContactTelephoneNumber)
                .set(vipContactMobileNumber).equalToWhenPresent(record::getVipContactMobileNumber)
                .set(vipPersonWorkUnit).equalToWhenPresent(record::getVipPersonWorkUnit)
                .set(vipPersonRemarks).equalToWhenPresent(record::getVipPersonRemarks)
                .set(inspireTime).equalToWhenPresent(record::getInspireTime)
                .set(expireTime).equalToWhenPresent(record::getExpireTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}