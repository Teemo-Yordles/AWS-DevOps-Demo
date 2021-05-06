package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsDelayDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsDelay fmsDelay = new FmsDelay();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsDelay.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsDelay.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> delayCode = fmsDelay.delayCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> startTime = fmsDelay.startTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> duration = fmsDelay.duration;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> comments = fmsDelay.comments;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsDelay.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsDelay.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsDelay extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<String> delayCode = column("delay_code", JDBCType.VARCHAR);

        public final SqlColumn<Date> startTime = column("start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Short> duration = column("duration", JDBCType.SMALLINT);

        public final SqlColumn<String> comments = column("comments", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsDelay() {
            super("fms_delay");
        }
    }
}