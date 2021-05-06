package com.unisys.aos.view.gateway.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * JWT properties
 *
 * @author LiuJ2
 * @since 2020/12/19 11:37
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * If JWT security is enabled
     */
    private Boolean enabled;
    /**
     * JWT secret
     */
    private String secret;
    /**
     * Access token expiry duration
     */
    private Long accessTokenExpiration;
    /**
     * Refresh token expiry duration
     */
    private Long refreshTokenExpiration;
    /**
     * header name used to send JWT from front-end to server
     */
    private String header;
    /**
     * header name used to send username
     */
    private String usernameHeader;
    /**
     * origins allowed for CORS
     */
    private List<String> corsAllowedOrigins;
    /**
     * HTTP methods allowed for CORS
     */
    private List<String> corsAllowedMethods;
    /**
     * if disable the CSRF protection
     */
    private Boolean csrfDisabled = true;
    /**
     * if to use the default JwtAuthController
     */
    private Boolean useDefaultController = true;
    /**
     * URI opened for all login user to access, no authorization needed
     */
    private List<String> permitLoginUserURI;
    /**
     * URI opened for all access permanently
     */
    private List<String> permitAllURI;
    /**
     * This is the token used for test. Test token will associate to administrator user.
     * Should not be specified in production environment.
     */
    private String testToken = "";
}
