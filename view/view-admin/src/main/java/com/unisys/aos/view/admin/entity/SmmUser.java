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
 * user information table
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmUser对象", description="user information table")
public class SmmUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "user name")
    private String username;

    @ApiModelProperty(value = "user password")
    private String password;

    @ApiModelProperty(value = "profile id")
    private Long profileId;

    @ApiModelProperty(value = "group id")
    private Long groupId;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "0-disabled，1-enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "user creation date/time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "user update date/time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "optimistic locker")
    @Version
    private Integer version;

    @ApiModelProperty(value = "profile")
    @TableField(exist = false)
    private SmmProfile profile;

    @ApiModelProperty(value = "role id")
    private Long roleId;
}
