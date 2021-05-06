/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dto;

import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsRegionCountryAssociationDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsCountry;
import com.unisys.aos.view.viewentity.entity.reference.RdmsRegionCountryAssociation;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Zhang Wenqiang
 * @since 2020/11/03 13:24
 */
@Data
public class CountryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String countryCode;
    private String description;
    private String localDescription;
    private Date createTime;
    private Date updateTime;
    private String regionCodes;
    private ReferenceProperties referenceProperties;

    /**
     * Constructor
     *
     * @param country Country
     */
    public CountryDTO(RdmsCountry country, RdmsRegionCountryAssociationDAO regionCountryAssociationDAO, ReferenceProperties referenceProperties) {
        this.id = country.getId();
        this.countryCode = country.getCountryCode();
        this.description = country.getDescription();
        this.localDescription = country.getLocalDescription();
        this.regionCodes = buildRegionCodeStr(country, regionCountryAssociationDAO);
        this.createTime = country.getCreateTime();
        this.updateTime = country.getUpdateTime();
        this.referenceProperties = referenceProperties;
    }

    public Long getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDescription() {
        return description;
    }

    public String getLocalDescription() {
        return localDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getRegionCodes() {
        return regionCodes;
    }

    /**
     * Build regions code string for this country
     *
     * @return string value of regions code.
     */
    public String buildRegionCodeStr(RdmsCountry country, RdmsRegionCountryAssociationDAO regionCountryAssociationDAO) {
        if (null == country) {
            return null;
        }
        String countryCode = country.getCountryCode();
        List<RdmsRegionCountryAssociation> regionCountryAssociationList = regionCountryAssociationDAO.selectByCountryCode(countryCode, referenceProperties.getDefaultInspireTime(), referenceProperties.getDefaultExpireTime());
        if (null != regionCountryAssociationList && !regionCountryAssociationList.isEmpty()) {
            StringBuilder regionCodesBuilder = new StringBuilder("[");
            for (int i = 0; i < regionCountryAssociationList.size(); i++) {
                regionCodesBuilder.append(regionCountryAssociationList.get(i).getRegionCode());
                if (i != regionCountryAssociationList.size() - 1) {
                    regionCodesBuilder.append(", ");
                }
            }
            regionCodesBuilder.append("]");
            return regionCodesBuilder.toString();
        } else {
            return null;
        }
    }
}
