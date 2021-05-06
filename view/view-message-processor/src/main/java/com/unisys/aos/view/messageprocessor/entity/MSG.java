/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Parent class for all AODB message XML entities.
 *
 * @author LiuJ2
 * @since 2020/9/7 14:19
 */
@Data
@NoArgsConstructor
@JacksonXmlRootElement(localName = "MSG")
public abstract class MSG {
	// header information common to all AODB messages
    @JacksonXmlProperty(localName = "META")
    private META meta;
}
