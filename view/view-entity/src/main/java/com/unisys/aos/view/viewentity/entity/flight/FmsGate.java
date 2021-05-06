package com.unisys.aos.view.viewentity.entity.flight;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.annotation.Generated;

public class FmsGate implements Serializable, AdditionFlightInfo, Comparable<FmsGate>  {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte sequenceNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String gateCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date plannedStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date plannedEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date actualStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date actualEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String gateIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;

    /**
     * Based on specific flight additional information characteristic,
     * match two AdditionFlightInfo entities, see if they are matching.
     *
     * @param info - an entity to compare with.
     * @return - true if matched, false if not match.
     */
    @Override
    public boolean matchFlightInfoRecord(AdditionFlightInfo info) {
        if (null == info) {
            return false;
        }
        if (!(info instanceof FmsGate)) {
            return false;
        }
        FmsGate record = (FmsGate) info;
        // flightid, sequence must be equal to detect match record.
        if (!flightId.equals(record.getFlightId())) {
            return false;
        }
        if (null == sequenceNumber) {
            return null == record.getSequenceNumber();
        } else {
            return sequenceNumber.equals(record.getSequenceNumber());
        }
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @JsonIgnoreProperties(ignoreUnknown = true)
    public String obtainCode() {
        return gateCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getFlightId() {
        return flightId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getSequenceNumber() {
        return sequenceNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSequenceNumber(Byte sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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
    public Date getPlannedStartTime() {
        return plannedStartTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPlannedStartTime(Date plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getPlannedEndTime() {
        return plannedEndTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPlannedEndTime(Date plannedEndTime) {
        this.plannedEndTime = plannedEndTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getActualStartTime() {
        return actualStartTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getActualEndTime() {
        return actualEndTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getGateIndicator() {
        return gateIndicator;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setGateIndicator(String gateIndicator) {
        this.gateIndicator = gateIndicator == null ? null : gateIndicator.trim();
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
        sb.append(", flightId=").append(flightId);
        sb.append(", sequenceNumber=").append(sequenceNumber);
        sb.append(", gateCode=").append(gateCode);
        sb.append(", plannedStartTime=").append(plannedStartTime);
        sb.append(", plannedEndTime=").append(plannedEndTime);
        sb.append(", actualStartTime=").append(actualStartTime);
        sb.append(", actualEndTime=").append(actualEndTime);
        sb.append(", gateIndicator=").append(gateIndicator);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * this equal method doesn't check for create_time and update_time properties.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FmsGate)) return false;
        FmsGate that = (FmsGate) o;
        return Objects.equals(flightId, that.flightId) &&
                Objects.equals(sequenceNumber, that.sequenceNumber) &&
                Objects.equals(gateCode, that.gateCode) &&
                Objects.equals(plannedStartTime, that.plannedStartTime) &&
                Objects.equals(plannedEndTime, that.plannedEndTime) &&
                Objects.equals(actualStartTime, that.actualStartTime) &&
                Objects.equals(actualEndTime, that.actualEndTime) &&
                Objects.equals(gateIndicator, that.gateIndicator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, sequenceNumber, gateCode, plannedStartTime, plannedEndTime, actualStartTime, actualEndTime, gateIndicator);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(FmsGate o) {
        return this.getSequenceNumber() - o.getSequenceNumber();
    }
}