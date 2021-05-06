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
public class DLYLParsingTest {

    @Test
    public void testDLYLParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>DLYL</TYPE>\n" +
                "  <STYP>ADD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                " <DLYL>\n" +
                "<DCOD>AX</DCOD>\n" +
                "<DCDN>82</DCDN>\n" +
                "<DDES>ATC STAFF/EQUIPMENT PROBLEM</DDES>\n" +
                "<DDSC>ATC STAFF/EQUIPMENT PROBLEM中文</DDSC>\n" +
                "</DLYL>\n" +
                "<DLYL>\n" +
                "<DCOD>CC</DCOD>\n" +
                "<DCDN>66</DCDN>\n" +
                "<DDES>ATC STAFF/EQUIPMENT PROBLEM</DDES>\n" +
                "<DDSC>ATC STAFF/EQUIPMENT PROBLEM中文</DDSC>\n" +
                " </DLYL>\n" +
                "</MSG>\n";
        DLYL parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, DLYL.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getDlyls().size() == 2).isTrue();
    }
}