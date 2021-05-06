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
public class CLSTParsingTest {

    @Test
    public void testCLSTParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                "\t<META>\n" +
                "\t\t<SNDR>AODB</SNDR>\n" +
                "\t\t<SEQN>1243</SEQN>\n" +
                "\t\t<DTTM>20021010090311</DTTM>\n" +
                "\t\t<TYPE>CLST</TYPE>\n" +
                "\t\t<STYP>DNLD</STYP>\n" +
                "\t</META>\n" +
                "\t<RECS>1</RECS>\n" +
                "\t<CLST>\n" +
                "\t\t<CCOD>C11</CCOD>\n" +
                "\t\t<CTNM>Desk 11 North side</CTNM>\n" +
                "\t\t<CNMC>北方柜台十一</CNMC>\n" +
                "\t\t<CCAT>D</CCAT>\n" +
                "\t\t<CTML>T3A</CTML>\n" +
                "\t\t<CTRA>Y</CTRA>\n" +
                "\t</CLST>\n" +
                "</MSG>";
        System.out.println(xml);
        CLST parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, CLST.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getCheckinDesks().size()==1).isTrue();
    }
}