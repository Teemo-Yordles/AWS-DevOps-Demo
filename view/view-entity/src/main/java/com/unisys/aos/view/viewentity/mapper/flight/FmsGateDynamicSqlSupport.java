package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsGateDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsGate fmsGate = new FmsGate();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsGate.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsGate.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sequenceNumber = fmsGate.sequenceNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> gateCode = fmsGate.gateCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> plannedStartTime = fmsGate.plannedStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> plannedEndTime = fmsGate.plannedEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> actualStartTime = fmsGate.actualStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> actualEndTime = fmsGate.actualEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> gateIndicator = fmsGate.gateIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsGate.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsGate.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsGate extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> sequenceNumber = column("sequence_number", JDBCType.TINYINT);

        public final SqlColumn<String> gateCode = column("gate_code", JDBCType.VARCHAR);

        public final SqlColumn<Date> plannedStartTime = column("planned_start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> plannedEndTime = column("planned_end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> actualStartTime = column("actual_start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> actualEndTime = column("actual_end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> gateIndicator = column("gate_indicator", JDBCType.CHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsGate() {
            super("fms_gate");
        }
    }
}