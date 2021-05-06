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
public class ORGNParsingTest {

    @Test
    public void testORGNParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>ORGN</TYPE>\n" +
                "  <STYP>ADD</STYP>\n" +
                " </META>\n" +
                " <RECS>2</RECS>\n" +
                " <ORGN>\n" +
                "<OGID>666666666</OGID>\n" +
                "<ONAM>ServisAir Private Limited</ONAM>\n" +
                "<ONMC>有限公司</ONMC>\n" +
                "<OCAT>RAMP HANDLER</OCAT>\n" +
                "</ORGN>\n" +
                "<ORGN>\n" +
                "<OGID>477923</OGID>\n" +
                "<ONAM>ServisAir Private Limited</ONAM>\n" +
                "<ONMC>美国SERVISAIR有限公司</ONMC>\n" +
                "<OCAT>RAMP HANDLER</OCAT>\n" +
                "<OCAT>PAX HANDLER</OCAT>\n" +
                " </ORGN>\n" +
                "</MSG>\n";
        ORGN parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, ORGN.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getOrgns().size() == 2).isTrue();
    }
}