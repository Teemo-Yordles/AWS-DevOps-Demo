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
public class VIPLParsingTest {

    @Test
    public void testVIPLParsing() throws JsonProcessingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation = \"unisysaodbsis.xsd\">\n" +
                " <META>\n" +
                "  <SNDR>AODB</SNDR>\n" +
                "  <SEQN>1243</SEQN>\n" +
                "  <DTTM>20021010090311</DTTM>\n" +
                "  <TYPE>VIPL</TYPE>\n" +
                "  <STYP>DNLD</STYP>\n" +
                " </META>\n" +
                " <RECS>1</RECS>\n" +
                " <VIPL>\n" +
                "  <VPCD>JACK</VPCD>\n" +
                "  <VPNC>211</VPNC>\n" +
                "  <VFNM>Jack</VFNM>\n" +
                "  <VLNM>Smith</VLNM>\n" +
                "  <VDES>Star</VDES>\n" +
                "  <VDSC>Star</VDSC>\n" +
                "  <VPON>K</VPON>\n" +
                "  <VRNK>1</VRNK>\n" +
                "  <VPTC>Fu Liu</VPTC>\n" +
                "  <VCTN>01000</VCTN>\n" +
                "  <VCMN>17611111111</VCMN>\n" +
                "  <VRMK>特特</VRMK>\n" +
                "  <VPWU>特特</VPWU>\n" +
                "  <VPPS>特特</VPPS>\n" +
                " </VIPL>\n" +
                "</MSG>";
        System.out.println(xml);
        VIPL parsedObj = null;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        try {
            parsedObj = objectMapper.readValue(xml, VIPL.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(parsedObj).isNotNull();
        assertThat(parsedObj.getVipPersonnals().size()==1).isTrue();
    }
}