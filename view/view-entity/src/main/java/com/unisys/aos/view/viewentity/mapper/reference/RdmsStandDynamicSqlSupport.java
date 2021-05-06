package com.unisys.aos.view.viewentity.mapper.reference;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsStandDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsStand rdmsStand = new RdmsStand();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsStand.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> standCode = rdmsStand.standCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsStand.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsStand.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> allocateBigAircraft = rdmsStand.allocateBigAircraft;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> standGroupCode = rdmsStand.standGroupCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> width = rdmsStand.width;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> length = rdmsStand.length;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> terminalCode = rdmsStand.terminalCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> attacheMaxAirbridge = rdmsStand.attacheMaxAirbridge;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> internationalGate = rdmsStand.internationalGate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> domesticGate = rdmsStand.domesticGate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> standType = rdmsStand.standType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> fixedElectric = rdmsStand.fixedElectric;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsStand.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsStand.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsStand.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsStand.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsStand extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> standCode = column("stand_code", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<String> allocateBigAircraft = column("allocate_big_aircraft", JDBCType.CHAR);

        public final SqlColumn<String> standGroupCode = column("stand_group_code", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> width = column("width", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> length = column("length", JDBCType.DECIMAL);

        public final SqlColumn<String> terminalCode = column("terminal_code", JDBCType.VARCHAR);

        public final SqlColumn<Byte> attacheMaxAirbridge = column("attache_max_airbridge", JDBCType.TINYINT);

        public final SqlColumn<String> internationalGate = column("international_gate", JDBCType.VARCHAR);

        public final SqlColumn<String> domesticGate = column("domestic_gate", JDBCType.VARCHAR);

        public final SqlColumn<String> standType = column("stand_type", JDBCType.CHAR);

        public final SqlColumn<Boolean> fixedElectric = column("fixed_electric", JDBCType.BIT);

        public final SqlColumn<Long> inspireTime = column("inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> expireTime = column("expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsStand() {
            super("rdms_stand");
        }
    }
}