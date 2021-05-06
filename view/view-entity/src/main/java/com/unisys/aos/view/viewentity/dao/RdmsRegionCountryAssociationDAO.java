/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsRegionCountryAssociation;

import java.util.List;

/**
 * The <b>RdmsRegionCountryAssociationDAO</b> is an interface, which is used to access
 * RdmsRegionCountryAssociation data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public interface RdmsRegionCountryAssociationDAO extends BaseDAO<RdmsRegionCountryAssociation> {
    /**
     * get map entries based on gate code
     *
     * @param currentRegionCode region code
     * @param inspire           inspire time
     * @param expire            expire time
     * @return map entries
     */
    List<RdmsRegionCountryAssociation> selectByRegionCode(String currentRegionCode, Long inspire, Long expire);

    /**
     * get map entries based on gate code
     *
     * @param currentCountryCode country code
     * @param inspire            inspire time
     * @param expire             expire time
     * @return map entries
     */
    List<RdmsRegionCountryAssociation> selectByCountryCode(String currentCountryCode, Long inspire, Long expire);

    /**
     * delete association through Country Code
     *
     * @param currentCountryCode Country Code
     * @param inspire            inspire time
     * @param expire             expire time
     * @return delete result
     */
    int deleteByCountryInNexTx(String currentCountryCode, Long inspire, Long expire);

    /**
     * delete association through Region Code
     *
     * @param currentRegionCode Region Code
     * @param inspire           inspire time
     * @param expire            expire time
     * @return delete result
     */
    int deleteByRegionInNexTx(String currentRegionCode, Long inspire, Long expire);
}
