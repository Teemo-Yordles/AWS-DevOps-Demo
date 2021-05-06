package com.unisys.aos.view.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 航班界面列数据表，配合smm_role_flight_column控制列访问权限
 * </p>
 *
 * @author LiuJ2
 * @since 2021-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmmFlightColumn对象", description="航班界面列数据表，配合smm_role_flight_column控制列访问权限")
public class SmmFlightColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "column code")
    private String code;

    @ApiModelProperty(value = "description in English")
    private String description;

    @ApiModelProperty(value = "description in local language")
    private String localDescription;

    @ApiModelProperty(value = "create date/time")
    private Date createTime;

    @ApiModelProperty(value = "update date/time")
    private Date updateTime;

    @ApiModelProperty(value = "optimistic locker")
    @Version
    private Integer version;


}
