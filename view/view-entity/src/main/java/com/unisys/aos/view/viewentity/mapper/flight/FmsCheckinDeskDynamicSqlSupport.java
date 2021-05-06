package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsCheckinDeskDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsCheckinDesk fmsCheckinDesk = new FmsCheckinDesk();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsCheckinDesk.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsCheckinDesk.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sequenceNumber = fmsCheckinDesk.sequenceNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> checkinDeskCode = fmsCheckinDesk.checkinDeskCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> checkinDeskCategory = fmsCheckinDesk.checkinDeskCategory;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> plannedStartTime = fmsCheckinDesk.plannedStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> plannedEndTime = fmsCheckinDesk.plannedEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> actualStartTime = fmsCheckinDesk.actualStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> actualEndTime = fmsCheckinDesk.actualEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> checkinIndicator = fmsCheckinDesk.checkinIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsCheckinDesk.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsCheckinDesk.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsCheckinDesk extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> sequenceNumber = column("sequence_number", JDBCType.TINYINT);

        public final SqlColumn<String> checkinDeskCode = column("checkin_desk_code", JDBCType.VARCHAR);

        public final SqlColumn<String> checkinDeskCategory = column("checkin_desk_category", JDBCType.CHAR);

        public final SqlColumn<Date> plannedStartTime = column("planned_start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> plannedEndTime = column("planned_end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> actualStartTime = column("actual_start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> actualEndTime = column("actual_end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> checkinIndicator = column("checkin_indicator", JDBCType.CHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsCheckinDesk() {
            super("fms_checkin_desk");
        }
    }
}