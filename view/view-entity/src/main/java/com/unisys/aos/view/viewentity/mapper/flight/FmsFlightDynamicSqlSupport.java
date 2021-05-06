package com.unisys.aos.view.viewentity.mapper.flight;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;

public final class FmsFlightDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsFlight fmsFlight = new FmsFlight();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsFlight.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> aodbId = fmsFlight.aodbId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airlineCode = fmsFlight.airlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airlineSubcompanyCode = fmsFlight.airlineSubcompanyCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> flightNumber = fmsFlight.flightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> movementIndicator = fmsFlight.movementIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> sto = fmsFlight.sto;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> flightType = fmsFlight.flightType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> flightIndicator = fmsFlight.flightIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> aircraftType = fmsFlight.aircraftType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> registration = fmsFlight.registration;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> linkedId = fmsFlight.linkedId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> linkedAodbId = fmsFlight.linkedAodbId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> linkedAirlineCode = fmsFlight.linkedAirlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> linkedFlightNumber = fmsFlight.linkedFlightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> terminal = fmsFlight.terminal;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> maxPax = fmsFlight.maxPax;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> masterId = fmsFlight.masterId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> masterAodbId = fmsFlight.masterAodbId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> masterAirlineCode = fmsFlight.masterAirlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> masterFlightNumber = fmsFlight.masterFlightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> eto = fmsFlight.eto;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> ato = fmsFlight.ato;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> currentStand = fmsFlight.currentStand;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> paxAgent = fmsFlight.paxAgent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> cancel = fmsFlight.cancel;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> remarks = fmsFlight.remarks;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> boardingOpen = fmsFlight.boardingOpen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> lastCall = fmsFlight.lastCall;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> finalTime = fmsFlight.finalTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> approvedTime = fmsFlight.approvedTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> engineStart = fmsFlight.engineStart;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> engineStartRequest = fmsFlight.engineStartRequest;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> fieldAgent = fmsFlight.fieldAgent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> maintenanceAgent = fmsFlight.maintenanceAgent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> vipCount = fmsFlight.vipCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> vipFlag = fmsFlight.vipFlag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> divAirport = fmsFlight.divAirport;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> divDirection = fmsFlight.divDirection;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> divReason = fmsFlight.divReason;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> retTyp = fmsFlight.retTyp;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> retRsn = fmsFlight.retRsn;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> landingAbortIndicator = fmsFlight.landingAbortIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> landingAbortReason = fmsFlight.landingAbortReason;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> localBagCount = fmsFlight.localBagCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> localBagWeight = fmsFlight.localBagWeight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> passengerCount = fmsFlight.passengerCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> externalStatusCode = fmsFlight.externalStatusCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> externalStatusComment = fmsFlight.externalStatusComment;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> statusCode = fmsFlight.statusCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> pedt = fmsFlight.pedt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> neat = fmsFlight.neat;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> padt = fmsFlight.padt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> naat = fmsFlight.naat;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> rwyCode = fmsFlight.rwyCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> calOffblock = fmsFlight.calOffblock;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> calTakeoff = fmsFlight.calTakeoff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> estimatedOffblock = fmsFlight.estimatedOffblock;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> internalEstimated = fmsFlight.internalEstimated;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> portOpenTime = fmsFlight.portOpenTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> portCloseTime = fmsFlight.portCloseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> targetOffblock = fmsFlight.targetOffblock;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> logicalRunway = fmsFlight.logicalRunway;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsFlight.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsFlight.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsFlight extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> aodbId = column("aodb_id", JDBCType.BIGINT);

        public final SqlColumn<String> airlineCode = column("airline_code", JDBCType.VARCHAR);

        public final SqlColumn<String> airlineSubcompanyCode = column("airline_subcompany_code", JDBCType.VARCHAR);

        public final SqlColumn<String> flightNumber = column("flight_number", JDBCType.VARCHAR);

        public final SqlColumn<String> movementIndicator = column("movement_indicator", JDBCType.CHAR);

        public final SqlColumn<Date> sto = column("sto", JDBCType.TIMESTAMP);

        public final SqlColumn<String> flightType = column("flight_type", JDBCType.VARCHAR);

        public final SqlColumn<String> flightIndicator = column("flight_indicator", JDBCType.CHAR);

        public final SqlColumn<String> aircraftType = column("aircraft_type", JDBCType.VARCHAR);

        public final SqlColumn<String> registration = column("registration", JDBCType.VARCHAR);

        public final SqlColumn<Long> linkedId = column("linked_id", JDBCType.BIGINT);

        public final SqlColumn<Long> linkedAodbId = column("linked_aodb_id", JDBCType.BIGINT);

        public final SqlColumn<String> linkedAirlineCode = column("linked_airline_code", JDBCType.VARCHAR);

        public final SqlColumn<String> linkedFlightNumber = column("linked_flight_number", JDBCType.VARCHAR);

        public final SqlColumn<String> terminal = column("terminal", JDBCType.VARCHAR);

        public final SqlColumn<Short> maxPax = column("max_pax", JDBCType.SMALLINT);

        public final SqlColumn<Long> masterId = column("master_id", JDBCType.BIGINT);

        public final SqlColumn<Long> masterAodbId = column("master_aodb_id", JDBCType.BIGINT);

        public final SqlColumn<String> masterAirlineCode = column("master_airline_code", JDBCType.VARCHAR);

        public final SqlColumn<String> masterFlightNumber = column("master_flight_number", JDBCType.VARCHAR);

        public final SqlColumn<Date> eto = column("eto", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> ato = column("ato", JDBCType.TIMESTAMP);

        public final SqlColumn<String> currentStand = column("current_stand", JDBCType.VARCHAR);

        public final SqlColumn<Integer> paxAgent = column("pax_agent", JDBCType.BIGINT);

        public final SqlColumn<Date> cancel = column("cancel", JDBCType.TIMESTAMP);

        public final SqlColumn<String> remarks = column("remarks", JDBCType.VARCHAR);

        public final SqlColumn<Date> boardingOpen = column("boarding_open", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> lastCall = column("last_call", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> finalTime = column("final_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> approvedTime = column("approved_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> engineStart = column("engine_start", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> engineStartRequest = column("engine_start_request", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> fieldAgent = column("field_agent", JDBCType.BIGINT);

        public final SqlColumn<Integer> maintenanceAgent = column("maintenance_agent", JDBCType.BIGINT);

        public final SqlColumn<Short> vipCount = column("vip_count", JDBCType.SMALLINT);

        public final SqlColumn<Byte> vipFlag = column("vip_flag", JDBCType.TINYINT);

        public final SqlColumn<String> divAirport = column("div_airport", JDBCType.VARCHAR);

        public final SqlColumn<String> divDirection = column("div_direction", JDBCType.VARCHAR);

        public final SqlColumn<String> divReason = column("div_reason", JDBCType.VARCHAR);

        public final SqlColumn<String> retTyp = column("ret_typ", JDBCType.CHAR);

        public final SqlColumn<String> retRsn = column("ret_rsn", JDBCType.VARCHAR);

        public final SqlColumn<String> landingAbortIndicator = column("landing_abort_indicator", JDBCType.CHAR);

        public final SqlColumn<String> landingAbortReason = column("landing_abort_reason", JDBCType.VARCHAR);

        public final SqlColumn<Short> localBagCount = column("local_bag_count", JDBCType.SMALLINT);

        public final SqlColumn<BigDecimal> localBagWeight = column("local_bag_weight", JDBCType.DECIMAL);

        public final SqlColumn<Short> passengerCount = column("passenger_count", JDBCType.SMALLINT);

        public final SqlColumn<String> externalStatusCode = column("external_status_code", JDBCType.VARCHAR);

        public final SqlColumn<String> externalStatusComment = column("external_status_comment", JDBCType.VARCHAR);

        public final SqlColumn<String> statusCode = column("status_code", JDBCType.VARCHAR);

        public final SqlColumn<Date> pedt = column("pedt", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> neat = column("neat", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> padt = column("padt", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> naat = column("naat", JDBCType.TIMESTAMP);

        public final SqlColumn<String> rwyCode = column("rwy_code", JDBCType.VARCHAR);

        public final SqlColumn<Date> calOffblock = column("cal_offblock", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> calTakeoff = column("cal_takeoff", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> estimatedOffblock = column("estimated_offblock", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> internalEstimated = column("internal_estimated", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> portOpenTime = column("port_open_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> portCloseTime = column("port_close_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> targetOffblock = column("target_offblock", JDBCType.TIMESTAMP);

        public final SqlColumn<String> logicalRunway = column("logical_runway", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsFlight() {
            super("fms_flight");
        }
    }
}