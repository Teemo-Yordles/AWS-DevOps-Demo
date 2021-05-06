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
public class BLSTParsingTest {

    @Test
    public void testBLSTParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>BLST</TYPE>\n" +
                "  <STYP>DNLD</STYP>\n" +
                " </META>\n" +
                " <RECS>1</RECS>\n" +
                " <BLST>\n" +
                "  <BCOD>B11</BCOD>\n" +
                "  <BTNM>Belt for night flights</BTNM>\n" +
                "  <BNMC>Belt for night flights中文</BNMC>\n" +
                "  <BCAT>D</BCAT>\n" +
                "  <BTML>T2</BTML>\n" +
                " </BLST>\n" +
                "</MSG>";
        System.out.println(xml);
        BLST parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, BLST.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getCarousels().size()==1).isTrue();
    }
}