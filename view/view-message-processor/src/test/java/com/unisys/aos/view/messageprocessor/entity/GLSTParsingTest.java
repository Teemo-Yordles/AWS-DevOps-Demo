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
public class GLSTParsingTest {

    @Test
    public void testGLSTParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>GLST</TYPE>\n" +
                "  <STYP>ADD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                " <GLST>\n" +
                "  <GCOD>G17</GCOD>\n" +
                "  <GTNM>Gate for CA</GTNM>\n" +
                "  <GNMC>Gate for CA 中文</GNMC>\n" +
                "  <GCAT>D</GCAT>\n" +
                "  <GTML>T1</GTML>\n" +
                "  <PIER>C</PIER>\n" +
                " </GLST>\n" +
                " <GLST>\n" +
                "  <GCOD>G20</GCOD>\n" +
                "  <GTNM>Gate 20</GTNM>\n" +
                "  <GNMC>Gate 20中</GNMC>\n" +
                "  <GCAT>M</GCAT>\n" +
                "  <GTML>T2</GTML>\n" +
                "  <PIER>D</PIER>\n" +
                " </GLST>\n" +
                "</MSG>\n";
        GLST parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, GLST.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getGlsts().size() == 2).isTrue();
    }
}