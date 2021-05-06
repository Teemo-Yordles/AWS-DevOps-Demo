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
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author jianglushuang
 * @since 2020/9/23 9:51 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CITYParsingTest {

    @Test
    public void testCITYParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>CITY</TYPE>\n" +
                "  <STYP>DNLD</STYP>\n" +
                " </META>\n" +
                " <RECS>3</RECS>\n" +
                " <CITY>\n" +
                "  <ITCD>SHA</ITCD>\n" +
                "  <ICCD>SHHA</ICCD>\n" +
                "  <CNAM>SHANGHAI</CNAM>\n" +
                "  <CNMC>上海</CNMC>\n" +
                "  <CTRY>PRC</CTRY>\n" +
                "     <ABBR CODE=\"ABR1\">HU</ABBR>\n" +
                "     <ABBR CODE=\"ABR2\">沪</ABBR>\n" +
                " </CITY>\n" +
                " <CITY>\n" +
                "  <ITCD>HET</ITCD>\n" +
                "  <ICCD>ZBHH</ICCD>\n" +
                "  <CNAM>HOHHOT</CNAM>\n" +
                "  <CNMC>呼和浩特</CNMC>\n" +
                "     <CTRY>PRC</CTRY>\n" +
                "     <ABBR CODE=\"ABR1\">HU</ABBR>\n" +
                "     <ABBR CODE=\"ABR2\">呼</ABBR>\n" +
                "     <ABBR CODE=\"ABR3\">HUSHI</ABBR>\n" +
                "     <ABBR CODE=\"ABR4\">呼市</ABBR>\n" +
                " </CITY>\n" +
                " <CITY>\n" +
                "  <ITCD>CTU</ITCD>\n" +
                "  <ICCD>CTUK</ICCD>\n" +
                "  <CNAM>CHENGDU</CNAM>\n" +
                "  <CNMC>成都</CNMC>  \n" +
                "     <CTRY>PRC</CTRY>\n" +
                "     <ABBR CODE=\"ABR1\">RONG</ABBR>\n" +
                "     <ABBR CODE=\"ABR2\">蓉</ABBR>\n" +
                " </CITY>\n" +
                "</MSG>\n";
        System.out.println(xml);
        CITY parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, CITY.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getCitys().size()==3).isTrue();
    }
}