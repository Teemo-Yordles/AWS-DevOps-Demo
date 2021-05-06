package com.unisys.aos.view.viewentity.mapper.reference;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsAircraftTypeDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsAircraftType rdmsAircraftType = new RdmsAircraftType();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsAircraftType.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> iataCode = rdmsAircraftType.iataCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> icaoCode = rdmsAircraftType.icaoCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsAircraftType.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsAircraftType.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> sizeCategory = rdmsAircraftType.sizeCategory;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> maxPax = rdmsAircraftType.maxPax;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> maxFreightWeight = rdmsAircraftType.maxFreightWeight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> maxTakeoffWeight = rdmsAircraftType.maxTakeoffWeight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> aircraftLength = rdmsAircraftType.aircraftLength;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> wingSpan = rdmsAircraftType.wingSpan;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> minHandlingTime = rdmsAircraftType.minHandlingTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> maxAirbridge = rdmsAircraftType.maxAirbridge;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsAircraftType.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsAircraftType.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsAircraftType.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsAircraftType.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsAircraftType extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> iataCode = column("iata_code", JDBCType.VARCHAR);

        public final SqlColumn<String> icaoCode = column("icao_code", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<String> sizeCategory = column("size_category", JDBCType.CHAR);

        public final SqlColumn<Short> maxPax = column("max_pax", JDBCType.SMALLINT);

        public final SqlColumn<BigDecimal> maxFreightWeight = column("max_freight_weight", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> maxTakeoffWeight = column("max_takeoff_weight", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> aircraftLength = column("aircraft_length", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> wingSpan = column("wing_span", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> minHandlingTime = column("min_handling_time", JDBCType.DECIMAL);

        public final SqlColumn<Byte> maxAirbridge = column("max_airbridge", JDBCType.TINYINT);

        public final SqlColumn<Long> inspireTime = column("inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> expireTime = column("expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsAircraftType() {
            super("rdms_aircraft_type");
        }
    }
}