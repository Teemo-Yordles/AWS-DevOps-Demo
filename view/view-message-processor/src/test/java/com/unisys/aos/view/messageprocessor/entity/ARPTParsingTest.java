package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
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
 * @author jianglushuang
 * @since 2020/9/23 9:51 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ARPTParsingTest {

    @Test
    public void testARPTParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>ARPT</TYPE>\n" +
                "  <STYP>DNLD</STYP>\n" +
                " </META>\n" +
                " <RECS>3</RECS>\n" +
                " <ARPT>\n" +
                "  <ITCD>SHA</ITCD>\n" +
                "  <ICCD>SHHA</ICCD>\n" +
                "  <ANAM>SHANGHAI HONGQIAO INTL AIRPORT</ANAM>\n" +
                "  <ANMC>上海虹桥国际机场</ANMC>\n" +
                "  <BDIS>1200</BDIS>\n" +
                "  <CTRY>PRC</CTRY>\n" +
                "  <ACTY>SHA</ACTY>\n" +
                "  <ATYP>D</ATYP>\n" +
                "  <HAUL>S</HAUL>\n" +
                "     <ABBR CODE=\"ABR1\">HU</ABBR>\n" +
                "     <ABBR CODE=\"ABR2\">沪</ABBR>\n" +
                "     <ABBR CODE=\"ABR3\">SHANGHAI</ABBR>\n" +
                "     <ABBR CODE=\"ABR4\">上海</ABBR>\n" +
                "     <ABBR CODE=\"ABR5\">HONGQIAO AIRPORT</ABBR>\n" +
                "     <ABBR CODE=\"ABR6\">上海虹桥</ABBR>\n" +
                " </ARPT>\n" +
                " <ARPT>\n" +
                "  <ITCD>MEL</ITCD>\n" +
                "  <ICCD>AMML</ICCD>\n" +
                "  <ANAM>MELBOURNE TULLMARINE</ANAM>\n" +
                "  <ANMC>墨尔本国际机场</ANMC>\n" +
                "  <BDIS>4500</BDIS>\n" +
                "  <CTRY>AUS</CTRY>\n" +
                "  <ACTY>MEL</ACTY>\n" +
                "  <ATYP>I</ATYP>\n" +
                "  <HAUL>L</HAUL>\n" +
                " </ARPT>\n" +
                " <ARPT>\n" +
                "  <ITCD>PEK</ITCD>\n" +
                "  <ICCD>ZBAA</ICCD>\n" +
                "  <ANAM>BEIJING CAPITAL AIRPORT</ANAM>\n" +
                "  <ANMC>北京首都国际机场</ANMC>\n" +
                "  <BDIS>1900</BDIS>\n" +
                "  <CTRY>PRC</CTRY>\n" +
                "  <ACTY>BJS</ACTY>\n" +
                "  <ATYP>D</ATYP>\n" +
                "  <HAUL>S</HAUL>\n" +
                "     <ABBR CODE=\"ABR5\">BEIJING</ABBR>\n" +
                "     <ABBR CODE=\"ABR6\">北京首都</ABBR>\n" +
                " </ARPT>\n" +
                "</MSG>";
        System.out.println(xml);
        ARPT parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, ARPT.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getAirports().size()==3).isTrue();
    }
}