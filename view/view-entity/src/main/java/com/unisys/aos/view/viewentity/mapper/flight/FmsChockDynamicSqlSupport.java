package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsChockDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsChock fmsChock = new FmsChock();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsChock.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsChock.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sequenceNumber = fmsChock.sequenceNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> standCode = fmsChock.standCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> chockTime = fmsChock.chockTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> chockIndicator = fmsChock.chockIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsChock.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsChock.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsChock extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> sequenceNumber = column("sequence_number", JDBCType.TINYINT);

        public final SqlColumn<String> standCode = column("stand_code", JDBCType.VARCHAR);

        public final SqlColumn<Date> chockTime = column("chock_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> chockIndicator = column("chock_indicator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsChock() {
            super("fms_chock");
        }
    }
}