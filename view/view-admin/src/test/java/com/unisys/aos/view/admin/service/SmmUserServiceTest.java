package com.unisys.aos.view.admin.service;

import com.unisys.aos.view.admin.entity.SmmUser;
import com.unisys.aos.view.common.exception.ApiException;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author LiuJ2
 * @since 2021/3/5 9:46
 */
@SpringBootTest
@FixMethodOrder
class SmmUserServiceTest {
    @Autowired
    SmmUserService userService;

    @Test
    void findById() {
        SmmUser user = userService.findById(1L);
        assertNotNull(user);
        System.out.println(user);
        try {
            userService.findById(0L);
        } catch (Throwable ex) {
            assertTrue(ex instanceof ApiException);
        }
    }

    @Test
    void findAll() {
        List<SmmUser> users = userService.findAll();
        assertTrue(users.size() > 0);
        for (SmmUser user : users) {
            System.out.println(user);
        }
    }

    @Test
    void addUser() {
        SmmUser newUser = new SmmUser();
        newUser.setName("刘杰");
        newUser.setEnabled(true);
        newUser.setRoleId(1L);
        newUser.setUsername("liujie");
        newUser.setGroupId(1L);
        newUser.setPassword("good9Luck");
        newUser = userService.addUser(newUser);
        assertNotNull(newUser.getProfile());
        assertTrue(userService.deleteUserById(newUser.getId()));
    }

    @Test
    @Disabled
    void deleteUserById() {
        assertTrue(userService.deleteUserById(3L));
    }

    @Test
    void updateUserTest() {
        SmmUser newUser = new SmmUser();
        newUser.setId(1L);
        newUser.setName("admin");
        newUser.setEnabled(true);
        newUser.setRoleId(2L);
        newUser.setUsername("admin");
        newUser.setGroupId(1L);
        newUser.setProfileId(1L);
        userService.updateUser(newUser);
    }

    @Test
    void updateUserColumnAuthorizedByRoleTest() {
        SmmUser user = new SmmUser();
        user.setId(1L);
        user.setRoleId(2L);
        user.setProfileId(2L);
        userService.updateUserColumnAuthorizedByRole(user);
    }
}