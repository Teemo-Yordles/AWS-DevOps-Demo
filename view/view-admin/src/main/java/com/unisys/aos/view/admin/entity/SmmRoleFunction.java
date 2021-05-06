package com.unisys.aos.view.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * role function access right relation table
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmRoleFunction对象", description="role function access right relation table")
public class SmmRoleFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "role id")
    private Long roleId;

    @ApiModelProperty(value = "function id")
    private Long functionId;

    public SmmRoleFunction(Long roleId, Long functionId) {
        this.roleId = roleId;
        this.functionId = functionId;
    }
}
