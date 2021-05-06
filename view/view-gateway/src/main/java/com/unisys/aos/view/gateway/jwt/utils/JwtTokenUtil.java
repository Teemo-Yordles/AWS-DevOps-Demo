package com.unisys.aos.view.gateway.jwt.utils;

import com.unisys.aos.view.gateway.jwt.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * JWT token utilities
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Component
@Slf4j
public class JwtTokenUtil {

    private final JwtProperties jwtProperties;

    public JwtTokenUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public JwtProperties getJwtProperties() {
        return jwtProperties;
    }

    /**
     * 生成token令牌
     *
     * @param userDetails 用户
     * @param payloads    令牌中携带的附加信息
     * @return 令token牌
     */
    public String generateToken(UserDetails userDetails,
                                Map<String, String> payloads,
                                long expireDuration) {
        int payloadSizes = payloads == null ? 0 : payloads.size();

        Map<String, Object> claims = new HashMap<>(payloadSizes + 2);
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date());

        if (payloadSizes > 0) {
            for (Map.Entry<String, String> entry : payloads.entrySet()) {
                claims.put(entry.getKey(), entry.getValue());
            }
        }

        return generateToken(claims, expireDuration);
    }

    /**
     * Retrieve username from JWT token
     *
     * @param token JWT Token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * determine if the token is expired
     *
     * @param token JWT token
     * @return true if expired
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims, jwtProperties.getAccessTokenExpiration());
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    /**
     * 从claims生成令牌,如果看不懂就看谁调用它
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims, long expireDuration) {
        Date expirationDate = new Date(System.currentTimeMillis() + expireDuration);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
