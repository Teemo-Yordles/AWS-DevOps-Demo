package com.unisys.aos.view.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * profile - flight column access right relation table
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmProfileColumnSetting对象", description="profile - flight column access right relation table")
public class SmmProfileColumnSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "profile id")
    private Long profileId;

    @ApiModelProperty(value = "column id")
    private Long columnId;

    @ApiModelProperty(value = "if authorized to see")
    private Integer authorized;

    @ApiModelProperty(value = "visible or not")
    private Integer visible;

    @ApiModelProperty(value = "column order")
    @TableField("`order`")
    private Integer order;

    @ApiModelProperty(value = "need highlight or not")
    private Integer highlight;

    @ApiModelProperty(value = "highlight color")
    private String highlightColor;

    @ApiModelProperty(value = "highlight duration in minute")
    private Integer highlightDuration;

    @ApiModelProperty(value = "allow notification or not")
    private Integer notification;

    @ApiModelProperty(value = "create date/time")
    private Date createTime;

    @ApiModelProperty(value = "update date/time")
    private Date updateTime;

    @ApiModelProperty(value = "optimistic locker")
    @Version
    private Integer version;

    @ApiModelProperty(value = "flight column")
    @TableField(exist = false)
    private SmmFlightColumn flightColumn;
}
