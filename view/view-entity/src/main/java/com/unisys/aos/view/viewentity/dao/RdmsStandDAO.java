/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao;

import com.unisys.aos.view.viewentity.entity.reference.RdmsStand;

import java.util.List;

/**
 * The <b>RdmsStandDAO</b> is an interface, which is used to access
 * RdmsStand data from database.
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
public interface RdmsStandDAO extends BaseDAO<RdmsStand> {
    /***
     * get map entries based on stand code
     * @param currentStandCode stand code
     * @param inspire inspire time
     * @param expire expire time
     * @return map entries
     */
    List<RdmsStand> selectByStandCode(String currentStandCode, Long inspire, Long expire);

    /**
     * get map entries based on international gate code
     *
     * @param internationalGateCode international gate code
     * @param inspire               inspire time
     * @param expire                expire time
     * @return map entries
     */
    List<RdmsStand> selectByInternationalGate(String internationalGateCode, Long inspire, Long expire);

    /**
     * get map entries based on domestic gate code
     *
     * @param domesticGateCode domestic gate code
     * @param inspire          inspire time
     * @param expire           expire time
     * @return map entries
     */
    List<RdmsStand> selectByDomesticGate(String domesticGateCode, Long inspire, Long expire);
}
