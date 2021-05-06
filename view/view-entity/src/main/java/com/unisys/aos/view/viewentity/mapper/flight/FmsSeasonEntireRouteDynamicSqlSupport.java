package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsSeasonEntireRouteDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsSeasonEntireRoute fmsSeasonEntireRoute = new FmsSeasonEntireRoute();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsSeasonEntireRoute.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsSeasonEntireRoute.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sequenceNumber = fmsSeasonEntireRoute.sequenceNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airportCode = fmsSeasonEntireRoute.airportCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> arrivalTime = fmsSeasonEntireRoute.arrivalTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> arrivalDayChange = fmsSeasonEntireRoute.arrivalDayChange;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> departureTime = fmsSeasonEntireRoute.departureTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> departureDayChange = fmsSeasonEntireRoute.departureDayChange;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsSeasonEntireRoute.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsSeasonEntireRoute.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsSeasonEntireRoute extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> sequenceNumber = column("sequence_number", JDBCType.TINYINT);

        public final SqlColumn<String> airportCode = column("airport_code", JDBCType.VARCHAR);

        public final SqlColumn<String> arrivalTime = column("arrival_time", JDBCType.CHAR);

        public final SqlColumn<String> arrivalDayChange = column("arrival_day_change", JDBCType.CHAR);

        public final SqlColumn<String> departureTime = column("departure_time", JDBCType.CHAR);

        public final SqlColumn<String> departureDayChange = column("departure_day_change", JDBCType.CHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsSeasonEntireRoute() {
            super("fms_season_entire_route");
        }
    }
}