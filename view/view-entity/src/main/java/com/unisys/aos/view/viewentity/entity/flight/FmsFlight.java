package com.unisys.aos.view.viewentity.entity.flight;

import javax.annotation.Generated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class FmsFlight implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long aodbId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String airlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String airlineSubcompanyCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String flightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String movementIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date sto;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String flightType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String flightIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String aircraftType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String registration;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long linkedId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long linkedAodbId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String linkedAirlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String linkedFlightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String terminal;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Short maxPax;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long masterId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long masterAodbId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String masterAirlineCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String masterFlightNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date eto;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date ato;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String currentStand;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer paxAgent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date cancel;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String remarks;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date boardingOpen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date lastCall;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date finalTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date approvedTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date engineStart;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date engineStartRequest;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer fieldAgent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer maintenanceAgent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Short vipCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte vipFlag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String divAirport;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String divDirection;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String divReason;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String retTyp;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String retRsn;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String landingAbortIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String landingAbortReason;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Short localBagCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal localBagWeight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Short passengerCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String externalStatusCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String externalStatusComment;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String statusCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date pedt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date neat;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date padt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date naat;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String rwyCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date calOffblock;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date calTakeoff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date estimatedOffblock;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date internalEstimated;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date portOpenTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date portCloseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date targetOffblock;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String logicalRunway;

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
    public Long getAodbId() {
        return aodbId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAodbId(Long aodbId) {
        this.aodbId = aodbId;
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
    public String getAirlineSubcompanyCode() {
        return airlineSubcompanyCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAirlineSubcompanyCode(String airlineSubcompanyCode) {
        this.airlineSubcompanyCode = airlineSubcompanyCode == null ? null : airlineSubcompanyCode.trim();
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
    public Date getSto() {
        return sto;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSto(Date sto) {
        this.sto = sto;
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
    public Long getLinkedId() {
        return linkedId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLinkedId(Long linkedId) {
        this.linkedId = linkedId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getLinkedAodbId() {
        return linkedAodbId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLinkedAodbId(Long linkedAodbId) {
        this.linkedAodbId = linkedAodbId;
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
    public Long getMasterId() {
        return masterId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getMasterAodbId() {
        return masterAodbId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMasterAodbId(Long masterAodbId) {
        this.masterAodbId = masterAodbId;
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
    public Date getEto() {
        return eto;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEto(Date eto) {
        this.eto = eto;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getAto() {
        return ato;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAto(Date ato) {
        this.ato = ato;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCurrentStand() {
        return currentStand;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCurrentStand(String currentStand) {
        this.currentStand = currentStand == null ? null : currentStand.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getPaxAgent() {
        return paxAgent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPaxAgent(Integer paxAgent) {
        this.paxAgent = paxAgent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCancel() {
        return cancel;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCancel(Date cancel) {
        this.cancel = cancel;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRemarks() {
        return remarks;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getBoardingOpen() {
        return boardingOpen;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setBoardingOpen(Date boardingOpen) {
        this.boardingOpen = boardingOpen;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getLastCall() {
        return lastCall;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLastCall(Date lastCall) {
        this.lastCall = lastCall;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getFinalTime() {
        return finalTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getApprovedTime() {
        return approvedTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setApprovedTime(Date approvedTime) {
        this.approvedTime = approvedTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getEngineStart() {
        return engineStart;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEngineStart(Date engineStart) {
        this.engineStart = engineStart;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getEngineStartRequest() {
        return engineStartRequest;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEngineStartRequest(Date engineStartRequest) {
        this.engineStartRequest = engineStartRequest;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getFieldAgent() {
        return fieldAgent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFieldAgent(Integer fieldAgent) {
        this.fieldAgent = fieldAgent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getMaintenanceAgent() {
        return maintenanceAgent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMaintenanceAgent(Integer maintenanceAgent) {
        this.maintenanceAgent = maintenanceAgent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Short getVipCount() {
        return vipCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipCount(Short vipCount) {
        this.vipCount = vipCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getVipFlag() {
        return vipFlag;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVipFlag(Byte vipFlag) {
        this.vipFlag = vipFlag;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDivAirport() {
        return divAirport;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDivAirport(String divAirport) {
        this.divAirport = divAirport == null ? null : divAirport.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDivDirection() {
        return divDirection;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDivDirection(String divDirection) {
        this.divDirection = divDirection == null ? null : divDirection.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDivReason() {
        return divReason;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDivReason(String divReason) {
        this.divReason = divReason == null ? null : divReason.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRetTyp() {
        return retTyp;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRetTyp(String retTyp) {
        this.retTyp = retTyp == null ? null : retTyp.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRetRsn() {
        return retRsn;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRetRsn(String retRsn) {
        this.retRsn = retRsn == null ? null : retRsn.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLandingAbortIndicator() {
        return landingAbortIndicator;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLandingAbortIndicator(String landingAbortIndicator) {
        this.landingAbortIndicator = landingAbortIndicator == null ? null : landingAbortIndicator.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLandingAbortReason() {
        return landingAbortReason;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLandingAbortReason(String landingAbortReason) {
        this.landingAbortReason = landingAbortReason == null ? null : landingAbortReason.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Short getLocalBagCount() {
        return localBagCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLocalBagCount(Short localBagCount) {
        this.localBagCount = localBagCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public BigDecimal getLocalBagWeight() {
        return localBagWeight;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLocalBagWeight(BigDecimal localBagWeight) {
        this.localBagWeight = localBagWeight;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Short getPassengerCount() {
        return passengerCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPassengerCount(Short passengerCount) {
        this.passengerCount = passengerCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getExternalStatusCode() {
        return externalStatusCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setExternalStatusCode(String externalStatusCode) {
        this.externalStatusCode = externalStatusCode == null ? null : externalStatusCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getExternalStatusComment() {
        return externalStatusComment;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setExternalStatusComment(String externalStatusComment) {
        this.externalStatusComment = externalStatusComment == null ? null : externalStatusComment.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getStatusCode() {
        return statusCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode == null ? null : statusCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getPedt() {
        return pedt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPedt(Date pedt) {
        this.pedt = pedt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getNeat() {
        return neat;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setNeat(Date neat) {
        this.neat = neat;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getPadt() {
        return padt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPadt(Date padt) {
        this.padt = padt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getNaat() {
        return naat;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setNaat(Date naat) {
        this.naat = naat;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRwyCode() {
        return rwyCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRwyCode(String rwyCode) {
        this.rwyCode = rwyCode == null ? null : rwyCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCalOffblock() {
        return calOffblock;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCalOffblock(Date calOffblock) {
        this.calOffblock = calOffblock;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCalTakeoff() {
        return calTakeoff;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCalTakeoff(Date calTakeoff) {
        this.calTakeoff = calTakeoff;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getEstimatedOffblock() {
        return estimatedOffblock;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEstimatedOffblock(Date estimatedOffblock) {
        this.estimatedOffblock = estimatedOffblock;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getInternalEstimated() {
        return internalEstimated;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setInternalEstimated(Date internalEstimated) {
        this.internalEstimated = internalEstimated;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getPortOpenTime() {
        return portOpenTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPortOpenTime(Date portOpenTime) {
        this.portOpenTime = portOpenTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getPortCloseTime() {
        return portCloseTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPortCloseTime(Date portCloseTime) {
        this.portCloseTime = portCloseTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getTargetOffblock() {
        return targetOffblock;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTargetOffblock(Date targetOffblock) {
        this.targetOffblock = targetOffblock;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLogicalRunway() {
        return logicalRunway;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLogicalRunway(String logicalRunway) {
        this.logicalRunway = logicalRunway == null ? null : logicalRunway.trim();
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
        sb.append(", aodbId=").append(aodbId);
        sb.append(", airlineCode=").append(airlineCode);
        sb.append(", airlineSubcompanyCode=").append(airlineSubcompanyCode);
        sb.append(", flightNumber=").append(flightNumber);
        sb.append(", movementIndicator=").append(movementIndicator);
        sb.append(", sto=").append(sto);
        sb.append(", flightType=").append(flightType);
        sb.append(", flightIndicator=").append(flightIndicator);
        sb.append(", aircraftType=").append(aircraftType);
        sb.append(", registration=").append(registration);
        sb.append(", linkedId=").append(linkedId);
        sb.append(", linkedAodbId=").append(linkedAodbId);
        sb.append(", linkedAirlineCode=").append(linkedAirlineCode);
        sb.append(", linkedFlightNumber=").append(linkedFlightNumber);
        sb.append(", terminal=").append(terminal);
        sb.append(", maxPax=").append(maxPax);
        sb.append(", masterId=").append(masterId);
        sb.append(", masterAodbId=").append(masterAodbId);
        sb.append(", masterAirlineCode=").append(masterAirlineCode);
        sb.append(", masterFlightNumber=").append(masterFlightNumber);
        sb.append(", eto=").append(eto);
        sb.append(", ato=").append(ato);
        sb.append(", currentStand=").append(currentStand);
        sb.append(", paxAgent=").append(paxAgent);
        sb.append(", cancel=").append(cancel);
        sb.append(", remarks=").append(remarks);
        sb.append(", boardingOpen=").append(boardingOpen);
        sb.append(", lastCall=").append(lastCall);
        sb.append(", finalTime=").append(finalTime);
        sb.append(", approvedTime=").append(approvedTime);
        sb.append(", engineStart=").append(engineStart);
        sb.append(", engineStartRequest=").append(engineStartRequest);
        sb.append(", fieldAgent=").append(fieldAgent);
        sb.append(", maintenanceAgent=").append(maintenanceAgent);
        sb.append(", vipCount=").append(vipCount);
        sb.append(", vipFlag=").append(vipFlag);
        sb.append(", divAirport=").append(divAirport);
        sb.append(", divDirection=").append(divDirection);
        sb.append(", divReason=").append(divReason);
        sb.append(", retTyp=").append(retTyp);
        sb.append(", retRsn=").append(retRsn);
        sb.append(", landingAbortIndicator=").append(landingAbortIndicator);
        sb.append(", landingAbortReason=").append(landingAbortReason);
        sb.append(", localBagCount=").append(localBagCount);
        sb.append(", localBagWeight=").append(localBagWeight);
        sb.append(", passengerCount=").append(passengerCount);
        sb.append(", externalStatusCode=").append(externalStatusCode);
        sb.append(", externalStatusComment=").append(externalStatusComment);
        sb.append(", statusCode=").append(statusCode);
        sb.append(", pedt=").append(pedt);
        sb.append(", neat=").append(neat);
        sb.append(", padt=").append(padt);
        sb.append(", naat=").append(naat);
        sb.append(", rwyCode=").append(rwyCode);
        sb.append(", calOffblock=").append(calOffblock);
        sb.append(", calTakeoff=").append(calTakeoff);
        sb.append(", estimatedOffblock=").append(estimatedOffblock);
        sb.append(", internalEstimated=").append(internalEstimated);
        sb.append(", portOpenTime=").append(portOpenTime);
        sb.append(", portCloseTime=").append(portCloseTime);
        sb.append(", targetOffblock=").append(targetOffblock);
        sb.append(", logicalRunway=").append(logicalRunway);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Compare objects without considering create_time and update_time columns
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FmsFlight)) return false;
        FmsFlight fmsFlight = (FmsFlight) o;
        if (null == getLocalBagWeight()) {
            if (null != fmsFlight.getLocalBagWeight()) {
                return false;
            }
        } else {
            if (null != fmsFlight.getLocalBagWeight()) {
                if (0 != localBagWeight.compareTo(fmsFlight.getLocalBagWeight())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return getAodbId().equals(fmsFlight.getAodbId()) &&
                getAirlineCode().equals(fmsFlight.getAirlineCode()) &&
                Objects.equals(getAirlineSubcompanyCode(), fmsFlight.getAirlineSubcompanyCode()) &&
                getFlightNumber().equals(fmsFlight.getFlightNumber()) &&
                getMovementIndicator().equals(fmsFlight.getMovementIndicator()) &&
                getSto().equals(fmsFlight.getSto()) &&
                getFlightType().equals(fmsFlight.getFlightType()) &&
                getFlightIndicator().equals(fmsFlight.getFlightIndicator()) &&
                getAircraftType().equals(fmsFlight.getAircraftType()) &&
                Objects.equals(getRegistration(), fmsFlight.getRegistration()) &&
                Objects.equals(getLinkedId(), fmsFlight.getLinkedId()) &&
                Objects.equals(getLinkedAodbId(), fmsFlight.getLinkedAodbId()) &&
                Objects.equals(getLinkedAirlineCode(), fmsFlight.getLinkedAirlineCode()) &&
                Objects.equals(getLinkedFlightNumber(), fmsFlight.getLinkedFlightNumber()) &&
                Objects.equals(getTerminal(), fmsFlight.getTerminal()) &&
                Objects.equals(getMaxPax(), fmsFlight.getMaxPax()) &&
                Objects.equals(getMasterId(), fmsFlight.getMasterId()) &&
                Objects.equals(getMasterAodbId(), fmsFlight.getMasterAodbId()) &&
                Objects.equals(getMasterAirlineCode(), fmsFlight.getMasterAirlineCode()) &&
                Objects.equals(getMasterFlightNumber(), fmsFlight.getMasterFlightNumber()) &&
                Objects.equals(getEto(), fmsFlight.getEto()) &&
                Objects.equals(getAto(), fmsFlight.getAto()) &&
                Objects.equals(getCurrentStand(), fmsFlight.getCurrentStand()) &&
                Objects.equals(getPaxAgent(), fmsFlight.getPaxAgent()) &&
                Objects.equals(getCancel(), fmsFlight.getCancel()) &&
                Objects.equals(getRemarks(), fmsFlight.getRemarks()) &&
                Objects.equals(getBoardingOpen(), fmsFlight.getBoardingOpen()) &&
                Objects.equals(getLastCall(), fmsFlight.getLastCall()) &&
                Objects.equals(getFinalTime(), fmsFlight.getFinalTime()) &&
                Objects.equals(getApprovedTime(), fmsFlight.getApprovedTime()) &&
                Objects.equals(getEngineStart(), fmsFlight.getEngineStart()) &&
                Objects.equals(getEngineStartRequest(), fmsFlight.getEngineStartRequest()) &&
                Objects.equals(getFieldAgent(), fmsFlight.getFieldAgent()) &&
                Objects.equals(getMaintenanceAgent(), fmsFlight.getMaintenanceAgent()) &&
                Objects.equals(getVipCount(), fmsFlight.getVipCount()) &&
                Objects.equals(getVipFlag(), fmsFlight.getVipFlag()) &&
                Objects.equals(getDivAirport(), fmsFlight.getDivAirport()) &&
                Objects.equals(getDivDirection(), fmsFlight.getDivDirection()) &&
                Objects.equals(getDivReason(), fmsFlight.getDivReason()) &&
                Objects.equals(getRetTyp(), fmsFlight.getRetTyp()) &&
                Objects.equals(getRetRsn(), fmsFlight.getRetRsn()) &&
                Objects.equals(getLandingAbortIndicator(), fmsFlight.getLandingAbortIndicator()) &&
                Objects.equals(getLandingAbortReason(), fmsFlight.getLandingAbortReason()) &&
                Objects.equals(getLocalBagCount(), fmsFlight.getLocalBagCount()) &&
                Objects.equals(getPassengerCount(), fmsFlight.getPassengerCount()) &&
                Objects.equals(getExternalStatusCode(), fmsFlight.getExternalStatusCode()) &&
                Objects.equals(getExternalStatusComment(), fmsFlight.getExternalStatusComment()) &&
                Objects.equals(getStatusCode(), fmsFlight.getStatusCode()) &&
                Objects.equals(getPedt(), fmsFlight.getPedt()) &&
                Objects.equals(getNeat(), fmsFlight.getNeat()) &&
                Objects.equals(getPadt(), fmsFlight.getPadt()) &&
                Objects.equals(getNaat(), fmsFlight.getNaat()) &&
                Objects.equals(getRwyCode(), fmsFlight.getRwyCode()) &&
                Objects.equals(getCalOffblock(), fmsFlight.getCalOffblock()) &&
                Objects.equals(getCalTakeoff(), fmsFlight.getCalTakeoff())&&
                Objects.equals(getEstimatedOffblock(), fmsFlight.getEstimatedOffblock()) &&
                Objects.equals(getInternalEstimated(), fmsFlight.getInternalEstimated()) &&
                Objects.equals(getPortOpenTime(), fmsFlight.getPortOpenTime()) &&
                Objects.equals(getPortCloseTime(), fmsFlight.getPortCloseTime()) &&
                Objects.equals(getTargetOffblock(), fmsFlight.getTargetOffblock())&&
                Objects.equals(getLogicalRunway(), fmsFlight.getLogicalRunway());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aodbId, airlineCode, airlineSubcompanyCode, flightNumber, movementIndicator, sto, flightType, flightIndicator, aircraftType, registration, linkedId, linkedAodbId, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterId, masterAodbId, masterAirlineCode, masterFlightNumber, eto, ato, currentStand, paxAgent, cancel, remarks, boardingOpen, lastCall, finalTime, approvedTime, engineStart, engineStartRequest, fieldAgent, maintenanceAgent, vipCount, vipFlag, divAirport, divDirection, divReason, retTyp, retRsn, landingAbortIndicator, landingAbortReason, localBagCount, localBagWeight, passengerCount, externalStatusCode, externalStatusComment, statusCode, pedt, neat, padt, naat, rwyCode, calOffblock, calTakeoff, estimatedOffblock, internalEstimated, portOpenTime, portCloseTime, targetOffblock, logicalRunway);
    }
}