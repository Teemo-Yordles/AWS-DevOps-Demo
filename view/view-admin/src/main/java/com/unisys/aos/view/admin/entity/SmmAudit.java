package com.unisys.aos.view.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * audit information table
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmAudit对象", description="audit information table")
public class SmmAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "username")
    private String username;

    @ApiModelProperty(value = "func")
    private String func;

    @ApiModelProperty(value = "audit description")
    private String description;

    @ApiModelProperty(value = "audit start date/time")
    @TableField(fill = FieldFill.INSERT)
    private Date startTime;

}
