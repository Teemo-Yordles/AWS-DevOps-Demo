package com.unisys.aos.view.viewentity.mapper.reference;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsRegistrationDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsRegistration rdmsRegistration = new RdmsRegistration();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsRegistration.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> registrationCode = rdmsRegistration.registrationCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> aircraftTypeCode = rdmsRegistration.aircraftTypeCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> organizationId = rdmsRegistration.organizationId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airlineCode = rdmsRegistration.airlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airlineSubcompanyCode = rdmsRegistration.airlineSubcompanyCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> maxPax = rdmsRegistration.maxPax;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> maxFreightWeight = rdmsRegistration.maxFreightWeight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> maxTakeoffWeight = rdmsRegistration.maxTakeoffWeight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsRegistration.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsRegistration.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsRegistration.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsRegistration.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsRegistration extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> registrationCode = column("registration_code", JDBCType.VARCHAR);

        public final SqlColumn<String> aircraftTypeCode = column("aircraft_type_code", JDBCType.CHAR);

        public final SqlColumn<Integer> organizationId = column("organization_id", JDBCType.INTEGER);

        public final SqlColumn<String> airlineCode = column("airline_code", JDBCType.VARCHAR);

        public final SqlColumn<String> airlineSubcompanyCode = column("airline_subcompany_code", JDBCType.VARCHAR);

        public final SqlColumn<Short> maxPax = column("max_pax", JDBCType.SMALLINT);

        public final SqlColumn<BigDecimal> maxFreightWeight = column("max_freight_weight", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> maxTakeoffWeight = column("max_takeoff_weight", JDBCType.DECIMAL);

        public final SqlColumn<Long> inspireTime = column("inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> expireTime = column("expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsRegistration() {
            super("rdms_registration");
        }
    }
}