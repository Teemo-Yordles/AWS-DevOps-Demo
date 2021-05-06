package com.unisys.aos.view.viewentity.mapper.flight;

import com.unisys.aos.view.viewentity.entity.flight.FmsFlight;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import java.util.List;

import static com.unisys.aos.view.viewentity.mapper.flight.FmsFlightDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Repository
@Mapper
public interface FmsFlightMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<FmsFlight> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FmsFlightResult")
    FmsFlight selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="FmsFlightResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="aodb_id", property="aodbId", jdbcType=JdbcType.BIGINT),
        @Result(column="airline_code", property="airlineCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="airline_subcompany_code", property="airlineSubcompanyCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="flight_number", property="flightNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="movement_indicator", property="movementIndicator", jdbcType=JdbcType.CHAR),
        @Result(column="sto", property="sto", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="flight_type", property="flightType", jdbcType=JdbcType.VARCHAR),
        @Result(column="flight_indicator", property="flightIndicator", jdbcType=JdbcType.CHAR),
        @Result(column="aircraft_type", property="aircraftType", jdbcType=JdbcType.VARCHAR),
        @Result(column="registration", property="registration", jdbcType=JdbcType.VARCHAR),
        @Result(column="linked_id", property="linkedId", jdbcType=JdbcType.BIGINT),
        @Result(column="linked_aodb_id", property="linkedAodbId", jdbcType=JdbcType.BIGINT),
        @Result(column="linked_airline_code", property="linkedAirlineCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="linked_flight_number", property="linkedFlightNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="terminal", property="terminal", jdbcType=JdbcType.VARCHAR),
        @Result(column="max_pax", property="maxPax", jdbcType=JdbcType.SMALLINT),
        @Result(column="master_id", property="masterId", jdbcType=JdbcType.BIGINT),
        @Result(column="master_aodb_id", property="masterAodbId", jdbcType=JdbcType.BIGINT),
        @Result(column="master_airline_code", property="masterAirlineCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="master_flight_number", property="masterFlightNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="eto", property="eto", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ato", property="ato", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="current_stand", property="currentStand", jdbcType=JdbcType.VARCHAR),
        @Result(column="pax_agent", property="paxAgent", jdbcType=JdbcType.BIGINT),
        @Result(column="cancel", property="cancel", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="boarding_open", property="boardingOpen", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_call", property="lastCall", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="final_time", property="finalTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="approved_time", property="approvedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="engine_start", property="engineStart", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="engine_start_request", property="engineStartRequest", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="field_agent", property="fieldAgent", jdbcType=JdbcType.BIGINT),
        @Result(column="maintenance_agent", property="maintenanceAgent", jdbcType=JdbcType.BIGINT),
        @Result(column="vip_count", property="vipCount", jdbcType=JdbcType.SMALLINT),
        @Result(column="vip_flag", property="vipFlag", jdbcType=JdbcType.TINYINT),
        @Result(column="div_airport", property="divAirport", jdbcType=JdbcType.VARCHAR),
        @Result(column="div_direction", property="divDirection", jdbcType=JdbcType.VARCHAR),
        @Result(column="div_reason", property="divReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="ret_typ", property="retTyp", jdbcType=JdbcType.CHAR),
        @Result(column="ret_rsn", property="retRsn", jdbcType=JdbcType.VARCHAR),
        @Result(column="landing_abort_indicator", property="landingAbortIndicator", jdbcType=JdbcType.CHAR),
        @Result(column="landing_abort_reason", property="landingAbortReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="local_bag_count", property="localBagCount", jdbcType=JdbcType.SMALLINT),
        @Result(column="local_bag_weight", property="localBagWeight", jdbcType=JdbcType.DECIMAL),
        @Result(column="passenger_count", property="passengerCount", jdbcType=JdbcType.SMALLINT),
        @Result(column="external_status_code", property="externalStatusCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="external_status_comment", property="externalStatusComment", jdbcType=JdbcType.VARCHAR),
        @Result(column="status_code", property="statusCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="pedt", property="pedt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="neat", property="neat", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="padt", property="padt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="naat", property="naat", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rwy_code", property="rwyCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="cal_offblock", property="calOffblock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="cal_takeoff", property="calTakeoff", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="estimated_offblock", property="estimatedOffblock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="internal_estimated", property="internalEstimated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="port_open_time", property="portOpenTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="port_close_time", property="portCloseTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="target_offblock", property="targetOffblock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="logical_runway", property="logicalRunway", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FmsFlight> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(fmsFlight);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsFlight);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, fmsFlight)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    /**
     * Delete by flight id
     *
     * @param aodbId - flight AODB ID.
     * @return number of rows affected
     */
    default int deleteByAODBId(Long aodbId) {
        DeleteStatementProvider deleteStatement = deleteFrom(fmsFlight)
                .where(FmsFlightDynamicSqlSupport.aodbId, isEqualTo(aodbId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return this.delete(deleteStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FmsFlight record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsFlight)
                .map(aodbId).toProperty("aodbId")
                .map(airlineCode).toProperty("airlineCode")
                .map(airlineSubcompanyCode).toProperty("airlineSubcompanyCode")
                .map(flightNumber).toProperty("flightNumber")
                .map(movementIndicator).toProperty("movementIndicator")
                .map(sto).toProperty("sto")
                .map(flightType).toProperty("flightType")
                .map(flightIndicator).toProperty("flightIndicator")
                .map(aircraftType).toProperty("aircraftType")
                .map(registration).toProperty("registration")
                .map(linkedId).toProperty("linkedId")
                .map(linkedAodbId).toProperty("linkedAodbId")
                .map(linkedAirlineCode).toProperty("linkedAirlineCode")
                .map(linkedFlightNumber).toProperty("linkedFlightNumber")
                .map(terminal).toProperty("terminal")
                .map(maxPax).toProperty("maxPax")
                .map(masterId).toProperty("masterId")
                .map(masterAodbId).toProperty("masterAodbId")
                .map(masterAirlineCode).toProperty("masterAirlineCode")
                .map(masterFlightNumber).toProperty("masterFlightNumber")
                .map(eto).toProperty("eto")
                .map(ato).toProperty("ato")
                .map(currentStand).toProperty("currentStand")
                .map(paxAgent).toProperty("paxAgent")
                .map(cancel).toProperty("cancel")
                .map(remarks).toProperty("remarks")
                .map(boardingOpen).toProperty("boardingOpen")
                .map(lastCall).toProperty("lastCall")
                .map(finalTime).toProperty("finalTime")
                .map(approvedTime).toProperty("approvedTime")
                .map(engineStart).toProperty("engineStart")
                .map(engineStartRequest).toProperty("engineStartRequest")
                .map(fieldAgent).toProperty("fieldAgent")
                .map(maintenanceAgent).toProperty("maintenanceAgent")
                .map(vipCount).toProperty("vipCount")
                .map(vipFlag).toProperty("vipFlag")
                .map(divAirport).toProperty("divAirport")
                .map(divDirection).toProperty("divDirection")
                .map(divReason).toProperty("divReason")
                .map(retTyp).toProperty("retTyp")
                .map(retRsn).toProperty("retRsn")
                .map(landingAbortIndicator).toProperty("landingAbortIndicator")
                .map(landingAbortReason).toProperty("landingAbortReason")
                .map(localBagCount).toProperty("localBagCount")
                .map(localBagWeight).toProperty("localBagWeight")
                .map(passengerCount).toProperty("passengerCount")
                .map(externalStatusCode).toProperty("externalStatusCode")
                .map(externalStatusComment).toProperty("externalStatusComment")
                .map(statusCode).toProperty("statusCode")
                .map(pedt).toProperty("pedt")
                .map(neat).toProperty("neat")
                .map(padt).toProperty("padt")
                .map(naat).toProperty("naat")
                .map(rwyCode).toProperty("rwyCode")
                .map(calOffblock).toProperty("calOffblock")
                .map(calTakeoff).toProperty("calTakeoff")
                .map(estimatedOffblock).toProperty("estimatedOffblock")
                .map(internalEstimated).toProperty("internalEstimated")
                .map(portOpenTime).toProperty("portOpenTime")
                .map(portCloseTime).toProperty("portCloseTime")
                .map(targetOffblock).toProperty("targetOffblock")
                .map(logicalRunway).toProperty("logicalRunway")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FmsFlight record) {
        return insert(SqlBuilder.insert(record)
                .into(fmsFlight)
                .map(aodbId).toPropertyWhenPresent("aodbId", record::getAodbId)
                .map(airlineCode).toPropertyWhenPresent("airlineCode", record::getAirlineCode)
                .map(airlineSubcompanyCode).toPropertyWhenPresent("airlineSubcompanyCode", record::getAirlineSubcompanyCode)
                .map(flightNumber).toPropertyWhenPresent("flightNumber", record::getFlightNumber)
                .map(movementIndicator).toPropertyWhenPresent("movementIndicator", record::getMovementIndicator)
                .map(sto).toPropertyWhenPresent("sto", record::getSto)
                .map(flightType).toPropertyWhenPresent("flightType", record::getFlightType)
                .map(flightIndicator).toPropertyWhenPresent("flightIndicator", record::getFlightIndicator)
                .map(aircraftType).toPropertyWhenPresent("aircraftType", record::getAircraftType)
                .map(registration).toPropertyWhenPresent("registration", record::getRegistration)
                .map(linkedId).toPropertyWhenPresent("linkedId", record::getLinkedId)
                .map(linkedAodbId).toPropertyWhenPresent("linkedAodbId", record::getLinkedAodbId)
                .map(linkedAirlineCode).toPropertyWhenPresent("linkedAirlineCode", record::getLinkedAirlineCode)
                .map(linkedFlightNumber).toPropertyWhenPresent("linkedFlightNumber", record::getLinkedFlightNumber)
                .map(terminal).toPropertyWhenPresent("terminal", record::getTerminal)
                .map(maxPax).toPropertyWhenPresent("maxPax", record::getMaxPax)
                .map(masterId).toPropertyWhenPresent("masterId", record::getMasterId)
                .map(masterAodbId).toPropertyWhenPresent("masterAodbId", record::getMasterAodbId)
                .map(masterAirlineCode).toPropertyWhenPresent("masterAirlineCode", record::getMasterAirlineCode)
                .map(masterFlightNumber).toPropertyWhenPresent("masterFlightNumber", record::getMasterFlightNumber)
                .map(eto).toPropertyWhenPresent("eto", record::getEto)
                .map(ato).toPropertyWhenPresent("ato", record::getAto)
                .map(currentStand).toPropertyWhenPresent("currentStand", record::getCurrentStand)
                .map(paxAgent).toPropertyWhenPresent("paxAgent", record::getPaxAgent)
                .map(cancel).toPropertyWhenPresent("cancel", record::getCancel)
                .map(remarks).toPropertyWhenPresent("remarks", record::getRemarks)
                .map(boardingOpen).toPropertyWhenPresent("boardingOpen", record::getBoardingOpen)
                .map(lastCall).toPropertyWhenPresent("lastCall", record::getLastCall)
                .map(finalTime).toPropertyWhenPresent("finalTime", record::getFinalTime)
                .map(approvedTime).toPropertyWhenPresent("approvedTime", record::getApprovedTime)
                .map(engineStart).toPropertyWhenPresent("engineStart", record::getEngineStart)
                .map(engineStartRequest).toPropertyWhenPresent("engineStartRequest", record::getEngineStartRequest)
                .map(fieldAgent).toPropertyWhenPresent("fieldAgent", record::getFieldAgent)
                .map(maintenanceAgent).toPropertyWhenPresent("maintenanceAgent", record::getMaintenanceAgent)
                .map(vipCount).toPropertyWhenPresent("vipCount", record::getVipCount)
                .map(vipFlag).toPropertyWhenPresent("vipFlag", record::getVipFlag)
                .map(divAirport).toPropertyWhenPresent("divAirport", record::getDivAirport)
                .map(divDirection).toPropertyWhenPresent("divDirection", record::getDivDirection)
                .map(divReason).toPropertyWhenPresent("divReason", record::getDivReason)
                .map(retTyp).toPropertyWhenPresent("retTyp", record::getRetTyp)
                .map(retRsn).toPropertyWhenPresent("retRsn", record::getRetRsn)
                .map(landingAbortIndicator).toPropertyWhenPresent("landingAbortIndicator", record::getLandingAbortIndicator)
                .map(landingAbortReason).toPropertyWhenPresent("landingAbortReason", record::getLandingAbortReason)
                .map(localBagCount).toPropertyWhenPresent("localBagCount", record::getLocalBagCount)
                .map(localBagWeight).toPropertyWhenPresent("localBagWeight", record::getLocalBagWeight)
                .map(passengerCount).toPropertyWhenPresent("passengerCount", record::getPassengerCount)
                .map(externalStatusCode).toPropertyWhenPresent("externalStatusCode", record::getExternalStatusCode)
                .map(externalStatusComment).toPropertyWhenPresent("externalStatusComment", record::getExternalStatusComment)
                .map(statusCode).toPropertyWhenPresent("statusCode", record::getStatusCode)
                .map(pedt).toPropertyWhenPresent("pedt", record::getPedt)
                .map(neat).toPropertyWhenPresent("neat", record::getNeat)
                .map(padt).toPropertyWhenPresent("padt", record::getPadt)
                .map(naat).toPropertyWhenPresent("naat", record::getNaat)
                .map(rwyCode).toPropertyWhenPresent("rwyCode", record::getRwyCode)
                .map(calOffblock).toPropertyWhenPresent("calOffblock", record::getCalOffblock)
                .map(calTakeoff).toPropertyWhenPresent("calTakeoff", record::getCalTakeoff)
                .map(estimatedOffblock).toPropertyWhenPresent("estimatedOffblock", record::getEstimatedOffblock)
                .map(internalEstimated).toPropertyWhenPresent("internalEstimated", record::getInternalEstimated)
                .map(portOpenTime).toPropertyWhenPresent("portOpenTime", record::getPortOpenTime)
                .map(portCloseTime).toPropertyWhenPresent("portCloseTime", record::getPortCloseTime)
                .map(targetOffblock).toPropertyWhenPresent("targetOffblock", record::getTargetOffblock)
                .map(logicalRunway).toPropertyWhenPresent("logicalRunway", record::getLogicalRunway)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsFlight>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, aodbId, airlineCode, airlineSubcompanyCode, flightNumber, movementIndicator, sto, flightType, flightIndicator, aircraftType, registration, linkedId, linkedAodbId, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterId, masterAodbId, masterAirlineCode, masterFlightNumber, eto, ato, currentStand, paxAgent, cancel, remarks, boardingOpen, lastCall, finalTime, approvedTime, engineStart, engineStartRequest, fieldAgent, maintenanceAgent, vipCount, vipFlag, divAirport, divDirection, divReason, retTyp, retRsn, landingAbortIndicator, landingAbortReason, localBagCount, localBagWeight, passengerCount, externalStatusCode, externalStatusComment, statusCode, pedt, neat, padt, naat, rwyCode, calOffblock, calTakeoff, estimatedOffblock, internalEstimated, portOpenTime, portCloseTime, targetOffblock, logicalRunway, createTime, updateTime)
                .from(fmsFlight);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<FmsFlight>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, aodbId, airlineCode, airlineSubcompanyCode, flightNumber, movementIndicator, sto, flightType, flightIndicator, aircraftType, registration, linkedId, linkedAodbId, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterId, masterAodbId, masterAirlineCode, masterFlightNumber, eto, ato, currentStand, paxAgent, cancel, remarks, boardingOpen, lastCall, finalTime, approvedTime, engineStart, engineStartRequest, fieldAgent, maintenanceAgent, vipCount, vipFlag, divAirport, divDirection, divReason, retTyp, retRsn, landingAbortIndicator, landingAbortReason, localBagCount, localBagWeight, passengerCount, externalStatusCode, externalStatusComment, statusCode, pedt, neat, padt, naat, rwyCode, calOffblock, calTakeoff, estimatedOffblock, internalEstimated, portOpenTime, portCloseTime, targetOffblock, logicalRunway, createTime, updateTime)
                .from(fmsFlight);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsFlight selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, aodbId, airlineCode, airlineSubcompanyCode, flightNumber, movementIndicator, sto, flightType, flightIndicator, aircraftType, registration, linkedId, linkedAodbId, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterId, masterAodbId, masterAirlineCode, masterFlightNumber, eto, ato, currentStand, paxAgent, cancel, remarks, boardingOpen, lastCall, finalTime, approvedTime, engineStart, engineStartRequest, fieldAgent, maintenanceAgent, vipCount, vipFlag, divAirport, divDirection, divReason, retTyp, retRsn, landingAbortIndicator, landingAbortReason, localBagCount, localBagWeight, passengerCount, externalStatusCode, externalStatusComment, statusCode, pedt, neat, padt, naat, rwyCode, calOffblock, calTakeoff, estimatedOffblock, internalEstimated, portOpenTime, portCloseTime, targetOffblock, logicalRunway, createTime, updateTime)
                .from(fmsFlight)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default FmsFlight selectByAodbId(Long aodbId_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, aodbId, airlineCode, airlineSubcompanyCode, flightNumber, movementIndicator, sto, flightType, flightIndicator, aircraftType, registration, linkedId, linkedAodbId, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterId, masterAodbId, masterAirlineCode, masterFlightNumber, eto, ato, currentStand, paxAgent, cancel, remarks, boardingOpen, lastCall, finalTime, approvedTime, engineStart, engineStartRequest, fieldAgent, maintenanceAgent, vipCount, vipFlag, divAirport, divDirection, divReason, retTyp, retRsn, landingAbortIndicator, landingAbortReason, localBagCount, localBagWeight, passengerCount, externalStatusCode, externalStatusComment, statusCode, pedt, neat, padt, naat, rwyCode, calOffblock, calTakeoff, estimatedOffblock, internalEstimated, portOpenTime, portCloseTime, targetOffblock, logicalRunway, createTime, updateTime)
                .from(fmsFlight)
                .where(aodbId, isEqualTo(aodbId_))
                .build()
                .execute();
    }

    default List<FmsFlight> selectByMasterAodbId(Long masterAodbId_){
        SelectStatementProvider selectStatement = select(id, aodbId, airlineCode, airlineSubcompanyCode, flightNumber, movementIndicator, sto, flightType, flightIndicator, aircraftType, registration, linkedId, linkedAodbId, linkedAirlineCode, linkedFlightNumber, terminal, maxPax, masterId, masterAodbId, masterAirlineCode, masterFlightNumber, eto, ato, currentStand, paxAgent, cancel, remarks, boardingOpen, lastCall, finalTime, approvedTime, engineStart, engineStartRequest, fieldAgent, maintenanceAgent, vipCount, vipFlag, divAirport, divDirection, divReason, retTyp, retRsn, landingAbortIndicator, landingAbortReason, localBagCount, localBagWeight, passengerCount, externalStatusCode, externalStatusComment, statusCode, pedt, neat, padt, naat, rwyCode, calOffblock, calTakeoff, estimatedOffblock, internalEstimated, portOpenTime, portCloseTime, targetOffblock, logicalRunway, createTime, updateTime)
                .from(fmsFlight)
                .where(masterAodbId, isEqualTo(masterAodbId_))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return selectMany(selectStatement);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(FmsFlight record) {
        return UpdateDSL.updateWithMapper(this::update, fmsFlight)
                .set(aodbId).equalTo(record::getAodbId)
                .set(airlineCode).equalTo(record::getAirlineCode)
                .set(airlineSubcompanyCode).equalTo(record::getAirlineSubcompanyCode)
                .set(flightNumber).equalTo(record::getFlightNumber)
                .set(movementIndicator).equalTo(record::getMovementIndicator)
                .set(sto).equalTo(record::getSto)
                .set(flightType).equalTo(record::getFlightType)
                .set(flightIndicator).equalTo(record::getFlightIndicator)
                .set(aircraftType).equalTo(record::getAircraftType)
                .set(registration).equalTo(record::getRegistration)
                .set(linkedId).equalTo(record::getLinkedId)
                .set(linkedAodbId).equalTo(record::getLinkedAodbId)
                .set(linkedAirlineCode).equalTo(record::getLinkedAirlineCode)
                .set(linkedFlightNumber).equalTo(record::getLinkedFlightNumber)
                .set(terminal).equalTo(record::getTerminal)
                .set(maxPax).equalTo(record::getMaxPax)
                .set(masterId).equalTo(record::getMasterId)
                .set(masterAodbId).equalTo(record::getMasterAodbId)
                .set(masterAirlineCode).equalTo(record::getMasterAirlineCode)
                .set(masterFlightNumber).equalTo(record::getMasterFlightNumber)
                .set(eto).equalTo(record::getEto)
                .set(ato).equalTo(record::getAto)
                .set(currentStand).equalTo(record::getCurrentStand)
                .set(paxAgent).equalTo(record::getPaxAgent)
                .set(cancel).equalTo(record::getCancel)
                .set(remarks).equalTo(record::getRemarks)
                .set(boardingOpen).equalTo(record::getBoardingOpen)
                .set(lastCall).equalTo(record::getLastCall)
                .set(finalTime).equalTo(record::getFinalTime)
                .set(approvedTime).equalTo(record::getApprovedTime)
                .set(engineStart).equalTo(record::getEngineStart)
                .set(engineStartRequest).equalTo(record::getEngineStartRequest)
                .set(fieldAgent).equalTo(record::getFieldAgent)
                .set(maintenanceAgent).equalTo(record::getMaintenanceAgent)
                .set(vipCount).equalTo(record::getVipCount)
                .set(vipFlag).equalTo(record::getVipFlag)
                .set(divAirport).equalTo(record::getDivAirport)
                .set(divDirection).equalTo(record::getDivDirection)
                .set(divReason).equalTo(record::getDivReason)
                .set(retTyp).equalTo(record::getRetTyp)
                .set(retRsn).equalTo(record::getRetRsn)
                .set(landingAbortIndicator).equalTo(record::getLandingAbortIndicator)
                .set(landingAbortReason).equalTo(record::getLandingAbortReason)
                .set(localBagCount).equalTo(record::getLocalBagCount)
                .set(localBagWeight).equalTo(record::getLocalBagWeight)
                .set(passengerCount).equalTo(record::getPassengerCount)
                .set(externalStatusCode).equalTo(record::getExternalStatusCode)
                .set(externalStatusComment).equalTo(record::getExternalStatusComment)
                .set(statusCode).equalTo(record::getStatusCode)
                .set(pedt).equalTo(record::getPedt)
                .set(neat).equalTo(record::getNeat)
                .set(padt).equalTo(record::getPadt)
                .set(naat).equalTo(record::getNaat)
                .set(rwyCode).equalTo(record::getRwyCode)
                .set(calOffblock).equalTo(record::getCalOffblock)
                .set(calTakeoff).equalTo(record::getCalTakeoff)
                .set(estimatedOffblock).equalTo(record::getEstimatedOffblock)
                .set(internalEstimated).equalTo(record::getInternalEstimated)
                .set(portOpenTime).equalTo(record::getPortOpenTime)
                .set(portCloseTime).equalTo(record::getPortCloseTime)
                .set(targetOffblock).equalTo(record::getTargetOffblock)
                .set(logicalRunway).equalTo(record::getLogicalRunway)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(FmsFlight record) {
        return UpdateDSL.updateWithMapper(this::update, fmsFlight)
                .set(aodbId).equalToWhenPresent(record::getAodbId)
                .set(airlineCode).equalToWhenPresent(record::getAirlineCode)
                .set(airlineSubcompanyCode).equalToWhenPresent(record::getAirlineSubcompanyCode)
                .set(flightNumber).equalToWhenPresent(record::getFlightNumber)
                .set(movementIndicator).equalToWhenPresent(record::getMovementIndicator)
                .set(sto).equalToWhenPresent(record::getSto)
                .set(flightType).equalToWhenPresent(record::getFlightType)
                .set(flightIndicator).equalToWhenPresent(record::getFlightIndicator)
                .set(aircraftType).equalToWhenPresent(record::getAircraftType)
                .set(registration).equalToWhenPresent(record::getRegistration)
                .set(linkedId).equalToWhenPresent(record::getLinkedId)
                .set(linkedAodbId).equalToWhenPresent(record::getLinkedAodbId)
                .set(linkedAirlineCode).equalToWhenPresent(record::getLinkedAirlineCode)
                .set(linkedFlightNumber).equalToWhenPresent(record::getLinkedFlightNumber)
                .set(terminal).equalToWhenPresent(record::getTerminal)
                .set(maxPax).equalToWhenPresent(record::getMaxPax)
                .set(masterId).equalToWhenPresent(record::getMasterId)
                .set(masterAodbId).equalToWhenPresent(record::getMasterAodbId)
                .set(masterAirlineCode).equalToWhenPresent(record::getMasterAirlineCode)
                .set(masterFlightNumber).equalToWhenPresent(record::getMasterFlightNumber)
                .set(eto).equalToWhenPresent(record::getEto)
                .set(ato).equalToWhenPresent(record::getAto)
                .set(currentStand).equalToWhenPresent(record::getCurrentStand)
                .set(paxAgent).equalToWhenPresent(record::getPaxAgent)
                .set(cancel).equalToWhenPresent(record::getCancel)
                .set(remarks).equalToWhenPresent(record::getRemarks)
                .set(boardingOpen).equalToWhenPresent(record::getBoardingOpen)
                .set(lastCall).equalToWhenPresent(record::getLastCall)
                .set(finalTime).equalToWhenPresent(record::getFinalTime)
                .set(approvedTime).equalToWhenPresent(record::getApprovedTime)
                .set(engineStart).equalToWhenPresent(record::getEngineStart)
                .set(engineStartRequest).equalToWhenPresent(record::getEngineStartRequest)
                .set(fieldAgent).equalToWhenPresent(record::getFieldAgent)
                .set(maintenanceAgent).equalToWhenPresent(record::getMaintenanceAgent)
                .set(vipCount).equalToWhenPresent(record::getVipCount)
                .set(vipFlag).equalToWhenPresent(record::getVipFlag)
                .set(divAirport).equalToWhenPresent(record::getDivAirport)
                .set(divDirection).equalToWhenPresent(record::getDivDirection)
                .set(divReason).equalToWhenPresent(record::getDivReason)
                .set(retTyp).equalToWhenPresent(record::getRetTyp)
                .set(retRsn).equalToWhenPresent(record::getRetRsn)
                .set(landingAbortIndicator).equalToWhenPresent(record::getLandingAbortIndicator)
                .set(landingAbortReason).equalToWhenPresent(record::getLandingAbortReason)
                .set(localBagCount).equalToWhenPresent(record::getLocalBagCount)
                .set(localBagWeight).equalToWhenPresent(record::getLocalBagWeight)
                .set(passengerCount).equalToWhenPresent(record::getPassengerCount)
                .set(externalStatusCode).equalToWhenPresent(record::getExternalStatusCode)
                .set(externalStatusComment).equalToWhenPresent(record::getExternalStatusComment)
                .set(statusCode).equalToWhenPresent(record::getStatusCode)
                .set(pedt).equalToWhenPresent(record::getPedt)
                .set(neat).equalToWhenPresent(record::getNeat)
                .set(padt).equalToWhenPresent(record::getPadt)
                .set(naat).equalToWhenPresent(record::getNaat)
                .set(rwyCode).equalToWhenPresent(record::getRwyCode)
                .set(calOffblock).equalToWhenPresent(record::getCalOffblock)
                .set(calTakeoff).equalToWhenPresent(record::getCalTakeoff)
                .set(estimatedOffblock).equalToWhenPresent(record::getEstimatedOffblock)
                .set(internalEstimated).equalToWhenPresent(record::getInternalEstimated)
                .set(portOpenTime).equalToWhenPresent(record::getPortOpenTime)
                .set(portCloseTime).equalToWhenPresent(record::getPortCloseTime)
                .set(targetOffblock).equalToWhenPresent(record::getTargetOffblock)
                .set(logicalRunway).equalToWhenPresent(record::getLogicalRunway)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FmsFlight record) {
        return UpdateDSL.updateWithMapper(this::update, fmsFlight)
                .set(aodbId).equalTo(record::getAodbId)
                .set(airlineCode).equalTo(record::getAirlineCode)
                .set(airlineSubcompanyCode).equalTo(record::getAirlineSubcompanyCode)
                .set(flightNumber).equalTo(record::getFlightNumber)
                .set(movementIndicator).equalTo(record::getMovementIndicator)
                .set(sto).equalTo(record::getSto)
                .set(flightType).equalTo(record::getFlightType)
                .set(flightIndicator).equalTo(record::getFlightIndicator)
                .set(aircraftType).equalTo(record::getAircraftType)
                .set(registration).equalTo(record::getRegistration)
                .set(linkedId).equalTo(record::getLinkedId)
                .set(linkedAodbId).equalTo(record::getLinkedAodbId)
                .set(linkedAirlineCode).equalTo(record::getLinkedAirlineCode)
                .set(linkedFlightNumber).equalTo(record::getLinkedFlightNumber)
                .set(terminal).equalTo(record::getTerminal)
                .set(maxPax).equalTo(record::getMaxPax)
                .set(masterId).equalTo(record::getMasterId)
                .set(masterAodbId).equalTo(record::getMasterAodbId)
                .set(masterAirlineCode).equalTo(record::getMasterAirlineCode)
                .set(masterFlightNumber).equalTo(record::getMasterFlightNumber)
                .set(eto).equalTo(record::getEto)
                .set(ato).equalTo(record::getAto)
                .set(currentStand).equalTo(record::getCurrentStand)
                .set(paxAgent).equalTo(record::getPaxAgent)
                .set(cancel).equalTo(record::getCancel)
                .set(remarks).equalTo(record::getRemarks)
                .set(boardingOpen).equalTo(record::getBoardingOpen)
                .set(lastCall).equalTo(record::getLastCall)
                .set(finalTime).equalTo(record::getFinalTime)
                .set(approvedTime).equalTo(record::getApprovedTime)
                .set(engineStart).equalTo(record::getEngineStart)
                .set(engineStartRequest).equalTo(record::getEngineStartRequest)
                .set(fieldAgent).equalTo(record::getFieldAgent)
                .set(maintenanceAgent).equalTo(record::getMaintenanceAgent)
                .set(vipCount).equalTo(record::getVipCount)
                .set(vipFlag).equalTo(record::getVipFlag)
                .set(divAirport).equalTo(record::getDivAirport)
                .set(divDirection).equalTo(record::getDivDirection)
                .set(divReason).equalTo(record::getDivReason)
                .set(retTyp).equalTo(record::getRetTyp)
                .set(retRsn).equalTo(record::getRetRsn)
                .set(landingAbortIndicator).equalTo(record::getLandingAbortIndicator)
                .set(landingAbortReason).equalTo(record::getLandingAbortReason)
                .set(localBagCount).equalTo(record::getLocalBagCount)
                .set(localBagWeight).equalTo(record::getLocalBagWeight)
                .set(passengerCount).equalTo(record::getPassengerCount)
                .set(externalStatusCode).equalTo(record::getExternalStatusCode)
                .set(externalStatusComment).equalTo(record::getExternalStatusComment)
                .set(statusCode).equalTo(record::getStatusCode)
                .set(pedt).equalTo(record::getPedt)
                .set(neat).equalTo(record::getNeat)
                .set(padt).equalTo(record::getPadt)
                .set(naat).equalTo(record::getNaat)
                .set(rwyCode).equalTo(record::getRwyCode)
                .set(calOffblock).equalTo(record::getCalOffblock)
                .set(calTakeoff).equalTo(record::getCalTakeoff)
                .set(estimatedOffblock).equalTo(record::getEstimatedOffblock)
                .set(internalEstimated).equalTo(record::getInternalEstimated)
                .set(portOpenTime).equalTo(record::getPortOpenTime)
                .set(portCloseTime).equalTo(record::getPortCloseTime)
                .set(targetOffblock).equalTo(record::getTargetOffblock)
                .set(logicalRunway).equalTo(record::getLogicalRunway)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FmsFlight record) {
        return UpdateDSL.updateWithMapper(this::update, fmsFlight)
                .set(aodbId).equalToWhenPresent(record::getAodbId)
                .set(airlineCode).equalToWhenPresent(record::getAirlineCode)
                .set(airlineSubcompanyCode).equalToWhenPresent(record::getAirlineSubcompanyCode)
                .set(flightNumber).equalToWhenPresent(record::getFlightNumber)
                .set(movementIndicator).equalToWhenPresent(record::getMovementIndicator)
                .set(sto).equalToWhenPresent(record::getSto)
                .set(flightType).equalToWhenPresent(record::getFlightType)
                .set(flightIndicator).equalToWhenPresent(record::getFlightIndicator)
                .set(aircraftType).equalToWhenPresent(record::getAircraftType)
                .set(registration).equalToWhenPresent(record::getRegistration)
                .set(linkedId).equalToWhenPresent(record::getLinkedId)
                .set(linkedAodbId).equalToWhenPresent(record::getLinkedAodbId)
                .set(linkedAirlineCode).equalToWhenPresent(record::getLinkedAirlineCode)
                .set(linkedFlightNumber).equalToWhenPresent(record::getLinkedFlightNumber)
                .set(terminal).equalToWhenPresent(record::getTerminal)
                .set(maxPax).equalToWhenPresent(record::getMaxPax)
                .set(masterId).equalToWhenPresent(record::getMasterId)
                .set(masterAodbId).equalToWhenPresent(record::getMasterAodbId)
                .set(masterAirlineCode).equalToWhenPresent(record::getMasterAirlineCode)
                .set(masterFlightNumber).equalToWhenPresent(record::getMasterFlightNumber)
                .set(eto).equalToWhenPresent(record::getEto)
                .set(ato).equalToWhenPresent(record::getAto)
                .set(currentStand).equalToWhenPresent(record::getCurrentStand)
                .set(paxAgent).equalToWhenPresent(record::getPaxAgent)
                .set(cancel).equalToWhenPresent(record::getCancel)
                .set(remarks).equalToWhenPresent(record::getRemarks)
                .set(boardingOpen).equalToWhenPresent(record::getBoardingOpen)
                .set(lastCall).equalToWhenPresent(record::getLastCall)
                .set(finalTime).equalToWhenPresent(record::getFinalTime)
                .set(approvedTime).equalToWhenPresent(record::getApprovedTime)
                .set(engineStart).equalToWhenPresent(record::getEngineStart)
                .set(engineStartRequest).equalToWhenPresent(record::getEngineStartRequest)
                .set(fieldAgent).equalToWhenPresent(record::getFieldAgent)
                .set(maintenanceAgent).equalToWhenPresent(record::getMaintenanceAgent)
                .set(vipCount).equalToWhenPresent(record::getVipCount)
                .set(vipFlag).equalToWhenPresent(record::getVipFlag)
                .set(divAirport).equalToWhenPresent(record::getDivAirport)
                .set(divDirection).equalToWhenPresent(record::getDivDirection)
                .set(divReason).equalToWhenPresent(record::getDivReason)
                .set(retTyp).equalToWhenPresent(record::getRetTyp)
                .set(retRsn).equalToWhenPresent(record::getRetRsn)
                .set(landingAbortIndicator).equalToWhenPresent(record::getLandingAbortIndicator)
                .set(landingAbortReason).equalToWhenPresent(record::getLandingAbortReason)
                .set(localBagCount).equalToWhenPresent(record::getLocalBagCount)
                .set(localBagWeight).equalToWhenPresent(record::getLocalBagWeight)
                .set(passengerCount).equalToWhenPresent(record::getPassengerCount)
                .set(externalStatusCode).equalToWhenPresent(record::getExternalStatusCode)
                .set(externalStatusComment).equalToWhenPresent(record::getExternalStatusComment)
                .set(statusCode).equalToWhenPresent(record::getStatusCode)
                .set(pedt).equalToWhenPresent(record::getPedt)
                .set(neat).equalToWhenPresent(record::getNeat)
                .set(padt).equalToWhenPresent(record::getPadt)
                .set(naat).equalToWhenPresent(record::getNaat)
                .set(rwyCode).equalToWhenPresent(record::getRwyCode)
                .set(calOffblock).equalToWhenPresent(record::getCalOffblock)
                .set(calTakeoff).equalToWhenPresent(record::getCalTakeoff)
                .set(estimatedOffblock).equalToWhenPresent(record::getEstimatedOffblock)
                .set(internalEstimated).equalToWhenPresent(record::getInternalEstimated)
                .set(portOpenTime).equalToWhenPresent(record::getPortOpenTime)
                .set(portCloseTime).equalToWhenPresent(record::getPortCloseTime)
                .set(targetOffblock).equalToWhenPresent(record::getTargetOffblock)
                .set(logicalRunway).equalToWhenPresent(record::getLogicalRunway)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}