package com.unisys.aos.view.security.config;

import com.unisys.aos.view.security.entity.ViewUserDetails;
import com.unisys.aos.view.security.service.ViewUserDetailsService;
import com.unisys.aos.view.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JWT token authentication/authorization filter
 * 1. check the validity of the token
 * 2. authorize the user
 *
 * @author LiuJ2
 * @since 2020/12/19 11:37
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtProperties jwtProperties;
    private final JwtTokenUtil jwtTokenUtil;
    private final ViewUserDetailsService viewUserDetailsService;

    @Autowired
    public JwtAuthenticationTokenFilter(AuthenticationEntryPoint authenticationEntryPoint,
                                        JwtProperties jwtProperties,
                                        JwtTokenUtil jwtTokenUtil,
                                        ViewUserDetailsService viewUserDetailsService) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtProperties = jwtProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.viewUserDetailsService = viewUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = request.getHeader(jwtProperties.getHeader());
        String username = request.getHeader(jwtProperties.getUsernameHeader());

        if (!StringUtils.isEmpty(jwtToken)) {
            // validate token and get username
            jwtToken = jwtToken.replace(JwtConstants.JWT_HEADER_PREFIX, "").trim();
            if(StringUtils.isEmpty(username)) {
                jwtTokenUtil.getUsernameFromToken(jwtToken);
            }

            if (username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                ViewUserDetails userDetails = (ViewUserDetails) viewUserDetailsService.loadUserByToken(jwtToken);
                // if userDetails is null in cache, it means user already logged out or token has expired in cache.
                // In this case, still the logout request should be processed. No need for logout user to refresh token again.
                if (null != userDetails) {
                    log.debug("Retrieved UserDetails from cache with username:[{}]", userDetails.getUsername());
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.debug("Security context authentication set successfully.");
                } else if(JwtConstants.CONTROLLER_LOGOUT.equals(request.getRequestURI())
                            || JwtConstants.CONTROLLER_TEST_LOGOUT.equals(request.getRequestURI())) {
                    // create an UserDetails with only logout authority
                    userDetails = new ViewUserDetails();
                    userDetails.setUsername(username);
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(JwtConstants.CONTROLLER_LOGOUT));
                    authorities.add(new SimpleGrantedAuthority(JwtConstants.CONTROLLER_TEST_LOGOUT));
                    userDetails.setAuthorities(authorities);
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.debug("Logout Security context authentication set successfully.");
                } else {
                    AuthenticationException authenticationException = new InvalidCookieException("token expired");
                    SecurityContextHolder.getContext().setAuthentication((Authentication) null);
                    log.debug("Cached token has been cleared.");
                    log.debug("report authentication exception to entry point...");
                    this.authenticationEntryPoint.commence(request, response, authenticationException);
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}