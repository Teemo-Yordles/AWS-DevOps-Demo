package com.unisys.aos.view.viewentity.entity.aodbinterface;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class Cminmsgs implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long cminmsgsId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date cminmsgsDateReceived;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date cminmsgsDateProcessed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String cminmsgsSubsystemName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer cminmsgsSubsystemSequence;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date cminmsgsSubsystemDateSent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String cminmsgsSubsystemSubtype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String cminmsgsSubsystemType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String cminmsgsStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String cminmsgsClobMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getCminmsgsId() {
        return cminmsgsId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsId(Long cminmsgsId) {
        this.cminmsgsId = cminmsgsId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCminmsgsDateReceived() {
        return cminmsgsDateReceived;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsDateReceived(Date cminmsgsDateReceived) {
        this.cminmsgsDateReceived = cminmsgsDateReceived;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCminmsgsDateProcessed() {
        return cminmsgsDateProcessed;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsDateProcessed(Date cminmsgsDateProcessed) {
        this.cminmsgsDateProcessed = cminmsgsDateProcessed;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCminmsgsSubsystemName() {
        return cminmsgsSubsystemName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsSubsystemName(String cminmsgsSubsystemName) {
        this.cminmsgsSubsystemName = cminmsgsSubsystemName == null ? null : cminmsgsSubsystemName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getCminmsgsSubsystemSequence() {
        return cminmsgsSubsystemSequence;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsSubsystemSequence(Integer cminmsgsSubsystemSequence) {
        this.cminmsgsSubsystemSequence = cminmsgsSubsystemSequence;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCminmsgsSubsystemDateSent() {
        return cminmsgsSubsystemDateSent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsSubsystemDateSent(Date cminmsgsSubsystemDateSent) {
        this.cminmsgsSubsystemDateSent = cminmsgsSubsystemDateSent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCminmsgsSubsystemSubtype() {
        return cminmsgsSubsystemSubtype;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsSubsystemSubtype(String cminmsgsSubsystemSubtype) {
        this.cminmsgsSubsystemSubtype = cminmsgsSubsystemSubtype == null ? null : cminmsgsSubsystemSubtype.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCminmsgsSubsystemType() {
        return cminmsgsSubsystemType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsSubsystemType(String cminmsgsSubsystemType) {
        this.cminmsgsSubsystemType = cminmsgsSubsystemType == null ? null : cminmsgsSubsystemType.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCminmsgsStatus() {
        return cminmsgsStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsStatus(String cminmsgsStatus) {
        this.cminmsgsStatus = cminmsgsStatus == null ? null : cminmsgsStatus.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCminmsgsClobMsg() {
        return cminmsgsClobMsg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCminmsgsClobMsg(String cminmsgsClobMsg) {
        this.cminmsgsClobMsg = cminmsgsClobMsg == null ? null : cminmsgsClobMsg.trim();
    }

    @Override
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cminmsgsId=").append(cminmsgsId);
        sb.append(", cminmsgsDateReceived=").append(cminmsgsDateReceived);
        sb.append(", cminmsgsDateProcessed=").append(cminmsgsDateProcessed);
        sb.append(", cminmsgsSubsystemName=").append(cminmsgsSubsystemName);
        sb.append(", cminmsgsSubsystemSequence=").append(cminmsgsSubsystemSequence);
        sb.append(", cminmsgsSubsystemDateSent=").append(cminmsgsSubsystemDateSent);
        sb.append(", cminmsgsSubsystemSubtype=").append(cminmsgsSubsystemSubtype);
        sb.append(", cminmsgsSubsystemType=").append(cminmsgsSubsystemType);
        sb.append(", cminmsgsStatus=").append(cminmsgsStatus);
        sb.append(", cminmsgsClobMsg=").append(cminmsgsClobMsg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}