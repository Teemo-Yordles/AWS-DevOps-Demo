/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * A flight record in the daily schedule message body
 *
 * @author LiuJ2
 * @since 2020/10/10 13:44
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FLTR {
    // Unique AODB ID for identifying a flight
    @JacksonXmlProperty(localName = "FLID")
    private Long FLID;

    /**
     * Airline code.
     * IATA operator code.
     */
    @JacksonXmlProperty(localName = "ALCD")
    private String ALCD;
    // Airline subcompany code
    @JacksonXmlProperty(localName = "ALSC")
    private String ALSC;

    /**
     * Flight number.
     */
    @JacksonXmlProperty(localName = "FLNO")
    private String FLNO;

    /**
     * Movement indicator.
     * A – Arrival
     * D – Departure
     */
    @JacksonXmlProperty(localName = "MVIN")
    private Character MVIN;
    // Scheduled operation date/time
    @JacksonXmlProperty(localName = "SODT")
    private String SODT;

    /**
     * Flight type like passenger, cargo, military, VIP etc.
     */
    @JacksonXmlProperty(localName = "FLTY")
    private Character FLTY;

    /**
     * Flight indicator.
     * D – Domestic
     * I – International
     * M – Mixed
     */
    @JacksonXmlProperty(localName = "FLIN")
    private Character FLIN;

    /**
     * Aircraft type used in this flight.
     * IATA aircraft type.
     */
    @JacksonXmlProperty(localName = "ACFT")
    private String ACFT;

    /**
     * Registration or tail number of flight.
     */
    @JacksonXmlProperty(localName = "RENO")
    private String RENO;

    /**
     * Turnaround operator when it leaves current airport.
     * IATA operator code
     * Present only for arrivals.
     */
    @JacksonXmlProperty(localName = "TAOP")
    private String TAOP;

    /**
     * Turnaround flight number when it leaves current airport.
     * Present only for arrivals.
     */
    @JacksonXmlProperty(localName = "TAFL")
    private String TAFL;
    // turnaround flight's flight id
    @JacksonXmlProperty(localName = "TAID")
    private Long TAID;

    /**
     * Terminal where the flight operates.
     */
    @JacksonXmlProperty(localName = "TRML")
    private String TRML;

    /**
     * Maximum passengers that this flight can carry.
     */
    @JacksonXmlProperty(localName = "MAXP")
    private Short MAXP;

    /**
     * Operator code of master flight if this is a code share flight.
     * IATA operator code
     */
    @JacksonXmlProperty(localName = "CSOP")
    private String CSOP;

    /**
     * Flight number of master flight if this is a code share flight.
     */
    @JacksonXmlProperty(localName = "CSFT")
    private String CSFT;
    // master flight's flight id
    @JacksonXmlProperty(localName = "MAID")
    private Long MAID;
    // estimate time
    @JacksonXmlProperty(localName = "ESTT")
    private String ESTT;
    // actual time
    @JacksonXmlProperty(localName = "ACTT")
    private String ACTT;
    // chute allocation
    @JacksonXmlProperty(localName = "CHDT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CHDT> chdts;
    // gate allocation
    @JacksonXmlProperty(localName = "GTDT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<GTDT> gtdts;
    // current stand
    @JacksonXmlProperty(localName = "STND")
    private String STND;
    // stand allocation
    @JacksonXmlProperty(localName = "PSDT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PSDT> psdts;
    // counter allocation
    @JacksonXmlProperty(localName = "CKDT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CKDT> ckdts;
    // Passenger handling agent
    @JacksonXmlProperty(localName = "PHAG")
    private Integer PHAG;
    // cancellation date/time
    @JacksonXmlProperty(localName = "CNCL")
    private String CNCL;
    // delays
    @JacksonXmlProperty(localName = "DELY")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DELY> delys;
    // remarks
    @JacksonXmlProperty(localName = "REMC")
    private String REMC;
    // carousel allocation
    @JacksonXmlProperty(localName = "CLDT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CLDT> cldts;
    // boarding open
    @JacksonXmlProperty(localName = "BOTM")
    private String BOTM;
    // last call
    @JacksonXmlProperty(localName = "LACL")
    private String LACL;
    // final time
    @JacksonXmlProperty(localName = "FINT")
    private String FINT;
    // chock on/off records
    @JacksonXmlProperty(localName = "CHOT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CHOT> chots;
    // approved time
    @JacksonXmlProperty(localName = "APPT")
    private String APPT;
    // engine start request time
    @JacksonXmlProperty(localName = "EGSR")
    private String EGSR;
    // engine start time
    @JacksonXmlProperty(localName = "EGST")
    private String EGST;
    // Ground handling agent
    @JacksonXmlProperty(localName = "FHAG")
    private Integer FHAG;
    // Maintenance handling agent
    @JacksonXmlProperty(localName = "MHAG")
    private Integer MHAG;
    // VIP passenger number
    @JacksonXmlProperty(localName = "VIPP")
    private Short VIPP;
    // VIP ranking
    @JacksonXmlProperty(localName = "VIPR")
    private Byte VIPR;
    // FDIV
    @JacksonXmlProperty(localName = "FDIV")
    private FDIV fdiv;
    // FRET
    @JacksonXmlProperty(localName = "FRET")
    private FRET fret;
    // airbridge usage records
    @JacksonXmlProperty(localName = "ABTM")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ABTM> abtms;
    // FLAB
    @JacksonXmlProperty(localName = "FLAB")
    private FLAB flab;
    // services
    @JacksonXmlProperty(localName = "SRVT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<SRVT> srvts;
    // vip transactions
    @JacksonXmlProperty(localName = "VIPF")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<VIPF> vipfs;
    // local bag count
    @JacksonXmlProperty(localName = "LBNO")
    private Short LBNO;
    // local bag weight
    @JacksonXmlProperty(localName = "LBWT")
    private BigDecimal LBWT;
    // total passenger count
    @JacksonXmlProperty(localName = "PAXC")
    private Short PAXC;

    /**
     * Entire flight routing, at least two routing records are expected for normal flights.
     */
    @JacksonXmlProperty(localName = "ERUT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ERUT> eruts;
    // external status code
    @JacksonXmlProperty(localName = "EXSC")
    private String EXSC;
    // external status remarks
    @JacksonXmlProperty(localName = "EXSR")
    private String EXSR;
    // Flight status code
    @JacksonXmlProperty(localName = "FTSS")
    private String FTSS;
    // previous step estimate time
    @JacksonXmlProperty(localName = "PEDT")
    private String PEDT;
    // next step estimate time
    @JacksonXmlProperty(localName = "NEAT")
    private String NEAT;
    // previous step actual time
    @JacksonXmlProperty(localName = "PADT")
    private String PADT;
    // next step actual time
    @JacksonXmlProperty(localName = "NAAT")
    private String NAAT;

    /**
     * Start date of the validity period for seasonal schedules.
     * DDMONYY
     */
    @JacksonXmlProperty(localName = "STDT")
    private String STDT;

    /**
     * End date of the validity period for seasonal schedules.
     * DDMONYY
     */
    @JacksonXmlProperty(localName = "EDDT")
    private String EDDT;

    /**
     * Days of operation in a week.
     * 1-7 = Monday – Sunday
     * 0 – Flight does not operate on that day
     */
    @JacksonXmlProperty(localName = "DYOP")
    private String DYOP;

    /**
     * Schedule time of flight. STA in case of arrival and STD in case of departure.
     * HHMM
     * Local time 24 hr format.
     */
    @JacksonXmlProperty(localName = "SCHT")
    private String SCHT;

    /***
     * Port open/close time data section.
     */
    @JacksonXmlProperty(localName = "PORT")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PORT> ports;

    /***
     * Calculated off block time.
     */
    @JacksonXmlProperty(localName = "COBT")
    private String COBT;

    /***
     * Target off block time.
     */
    @JacksonXmlProperty(localName = "TOBT")
    private String TOBT;

    /***
     * Estimated off block time.
     */
    @JacksonXmlProperty(localName = "EOBT")
    private String EOBT;

    /***
     * Internal estimated time.
     */
    @JacksonXmlProperty(localName = "IEST")
    private String IEST;

    /***
     * Logical Runway.
     */
    @JacksonXmlProperty(localName = "RUNW")
    private String RUNW;

    /***
     * Tractor in place time data.
     */
    @JacksonXmlProperty(localName = "TIPT")
    private TIPT tipt;

}
