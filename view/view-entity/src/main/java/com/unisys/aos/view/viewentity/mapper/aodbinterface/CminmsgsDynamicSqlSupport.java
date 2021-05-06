package com.unisys.aos.view.viewentity.mapper.aodbinterface;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CminmsgsDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Cminmsgs cminmsgs = new Cminmsgs();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> cminmsgsId = cminmsgs.cminmsgsId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> cminmsgsDateReceived = cminmsgs.cminmsgsDateReceived;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> cminmsgsDateProcessed = cminmsgs.cminmsgsDateProcessed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cminmsgsSubsystemName = cminmsgs.cminmsgsSubsystemName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> cminmsgsSubsystemSequence = cminmsgs.cminmsgsSubsystemSequence;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> cminmsgsSubsystemDateSent = cminmsgs.cminmsgsSubsystemDateSent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cminmsgsSubsystemSubtype = cminmsgs.cminmsgsSubsystemSubtype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cminmsgsSubsystemType = cminmsgs.cminmsgsSubsystemType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cminmsgsStatus = cminmsgs.cminmsgsStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cminmsgsClobMsg = cminmsgs.cminmsgsClobMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Cminmsgs extends SqlTable {
        public final SqlColumn<Long> cminmsgsId = column("CMINMSGS_ID", JDBCType.BIGINT);

        public final SqlColumn<Date> cminmsgsDateReceived = column("CMINMSGS_DATE_RECEIVED", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> cminmsgsDateProcessed = column("CMINMSGS_DATE_PROCESSED", JDBCType.TIMESTAMP);

        public final SqlColumn<String> cminmsgsSubsystemName = column("CMINMSGS_SUBSYSTEM_NAME", JDBCType.VARCHAR);

        public final SqlColumn<Integer> cminmsgsSubsystemSequence = column("CMINMSGS_SUBSYSTEM_SEQUENCE", JDBCType.INTEGER);

        public final SqlColumn<Date> cminmsgsSubsystemDateSent = column("CMINMSGS_SUBSYSTEM_DATE_SENT", JDBCType.TIMESTAMP);

        public final SqlColumn<String> cminmsgsSubsystemSubtype = column("CMINMSGS_SUBSYSTEM_SUBTYPE", JDBCType.VARCHAR);

        public final SqlColumn<String> cminmsgsSubsystemType = column("CMINMSGS_SUBSYSTEM_TYPE", JDBCType.VARCHAR);

        public final SqlColumn<String> cminmsgsStatus = column("CMINMSGS_STATUS", JDBCType.VARCHAR);

        public final SqlColumn<String> cminmsgsClobMsg = column("CMINMSGS_CLOB_MSG", JDBCType.LONGVARCHAR);

        public Cminmsgs() {
            super("cminmsgs");
        }
    }
}