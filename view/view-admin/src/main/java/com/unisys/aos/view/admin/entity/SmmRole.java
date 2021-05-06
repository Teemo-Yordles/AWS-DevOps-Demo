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
 * system role table
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmRole对象", description="system role table")
public class SmmRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "profile id")
    private Long profileId;

    @ApiModelProperty(value = "role name")
    private String name;

    @ApiModelProperty(value = "role description")
    private String description;

    @ApiModelProperty(value = "role creation date/time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "role update date/time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "optimistic locker")
    @Version
    private Integer version;

    @ApiModelProperty(value = "functions with access right")
    @TableField(exist = false)
    private List<SmmFunction> functions;

    @ApiModelProperty(value = "role related parameters")
    @TableField(exist = false)
    private List<SmmParameter> parameters;

    @ApiModelProperty(value = "role profile")
    @TableField(exist = false)
    private SmmProfile profile;
}
