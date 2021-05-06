package com.unisys.aos.view.admin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * system profile table
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmProfile对象", description="system profile table")
public class SmmProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "profile name")
    private String name;

    @ApiModelProperty(value = "profile description")
    private String description;

    @ApiModelProperty(value = "role id")
    private Long roleId;

    @ApiModelProperty(value = "creation date/time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "update date/time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "optimistic locker")
    @Version
    private Integer version;

    @ApiModelProperty(value = "flight column settings")
    @TableField(exist = false)
    private List<SmmProfileColumnSetting> columnSettings;

    @ApiModelProperty(value = "profile related parameters")
    @TableField(exist = false)
    private List<SmmParameter> parameters;
}
