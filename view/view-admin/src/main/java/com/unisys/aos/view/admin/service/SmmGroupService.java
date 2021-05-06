package com.unisys.aos.view.admin.service;

import com.unisys.aos.view.admin.entity.SmmGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.unisys.aos.view.admin.entity.SmmGroup;

import java.util.List;

/**
 * <p>
 * group information table 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
public interface SmmGroupService extends IService<SmmGroup> {
    /**
     * find all groups in DB.
     * @return
     */
    List<SmmGroup> findGroups();

    /**
     * Add new group
     * @param newGroup - new group
     * @return new group added to the db.
     */
    SmmGroup addGroup(SmmGroup newGroup);

    /**
     * update a group
     *
     * @param group - group entity to be persisted to database
     * @return group updated with new values
     */
    SmmGroup updateGroup(SmmGroup group);
}
