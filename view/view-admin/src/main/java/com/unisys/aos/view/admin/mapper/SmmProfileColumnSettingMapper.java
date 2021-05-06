package com.unisys.aos.view.admin.mapper;

import com.unisys.aos.view.admin.entity.SmmProfileColumnSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * profile - flight column access right relation table Mapper 接口
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Mapper
@Repository
public interface SmmProfileColumnSettingMapper extends BaseMapper<SmmProfileColumnSetting> {
    /**
     * Find profile column settings by profile id
     * @param id - profile Id
     * @return profile related column setting list.
     */
    List<SmmProfileColumnSetting> findColumnSettingByProfileId(Long id);

    /**
     * Delete profile related column settings.
     * @param id - profile Id
     */
    void deleteByProfileId(@Param("id") Long id);

//    /**
//     * 根据 ID 修改
//     *
//     * @param entity 实体对象
//     */
//    @Override
//    int updateById(SmmRoleColumnSetting entity);
}
