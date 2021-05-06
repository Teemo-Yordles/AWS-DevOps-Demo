package com.unisys.aos.view.viewentity.mapper.reference;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsVipPersonnalDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsVipPersonnal rdmsVipPersonnal = new RdmsVipPersonnal();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsVipPersonnal.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipPersonCode = rdmsVipPersonnal.vipPersonCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipPersonNumber = rdmsVipPersonnal.vipPersonNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipPersonFirstname = rdmsVipPersonnal.vipPersonFirstname;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipPersonLastname = rdmsVipPersonnal.vipPersonLastname;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipPersonPosition = rdmsVipPersonnal.vipPersonPosition;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipPersonRanking = rdmsVipPersonnal.vipPersonRanking;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsVipPersonnal.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsVipPersonnal.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipContactPerson = rdmsVipPersonnal.vipContactPerson;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipContactTelephoneNumber = rdmsVipPersonnal.vipContactTelephoneNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipContactMobileNumber = rdmsVipPersonnal.vipContactMobileNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipPersonWorkUnit = rdmsVipPersonnal.vipPersonWorkUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipPersonRemarks = rdmsVipPersonnal.vipPersonRemarks;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsVipPersonnal.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsVipPersonnal.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsVipPersonnal.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsVipPersonnal.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsVipPersonnal extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> vipPersonCode = column("vip_person_code", JDBCType.VARCHAR);

        public final SqlColumn<String> vipPersonNumber = column("vip_person_number", JDBCType.VARCHAR);

        public final SqlColumn<String> vipPersonFirstname = column("vip_person_firstname", JDBCType.VARCHAR);

        public final SqlColumn<String> vipPersonLastname = column("vip_person_lastname", JDBCType.VARCHAR);

        public final SqlColumn<String> vipPersonPosition = column("vip_person_position", JDBCType.VARCHAR);

        public final SqlColumn<String> vipPersonRanking = column("vip_person_ranking", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<String> vipContactPerson = column("vip_contact_person", JDBCType.VARCHAR);

        public final SqlColumn<String> vipContactTelephoneNumber = column("vip_contact_telephone_number", JDBCType.VARCHAR);

        public final SqlColumn<String> vipContactMobileNumber = column("vip_contact_mobile_number", JDBCType.VARCHAR);

        public final SqlColumn<String> vipPersonWorkUnit = column("vip_person_work_unit", JDBCType.VARCHAR);

        public final SqlColumn<String> vipPersonRemarks = column("vip_person_remarks", JDBCType.VARCHAR);

        public final SqlColumn<Long> inspireTime = column("inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> expireTime = column("expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsVipPersonnal() {
            super("rdms_vip_personnal");
        }
    }
}