/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.unisys.aos.view.messageprocessor.entity.ORGN;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsOrganizationDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsOrganization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Organization code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("ORGN")
@Slf4j
public class OrgnProcessor implements Processor {
    private final RdmsOrganizationDAO rdmsOrganizationDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public OrgnProcessor(RdmsOrganizationDAO rdmsOrganizationDAO, ReferenceProperties referenceProperties) {
        this.rdmsOrganizationDAO = rdmsOrganizationDAO;
        this.referenceProperties = referenceProperties;
    }

    /**
     * Process the message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        ORGN parsedObj = objectMapper.readValue(msg, ORGN.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<ORGN.OrganizationData> newOrganizations = parsedObj.getOrgns();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsOrganization> oldOrganizations = rdmsOrganizationDAO.findAll();
        ConcurrentMap<String, RdmsOrganization> organizationMap = oldOrganizations.stream()
                .collect(Collectors.toConcurrentMap(RdmsOrganization::getIdentity, (p) -> p));

        // loop through all XML records
        // if Organization Code already exists then UPDATE otherwise INSERT
        if (null != newOrganizations) {
            for (ORGN.OrganizationData record : newOrganizations) {
                log.debug("processing Organization record code [{}]", record.getOGID());

                // create empty domain abnormal reason entity and map properties
                RdmsOrganization newOrganization = new RdmsOrganization();
                newOrganization.setOrganizationIdentifier(record.getOGID());
                newOrganization.setDescription(record.getONAM());
                newOrganization.setLocalDescription(record.getONMC());

                newOrganization.setInspireTime(defaultInspireTime);
                newOrganization.setExpireTime(defaultExpireTime);

                List<String> categoryList = record.getOCAT();
                if (null != categoryList) {
                    categoryList.removeIf(""::equals);
                    newOrganization.setOrganizationCategories(categoryList.toString());
                }

                RdmsOrganization oldOrganization = null;
                Integer organizationId = record.getOGID();
                if (null != organizationId) {
                    String identity = organizationId.toString() + newOrganization.getInspireTime() + newOrganization.getExpireTime();
                    oldOrganization = organizationMap.get(identity);
                }

                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldOrganization) {
                        rdmsOrganizationDAO.deleteInNewTx(oldOrganization.getId());
                    }
                } else {
                    if (null == oldOrganization) {
                        // new organization code then create new record
                        newOrganization.setCreateTime(new Date());
                        newOrganization = rdmsOrganizationDAO.insertInNewTx(newOrganization);
                        // update the map to show latest status
                        organizationMap.put(newOrganization.getIdentity(), newOrganization);
                    } else {
                        // not new then update record. If same ignore.
                        newOrganization.setCreateTime(oldOrganization.getCreateTime());
                        newOrganization.setUpdateTime(new Date());
                        newOrganization.setId(oldOrganization.getId());
                        if (!rdmsOrganizationDAO.entityEqual(newOrganization, oldOrganization)) {
                            rdmsOrganizationDAO.updateInNewTx(newOrganization, oldOrganization);
                            // update the map to show latest status
                            organizationMap.replace(newOrganization.getIdentity(), newOrganization);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newOrganization.getOrganizationIdentifier());
                        }
                        oldOrganizations.remove(oldOrganization);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain ORGN record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsOrganization record : oldOrganizations) {
                rdmsOrganizationDAO.deleteInNewTx(record.getId());
            }
        }
    }
}
