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
public class HLTRParsingTest {

    @Test
    public void testHLTRParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>HLTR</TYPE>\n" +
                "  <STYP>ADD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                " <HLTR>\n" +
                "<HLTC>MNTT</HLTC>\n" +
                "<HLTD>MAINTENANCE APPROVED TIME</HLTD>\n" +
                "<HTDC>机务放行时间</HTDC>\n" +
                "<HLTT>T</HLTT>\n" +
                "<UMNM></UMNM>\n" +
                "</HLTR>\n" +
                "<HLTR>\n" +
                "<HLTC>XXXX</HLTC>\n" +
                "<HLTD>XXXXXX</HLTD>\n" +
                "<HTDC>时间</HTDC>\n" +
                "<HLTT>T</HLTT>\n" +
                "<UMNM></UMNM>\n" +
                " </HLTR>\n" +
                "</MSG>\n";
        HLTR parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, HLTR.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getHltrs().size() == 2).isTrue();
    }
}