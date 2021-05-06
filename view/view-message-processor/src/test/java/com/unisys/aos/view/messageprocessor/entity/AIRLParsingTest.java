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
public class AIRLParsingTest {

    @Test
    public void testAIRLParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>AIRL</TYPE>\n" +
                "  <STYP>ADD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                "<AIRL>\n" +
                "<ITOP>AA</ITOP>\n" +
                "<ICOP>AAL</ICOP>\n" +
                "<ONAM>AMERICAN AIRLINES</ONAM>\n" +
                "<ONMC>美国航空公司</ONMC>\n" +
                "<CTRY>USA</CTRY>\n" +
                "<TRML MVIN=\"\" FLIN=\"\"></TRML>\n" +
                "<OGRP>ONE WORLD</OGRP>\n" +
                "<DORI>I</DORI>\n" +
                "</AIRL>\n" +
                "<AIRL>\n" +
                "<ITOP>CX</ITOP>\n" +
                "<ICOP>CPA</ICOP>\n" +
                "<ONAM>CATHAY PACIFIC</ONAM>\n" +
                "<ONMC>香港航空公司</ONMC>\n" +
                "<CTRY>HKG</CTRY>\n" +
                "<TRML MVIN=\"A\" FLIN=\"I\">T3A</TRML>\n" +
                "<TRML MVIN=\"D\" FLIN=\"I\">T3B</TRML>\n" +
                "<OGRP>TWO WORLD</OGRP>\n" +
                "<OGRP>CHINA WORLD</OGRP>\n" +
                "<DORI>I</DORI>\n" +
                "</AIRL>\n" +
                "</MSG>\n";
        AIRL parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, AIRL.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getAirls().size() == 2).isTrue();
    }
}