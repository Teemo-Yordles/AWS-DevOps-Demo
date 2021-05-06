package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsChuteDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsChute fmsChute = new FmsChute();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsChute.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsChute.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sequenceNumber = fmsChute.sequenceNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> chuteCode = fmsChute.chuteCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> chuteCategory = fmsChute.chuteCategory;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> plannedStartTime = fmsChute.plannedStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> plannedEndTime = fmsChute.plannedEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> actualStartTime = fmsChute.actualStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> actualEndTime = fmsChute.actualEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> chuteIndicator = fmsChute.chuteIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsChute.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsChute.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsChute extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> sequenceNumber = column("sequence_number", JDBCType.TINYINT);

        public final SqlColumn<String> chuteCode = column("chute_code", JDBCType.VARCHAR);

        public final SqlColumn<String> chuteCategory = column("chute_category", JDBCType.CHAR);

        public final SqlColumn<Date> plannedStartTime = column("planned_start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> plannedEndTime = column("planned_end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> actualStartTime = column("actual_start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> actualEndTime = column("actual_end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> chuteIndicator = column("chute_indicator", JDBCType.CHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsChute() {
            super("fms_chute");
        }
    }
}