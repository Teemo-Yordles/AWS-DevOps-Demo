package com.unisys.aos.view.viewentity.mapper.reference;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RdmsCheckinDeskDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RdmsCheckinDesk rdmsCheckinDesk = new RdmsCheckinDesk();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rdmsCheckinDesk.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> checkinDeskCode = rdmsCheckinDesk.checkinDeskCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = rdmsCheckinDesk.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> localDescription = rdmsCheckinDesk.localDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> checkinDeskCategory = rdmsCheckinDesk.checkinDeskCategory;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> terminalCode = rdmsCheckinDesk.terminalCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> transfer = rdmsCheckinDesk.transfer;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> inspireTime = rdmsCheckinDesk.inspireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> expireTime = rdmsCheckinDesk.expireTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = rdmsCheckinDesk.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = rdmsCheckinDesk.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RdmsCheckinDesk extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> checkinDeskCode = column("checkin_desk_code", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> localDescription = column("local_description", JDBCType.VARCHAR);

        public final SqlColumn<String> checkinDeskCategory = column("checkin_desk_category", JDBCType.CHAR);

        public final SqlColumn<String> terminalCode = column("terminal_code", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> transfer = column("transfer", JDBCType.BIT);

        public final SqlColumn<Long> inspireTime = column("inspire_time", JDBCType.BIGINT);

        public final SqlColumn<Long> expireTime = column("expire_time", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public RdmsCheckinDesk() {
            super("rdms_checkin_desk");
        }
    }
}