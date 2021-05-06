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

public class RdmsAirport implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
    @Transient
    public Date inspire;
    @Transient
    public Date expire;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String iataCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String icaoCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String localDescription;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long distance;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String countryCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String cityCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String airportCategory;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String haulCategory;
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


    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getIataCode() {
        return iataCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode == null ? null : iataCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getIcaoCode() {
        return icaoCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode == null ? null : icaoCode.trim();
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
    public Long getDistance() {
        return distance;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDistance(Long distance) {
        this.distance = distance;
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
    public String getCityCode() {
        return cityCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAirportCategory() {
        return airportCategory;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAirportCategory(String airportCategory) {
        this.airportCategory = airportCategory == null ? null : airportCategory.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getHaulCategory() {
        return haulCategory;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setHaulCategory(String haulCategory) {
        this.haulCategory = haulCategory == null ? null : haulCategory.trim();
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

    @Override
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", iataCode=").append(iataCode);
        sb.append(", icaoCode=").append(icaoCode);
        sb.append(", description=").append(description);
        sb.append(", localDescription=").append(localDescription);
        sb.append(", distance=").append(distance);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", airportCategory=").append(airportCategory);
        sb.append(", haulCategory=").append(haulCategory);
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
        if (!(o instanceof RdmsAirport)) return false;
        RdmsAirport airport = (RdmsAirport) o;
        return Objects.equals(getIataCode(), airport.getIataCode()) &&
                Objects.equals(getIcaoCode(), airport.getIcaoCode()) &&
                Objects.equals(getDescription(), airport.getDescription()) &&
                Objects.equals(getLocalDescription(), airport.getLocalDescription()) &&
                Objects.equals(getDistance(), airport.getDistance()) &&
                Objects.equals(getCountryCode(), airport.getCountryCode()) &&
                Objects.equals(getCityCode(), airport.getCityCode()) &&
                Objects.equals(getAirportCategory(), airport.getAirportCategory()) &&
                Objects.equals(getHaulCategory(), airport.getHaulCategory()) &&
                Objects.equals(getAbbreviation1(), airport.getAbbreviation1()) &&
                Objects.equals(getAbbreviation2(), airport.getAbbreviation2()) &&
                Objects.equals(getAbbreviation3(), airport.getAbbreviation3()) &&
                Objects.equals(getAbbreviation4(), airport.getAbbreviation4()) &&
                Objects.equals(getAbbreviation5(), airport.getAbbreviation5()) &&
                Objects.equals(getAbbreviation6(), airport.getAbbreviation6()) &&
                Objects.equals(getInspireTime(), airport.getInspireTime()) &&
                Objects.equals(getExpireTime(), airport.getExpireTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIataCode(), getIcaoCode(), getDescription(), getLocalDescription(), getDistance(), getCountryCode(), getCityCode(), getAirportCategory(), getHaulCategory(), getAbbreviation1(), getAbbreviation2(), getAbbreviation3(), getAbbreviation4(), getAbbreviation5(), getAbbreviation6(), getInspireTime(), getExpireTime());
    }


    /***
     * Get the identity of entity.
     * @return identity of entity
     */
    @JsonIgnore
    public String getIdentity() {
        return iataCode + inspireTime + expireTime;
    }
}