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

/**
 * The <b>RdmsGate</b> class is a POJO entity that represents the Gate Code
 * for reference data in message.
 * It is automatically generated by MyBatisGenerator.
 * The reference data in message definition can be referred to the section 3 of
 * the document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public class RdmsGate implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
    @Transient
    public Date inspire;
    @Transient
    public Date expire;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String gateCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String localDescription;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String gateCategory;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String terminalCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String pierCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RdmsGate)) return false;
        RdmsGate rdmsGate = (RdmsGate) o;
        return Objects.equals(getGateCode(), rdmsGate.getGateCode()) &&
                Objects.equals(getDescription(), rdmsGate.getDescription()) &&
                Objects.equals(getLocalDescription(), rdmsGate.getLocalDescription()) &&
                Objects.equals(getGateCategory(), rdmsGate.getGateCategory()) &&
                Objects.equals(getTerminalCode(), rdmsGate.getTerminalCode()) &&
                Objects.equals(getPierCode(), rdmsGate.getPierCode()) &&
                Objects.equals(getInspireTime(), rdmsGate.getInspireTime()) &&
                Objects.equals(getExpireTime(), rdmsGate.getExpireTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGateCode(), getDescription(), getLocalDescription(), getGateCategory(), getTerminalCode(), getPierCode(), getInspireTime(), getExpireTime());
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
    public String getGateCode() {
        return gateCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setGateCode(String gateCode) {
        this.gateCode = gateCode == null ? null : gateCode.trim();
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
    public String getGateCategory() {
        return gateCategory;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setGateCategory(String gateCategory) {
        this.gateCategory = gateCategory == null ? null : gateCategory.trim();
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
    public String getPierCode() {
        return pierCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPierCode(String pierCode) {
        this.pierCode = pierCode == null ? null : pierCode.trim();
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
        sb.append(", gateCode=").append(gateCode);
        sb.append(", description=").append(description);
        sb.append(", localDescription=").append(localDescription);
        sb.append(", gateCategory=").append(gateCategory);
        sb.append(", terminalCode=").append(terminalCode);
        sb.append(", pierCode=").append(pierCode);
        sb.append(", inspireTime=").append(inspireTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


    /***
     * Get the identity of entity.
     * @return identity of entity
     */
    @JsonIgnore
    public String getIdentity() {
        return gateCode + inspireTime + expireTime;
    }
}