package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsVipDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsVip fmsVip = new FmsVip();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsVip.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsVip.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipCode = fmsVip.vipCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> entourageSize = fmsVip.entourageSize;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> serviceCode = fmsVip.serviceCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> serviceQuantity = fmsVip.serviceQuantity;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> serviceStartTime = fmsVip.serviceStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> serviceEndTime = fmsVip.serviceEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsVip.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsVip.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsVip extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<String> vipCode = column("vip_code", JDBCType.VARCHAR);

        public final SqlColumn<Short> entourageSize = column("entourage_size", JDBCType.SMALLINT);

        public final SqlColumn<String> serviceCode = column("service_code", JDBCType.VARCHAR);

        public final SqlColumn<Long> serviceQuantity = column("service_quantity", JDBCType.BIGINT);

        public final SqlColumn<Date> serviceStartTime = column("service_start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> serviceEndTime = column("service_end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsVip() {
            super("fms_vip");
        }
    }
}