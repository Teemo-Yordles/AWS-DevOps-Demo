package com.unisys.aos.view.viewentity.mapper.reference;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsAirportDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsAirport rdmsAirport = new RdmsAirport();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsAirport.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> iataCode = rdmsAirport.iataCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> icaoCode = rdmsAirport.icaoCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsAirport.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsAirport.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> distance = rdmsAirport.distance;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> countryCode = rdmsAirport.countryCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cityCode = rdmsAirport.cityCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airportCategory = rdmsAirport.airportCategory;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> haulCategory = rdmsAirport.haulCategory;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation1 = rdmsAirport.abbreviation1;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation2 = rdmsAirport.abbreviation2;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation3 = rdmsAirport.abbreviation3;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation4 = rdmsAirport.abbreviation4;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation5 = rdmsAirport.abbreviation5;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abbreviation6 = rdmsAirport.abbreviation6;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsAirport.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsAirport.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsAirport.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsAirport.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsAirport extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> iataCode = column("iata_code", JDBCType.CHAR);

        public final SqlColumn<String> icaoCode = column("icao_code", JDBCType.CHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<Long> distance = column("distance", JDBCType.BIGINT);

        public final SqlColumn<String> countryCode = column("country_code", JDBCType.VARCHAR);

        public final SqlColumn<String> cityCode = column("city_code", JDBCType.CHAR);

        public final SqlColumn<String> airportCategory = column("airport_category", JDBCType.CHAR);

        public final SqlColumn<String> haulCategory = column("haul_category", JDBCType.CHAR);

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

        public RdmsAirport() {
            super("rdms_airport");
        }
    }
}