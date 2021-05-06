/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LiuJ2
 * @since 2021/1/11 11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewUserDetailsFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private String description;
    private String localDescription;
    private String url;
    private Integer sort;
    private Boolean leaf;
    private Long parentId;
    private Date createTime;
    private Date updateTime;
    private Integer version;
}
