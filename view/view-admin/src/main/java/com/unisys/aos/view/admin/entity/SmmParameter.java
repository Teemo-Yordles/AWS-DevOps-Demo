package com.unisys.aos.view.admin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * system global parameters
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmParameter对象", description="system global parameters")
public class SmmParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "unique parameter key")
    @TableField("`key`")
    private String key;

    @ApiModelProperty(value = "parameter description")
    private String description;

    @ApiModelProperty(value = "parameter level")
    private String level;

    @ApiModelProperty(value = "parameter description in local language")
    private String localDescription;

    @ApiModelProperty(value = "parameter value")
    private String value;

    @ApiModelProperty(value = "create date/time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "parameter update date/time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "optimistic locker")
    @Version
    private Integer version;


}
