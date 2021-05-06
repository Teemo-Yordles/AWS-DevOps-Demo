package com.unisys.aos.view.security.mapper;

import com.unisys.aos.view.security.entity.ViewUserDetails;
import com.unisys.aos.view.security.entity.ViewUserDetailsFunction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper used for ViewUserDetails related SQL
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Mapper
@Repository
public interface ViewUserDetailsMapper {

    /**
     * get basic user information based on username
     *
     * @param username user name
     * @return ViewUserDetails instance
     */
    @Select("SELECT username, password, enabled\n" +
            "FROM smm_user u\n" +
            "WHERE u.username = #{username}")
    ViewUserDetails findByUserName(@Param("username") String username);

    /**
     * get role name based on username
     *
     * @param username user name
     * @return role assigned for this user
     */
    @Select("SELECT r.name name\n" +
            "FROM smm_role r\n" +
            "LEFT JOIN smm_user u ON u.role_id = r.id\n" +
            "WHERE u.username = #{username}")
    String findRoleNameByUserName(@Param("username") String username);

    /**
     * find function urls of to which the specified roles related
     *
     * @param roleName - role name
     * @return url list
     */
    List<String> findFunctionUrlByRoleName(@Param("roleName") String roleName);

    /**
     * find UserDetailsFunction of to which the specified role related
     *
     * @param roleName - role name
     * @return UserDetailsFunction list
     */
    List<ViewUserDetailsFunction> findUserDetailsFunctionsByRoleName(@Param("roleName") String roleName);
}