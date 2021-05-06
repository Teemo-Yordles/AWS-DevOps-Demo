package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsServiceDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsService fmsService = new FmsService();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsService.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsService.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> serviceCode = fmsService.serviceCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> serviceQuantity = fmsService.serviceQuantity;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> startTime = fmsService.startTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> endTime = fmsService.endTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> serviceProvider = fmsService.serviceProvider;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abnormalReason = fmsService.abnormalReason;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> abnormalRemark = fmsService.abnormalRemark;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsService.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsService.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsService extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<String> serviceCode = column("service_code", JDBCType.VARCHAR);

        public final SqlColumn<Byte> serviceQuantity = column("service_quantity", JDBCType.TINYINT);

        public final SqlColumn<Date> startTime = column("start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> endTime = column("end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> serviceProvider = column("service_provider", JDBCType.INTEGER);

        public final SqlColumn<String> abnormalReason = column("abnormal_reason", JDBCType.VARCHAR);

        public final SqlColumn<String> abnormalRemark = column("abnormal_remark", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsService() {
            super("fms_service");
        }
    }
}