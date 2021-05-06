package com.unisys.aos.view.admin.mapper;

import com.unisys.aos.view.admin.entity.SmmRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author LiuJ2
 * @since 2020/12/17 19:57
 */
@SpringBootTest
class SmmRoleMapperTest {
    @Autowired
    SmmRoleMapper roleMapper;

    @Test
    void selectRoles() {
        List<SmmRole> roleList = roleMapper.findRoles();
        roleList.forEach(System.out::println);
    }
}