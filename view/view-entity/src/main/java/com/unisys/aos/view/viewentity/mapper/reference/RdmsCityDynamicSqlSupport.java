package com.unisys.aos.view.viewentity.mapper.reference;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsCityDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsCity rdmsCity = new RdmsCity();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsCity.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> iataCityCode = rdmsCity.iataCityCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> icaoCityCode = rdmsCity.icaoCityCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsCity.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsCity.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> countryCode = rdmsCity.countryCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation1 = rdmsCity.abbreviation1;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation2 = rdmsCity.abbreviation2;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation3 = rdmsCity.abbreviation3;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation4 = rdmsCity.abbreviation4;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation5 = rdmsCity.abbreviation5;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation6 = rdmsCity.abbreviation6;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsCity.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsCity.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsCity.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsCity.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsCity extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> iataCityCode = column("iata_city_code", JDBCType.CHAR);

        public final SqlColumn<String> icaoCityCode = column("icao_city_code", JDBCType.CHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<String> countryCode = column("country_code", JDBCType.VARCHAR);

        public final SqlColumn<String> abbreviation1 = column("abbreviation_1", JDBCType.VARCHAR);

        public final SqlColumn<String> abbreviation2 = column("abbreviation_2", JDBCType.VARCHAR);

        public final SqlColumn<String> abbreviation3 = column("abbreviation_3", JDBCType.VARCHAR);

        public final SqlColumn<String> abbreviation4 = column("abbreviation_4", JDBCType.VARCHAR);

        public final SqlColumn<String> abbreviation5 = column("abbreviation_5", JDBCType.VARCHAR);

        public final SqlColumn<String> abbreviation6 = column("abbreviation_6", JDBCType.VARCHAR);

        public final SqlColumn<Long> inspireTime = column("inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> expireTime = column("expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsCity() {
            super("rdms_city");
        }
    }
}