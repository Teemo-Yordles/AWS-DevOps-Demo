package com.unisys.aos.view.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unisys.aos.view.admin.entity.SmmRole;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LiuJ2
 * @since 2021/3/4 17:58
 */
@SpringBootTest
@FixMethodOrder
class SmmRoleServiceTest {
    @Autowired
    SmmRoleService roleService;

    @Test
    void findRoles() {
    }

    @Test
    void findById() {
        SmmRole role = roleService.findById(1L);
        assertTrue(null != role);
        System.out.println(role);
    }

    @Test
    void addRole() {
        SmmRole newRole = new SmmRole();
        newRole.setDescription("test role");
        newRole.setName("test_role");
        newRole.setId(Long.MAX_VALUE);
        newRole = roleService.addRole(newRole);
        assertTrue(null != newRole);
    }

    @Test
    void updateRole() {
    }

    @Test
    void deleteRole() {
        QueryWrapper<SmmRole> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "test_role");
        SmmRole one = roleService.getOne(wrapper);
        assertTrue(roleService.deleteRole(one.getId()));
        SmmRole role = roleService.findById(Long.MAX_VALUE);
        assertTrue(null == role);
    }

}