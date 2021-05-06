/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewportal.service.impl;

import com.unisys.aos.view.common.bean.ApplicationContextHelper;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.BaseDAO;
import com.unisys.aos.view.viewentity.dao.RdmsRegionCountryAssociationDAO;
import com.unisys.aos.view.viewentity.dto.CountryDTO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsCountry;
import com.unisys.aos.view.viewportal.service.ReferenceDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Reference Data Service implementation
 *
 * @author jianglushuang
 * @since 2020/10/29 1:47 下午
 */
@Service
@Slf4j
public class ReferenceDataServiceImpl implements ReferenceDataService {
    private static final String RDMS = "rdms";
    private static final String IMPL = "DAOImpl";
    private static final String COUNTRIES = "countries";
    @Autowired
    private RdmsRegionCountryAssociationDAO regionCountryAssociationDAO;
    @Autowired
    private ReferenceProperties referenceProperties;

    private Map<String, String> DAOByTypes = new ConcurrentHashMap<String, String>() {{
        put("abnormal-status", "rdmsAbnormalStatusDAOImpl");
        put("cities", "rdmsCityDAOImpl");
        put("countries", "rdmsCountryDAOImpl");
        put("status", "rdmsStatusDAOImpl");
        put("airline-subcompanies", "rdmsAirlineSubcompanyDAOImpl");
    }};

    /**
     * select the reference data of type {dataType}
     *
     * @param dataType The type of reference data
     * @return map entities
     */
    @Override
    public Collection<Serializable> selectAll(String dataType) {
        Collection<Serializable> datas = null;
        try {
            BaseDAO dao = typeToDao(dataType);
            datas = (Collection<Serializable>) dao.findAll();
            // Since Country data may contain multiple region codes,
            // it is necessary to find all region codes related to it in association table according to country code.
            // Then recombine country data and the queried region codes into a DTO and return it as a response.
            if (COUNTRIES.equals(dataType) && null != datas) {
                List<Serializable> tempDatas = new ArrayList<>();
                Iterator<Serializable> iterator = datas.iterator();
                while (iterator.hasNext()) {
                    CountryDTO countryDTO = new CountryDTO((RdmsCountry) iterator.next(), regionCountryAssociationDAO, referenceProperties);
                    tempDatas.add(countryDTO);
                }
                datas = tempDatas;
            }
        } catch (Exception e) {
            log.error("Failed to get reference data for type [{}] : {}", dataType, e.getMessage());
        }
        return datas;
    }

    /**
     * select the reference data by IATA Code of type {dataType}
     *
     * @param dataType The type of reference data
     * @param code     IATA code
     * @return map entities
     */
    @Override
    public Collection<Serializable> selectByCode(String dataType, String code) {
        Collection<Serializable> datas = null;
        try {
            BaseDAO dao = typeToDao(dataType);
            datas = (Collection<Serializable>) dao.findByCode(code);
        } catch (Exception e) {
            log.error("Failed to get reference data for type [{}] : {}", dataType, e.getMessage());
        }
        return datas;
    }

    /**
     * Get corresponding DAOImpl by data type
     *
     * @param type The type of reference data
     * @return daoImpl
     */
    private BaseDAO typeToDao(String type) {
        String name = DAOByTypes.get(type);
        if (null == name || name.isEmpty()) {
            name = toDAOName(type);
        }
        return (BaseDAO) ApplicationContextHelper.getBean(name);
    }

    /**
     * Perform string splicing according to the type of reference data to obtain the corresponding DAOImpl class name,
     * and then obtain the corresponding bean through DAOImpl class name.
     * This method converts the letters after "-" to uppercase.
     *
     * @param type The type of reference data.
     * @return DAOImpl class name.
     */
    private String toDAOName(String type) {
        StringBuilder sb = new StringBuilder().append(RDMS);
        char[] cs = type.toCharArray();
        boolean up = true;
        for (int i = 0; i < cs.length - 1; i++) {
            if (cs[i] == '-') {
                up = true;
                continue;
            } else if (up) {
                cs[i] -= 32;
                up = false;
            }
            sb.append(cs[i]);
        }
        return sb.append(IMPL).toString();
    }
}
