/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.entity.flight;

/**
 * @author LiuJ2
 * @since 2020/10/26 8:44
 */
public interface AdditionFlightInfo {
    /**
     * Based on specific flight additional information characteristic,
     * match two AdditionFlightInfo entities, see if they are matching.
     * @param info - an entity to compare with.
     * @return - true if matched, false if not match.
     */
    boolean  matchFlightInfoRecord(AdditionFlightInfo info);

    /**
     * Get id which is the database primary key for the record
     * @return - record id
     */
    Long getId();

    /**
     * set id which is the database primary key for the record
     * @param - record id to set
     */
    void setId(Long id);

    /**
     * Get code for the record
     *
     * @return - record code
     */
    String obtainCode();
}
