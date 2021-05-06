package com.unisys.aos.view.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * UserDetails interface implementation for SpringSecurity
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewUserDetails implements UserDetails {
    private static final long serialVersionUID = -5626924056052495185L;

    private String password;
    private String username;
    /**
     * if account enabled
     */
    private boolean enabled;
    /**
     * if account not expired
     */
    private boolean accountNonExpired;
    /**
     * if account not locked
     */
    private boolean accountNonLocked;
    /**
     * if credential not expired
     */
    private boolean credentialsNonExpired;
    /**
     * functions can be accessed by this user.
     */
    private List<ViewUserDetailsFunction> functions;
    /**
     * user authority list
     */
    Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}