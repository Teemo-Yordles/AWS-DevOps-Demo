package com.unisys.aos.view.viewentity.mapper.aodbinterface;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CoutmsgsDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Coutmsgs coutmsgs = new Coutmsgs();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> coutmsgsId = coutmsgs.coutmsgsId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> coutmsgsDateInserted = coutmsgs.coutmsgsDateInserted;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> coutmsgsDateSent = coutmsgs.coutmsgsDateSent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> routingid = coutmsgs.routingid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> coutmsgsTruefalsGroup = coutmsgs.coutmsgsTruefalsGroup;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> coutmsgsNoMessages = coutmsgs.coutmsgsNoMessages;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> coutmsgsGroupOrder = coutmsgs.coutmsgsGroupOrder;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Short> coutmsgsGroupId = coutmsgs.coutmsgsGroupId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> coutmsgsFinalGroupInd = coutmsgs.coutmsgsFinalGroupInd;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> coutmsgsAckReqd = coutmsgs.coutmsgsAckReqd;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> coutmsgsAckResendTimes = coutmsgs.coutmsgsAckResendTimes;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> coutmsgsAckDateRecv = coutmsgs.coutmsgsAckDateRecv;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> coutmsgsEncrypt = coutmsgs.coutmsgsEncrypt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> coutmsgsError = coutmsgs.coutmsgsError;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> coutmsgsClobMsg = coutmsgs.coutmsgsClobMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Coutmsgs extends SqlTable {
        public final SqlColumn<Long> coutmsgsId = column("COUTMSGS_ID", JDBCType.BIGINT);

        public final SqlColumn<Date> coutmsgsDateInserted = column("COUTMSGS_DATE_INSERTED", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> coutmsgsDateSent = column("COUTMSGS_DATE_SENT", JDBCType.TIMESTAMP);

        public final SqlColumn<String> routingid = column("ROUTINGID", JDBCType.VARCHAR);

        public final SqlColumn<Byte> coutmsgsTruefalsGroup = column("COUTMSGS_TRUEFALS_GROUP", JDBCType.TINYINT);

        public final SqlColumn<Byte> coutmsgsNoMessages = column("COUTMSGS_NO_MESSAGES", JDBCType.TINYINT);

        public final SqlColumn<Byte> coutmsgsGroupOrder = column("COUTMSGS_GROUP_ORDER", JDBCType.TINYINT);

        public final SqlColumn<Short> coutmsgsGroupId = column("COUTMSGS_GROUP_ID", JDBCType.SMALLINT);

        public final SqlColumn<Byte> coutmsgsFinalGroupInd = column("COUTMSGS_FINAL_GROUP_IND", JDBCType.TINYINT);

        public final SqlColumn<Byte> coutmsgsAckReqd = column("COUTMSGS_ACK_REQD", JDBCType.TINYINT);

        public final SqlColumn<Byte> coutmsgsAckResendTimes = column("COUTMSGS_ACK_RESEND_TIMES", JDBCType.TINYINT);

        public final SqlColumn<Date> coutmsgsAckDateRecv = column("COUTMSGS_ACK_DATE_RECV", JDBCType.TIMESTAMP);

        public final SqlColumn<Byte> coutmsgsEncrypt = column("COUTMSGS_ENCRYPT", JDBCType.TINYINT);

        public final SqlColumn<Byte> coutmsgsError = column("COUTMSGS_ERROR", JDBCType.TINYINT);

        public final SqlColumn<String> coutmsgsClobMsg = column("COUTMSGS_CLOB_MSG", JDBCType.LONGVARCHAR);

        public Coutmsgs() {
            super("coutmsgs");
        }
    }
}