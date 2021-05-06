/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The <b>AIRC</b> class is a POJO entity that represents the aircraft for reference data in message.
 * This class includes all the tags for input AIRCRAFT messages.
 * The Reference data in message definition can be referred to the section 3 of the
 * document SIS_AODB_RMS.doc.
 *
 * @author LiuJ2
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
public class AIRC extends MSG {
	/**
	 * A aircraft record in the body
	 */
	@Data
	@NoArgsConstructor
	public static class AircraftTypeData {
		// The IATA code for aircraft type
		@JacksonXmlProperty(localName = "ITAT")
		private String ITAT;
		// The ICAT code for aircraft type
		@JacksonXmlProperty(localName = "ICAT")
		private String ICAT;
		// Full description of the aircraft type
		@JacksonXmlProperty(localName = "DESC")
		private String DESC;
		// Chinese description of the aircraft type
		@JacksonXmlProperty(localName = "CDSC")
		private String CDSC;
		// Aircraft noise chapter code
		@JacksonXmlProperty(localName = "CHAP")
		private String CHAP;
		// Maximum passengers aircraft can carry
		@JacksonXmlProperty(localName = "MAXP")
		private Short MAXP;
		/**
		 * Maximum freight weight
		 */
		@JacksonXmlProperty(localName = "MFWT")
		private BigDecimal MFWT;
		/**
		 * Maximum takeoff weight
		 */
		@JacksonXmlProperty(localName = "MTWT")
		private BigDecimal MTWT;
		// Aircraft length
		@JacksonXmlProperty(localName = "ALEN")
		private BigDecimal ALEN;
		// Aircraft Wing span
		@JacksonXmlProperty(localName = "WSPN")
		private BigDecimal WSPN;
		// Minimum handling time
		@JacksonXmlProperty(localName = "MHTM")
		private BigDecimal MHTM;
		/**
		 * Maximum air bridges can be connected
		 */
		@JacksonXmlProperty(localName = "MABR")
		private Byte MABR;
	}

	/**
	 * The Aircraft message body list
	 */
	@JacksonXmlProperty(localName = "AIRC")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<AircraftTypeData> aircs;
	/**
	 * The record count for reference data.
	 */
	@JacksonXmlProperty(localName = "RECS")
	private Integer RECS;
}
