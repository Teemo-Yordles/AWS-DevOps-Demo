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
 * @author LiuJ2
 * @since 2020/9/7 16:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ABDGParsingTest {

    @Test
    public void testABDGParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>ABDG</TYPE>\n" +
                "  <STYP>ADD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                " <ABDG>\n" +
                "<ABCD>212</ABCD>\n" +
                "<DESC>Airbridge 212</DESC>\n" +
                "<CDSC>登机桥 212</CDSC>\n" +
                "<AHGT></AHGT>\n" +
                "<ATML>T2</ATML>\n" +
                "<ATHE>N</ATHE>\n" +
                "<STND>212</STND>\n" +
                "<GATE>21</GATE>\n" +
                "<AMNO>1</AMNO>\n" +
                "</ABDG>\n" +
                "<ABDG>\n" +
                "<ABCD>112</ABCD>\n" +
                "<DESC>Airbridge 112</DESC>\n" +
                "<CDSC>登机桥 112</CDSC>\n" +
                "<AHGT></AHGT>\n" +
                "<ATML>T1</ATML>\n" +
                "<ATHE>Y</ATHE>\n" +
                "<STND>112</STND>\n" +
                "<STND>111</STND>\n" +
                "<GATE>11A</GATE>\n" +
                "<GATE>11B</GATE>\n" +
                "<AMNO>2</AMNO>\n" +

                " </ABDG>\n" +
                "</MSG>\n";
        ABDG parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, ABDG.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getAbdgs().size() == 2).isTrue();
    }
}