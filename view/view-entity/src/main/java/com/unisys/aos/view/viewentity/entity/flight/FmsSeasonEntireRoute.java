package com.unisys.aos.view.viewentity.entity.flight;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class FmsSeasonEntireRoute implements Serializable, Comparable<FmsSeasonEntireRoute> {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long flightId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte sequenceNumber;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String airportCode;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String arrivalTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String arrivalDayChange;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String departureTime;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String departureDayChange;
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
    public String getAirportCode() {
        return airportCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode == null ? null : airportCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getArrivalTime() {
        return arrivalTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getArrivalDayChange() {
        return arrivalDayChange;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setArrivalDayChange(String arrivalDayChange) {
        this.arrivalDayChange = arrivalDayChange == null ? null : arrivalDayChange.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDepartureTime() {
        return departureTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDepartureDayChange() {
        return departureDayChange;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDepartureDayChange(String departureDayChange) {
        this.departureDayChange = departureDayChange == null ? null : departureDayChange.trim();
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
        sb.append(", airportCode=").append(airportCode);
        sb.append(", arrivalTime=").append(arrivalTime);
        sb.append(", arrivalDayChange=").append(arrivalDayChange);
        sb.append(", departureTime=").append(departureTime);
        sb.append(", departureDayChange=").append(departureDayChange);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
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
    public int compareTo(FmsSeasonEntireRoute o) {
        return this.getSequenceNumber() - o.getSequenceNumber();
    }

    /**
     * Compare objects without considering id, create_time and update_time columns
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FmsSeasonEntireRoute)) return false;
        FmsSeasonEntireRoute that = (FmsSeasonEntireRoute) o;
        return Objects.equals(flightId, that.flightId) &&
                Objects.equals(sequenceNumber, that.sequenceNumber) &&
                Objects.equals(airportCode, that.airportCode) &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(arrivalDayChange, that.arrivalDayChange) &&
                Objects.equals(departureTime, that.departureTime) &&
                Objects.equals(departureDayChange, that.departureDayChange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, sequenceNumber, airportCode, arrivalTime, arrivalDayChange, departureTime, departureDayChange);
    }
}