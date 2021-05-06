package com.unisys.aos.view.viewentity.mapper.reference;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsVipServiceDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsVipService rdmsVipService = new RdmsVipService();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsVipService.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipServiceCode = rdmsVipService.vipServiceCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsVipService.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsVipService.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> vipServiceQtyortimCode = rdmsVipService.vipServiceQtyortimCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vipServiceUnitmeasCodeSingle = rdmsVipService.vipServiceUnitmeasCodeSingle;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsVipService.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsVipService.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsVipService.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsVipService.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsVipService extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> vipServiceCode = column("vip_service_code", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<Byte> vipServiceQtyortimCode = column("vip_service_qtyortim_code", JDBCType.TINYINT);

        public final SqlColumn<String> vipServiceUnitmeasCodeSingle = column("vip_service_unitmeas_code_single", JDBCType.VARCHAR);

        public final SqlColumn<Long> inspireTime = column("inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> expireTime = column("expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsVipService() {
            super("rdms_vip_service");
        }
    }
}