package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsSeasonFlightDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsSeasonFlight fmsSeasonFlight = new FmsSeasonFlight();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsSeasonFlight.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> seasonName = fmsSeasonFlight.seasonName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> seasonStartDate = fmsSeasonFlight.seasonStartDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> seasonEndDate = fmsSeasonFlight.seasonEndDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airlineCode = fmsSeasonFlight.airlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> flightNumber = fmsSeasonFlight.flightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> movementIndicator = fmsSeasonFlight.movementIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> seasonScheduleStartDate = fmsSeasonFlight.seasonScheduleStartDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> seasonScheduleEndDate = fmsSeasonFlight.seasonScheduleEndDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> weekOperateDay = fmsSeasonFlight.weekOperateDay;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> flightScheduleTime = fmsSeasonFlight.flightScheduleTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> flightType = fmsSeasonFlight.flightType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> flightIndicator = fmsSeasonFlight.flightIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> aircraftType = fmsSeasonFlight.aircraftType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> registration = fmsSeasonFlight.registration;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> linkedAirlineCode = fmsSeasonFlight.linkedAirlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> linkedFlightNumber = fmsSeasonFlight.linkedFlightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> terminal = fmsSeasonFlight.terminal;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> maxPax = fmsSeasonFlight.maxPax;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> masterAirlineCode = fmsSeasonFlight.masterAirlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> masterFlightNumber = fmsSeasonFlight.masterFlightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsSeasonFlight.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsSeasonFlight.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsSeasonFlight extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> seasonName = column("season_name", JDBCType.VARCHAR);

        public final SqlColumn<Date> seasonStartDate = column("season_start_date", JDBCType.DATE);

        public final SqlColumn<Date> seasonEndDate = column("season_end_date", JDBCType.DATE);

        public final SqlColumn<String> airlineCode = column("airline_code", JDBCType.VARCHAR);

        public final SqlColumn<String> flightNumber = column("flight_number", JDBCType.VARCHAR);

        public final SqlColumn<String> movementIndicator = column("movement_indicator", JDBCType.CHAR);

        public final SqlColumn<Date> seasonScheduleStartDate = column("season_schedule_start_date", JDBCType.DATE);

        public final SqlColumn<Date> seasonScheduleEndDate = column("season_schedule_end_date", JDBCType.DATE);

        public final SqlColumn<String> weekOperateDay = column("week_operate_day", JDBCType.CHAR);

        public final SqlColumn<String> flightScheduleTime = column("flight_schedule_time", JDBCType.CHAR);

        public final SqlColumn<String> flightType = column("flight_type", JDBCType.VARCHAR);

        public final SqlColumn<String> flightIndicator = column("flight_indicator", JDBCType.CHAR);

        public final SqlColumn<String> aircraftType = column("aircraft_type", JDBCType.CHAR);

        public final SqlColumn<String> registration = column("registration", JDBCType.VARCHAR);

        public final SqlColumn<String> linkedAirlineCode = column("linked_airline_code", JDBCType.VARCHAR);

        public final SqlColumn<String> linkedFlightNumber = column("linked_flight_number", JDBCType.VARCHAR);

        public final SqlColumn<String> terminal = column("terminal", JDBCType.VARCHAR);

        public final SqlColumn<Short> maxPax = column("max_pax", JDBCType.SMALLINT);

        public final SqlColumn<String> masterAirlineCode = column("master_airline_code", JDBCType.VARCHAR);

        public final SqlColumn<String> masterFlightNumber = column("master_flight_number", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsSeasonFlight() {
            super("fms_season_flight");
        }
    }
}