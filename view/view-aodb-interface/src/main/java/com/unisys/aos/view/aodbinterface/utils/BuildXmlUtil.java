/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Zhang Wenqiang
 * @since 2021/1/13 15:20
 */
@Slf4j
@Component
public class BuildXmlUtil {
    public static final String REFERENCE_DATA_TYPE = "RQRD";
    public static final String DAILY_SCHEDULE_DATA_TYPE = "RQFD";
    public static final String DAILY_SCHEDULE_SUBTYPE = "NONE";
    private static final String NAME_SPACE_URI = "unisysaodbsis.xsd";
    private static final List<String> referenceDataSubtypeList = new ArrayList<>();
    private static final String SNDR = "VIEW";

    static {
        referenceDataSubtypeList.add("SLST");
        referenceDataSubtypeList.add("GLST");
        referenceDataSubtypeList.add("BLST");
        referenceDataSubtypeList.add("CLST");
        referenceDataSubtypeList.add("TLST");
        referenceDataSubtypeList.add("ABDG");
        referenceDataSubtypeList.add("CHLT");
        referenceDataSubtypeList.add("PLST");
        referenceDataSubtypeList.add("AIRC");
        referenceDataSubtypeList.add("REGN");
        referenceDataSubtypeList.add("AIRL");
        referenceDataSubtypeList.add("ARPT");
        referenceDataSubtypeList.add("CITY");
        referenceDataSubtypeList.add("COUL");
        referenceDataSubtypeList.add("REGL");
        referenceDataSubtypeList.add("ABNR");
        referenceDataSubtypeList.add("DLYL");
        referenceDataSubtypeList.add("HLTR");
        referenceDataSubtypeList.add("ORGN");
        referenceDataSubtypeList.add("STTS");
        referenceDataSubtypeList.add("FLTL");
        referenceDataSubtypeList.add("VIPL");
        referenceDataSubtypeList.add("VIPS");
    }

    private final MessageSequenceMaintainUtil messageSequenceMaintainUtil;

    @Autowired
    public BuildXmlUtil(MessageSequenceMaintainUtil messageSequenceMaintainUtil) {
        this.messageSequenceMaintainUtil = messageSequenceMaintainUtil;
    }

    /***
     * Convert the current time to a string-"yyyyMMddHHmmss".
     * @return Return the converted time string as the value of the "DTTM" tag.
     */
    public String getTimeString() {
        Date time = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(time);
    }

