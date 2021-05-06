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

public class RdmsChute implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
    @Transient
    public Date inspire;
    @Transient
    public Date expire;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String chuteCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String localDescription;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String terminalCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String chuteCategory;
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
    public String getChuteCode() {
        return chuteCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setChuteCode(String chuteCode) {
        this.chuteCode = chuteCode == null ? null : chuteCode.trim();
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
    public String getTerminalCode() {
        return terminalCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode == null ? null : terminalCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getChuteCategory() {
        return chuteCategory;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setChuteCategory(String chuteCategory) {
        this.chuteCategory = chuteCategory == null ? null : chuteCategory.trim();
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
        sb.append(", chuteCode=").append(chuteCode);
        sb.append(", description=").append(description);
        sb.append(", localDescription=").append(localDescription);
        sb.append(", terminalCode=").append(terminalCode);
        sb.append(", chuteCategory=").append(chuteCategory);
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
        if (!(o instanceof RdmsChute)) return false;
        RdmsChute rdmsChute = (RdmsChute) o;
        return Objects.equals(getChuteCode(), rdmsChute.getChuteCode()) &&
                Objects.equals(getDescription(), rdmsChute.getDescription()) &&
                Objects.equals(getLocalDescription(), rdmsChute.getLocalDescription()) &&
                Objects.equals(getTerminalCode(), rdmsChute.getTerminalCode()) &&
                Objects.equals(getChuteCategory(), rdmsChute.getChuteCategory()) &&
                Objects.equals(getInspireTime(), rdmsChute.getInspireTime()) &&
                Objects.equals(getExpireTime(), rdmsChute.getExpireTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChuteCode(), getDescription(), getLocalDescription(), getTerminalCode(), getChuteCategory(), getInspireTime(), getExpireTime());
    }


    /***
     * Get the identity of entity.
     * @return identity of entity
     */
    @JsonIgnore
    public String getIdentity() {
        return chuteCode + inspireTime + expireTime;
    }
}