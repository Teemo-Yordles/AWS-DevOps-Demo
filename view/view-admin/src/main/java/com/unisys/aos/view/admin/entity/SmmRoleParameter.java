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
 * role parameter association table
 * </p>
 *
 * @author LiuJ2
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmRoleParameter对象", description="role parameter association table")
public class SmmRoleParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "role id")
    private Long roleId;

    @ApiModelProperty(value = "parameter id")
    private Long parameterId;
}
