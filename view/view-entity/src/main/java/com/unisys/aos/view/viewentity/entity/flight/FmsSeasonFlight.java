package com.unisys.aos.view.viewentity.entity.flight;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.annotation.Generated;

public class FmsSeasonFlight implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String seasonName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date seasonStartDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date seasonEndDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String airlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String flightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String movementIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date seasonScheduleStartDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date seasonScheduleEndDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String weekOperateDay;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String flightScheduleTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String flightType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String flightIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String aircraftType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String registration;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String linkedAirlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String linkedFlightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String terminal;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Short maxPax;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String masterAirlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String masterFlightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getSeasonName() {
        return seasonName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName == null ? null : seasonName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getSeasonStartDate() {
        return seasonStartDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSeasonStartDate(Date seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getSeasonEndDate() {
        return seasonEndDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSeasonEndDate(Date seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAirlineCode() {
        return airlineCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode == null ? null : airlineCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getFlightNumber() {
        return flightNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber == null ? null : flightNumber.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMovementIndicator() {
        return movementIndicator;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMovementIndicator(String movementIndicator) {
        this.movementIndicator = movementIndicator == null ? null : movementIndicator.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getSeasonScheduleStartDate() {
        return seasonScheduleStartDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSeasonScheduleStartDate(Date seasonScheduleStartDate) {
        this.seasonScheduleStartDate = seasonScheduleStartDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getSeasonScheduleEndDate() {
        return seasonScheduleEndDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSeasonScheduleEndDate(Date seasonScheduleEndDate) {
        this.seasonScheduleEndDate = seasonScheduleEndDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getWeekOperateDay() {
        return weekOperateDay;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWeekOperateDay(String weekOperateDay) {
        this.weekOperateDay = weekOperateDay == null ? null : weekOperateDay.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getFlightScheduleTime() {
        return flightScheduleTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFlightScheduleTime(String flightScheduleTime) {
        this.flightScheduleTime = flightScheduleTime == null ? null : flightScheduleTime.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getFlightType() {
        return flightType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFlightType(String flightType) {
        this.flightType = flightType == null ? null : flightType.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getFlightIndicator() {
        return flightIndicator;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFlightIndicator(String flightIndicator) {
        this.flightIndicator = flightIndicator == null ? null : flightIndicator.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAircraftType() {
        return aircraftType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType == null ? null : aircraftType.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRegistration() {
        return registration;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRegistration(String registration) {
        this.registration = registration == null ? null : registration.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLinkedAirlineCode() {
        return linkedAirlineCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLinkedAirlineCode(String linkedAirlineCode) {
        this.linkedAirlineCode = linkedAirlineCode == null ? null : linkedAirlineCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLinkedFlightNumber() {
        return linkedFlightNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLinkedFlightNumber(String linkedFlightNumber) {
        this.linkedFlightNumber = linkedFlightNumber == null ? null : linkedFlightNumber.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getTerminal() {
        return terminal;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Short getMaxPax() {
        return maxPax;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMaxPax(Short maxPax) {
        this.maxPax = maxPax;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMasterAirlineCode() {
        return masterAirlineCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMasterAirlineCode(String masterAirlineCode) {
        this.masterAirlineCode = masterAirlineCode == null ? null : masterAirlineCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMasterFlightNumber() {
        return masterFlightNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMasterFlightNumber(String masterFlightNumber) {
        this.masterFlightNumber = masterFlightNumber == null ? null : masterFlightNumber.trim();
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
        sb.append(", seasonName=").append(seasonName);
        sb.append(", seasonStartDate=").append(seasonStartDate);
        sb.append(", seasonEndDate=").append(seasonEndDate);
        sb.append(", airlineCode=").append(airlineCode);
        sb.append(", flightNumber=").append(flightNumber);
        sb.append(", movementIndicator=").append(movementIndicator);
        sb.append(", seasonScheduleStartDate=").append(seasonScheduleStartDate);
        sb.append(", seasonScheduleEndDate=").append(seasonScheduleEndDate);
        sb.append(", weekOperateDay=").append(weekOperateDay);
        sb.append(", flightScheduleTime=").append(flightScheduleTime);
        sb.append(", flightType=").append(flightType);
        sb.append(", flightIndicator=").append(flightIndicator);
        sb.append(", aircraftType=").append(aircraftType);
        sb.append(", registration=").append(registration);
        sb.append(", linkedAirlineCode=").append(linkedAirlineCode);
        sb.append(", linkedFlightNumber=").append(linkedFlightNumber);
        sb.append(", terminal=").append(terminal);
        sb.append(", maxPax=").append(maxPax);
        sb.append(", masterAirlineCode=").append(masterAirlineCode);
        sb.append(", masterFlightNumber=").append(masterFlightNumber);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Compare objects without considering id, create_time and update_time columns
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FmsSeasonFlight)) return false;
        FmsSeasonFlight that = (FmsSeasonFlight) o;
        return Objects.equals(seasonName, that.seasonName) &&
                Objects.equals(seasonStartDate, that.seasonStartDate) &&
                Objects.equals(seasonEndDate, that.seasonEndDate) &&
                Objects.equals(airlineCode, that.airlineCode) &&
                Objects.equals(flightNumber, that.flightNumber) &&
                Objects.equals(movementIndicator, that.movementIndicator) &&
                Objects.equals(seasonScheduleStartDate, that.seasonScheduleStartDate) &&
                Objects.equals(seasonScheduleEndDate, that.seasonScheduleEndDate) &&
                Objects.equals(weekOperateDay, that.weekOperateDay) &&
                Objects.equals(flightScheduleTime, that.flightScheduleTime) &&
                Objects.equals(flightType, that.flightType) &&
                Objects.equals(flightIndicator, that.flightIndicator) &&
                Objects.equals(aircraftType, that.aircraftType) &&
                Objects.equals(registration, that.registration) &&
                Objects.equals(linkedAirlineCode, that.linkedAirlineCode) &&
                Objects.equals(linkedFlightNumber, that.linkedFlightNumber) &&
                Objects.equals(terminal, that.terminal) &&
                Objects.equals(maxPax, that.maxPax) &&
                Objects.equals(masterAirlineCode, that.masterAirlineCode) &&
                Objects.equals(masterFlightNumber, that.masterFlightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonName, seasonStartDate, seasonEndDate, airlineCode, flightNumber, movementIndicator, seasonScheduleStartDate, seasonScheduleEndDate, weekOperateDay, flightScheduleTime, flightType, flightIndicator, aircraftType, registration, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterAirlineCode, masterFlightNumber);
    }
}