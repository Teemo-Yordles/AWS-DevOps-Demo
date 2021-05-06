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
 * group information table
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmGroup对象", description="group information table")
public class SmmGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "group name")
    private String name;

    @ApiModelProperty(value = "group description")
    private String description;

    @ApiModelProperty(value = "group context")
    private String context;

    @ApiModelProperty(value = "group tel")
    private String tel;

    @ApiModelProperty(value = "group address")
    private String address;


    @ApiModelProperty(value = "group creation date/time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "group update date/time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "optimistic locker")
    @Version
    private Integer version;


}
