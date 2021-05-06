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
public class SLSTParsingTest {

    @Test
    public void testSLSTParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>SLST</TYPE>\n" +
                "  <STYP>ADD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                " <SLST>\n" +
                "<SCOD>S209</SCOD>\n" +
                "<STNM>South Stand 209</STNM>\n" +
                "<SNMC>南方机位209</SNMC>\n" +
                "<SATC>C</SATC>\n" +
                "<STGP>T1 SOUTH</STGP>\n" +
                "<SWID></SWID>\n" +
                "<SHGT></SHGT>\n" +
                "<STML>T1</STML>\n" +
                "<MABS>2</MABS>\n" +
                "<DIGT></DIGT>\n" +
                "<DDGT></DDGT>\n" +
                "<REMT>N</REMT>\n" +
                "<FEPU></FEPU>\n" +
                " </SLST>\n" +
                " <SLST>\n" +
                "<SCOD>S109</SCOD>\n" +
                "<STNM>South Stand 109</STNM>\n" +
                "<SNMC>南方机位109</SNMC>\n" +
                "<SATC>C</SATC>\n" +
                "<STGP>T1 SOUTH</STGP>\n" +
                "<SWID></SWID>\n" +
                "<SHGT></SHGT>\n" +
                "<STML>T1</STML>\n" +
                "<MABS>2</MABS>\n" +
                "<DIGT></DIGT>\n" +
                "<DDGT></DDGT>\n" +
                "<REMT>N</REMT>\n" +
                "<FEPU></FEPU>\n" +
                " </SLST>\n" +
                "</MSG>\n";
        SLST parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, SLST.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getSlsts().size() == 2).isTrue();
    }
}