/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author LiuJ2
 * @since 2020/10/10 11:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SCHDParsingTest {
    @Test
    public void testSCHDParsing() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"unisysaodbsis.xsd\">\n" +
                "    <META>\n" +
                "        <SNDR>AODB</SNDR>\n" +
                "<SEQN>345935</SEQN>\n" +
                "<DTTM>20201105223126</DTTM>\n" +
                "<TYPE>SCHD</TYPE>\n" +
                "<STYP>DNLD</STYP>\n" +
                "</META>\n" +
                "<SCHD>\n" +
                "<RECS>2</RECS>\n" +
                "<FLTR>\n" +
                "<FLID>15</FLID>\n" +
                "<ALCD>YG</ALCD>\n" +
                "<FLNO>YG9028</FLNO>\n" +
                "<MVIN>D</MVIN>\n" +
                "<SODT>16Nov200535</SODT>\n" +
                "<FLTY>W</FLTY>\n" +
                "<FLIN>D</FLIN>\n" +
                "<ROUT RTNO = \"1\">\n" +
                "<APCD>XIY</APCD>\n" +
                "<SCAT>16NOV200710</SCAT>\n" +
                "</ROUT>\n" +
                "<ACFT>733</ACFT>\n" +
                "<RENO>B2574</RENO>\n" +
                "<TAOP>YG</TAOP>\n" +
                "<TAFL>YG9061</TAFL>\n" +
                "<TAID>16</TAID>\n" +
                "<TRML>T2</TRML>\n" +
                "<MAXP>300</MAXP>\n" +
                "<CHDT CHNO=\"1\">\n" +
                "   <CHUT>CH0021</CHUT>\n" +
                "   <CCLS>X</CCLS>\n" +
                "   <PCBT>15DEC031530</PCBT>\n" +
                "   <PCET>15DEC031830</PCET>\n" +
                "   <CBTM>15DEC031520</CBTM>\n" +
                "   <CETM>15DEC031900</CETM>\n" +
                "   <CTYP>D</CTYP>\n" +
                "</CHDT>\n" +
                "<CHDT CHNO=\"2\">\n" +
                "   <CHUT>CH0025</CHUT>\n" +
                "   <CCLS>X</CCLS>\n" +
                "   <PCBT>15DEC031530</PCBT>\n" +
                "   <PCET>15DEC031830</PCET>\n" +
                "   <CBTM>15DEC031525</CBTM>\n" +
                "   <CETM>15DEC031900</CETM>\n" +
                "   <CTYP>D</CTYP>\n" +
                "</CHDT>\n" +
                "<GTDT GTNO=\"1\">\n" +
                "   <GATE>G28</GATE>\n" +
                "   <PGOT>15DEC031805</PGOT>\n" +
                "   <PGCT>15DEC031925</PGCT>\n" +
                "   <GOTM>15DEC031825</GOTM>\n" +
                "   <GTYP>D</GTYP>\n" +
                "</GTDT>\n" +
                "<GTDT GTNO=\"2\">\n" +
                "   <GATE>G33</GATE>\n" +
                "   <PGOT>15DEC031805</PGOT>\n" +
                "   <PGCT>15DEC031925</PGCT>\n" +
                "   <GTYP>I</GTYP>\n" +
                "</GTDT>\n" +
                "<GTDT GTNO=\"3\">\n" +
                "   <GATE>G23</GATE>\n" +
                "   <PGOT>15DEC031805</PGOT>\n" +
                "   <PGCT>15DEC031925</PGCT>\n" +
                "   <GTYP>D</GTYP>\n" +
                "</GTDT>\n" +
                "<STND>504</STND>\n" +
                "<PSDT PSNO=\"1\">\n" +
                "<PSST>504</PSST>\n" +
                "<STST>16NOV200237</STST>\n" +
                "<STET>16NOV200549</STET>\n" +
                "</PSDT>\n" +
                "<CKDT CKNO=\"1\">\n" +
                "   <CHKC>01</CHKC>\n" +
                "   <CCLS>X</CCLS>\n" +
                "   <PCOT>15DEC031515</PCOT>\n" +
                "   <PCCT>15DEC031725</PCCT>\n" +
                "   <COTM>15DEC031525</COTM>\n" +
                "   <CTYP>D</CTYP>\n" +
                "</CKDT>\n" +
                "<PHAG>110009</PHAG>\n" +
                "<FHAG>110009</FHAG>\n" +
                "<MHAG>110009</MHAG>\n" +
                "<FDIV DDES=\"HKG\" DDIR=\"TO\">weather</FDIV>\n" +
                "<FRET REID=\"A\">weather return</FRET>\n" +
                "<FLAB ARES=\"O\">over shot</FLAB>\n" +
                "<ERUT RTNO = \"1\">\n" +
                "<APCD>CTU</APCD>\n" +
                "<SCDT>16NOV200535</SCDT>\n" +
                "</ERUT>\n" +
                "<ERUT RTNO = \"2\">\n" +
                "<APCD>XIY</APCD>\n" +
                "<SCAT>16NOV200710</SCAT>\n" +
                "</ERUT>\n" +
                "<EXSC>RCFLOP</EXSC>\n" +
                "<FTSS>RCFLOP</FTSS>\n" +
                "</FLTR>\n" +
                "<FLTR>\n" +
                "<FLID>16</FLID>\n" +
                "<ALCD>YG</ALCD>\n" +
                "<FLNO>YG9061</FLNO>\n" +
                "<MVIN>A</MVIN>\n" +
                "<SODT>16NOV200325</SODT>\n" +
                "<FLTY>W</FLTY>\n" +
                "<FLIN>D</FLIN>\n" +
                "<ROUT RTNO = \"1\">\n" +
                "<APCD>HGH</APCD>\n" +
                "<SCDT>16NOV200010</SCDT>\n" +
                "</ROUT>\n" +
                "<ACFT>733</ACFT>\n" +
                "<RENO>B2574</RENO>\n" +
                "<TAOP>YG</TAOP>\n" +
                "<TAFL>YG9028</TAFL>\n" +
                "<TAID>15</TAID>\n" +
                "<TRML>T2</TRML>\n" +
                "<MAXP>300</MAXP>\n" +
                "<STND>504</STND>\n" +
                "<PSDT PSNO=\"1\">\n" +
                "<PSST>504</PSST>\n" +
                "<STST>16NOV200237</STST>\n" +
                "<STET>16NOV200549</STET>\n" +
                "</PSDT>\n" +
                "<PHAG>110009</PHAG>\n" +
                "<CLDT CLNO=\"1\">\n" +
                "   <BELT>B01</BELT>\n" +
                "   <BCLS>X</BCLS>\n" +
                "   <PCOT>12DEC031330</PCOT>\n" +
                "   <PCCT>12DEC031430</PCCT>\n" +
                "   <BTYP>D</BTYP>\n" +
                "</CLDT>\n" +
                "<CLDT CLNO=\"2\">\n" +
                "   <BELT>B02</BELT>\n" +
                "   <BCLS>X</BCLS>\n" +
                "   <PCOT>12DEC031330</PCOT>\n" +
                "   <PCCT>12DEC031430</PCCT>\n" +
                "   <BTYP>D</BTYP>\n" +
                "</CLDT>\n" +
                "<FHAG>110009</FHAG>\n" +
                "<MHAG>110009</MHAG>\n" +
                "<ERUT RTNO = \"1\">\n" +
                "<APCD>HGH</APCD>\n" +
                "<SCDT>16NOV200010</SCDT>\n" +
                "</ERUT>\n" +
                "<ERUT RTNO = \"2\">\n" +
                "<APCD>CTU</APCD>\n" +
                "<SCAT>16NOV200325</SCAT>\n" +
                "</ERUT>\n" +
                "<EXSC>RCFLOP</EXSC>\n" +
                "<FTSS>RCFLOP</FTSS>\n" +
                "</FLTR>\n" +
                "</SCHD>\n" +
                "</MSG>";
        ScheduleMessage parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, ScheduleMessage.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getSchd().getFltrs().size()==2).isTrue();
    }
}