    /***
     * Build based document for xml.
     * @param type The data type to be requested.
     * @param subType The data subtype to be requested.
     * @return Based document for xml.
     */
    public Document buidBaseDocument(String type, String subType) {
        if (null == type || null == subType) {
            return null;
        }
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            // set MSG tag
            Element rootElement = document.createElement("MSG");
            // set attribute
            Attr xsiAttr = document.createAttribute("xmlns:xsi");
            xsiAttr.setValue(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
            rootElement.setAttributeNode(xsiAttr);
            Attr noNameSpaceAttr = document.createAttribute("xsi:noNamespaceSchemaLocation");
            noNameSpaceAttr.setValue(NAME_SPACE_URI);
            rootElement.setAttributeNode(noNameSpaceAttr);
            document.appendChild(rootElement);

            // set META tag
            Element metaElement = document.createElement("META");

            // set SNDR tag
            Element sndrElement = document.createElement("SNDR");
            sndrElement.setTextContent(SNDR);
            metaElement.appendChild(sndrElement);

            // set SEQN tag
            Element seqnElement = document.createElement("SEQN");
            seqnElement.setTextContent(messageSequenceMaintainUtil.getSequenceFromRedis());
            metaElement.appendChild(seqnElement);

            // set DTTM tag
            Element dttmElement = document.createElement("DTTM");
            dttmElement.setTextContent(getTimeString());
            metaElement.appendChild(dttmElement);

            // set TYPE tag
            Element typeElement = document.createElement("TYPE");
            typeElement.setTextContent(type);
            metaElement.appendChild(typeElement);

            // set STYP tag
            Element stypElement = document.createElement("STYP");
            stypElement.setTextContent(subType);
            metaElement.appendChild(stypElement);

            rootElement.appendChild(metaElement);
            return document;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * Build references data document for xml.
     * @param subType The subtype of the reference data.
     * @return References data document for xml.
     */
    public Document buildReferenceDataDocument(String subType) {
        Document document = buidBaseDocument(REFERENCE_DATA_TYPE, subType);
        // set RQRD tag
        Element rqrdElement = document.createElement(REFERENCE_DATA_TYPE);

        // set RTYP tag
        Element rtypElement = document.createElement("RTYP");
        rtypElement.setTextContent(subType);
        rqrdElement.appendChild(rtypElement);

        NodeList msg = document.getElementsByTagName("MSG");
        Element rootElement = (Element) msg.item(0);
        rootElement.appendChild(rqrdElement);
        return document;
    }

    /***
     * Build daily schedule data document for xml.
     * @param startTime The start time of requested data.
     * @param endTime The end time of requested data.
     * @return Daily schedule data document for xml.
     */
    public Document buildDailyScheduleDataDocument(String startTime, String endTime) {
        Document document = buidBaseDocument(DAILY_SCHEDULE_DATA_TYPE, DAILY_SCHEDULE_SUBTYPE);
        // set RQFD tag
        Element rqfdElement = document.createElement(DAILY_SCHEDULE_DATA_TYPE);

        // set STDB tag
        if (null != startTime) {
            Element stdbElement = document.createElement("STDB");
            stdbElement.setTextContent(startTime);
            rqfdElement.appendChild(stdbElement);
        }

        // set STDE tag
        if (null != endTime) {
            Element stdeElement = document.createElement("STDE");
            stdeElement.setTextContent(endTime);
            rqfdElement.appendChild(stdeElement);
        }

        NodeList msg = document.getElementsByTagName("MSG");
        Element rootElement = (Element) msg.item(0);
        rootElement.appendChild(rqfdElement);
        return document;
    }

    /***
     * Build xml string.
     * @param type The data type to be requested.
     * @param subtype The data subtype to be requested.
     * @param startTime The start time of requested data (Only has value when requesting daily schedule data, and it is null when requesting reference data).
     * @param endTime The end time of requested data (Only has value when requesting daily schedule data, and it is null when requesting reference data).
     * @return Return xml string if build success, otherwise return null.
     */
    public String buildXmlString(String type, String subtype, String startTime, String endTime) {
        log.debug("Start build xml string, request data type = {}, request data subtype = {}, request data start time = {}, request data end time = {}", type, subtype, startTime, endTime);
        if (null == type) {
            log.error("Message type cannot be null.");
            return null;
        }
        Document document;
        if (REFERENCE_DATA_TYPE.equals(type)) {
            if (!referenceDataSubtypeList.contains(subtype)) {
                log.error("Message subtype error, type = {}, subtype = {}.", type, subtype);
                return null;
            }
            document = buildReferenceDataDocument(subtype);
        } else if (DAILY_SCHEDULE_DATA_TYPE.equals(type)) {
            if (!DAILY_SCHEDULE_SUBTYPE.equals(subtype)) {
                log.error("Message subtype error, type = {}, subtype = {}.", type, subtype);
                return null;
            }
            if (null == startTime && null == endTime) {
                log.error("Failed to build xml, no time information was provided when requesting daily flight schedule.");
                return null;
            }
            document = buildDailyScheduleDataDocument(startTime, endTime);
        } else {
            log.error("Message type must be {} or {}, message type = {}", REFERENCE_DATA_TYPE, DAILY_SCHEDULE_DATA_TYPE, type);
            return null;
        }
        String xml = convertDocumentToString(document);
        if (null == xml) {
            log.error("Failed to build xml.");
            log.debug("Failed to build xml.");
        }
        log.debug("Build success, xml string = {}", xml);
        return xml;
    }

    /***
     * Convert document to String.
     * @param document The document object to be converted.
     * @return The converted String is returned when the conversion is successful, and null is returned when the conversion fails.
     */
    public String convertDocumentToString(Document document) {
        if (null == document) {
            return null;
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            document.setXmlStandalone(true);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
