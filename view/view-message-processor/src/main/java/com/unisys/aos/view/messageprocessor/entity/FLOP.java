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
 * The <b>FLOP</b> class is a POJO entity that represents the operational data in message.
 * This class includes all the tags for input messages.
 * The operational data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author Zhang Wenqiang
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class FLOP extends MSG {
    /**
     * The FLOP message body list
     */
    @JacksonXmlProperty(localName = "FLOP")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FlopData> flops;

    /**
     * A FLOP record in the body
     */
    @Data
    @NoArgsConstructor
    public static class FlopData {
        // Unique AODB ID for identifying a flight
        @JacksonXmlProperty(localName = "FLID")
        private Long FLID;
        // Unique FFID ID for identifying a flight
        @JacksonXmlProperty(localName = "FFID")
        private String FFID;
        // airline code
        @JacksonXmlProperty(localName = "ALCD")
        private String ALCD;
        // Airline subcompany code
        @JacksonXmlProperty(localName = "ALSC")
        private String ALSC;
        // Flight Number
        @JacksonXmlProperty(localName = "FLNO")
        private String FLNO;
        // Movement indicator
        @JacksonXmlProperty(localName = "MVIN")
        private Character MVIN;
        // Scheduled operation date/time
        @JacksonXmlProperty(localName = "SODT")
        private String SODT;
        // flight type
        @JacksonXmlProperty(localName = "FLTY")
        private Character FLTY;
        // flight indicator
        @JacksonXmlProperty(localName = "FLIN")
        private Character FLIN;
        // aircraft type
        @JacksonXmlProperty(localName = "ACFT")
        private String ACFT;
        // registration number
        @JacksonXmlProperty(localName = "RENO")
        private String RENO;
        // turnaround flight's operator
        @JacksonXmlProperty(localName = "TAOP")
        private String TAOP;
        // turnaround flight's flight number
        @JacksonXmlProperty(localName = "TAFL")
        private String TAFL;
        // turnaround flight's flight id
        @JacksonXmlProperty(localName = "TAID")
        private Long TAID;
        // terminal
        @JacksonXmlProperty(localName = "TRML")
        private String TRML;
        // maximum passengers
        @JacksonXmlProperty(localName = "MAXP")
        private Short MAXP;
        // master flight's operator
        @JacksonXmlProperty(localName = "CSOP")
        private String CSOP;
        // master flight's flight number
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
        @JacksonXmlProperty(localName = "FDEL")
        private String FDEL;
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
        // entire route
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
//        @JacksonXmlProperty(localName = "TIPT")
//        @JacksonXmlElementWrapper(useWrapping = false)
//        private List<TIPT> tipts;
        //This identifies the start of push time data section.
        @JacksonXmlProperty(localName = "PSHT")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<PSHT> pshts;
        //This identifies the start of tractor in place time data section.
        @JacksonXmlProperty(localName = "TIPT")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<TIPT> tipts;
        //Estimated off block time
        @JacksonXmlProperty(localName = "EOBT")
        private String EOBT;
        //Internal Estimated time
        @JacksonXmlProperty(localName = "IEST")
        private String IEST;
        //This identifies the start of port time data section.
        @JacksonXmlProperty(localName = "PORT")
        private PORT PORT;
        //Calculated off block time
        @JacksonXmlProperty(localName = "COBT")
        private String COBT;
        //Target off block time
        @JacksonXmlProperty(localName = "TOBT")
        private String TOBT;
        //Calculated take off time
        @JacksonXmlProperty(localName = "CTOT")
        private String CTOT;
        //Logical Runway
        @JacksonXmlProperty(localName = "RUNW")
        private String RUNW;
    }
}
