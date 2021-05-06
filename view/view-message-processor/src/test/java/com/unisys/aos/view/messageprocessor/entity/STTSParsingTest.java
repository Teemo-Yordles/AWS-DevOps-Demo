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
public class STTSParsingTest {

    @Test
    public void testSTTSParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>STTS</TYPE>\n" +
                "  <STYP>ADD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                " <STTS>\n" +
                "<STTC>RETURN</STTC>\n" +
                "<STTD>Flight Air Return Because of some reason</STTD>\n" +
                "<STDC>由于某种原因导致航班空返</STDC>\n" +
                "<ABNS>Y</ABNS>\n" +
                "</STTS>\n" +
                "<STTS>\n" +
                "<STTC>STOP</STTC>\n" +
                "<STTD>Flight stop</STTD>\n" +
                "<STDC>停止</STDC>\n" +
                "<ABNS>Y</ABNS>\n" +
                " </STTS>\n" +
                "</MSG>\n";
        STTS parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, STTS.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getSttss().size() == 2).isTrue();
    }
}