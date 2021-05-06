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
public class CHLTParsingTest {

    @Test
    public void testCHLTParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>CHLT</TYPE>\n" +
                "  <STYP>DNLD</STYP>\n" +
                " </META>\n" +
                " <RECS>1</RECS>\n" +
                " <CHLT>\n" +
                "  <CCOD>C11</CCOD>\n" +
                "  <CHNM>Chute 11 North side</CHNM>\n" +
                "  <CNMC>北方行李传送带十一</CNMC>\n" +
                "  <CTML>T3A</CTML>\n" +
                "  <CCAT>D</CCAT>\n" +
                " </CHLT>\n" +
                "</MSG>";
        System.out.println(xml);
        CHLT parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, CHLT.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getChutes().size()==1).isTrue();
    }
}