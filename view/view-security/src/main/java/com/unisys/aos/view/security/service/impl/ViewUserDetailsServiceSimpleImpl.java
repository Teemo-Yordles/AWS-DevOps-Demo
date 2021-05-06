package com.unisys.aos.view.security.service.impl;

import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.security.config.JwtConstants;
import com.unisys.aos.view.security.entity.ViewUserDetails;
import com.unisys.aos.view.security.service.ViewUserDetailsService;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * <p>
 * Implementation of SpringBoot Security UserDetailsService interface.
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Component
@ConditionalOnMissingClass(value = {"com.unisys.aos.view.admin.ViewAdminApplication"})
@Slf4j
public class ViewUserDetailsServiceSimpleImpl implements ViewUserDetailsService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    /**
     * This method is not actually used outside admin micro-service.
     * Whenever used, exception should be thrown.
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException(username);
    }

    /**
     * Get UserDetails from cache with token key
     *
     * @param token token string. Cannot be empty.
     * @return UserDetails - actually ViewUserDetails object
     */
    @Override
    public UserDetails loadUserByToken(String token) {
        Assert.notNull(token, "token cannot be empty");
        return (ViewUserDetails) redisUtil.get(token);
    }

    /**
     * reload user details in cache for user specified
     *
     * @param username - target user
     */
    @Override
    public void reloadUserDetails(String username) {
        UserDetails userDetails = loadUserByUsername(username);
        int updateCount = resetUserDetailsCache(username, userDetails);
        log.debug("update [{}] token cache for user [{}]", updateCount, username);
    }

    /**
     * Delete the set values if the value represented token has already expired.
     *
     * @param username - username
     * @return - numbers of deleted set value
     */
    @Override
    public int deleteExpiredTokenSetValue(String username) {
        // get user tokens
        String usernameSetKey = JwtConstants.USERNAME_REDIS_SET_KEY_PREFIX + username;
        Set<Object> userTokens = redisUtil.smember(usernameSetKey);
        if (null == userTokens || userTokens.isEmpty()) {
            return 0;
        }

        // delete tokens expired
        int deletedCount = 0;
        for (Object userToken : userTokens) {
            if (!redisUtil.exists((String) userToken)) {
                boolean deleted = redisUtil.srem(usernameSetKey, (String) userToken);
                if (deleted) {
                    deletedCount++;
                }
            }
        }
        return deletedCount;
    }

    /**
     * reset the user details values if the value represented token.
     *
     * @param username - username
     * @return - numbers of updated set value
     */
    @Override
    public int resetUserDetailsCache(String username, UserDetails userDetails) {
        // get user tokens
        String usernameSetKey = JwtConstants.USERNAME_REDIS_SET_KEY_PREFIX + username;
        Set<Object> userTokens = redisUtil.smember(usernameSetKey);
        if (null == userTokens || userTokens.isEmpty()) {
            return 0;
        }

        // reset tokens value
        int updateCount = 0;
        for (Object userToken : userTokens) {
            boolean updated = redisUtil.setIfPresent((String) userToken, userDetails);
            if (updated) {
                updateCount++;
            }
        }
        return updateCount;
    }

}
