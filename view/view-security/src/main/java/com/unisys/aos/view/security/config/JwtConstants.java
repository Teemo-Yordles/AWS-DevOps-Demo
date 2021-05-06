package com.unisys.aos.view.security.config;

/**
 * <p>
 * JWT constants
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
public class JwtConstants {
    public static final String JWT_HEADER_PREFIX = "Bearer ";
    public static final String CONTROLLER_LOGIN = "/view/login";
    public static final String CONTROLLER_REFRESH = "/view/refresh-token";
    public static final String CONTROLLER_LOGOUT = "/view/logout";
    public static final String ACCESS_TOKEN_NAME = "accessToken";
    public static final String REFRESH_TOKEN_NAME = "refreshToken";
    public static final String USERNAME_REDIS_SET_KEY_PREFIX = "username:";
    public static final String CONTROLLER_TEST_LOGIN = "/view/login-test";
    public static final String CONTROLLER_TEST_LOGOUT = "/view/logout-test";
}
