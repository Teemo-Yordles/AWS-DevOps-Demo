package com.unisys.aos.view.viewentity.entity.aodbinterface;

import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.Date;

@Repository
public class Coutmsgs implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long coutmsgsId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date coutmsgsDateInserted;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date coutmsgsDateSent;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String routingid;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte coutmsgsTruefalsGroup;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte coutmsgsNoMessages;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte coutmsgsGroupOrder;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Short coutmsgsGroupId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte coutmsgsFinalGroupInd;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte coutmsgsAckReqd;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte coutmsgsAckResendTimes;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date coutmsgsAckDateRecv;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte coutmsgsEncrypt;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte coutmsgsError;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String coutmsgsClobMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getCoutmsgsId() {
        return coutmsgsId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsId(Long coutmsgsId) {
        this.coutmsgsId = coutmsgsId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCoutmsgsDateInserted() {
        return coutmsgsDateInserted;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsDateInserted(Date coutmsgsDateInserted) {
        this.coutmsgsDateInserted = coutmsgsDateInserted;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCoutmsgsDateSent() {
        return coutmsgsDateSent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsDateSent(Date coutmsgsDateSent) {
        this.coutmsgsDateSent = coutmsgsDateSent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRoutingid() {
        return routingid;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRoutingid(String routingid) {
        this.routingid = routingid == null ? null : routingid.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getCoutmsgsTruefalsGroup() {
        return coutmsgsTruefalsGroup;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsTruefalsGroup(Byte coutmsgsTruefalsGroup) {
        this.coutmsgsTruefalsGroup = coutmsgsTruefalsGroup;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getCoutmsgsNoMessages() {
        return coutmsgsNoMessages;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsNoMessages(Byte coutmsgsNoMessages) {
        this.coutmsgsNoMessages = coutmsgsNoMessages;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getCoutmsgsGroupOrder() {
        return coutmsgsGroupOrder;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsGroupOrder(Byte coutmsgsGroupOrder) {
        this.coutmsgsGroupOrder = coutmsgsGroupOrder;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Short getCoutmsgsGroupId() {
        return coutmsgsGroupId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsGroupId(Short coutmsgsGroupId) {
        this.coutmsgsGroupId = coutmsgsGroupId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getCoutmsgsFinalGroupInd() {
        return coutmsgsFinalGroupInd;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsFinalGroupInd(Byte coutmsgsFinalGroupInd) {
        this.coutmsgsFinalGroupInd = coutmsgsFinalGroupInd;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getCoutmsgsAckReqd() {
        return coutmsgsAckReqd;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsAckReqd(Byte coutmsgsAckReqd) {
        this.coutmsgsAckReqd = coutmsgsAckReqd;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getCoutmsgsAckResendTimes() {
        return coutmsgsAckResendTimes;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsAckResendTimes(Byte coutmsgsAckResendTimes) {
        this.coutmsgsAckResendTimes = coutmsgsAckResendTimes;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCoutmsgsAckDateRecv() {
        return coutmsgsAckDateRecv;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsAckDateRecv(Date coutmsgsAckDateRecv) {
        this.coutmsgsAckDateRecv = coutmsgsAckDateRecv;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getCoutmsgsEncrypt() {
        return coutmsgsEncrypt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsEncrypt(Byte coutmsgsEncrypt) {
        this.coutmsgsEncrypt = coutmsgsEncrypt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getCoutmsgsError() {
        return coutmsgsError;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsError(Byte coutmsgsError) {
        this.coutmsgsError = coutmsgsError;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCoutmsgsClobMsg() {
        return coutmsgsClobMsg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCoutmsgsClobMsg(String coutmsgsClobMsg) {
        this.coutmsgsClobMsg = coutmsgsClobMsg == null ? null : coutmsgsClobMsg.trim();
    }

    @Override
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", coutmsgsId=").append(coutmsgsId);
        sb.append(", coutmsgsDateInserted=").append(coutmsgsDateInserted);
        sb.append(", coutmsgsDateSent=").append(coutmsgsDateSent);
        sb.append(", routingid=").append(routingid);
        sb.append(", coutmsgsTruefalsGroup=").append(coutmsgsTruefalsGroup);
        sb.append(", coutmsgsNoMessages=").append(coutmsgsNoMessages);
        sb.append(", coutmsgsGroupOrder=").append(coutmsgsGroupOrder);
        sb.append(", coutmsgsGroupId=").append(coutmsgsGroupId);
        sb.append(", coutmsgsFinalGroupInd=").append(coutmsgsFinalGroupInd);
        sb.append(", coutmsgsAckReqd=").append(coutmsgsAckReqd);
        sb.append(", coutmsgsAckResendTimes=").append(coutmsgsAckResendTimes);
        sb.append(", coutmsgsAckDateRecv=").append(coutmsgsAckDateRecv);
        sb.append(", coutmsgsEncrypt=").append(coutmsgsEncrypt);
        sb.append(", coutmsgsError=").append(coutmsgsError);
        sb.append(", coutmsgsClobMsg=").append(coutmsgsClobMsg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}