package com.unisys.aos.view.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * profile parameter association table
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmProfileParameter对象", description="profile parameter association table")
public class SmmProfileParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "role id")
    private Long profileId;

    @ApiModelProperty(value = "parameter id")
    private Long parameterId;
}
