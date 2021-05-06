package com.unisys.aos.view.viewentity.mapper.reference;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsAirlineSubcompanyDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsAirlineSubcompany rdmsAirlineSubcompany = new RdmsAirlineSubcompany();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsAirlineSubcompany.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airlineIataOperatorCode = rdmsAirlineSubcompany.airlineIataOperatorCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> airlineSubcompanyCode = rdmsAirlineSubcompany.airlineSubcompanyCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsAirlineSubcompany.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsAirlineSubcompany.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> parentCompanyInspireTime = rdmsAirlineSubcompany.parentCompanyInspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> parentCompanyExpireTime = rdmsAirlineSubcompany.parentCompanyExpireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsAirlineSubcompany.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsAirlineSubcompany.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsAirlineSubcompany extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> airlineIataOperatorCode = column("airline_iata_operator_code", JDBCType.VARCHAR);

        public final SqlColumn<String> airlineSubcompanyCode = column("airline_subcompany_code", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<Long> parentCompanyInspireTime = column("parent_company_inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> parentCompanyExpireTime = column("parent_company_expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsAirlineSubcompany() {
            super("rdms_airline_subcompany");
        }
    }
}