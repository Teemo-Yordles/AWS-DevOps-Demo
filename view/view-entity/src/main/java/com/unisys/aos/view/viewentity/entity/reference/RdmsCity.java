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

public class RdmsCity implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
    @Transient
    public Date inspire;
    @Transient
    public Date expire;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String iataCityCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String icaoCityCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String localDescription;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String countryCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String abbreviation1;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String abbreviation2;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String abbreviation3;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String abbreviation4;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String abbreviation5;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String abbreviation6;
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
    public String getIataCityCode() {
        return iataCityCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIataCityCode(String iataCityCode) {
        this.iataCityCode = iataCityCode == null ? null : iataCityCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getIcaoCityCode() {
        return icaoCityCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIcaoCityCode(String icaoCityCode) {
        this.icaoCityCode = icaoCityCode == null ? null : icaoCityCode.trim();
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
    public String getCountryCode() {
        return countryCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAbbreviation1() {
        return abbreviation1;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAbbreviation1(String abbreviation1) {
        this.abbreviation1 = abbreviation1 == null ? null : abbreviation1.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAbbreviation2() {
        return abbreviation2;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAbbreviation2(String abbreviation2) {
        this.abbreviation2 = abbreviation2 == null ? null : abbreviation2.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAbbreviation3() {
        return abbreviation3;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAbbreviation3(String abbreviation3) {
        this.abbreviation3 = abbreviation3 == null ? null : abbreviation3.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAbbreviation4() {
        return abbreviation4;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAbbreviation4(String abbreviation4) {
        this.abbreviation4 = abbreviation4 == null ? null : abbreviation4.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAbbreviation5() {
        return abbreviation5;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAbbreviation5(String abbreviation5) {
        this.abbreviation5 = abbreviation5 == null ? null : abbreviation5.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAbbreviation6() {
        return abbreviation6;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAbbreviation6(String abbreviation6) {
        this.abbreviation6 = abbreviation6 == null ? null : abbreviation6.trim();
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
        sb.append(", iataCityCode=").append(iataCityCode);
        sb.append(", icaoCityCode=").append(icaoCityCode);
        sb.append(", description=").append(description);
        sb.append(", localDescription=").append(localDescription);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", abbreviation1=").append(abbreviation1);
        sb.append(", abbreviation2=").append(abbreviation2);
        sb.append(", abbreviation3=").append(abbreviation3);
        sb.append(", abbreviation4=").append(abbreviation4);
        sb.append(", abbreviation5=").append(abbreviation5);
        sb.append(", abbreviation6=").append(abbreviation6);
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
        if (!(o instanceof RdmsCity)) return false;
        RdmsCity rdmsCity = (RdmsCity) o;
        return Objects.equals(getIataCityCode(), rdmsCity.getIataCityCode()) &&
                Objects.equals(getIcaoCityCode(), rdmsCity.getIcaoCityCode()) &&
                Objects.equals(getDescription(), rdmsCity.getDescription()) &&
                Objects.equals(getLocalDescription(), rdmsCity.getLocalDescription()) &&
                Objects.equals(getCountryCode(), rdmsCity.getCountryCode()) &&
                Objects.equals(getAbbreviation1(), rdmsCity.getAbbreviation1()) &&
                Objects.equals(getAbbreviation2(), rdmsCity.getAbbreviation2()) &&
                Objects.equals(getAbbreviation3(), rdmsCity.getAbbreviation3()) &&
                Objects.equals(getAbbreviation4(), rdmsCity.getAbbreviation4()) &&
                Objects.equals(getAbbreviation5(), rdmsCity.getAbbreviation5()) &&
                Objects.equals(getAbbreviation6(), rdmsCity.getAbbreviation6()) &&
                Objects.equals(getInspireTime(), rdmsCity.getInspireTime()) &&
                Objects.equals(getExpireTime(), rdmsCity.getExpireTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIataCityCode(), getIcaoCityCode(), getDescription(), getLocalDescription(), getCountryCode(), getAbbreviation1(), getAbbreviation2(), getAbbreviation3(), getAbbreviation4(), getAbbreviation5(), getAbbreviation6(), getInspireTime(), getExpireTime());
    }


    /***
     * Get the identity of entity.
     * @return identity of entity
     */
    @JsonIgnore
    public String getIdentity() {
        return iataCityCode + inspireTime + expireTime;
    }
}