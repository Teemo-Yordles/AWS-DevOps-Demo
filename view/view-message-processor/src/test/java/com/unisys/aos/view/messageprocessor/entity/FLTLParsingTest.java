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
public class FLTLParsingTest {

    @Test
    public void testFLTLParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                "\t<META>\n" +
                "\t\t<SNDR>AODB</SNDR>\n" +
                "\t\t<SEQN>1243</SEQN>\n" +
                "\t\t<DTTM>20021010090311</DTTM>\n" +
                "\t\t<TYPE>FLTL</TYPE>\n" +
                "\t\t<STYP>DNLD</STYP>\n" +
                "\t</META>\n" +
                "\t<RECS>2</RECS>\n" +
                "\t<FLTL>\n" +
                "\t\t<FTYP>J</FTYP>\n" +
                "\t\t<CTYP>SCH</CTYP>\n" +
                "\t\t<FDES>SCHEDULED FLT</FDES>\n" +
                "\t\t<FDSC>定期航班</FDSC>\n" +
                "\t\t<FCML>Y</FCML>\n" +
                "\t</FLTL>\n" +
                "\t<FLTL>\n" +
                "\t\t<FTYP>I</FTYP>\n" +
                "\t\t<CTYP></CTYP>\n" +
                "\t\t<FDES>DIPLOMATIC USE</FDES>\n" +
                "\t\t<FDSC>外交航班</FDSC>\n" +
                "\t\t<FCML>N</FCML>\n" +
                "\t</FLTL>\n" +
                "</MSG>";
        System.out.println(xml);
        FLTL parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, FLTL.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getFlightTypes().size()==2).isTrue();
    }
}