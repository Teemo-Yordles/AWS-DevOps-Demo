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
public class REGLParsingTest {

    @Test
    public void testREGLParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>REGL</TYPE>\n" +
                "  <STYP>DNLD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                " <REGL>\n" +
                "  <REGC>ASIAPC</REGC>\n" +
                "  <REGN>ASIA AND ASIA PACIFIC AREA</REGN>\n" +
                "  <RGNC>亚洲和太平</RGNC>\n" +
                " </REGL>\n" +
                " <REGL>\n" +
                "  <REGC>ASIA</REGC>\n" +
                "  <REGN>ASIA</REGN>\n" +
                "  <RGNC>亚洲</RGNC>\n" +
                " </REGL>\n" +
                "</MSG>\n";
        REGL parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, REGL.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getRegls().size() == 2).isTrue();
    }
}