package com.unisys.aos.view.admin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 系统功能接口表，配合smm_role_function控制功能访问权限
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmFunction对象", description="系统功能接口表，配合smm_role_function控制功能访问权限")
public class SmmFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "function code")
    private String code;

    @ApiModelProperty(value = "description in English")
    private String description;

    @ApiModelProperty(value = "description in local language")
    private String localDescription;

    @ApiModelProperty(value = "corresponding URL")
    private String url;

    @ApiModelProperty(value = "sort sequence")
    private Integer sort;

    @ApiModelProperty(value = "0-not leaf，1-leaf node")
    private Boolean leaf;

    @ApiModelProperty(value = "parent function id")
    private Long parentId;

    @ApiModelProperty(value = "create date/time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "update date/time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "optimistic locker")
    @Version
    private Integer version;


}
