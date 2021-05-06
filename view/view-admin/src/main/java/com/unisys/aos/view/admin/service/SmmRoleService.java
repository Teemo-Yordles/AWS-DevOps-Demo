package com.unisys.aos.view.admin.service;

import com.unisys.aos.view.admin.entity.SmmRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * <p>
 * system role table 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
public interface SmmRoleService extends IService<SmmRole> {

    /**
     * find all roles in DB.
     * @return
     */
    List<SmmRole> findRoles();

    /**
     * find a role in by roldId.
     * @return role
     */
    SmmRole findById(Long roleId);

    /**
     * Add new role
     * @param newRole - new role
     * @return new role added to the db.
     */
    SmmRole addRole(SmmRole newRole);

    /**
     * if there are uses have role with specified id
     * @param roleId - role id
     * @return true if there are some otherwise false
     */
    boolean hasAssociatedUser(Long roleId);

    /**
     * update a role
     * SmmRole table and SmmRoleFunction table have to be updated at the same time
     * so need transaction to ensure transaction atomic.
     *
     * @param role - role entity to be persisted to database
     * @return role updated with new values
     */
    SmmRole updateRole(SmmRole role);

    /**
     * delete a role
     * SmmRole table, SmmRoleFunction table and SmmRoleFlightColumn table have to be updated at the same time
     * so need transaction to ensure transaction atomic.
     *
     * @param id - role id
     */
    boolean deleteRole(Long id);
}
