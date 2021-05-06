package com.unisys.aos.view.viewentity.entity.reference;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * The <b>RdmsRegionCountryAssociation</b> class is a POJO entity that automatically generated
 * by MyBatisGenerator through the association table of Region and Country.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public class RdmsRegionCountryAssociation implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String regionCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String countryCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long countryInspireTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long countryExpireTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long regionInspireTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long regionExpireTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRegionCode() {
        return regionCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
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
    public Long getCountryInspireTime() {
        return countryInspireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCountryInspireTime(Long countryInspireTime) {
        this.countryInspireTime = countryInspireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getCountryExpireTime() {
        return countryExpireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCountryExpireTime(Long countryExpireTime) {
        this.countryExpireTime = countryExpireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getRegionInspireTime() {
        return regionInspireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRegionInspireTime(Long regionInspireTime) {
        this.regionInspireTime = regionInspireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getRegionExpireTime() {
        return regionExpireTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRegionExpireTime(Long regionExpireTime) {
        this.regionExpireTime = regionExpireTime;
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
        sb.append(", regionCode=").append(regionCode);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", countryInspireTime=").append(countryInspireTime);
        sb.append(", countryExpireTime=").append(countryExpireTime);
        sb.append(", regionInspireTime=").append(regionInspireTime);
        sb.append(", regionExpireTime=").append(regionExpireTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RdmsRegionCountryAssociation)) return false;
        RdmsRegionCountryAssociation that = (RdmsRegionCountryAssociation) o;
        return Objects.equals(getRegionCode(), that.getRegionCode()) &&
                Objects.equals(getCountryCode(), that.getCountryCode()) &&
                Objects.equals(getCountryInspireTime(), that.getCountryInspireTime()) &&
                Objects.equals(getCountryExpireTime(), that.getCountryExpireTime()) &&
                Objects.equals(getRegionInspireTime(), that.getRegionInspireTime()) &&
                Objects.equals(getRegionExpireTime(), that.getRegionExpireTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegionCode(), getCountryCode(), getCountryInspireTime(), getCountryExpireTime(), getRegionInspireTime(), getRegionExpireTime());
    }
}