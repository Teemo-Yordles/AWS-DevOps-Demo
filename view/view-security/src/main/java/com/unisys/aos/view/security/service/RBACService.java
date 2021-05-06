package com.unisys.aos.view.security.service;

import com.unisys.aos.view.security.config.JwtProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * Check authority for user's request
 *
 * @author LiuJ2
 * @since 2020/12/18 12:08
 */
@Component("rbacService")
public class RBACService {
    @Resource
    private JwtProperties jwtProperties;

    /**
     * Determine if the user has enough access right to access the URL
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails = ((UserDetails)principal);
            List<GrantedAuthority> authorityList =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(request.getRequestURI());

            return jwtProperties.getPermitLoginUserURI().contains(request.getRequestURI())
                    || containsPrefix(jwtProperties.getPermitLoginUserURI(), request.getRequestURI())
                    || userDetails.getAuthorities().contains(authorityList.get(0))
                    || containsPrefix(userDetails.getAuthorities(), request.getRequestURI());
        }

        return false;
    }

    /**
     * check if the request URI starts with the permitted URI
     * @param permitLoginUserURI - permitted uris
     * @param requestURI -  request uri
     * @return true if contains the permit uri
     */
    private static boolean containsPrefix(List<String> permitLoginUserURI, String requestURI) {
        for (String uri : permitLoginUserURI) {
            String permitUri = uri + "/";
            if (requestURI.indexOf(permitUri) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the request URI starts with the permitted URI
     * @param authorities - permitted uris
     * @param requestURI -  request uri
     * @return true if contains the permit authority
     */
    private static boolean containsPrefix(Collection<? extends GrantedAuthority> authorities, String requestURI) {
        for (GrantedAuthority authority : authorities) {
            String permitUri = authority.getAuthority() + "/";
            if (requestURI.indexOf(permitUri) == 0) {
                return true;
            }
        }
        return false;
    }

}
