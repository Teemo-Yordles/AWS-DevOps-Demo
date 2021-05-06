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
 * user-profile relation table
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmUserProfile对象", description="user-profile relation table")
public class SmmUserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "user id")
    private Long userId;

    @ApiModelProperty(value = "profile id")
    private Long profileId;


}
