package com.unisys.aos.view.security.config;

import com.unisys.aos.view.security.service.ViewUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

/**
 * Spring Security 配置
 * 可以配置多个WebSecurityConfigurerAdapter
 * 但是多个Adaptor有执行顺序，默认值是100
 * 这里设置为1会优先执行
 *
 * @author LiuJ2
 * @since 2020/12/19 11:37
 */
@Configuration
@Order(1)
public class JwtWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private ViewUserDetailsService userDetailsService;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private ViewAuthExceptionEntryPoint viewAuthExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // solve the cors problem. allow all cors preflight request
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        http.exceptionHandling()
                .authenticationEntryPoint(viewAuthExceptionEntryPoint);

        // disable session management
        // set "Cache-Control: no-cache, no-store, max-age=0, must-revalidate"
        http.cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers()
                .defaultsDisabled()
                .cacheControl();

        // allow every one to access login and refresh url
        // also add the JWT token filter before username/password authentication
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(
                        JwtConstants.CONTROLLER_LOGIN
                )
                .permitAll();

        if (jwtProperties.getCsrfDisabled()) {
            http = http.csrf().disable();
        }

        // add some URLs which don't need tokens to access
        for (String uri : jwtProperties.getPermitAllURI()) {
            http.authorizeRequests().antMatchers(uri).permitAll();
        }

        // for any other urls call RBAC service to check authority.
        http.authorizeRequests().anyRequest()
                .access("@rbacService.hasPermission(request,authentication)");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @ConditionalOnMissingBean(AuthenticationManager.class)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
