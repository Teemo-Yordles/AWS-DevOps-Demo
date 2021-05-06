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
public class PLSTParsingTest {

    @Test
    public void testPLSTParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                "\t<META>\n" +
                "\t\t<SNDR>AODB</SNDR>\n" +
                "\t\t<SEQN>1243</SEQN>\n" +
                "\t\t<DTTM>20111112120000</DTTM>\n" +
                "\t\t<TYPE>PLST</TYPE>\n" +
                "\t\t<STYP>DNLD</STYP>\n" +
                "\t</META>\n" +
                "\t<RECS>2</RECS>\n" +
                "\t<PLST>\n" +
                "\t\t<PCOD>C</PCOD>\n" +
                "\t\t<PRNM>PIER C</PRNM>\n" +
                "\t\t<PNMC>C指廊</PNMC>\n" +
                "\t\t<PCAT>I</PCAT>\n" +
                "\t\t<PTML>T1</PTML>\n" +
                "\t</PLST>\n" +
                "\t<PLST>\n" +
                "\t\t<PCOD>D</PCOD>\n" +
                "\t\t<PRNM>PIER D</PRNM>\n" +
                "\t\t<PNMC>D指廊</PNMC>\n" +
                "\t\t<PCAT>I</PCAT>\n" +
                "\t\t<PTML>T1</PTML>\n" +
                "\t</PLST>\n" +
                "</MSG>";
        System.out.println(xml);
        PLST parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, PLST.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getPiers().size()==2).isTrue();
    }
}