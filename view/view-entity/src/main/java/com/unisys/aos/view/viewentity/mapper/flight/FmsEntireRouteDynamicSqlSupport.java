package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsEntireRouteDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsEntireRoute fmsEntireRoute = new FmsEntireRoute();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsEntireRoute.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsEntireRoute.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sequenceNumber = fmsEntireRoute.sequenceNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airportCode = fmsEntireRoute.airportCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> arrivalTime = fmsEntireRoute.arrivalTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> departureTime = fmsEntireRoute.departureTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsEntireRoute.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsEntireRoute.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsEntireRoute extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> sequenceNumber = column("sequence_number", JDBCType.TINYINT);

        public final SqlColumn<String> airportCode = column("airport_code", JDBCType.VARCHAR);

        public final SqlColumn<Date> arrivalTime = column("arrival_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> departureTime = column("departure_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsEntireRoute() {
            super("fms_entire_route");
        }
    }
}