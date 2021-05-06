package com.unisys.aos.view.viewentity.mapper.reference;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsAirlineDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsAirline rdmsAirline = new RdmsAirline();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsAirline.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> iataOperatorCode = rdmsAirline.iataOperatorCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> icaoOperatorCode = rdmsAirline.icaoOperatorCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsAirline.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsAirline.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> countryCode = rdmsAirline.countryCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> defaultTerminal = rdmsAirline.defaultTerminal;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> defaultTerminalMvin = rdmsAirline.defaultTerminalMvin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> defaultTerminalFlin = rdmsAirline.defaultTerminalFlin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> operatorGroups = rdmsAirline.operatorGroups;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airlineCategory = rdmsAirline.airlineCategory;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsAirline.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsAirline.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsAirline.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsAirline.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsAirline extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> iataOperatorCode = column("iata_operator_code", JDBCType.VARCHAR);

        public final SqlColumn<String> icaoOperatorCode = column("icao_operator_code", JDBCType.CHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<String> countryCode = column("country_code", JDBCType.VARCHAR);

        public final SqlColumn<String> defaultTerminal = column("default_terminal", JDBCType.VARCHAR);

        public final SqlColumn<String> defaultTerminalMvin = column("default_terminal_mvin", JDBCType.CHAR);

        public final SqlColumn<String> defaultTerminalFlin = column("default_terminal_flin", JDBCType.CHAR);

        public final SqlColumn<String> operatorGroups = column("operator_groups", JDBCType.VARCHAR);

        public final SqlColumn<String> airlineCategory = column("airline_category", JDBCType.CHAR);

        public final SqlColumn<Long> inspireTime = column("inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> expireTime = column("expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsAirline() {
            super("rdms_airline");
        }
    }
}