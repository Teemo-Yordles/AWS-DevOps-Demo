package com.unisys.aos.view.admin.mapper;

import com.unisys.aos.view.security.entity.ViewUserDetails;
import com.unisys.aos.view.security.mapper.ViewUserDetailsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author LiuJ2
 * @since 2020/12/19 7:57
 */
@SpringBootTest
class ViewUserDetailsMapperTest {
    @Autowired
    ViewUserDetailsMapper userDetailsMapper;

    @Test
    void findByUserName() {
        ViewUserDetails admin = userDetailsMapper.findByUserName("admin");
        System.out.println(admin);
    }

    @Test
    void findRoleNameByUserName() {
        String roleName = userDetailsMapper.findRoleNameByUserName("admin");
        System.out.println(roleName);
    }

    @Test
    void findFunctionUrlNameByRoleNamesTest() {
        String roleName = "administrator";
        List<String> functionNameByRoleNames = userDetailsMapper.findFunctionUrlByRoleName(roleName);
        functionNameByRoleNames.forEach(System.out::println);
    }

}