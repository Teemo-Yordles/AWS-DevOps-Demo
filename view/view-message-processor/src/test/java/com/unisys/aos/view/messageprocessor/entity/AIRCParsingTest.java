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
public class AIRCParsingTest {

    @Test
    public void testAIRCParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>AIRC</TYPE>\n" +
                "  <STYP>DNLD</STYP>\n" +
                " </META>\n" +
                " <RECS>1</RECS>\n" +
                " <AIRC>\n" +
                "  <ITAT>312</ITAT>\n" +
                "  <ICAT>A310</ICAT>\n" +
                "  <DESC>AIRBUS 310- 100 / 200</DESC>\n" +
                "  <CDSC>AIRBUS中文310- 100 / 200</CDSC>\n" +
                "  <CHAP>C</CHAP>\n" +
                "  <MAXP>241</MAXP>\n" +
                "  <MFWT>31730</MFWT>\n" +
                "  <MTWT>138600</MTWT>\n" +
                "  <ALEN>33.8</ALEN>\n" +
                "  <WSPN>30</WSPN>\n" +
                "  <MHTM>40</MHTM>\n" +
                "  <MABR>2</MABR>\n" +
                " </AIRC>\n" +
                " <AIRC>\n" +
                "  <ITAT>320</ITAT>\n" +
                "  <ICAT>A320</ICAT>\n" +
                "  <DESC>AIRBUS 320- 100 / 200</DESC>\n" +
                "  <CDSC>AIRBUS中文320- 100 / 200</CDSC>\n" +
                "  <CHAP>C</CHAP>\n" +
                "  <MAXP>241</MAXP>\n" +
                "  <MFWT>31730</MFWT>\n" +
                "  <MTWT>138600</MTWT>\n" +
                "  <ALEN>33.8</ALEN>\n" +
                "  <WSPN>30</WSPN>\n" +
                "a  <MHTM>40</MHTM>\n" +
                "  <MABR>2</MABR>\n" +
                " </AIRC>\n" +
                "</MSG>\n";
        AIRC parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, AIRC.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getAircs().size()==2).isTrue();
    }
}