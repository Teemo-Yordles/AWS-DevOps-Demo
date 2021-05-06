package com.unisys.aos.view.viewentity.mapper.reference;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsCountryDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsCountry rdmsCountry = new RdmsCountry();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsCountry.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> countryCode = rdmsCountry.countryCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsCountry.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsCountry.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsCountry.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsCountry.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsCountry.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsCountry.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsCountry extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> countryCode = column("country_code", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<Long> inspireTime = column("inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> expireTime = column("expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsCountry() {
            super("rdms_country");
        }
    }
}