package com.unisys.aos.view.admin.service;

import com.unisys.aos.view.admin.entity.SmmProfile;
import com.unisys.aos.view.admin.entity.SmmProfileColumnSetting;
import com.unisys.aos.view.admin.entity.SmmUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author LiuJ2
 * @since 2021/3/4 11:01
 */
@SpringBootTest
class SmmProfileServiceTest {
    @Autowired
    SmmProfileService profileService;

    @Test
    void findById() {
        Optional<SmmProfile> profile = profileService.findById(1L);
        assertTrue(profile.isPresent());
        System.out.println(profile.get());
        profile = profileService.findById(0L);
        assertFalse(profile.isPresent());
    }

    @Test
    void findRoleRelatedProfilesByUsername() {
        List<SmmProfile> profiles = profileService.findRoleRelatedProfilesByUsername("admin");
        assertTrue(profiles.size() > 0);
        for (SmmProfile profile : profiles) {
            System.out.println(profile);
        }
    }

    @Test
    void initProfileForNewRole() {
        SmmProfile profile = profileService.initProfileForNewRole(Long.MAX_VALUE, "testRole");
        assertTrue(profile != null);
        System.out.println(profile);
        profileService.deleteById(profile.getId());
        System.out.println("test profile deleted");
    }

    @Test
    void findByUserIdTest() {
        List<SmmProfile> profileList = profileService.findByUserId(1L);
        System.out.println(profileList.size());
    }

    @Test
    void findByRoleIdTest() {
        SmmProfile profile = profileService.findByRoleId(2L);
        System.out.println(profile.getId());
        System.out.println(profile.getColumnSettings().size());
        System.out.println(profile.getParameters().size());
    }

    @Test
    void updateRoleProfileTest() {
        SmmProfile newProfile = new SmmProfile();
        newProfile.setId(1L);
        newProfile.setRoleId(1L);
        newProfile.setVersion(1);
        profileService.updateRoleProfile(newProfile);
    }

    @Test
    void updateProfileTest() {
        SmmProfile profile = profileService.findByRoleId(2L);
        SmmProfile newProfile = new SmmProfile();
        newProfile.setId(2L);
        newProfile.setDescription("user admin profile updated");
        newProfile.setName("user:admin/admin_profile_updated");
        newProfile.setRoleId(profile.getRoleId());
        newProfile.setVersion(4);

        List<SmmProfileColumnSetting> columnSettingList = profile.getColumnSettings();
        columnSettingList.forEach(c -> c.setAuthorized(0));
        newProfile.setColumnSettings(columnSettingList);
        newProfile.setParameters(profile.getParameters());
        SmmProfile updatedProfile = profileService.updateProfile(newProfile);
        System.out.println(updatedProfile.getColumnSettings().size());
        System.out.println(updatedProfile.toString());
    }

    @Test
    void addUserProfileFromSourceProfileTest() {
        SmmProfile newProfile = new SmmProfile();
        newProfile.setName("add profile test");
        newProfile.setDescription("add profile test");
        SmmProfile sourceProfile = profileService.getById(1L);
        SmmUser user = new SmmUser();
        user.setId(1L);
        SmmProfile profile = profileService.addUserProfileFromSourceProfile(user, newProfile, sourceProfile);
        System.out.println(profile.toString());
    }

    @Test
    void deleteByIdTest() {
        System.out.println(profileService.deleteById(9L));
    }
}