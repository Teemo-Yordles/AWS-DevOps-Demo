package com.unisys.aos.view.viewentity.mapper.reference;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsRegionCountryAssociationDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsRegionCountryAssociation rdmsRegionCountryAssociation = new RdmsRegionCountryAssociation();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsRegionCountryAssociation.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> regionCode = rdmsRegionCountryAssociation.regionCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> countryCode = rdmsRegionCountryAssociation.countryCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> countryInspireTime = rdmsRegionCountryAssociation.countryInspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> countryExpireTime = rdmsRegionCountryAssociation.countryExpireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> regionInspireTime = rdmsRegionCountryAssociation.regionInspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> regionExpireTime = rdmsRegionCountryAssociation.regionExpireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsRegionCountryAssociation.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsRegionCountryAssociation.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsRegionCountryAssociation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> regionCode = column("region_code", JDBCType.VARCHAR);

        public final SqlColumn<String> countryCode = column("country_code", JDBCType.VARCHAR);

        public final SqlColumn<Long> countryInspireTime = column("country_inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> countryExpireTime = column("country_expire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> regionInspireTime = column("region_inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> regionExpireTime = column("region_expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsRegionCountryAssociation() {
            super("rdms_region_country_association");
        }
    }
}