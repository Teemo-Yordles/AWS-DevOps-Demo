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
public class REGNParsingTest {

    @Test
    public void testREGNParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>REGN</TYPE>\n" +
                "  <STYP>ADD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                " <REGN>\n" +
                "  <RNUM>B2290</RNUM>\n" +
                "  <ITAT>321</ITAT>\n" +
                "  <OWID>52156</OWID>\n" +
                "  <ACAL>CA</ACAL>\n" +
                "  <ALSC>WN</ALSC>\n" +
                "  <MAXP>200</MAXP>\n" +
                "  <MFWT>31730</MFWT>\n" +
                "  <MTWT>138600</MTWT>\n" +
                " </REGN>\n" +
                " <REGN>\n" +
                "  <RNUM>B390</RNUM>\n" +
                "  <ITAT>321</ITAT>\n" +
                "  <OWID>52156</OWID>\n" +
                "  <ACAL>CA</ACAL>\n" +
                "  <ALSC>WN</ALSC>\n" +
                "  <MAXP>200</MAXP>\n" +
                "  <MFWT></MFWT>\n" +
                "  <MTWT>138600</MTWT>\n" +
                " </REGN>\n" +
                "</MSG>\n";
        REGN parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, REGN.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getRegns().size() == 2).isTrue();
    }
}