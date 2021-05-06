package com.unisys.aos.view.viewentity.entity.reference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unisys.aos.view.common.util.ThreadLocalDateUtil;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import org.springframework.data.annotation.Transient;

import javax.annotation.Generated;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class RdmsVipPersonnal implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
    @Transient
    public Date inspire;
    @Transient
    public Date expire;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipPersonCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipPersonNumber;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipPersonFirstname;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipPersonLastname;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipPersonPosition;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipPersonRanking;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String localDescription;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipContactPerson;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipContactTelephoneNumber;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipContactMobileNumber;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipPersonWorkUnit;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vipPersonRemarks;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long inspireTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long expireTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;
    @Resource
    @Transient
    private ReferenceProperties referenceProperties;

    public Date getInspire() {
        if (null == inspireTime) {
            inspire = ThreadLocalDateUtil.timestampToDate(referenceProperties.getDefaultInspireTime());
        } else {
            inspire = ThreadLocalDateUtil.timestampToDate(inspireTime);
        }
        return inspire;
    }

    public Date getExpire() {
        if (null == expireTime) {
            expire = ThreadLocalDateUtil.timestampToDate(referenceProperties.getDefaultExpireTime());
        } else {
            expire = ThreadLocalDateUtil.timestampToDate(expireTime);
        }
        return expire;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipPersonCode() {
        return vipPersonCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipPersonCode(String vipPersonCode) {
        this.vipPersonCode = vipPersonCode == null ? null : vipPersonCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipPersonNumber() {
        return vipPersonNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipPersonNumber(String vipPersonNumber) {
        this.vipPersonNumber = vipPersonNumber == null ? null : vipPersonNumber.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipPersonFirstname() {
        return vipPersonFirstname;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipPersonFirstname(String vipPersonFirstname) {
        this.vipPersonFirstname = vipPersonFirstname == null ? null : vipPersonFirstname.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipPersonLastname() {
        return vipPersonLastname;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipPersonLastname(String vipPersonLastname) {
        this.vipPersonLastname = vipPersonLastname == null ? null : vipPersonLastname.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipPersonPosition() {
        return vipPersonPosition;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipPersonPosition(String vipPersonPosition) {
        this.vipPersonPosition = vipPersonPosition == null ? null : vipPersonPosition.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipPersonRanking() {
        return vipPersonRanking;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipPersonRanking(String vipPersonRanking) {
        this.vipPersonRanking = vipPersonRanking == null ? null : vipPersonRanking.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDescription() {
        return description;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLocalDescription() {
        return localDescription;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLocalDescription(String localDescription) {
        this.localDescription = localDescription == null ? null : localDescription.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipContactPerson() {
        return vipContactPerson;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipContactPerson(String vipContactPerson) {
        this.vipContactPerson = vipContactPerson == null ? null : vipContactPerson.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipContactTelephoneNumber() {
        return vipContactTelephoneNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipContactTelephoneNumber(String vipContactTelephoneNumber) {
        this.vipContactTelephoneNumber = vipContactTelephoneNumber == null ? null : vipContactTelephoneNumber.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipContactMobileNumber() {
        return vipContactMobileNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipContactMobileNumber(String vipContactMobileNumber) {
        this.vipContactMobileNumber = vipContactMobileNumber == null ? null : vipContactMobileNumber.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipPersonWorkUnit() {
        return vipPersonWorkUnit;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipPersonWorkUnit(String vipPersonWorkUnit) {
        this.vipPersonWorkUnit = vipPersonWorkUnit == null ? null : vipPersonWorkUnit.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVipPersonRemarks() {
        return vipPersonRemarks;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipPersonRemarks(String vipPersonRemarks) {
        this.vipPersonRemarks = vipPersonRemarks == null ? null : vipPersonRemarks.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getInspireTime() {
        return inspireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setInspireTime(Long inspireTime) {
        this.inspireTime = inspireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getExpireTime() {
        return expireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getUpdateTime() {
        return updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", vipPersonCode=").append(vipPersonCode);
        sb.append(", vipPersonNumber=").append(vipPersonNumber);
        sb.append(", vipPersonFirstname=").append(vipPersonFirstname);
        sb.append(", vipPersonLastname=").append(vipPersonLastname);
        sb.append(", vipPersonPosition=").append(vipPersonPosition);
        sb.append(", vipPersonRanking=").append(vipPersonRanking);
        sb.append(", description=").append(description);
        sb.append(", localDescription=").append(localDescription);
        sb.append(", vipContactPerson=").append(vipContactPerson);
        sb.append(", vipContactTelephoneNumber=").append(vipContactTelephoneNumber);
        sb.append(", vipContactMobileNumber=").append(vipContactMobileNumber);
        sb.append(", vipPersonWorkUnit=").append(vipPersonWorkUnit);
        sb.append(", vipPersonRemarks=").append(vipPersonRemarks);
        sb.append(", inspireTime=").append(inspireTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RdmsVipPersonnal)) return false;
        RdmsVipPersonnal that = (RdmsVipPersonnal) o;
        return Objects.equals(getVipPersonCode(), that.getVipPersonCode()) &&
                Objects.equals(getVipPersonNumber(), that.getVipPersonNumber()) &&
                Objects.equals(getVipPersonFirstname(), that.getVipPersonFirstname()) &&
                Objects.equals(getVipPersonLastname(), that.getVipPersonLastname()) &&
                Objects.equals(getVipPersonPosition(), that.getVipPersonPosition()) &&
                Objects.equals(getVipPersonRanking(), that.getVipPersonRanking()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getLocalDescription(), that.getLocalDescription()) &&
                Objects.equals(getVipContactPerson(), that.getVipContactPerson()) &&
                Objects.equals(getVipContactTelephoneNumber(), that.getVipContactTelephoneNumber()) &&
                Objects.equals(getVipContactMobileNumber(), that.getVipContactMobileNumber()) &&
                Objects.equals(getVipPersonWorkUnit(), that.getVipPersonWorkUnit()) &&
                Objects.equals(getVipPersonRemarks(), that.getVipPersonRemarks()) &&
                Objects.equals(getInspireTime(), that.getInspireTime()) &&
                Objects.equals(getExpireTime(), that.getExpireTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVipPersonCode(), getVipPersonNumber(), getVipPersonFirstname(), getVipPersonLastname(), getVipPersonPosition(), getVipPersonRanking(), getDescription(), getLocalDescription(), getVipContactPerson(), getVipContactTelephoneNumber(), getVipContactMobileNumber(), getVipPersonWorkUnit(), getVipPersonRemarks(), getInspireTime(), getExpireTime());
    }


    /***
     * Get the identity of entity.
     * @return identity of entity
     */
    @JsonIgnore
    public String getIdentity() {
        return vipPersonCode + inspireTime + expireTime;
    }
}