/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.common.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Utility class to access redis
 *
 * @author LiuJ2
 * @since 2020/9/14 16:44
 */
@Component
@Slf4j
public final class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * check if a key is existed in Redis
     *
     * @param key - key to check
     * @return true if exists
     */
    public boolean exists(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception ex) {
            log.error("[Redis] exists executed failed", ex);
            return false;
        }
    }

    // *********** String operations **********

    public boolean setEx(String key, Object value, long expiration) {
        try {
            redisTemplate.opsForValue().set(key, value, expiration, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception ex) {
            log.error("[Redis] setEx operation failed!", ex);
            return false;
        }
    }

    /**
     * set the value if key is present
     *
     * @param key   - redis key
     * @param value - corresponding value
     * @return true is successful.
     */
    public boolean setIfPresent(String key, Object value) {
        try {
            return redisTemplate.opsForValue().setIfPresent(key, value);
        } catch (Exception ex) {
            log.error("[Redis] set operation failed!", ex);
            return false;
        }
    }

    /**
     * Get key value
     *
     * @param key Redis key
     * @return value
     */
    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception ex) {
            log.error("[Redis] get operation failed!", ex);
            return null;
        }
    }

    /**
     * delete a key from redis
     * key cannot be null.
     *
     * @param key Redis key
     */
    public boolean del(String key) {
        Assert.notNull(key, "redis key cannot be null");

        try {
            return redisTemplate.delete(key);
        } catch (Exception ex) {
            log.error("[Redis] delete operation failed! key:[" + key + "]", ex);
            return false;
        }
    }

    // *********** set operations ************

    /**
     * add a set member
     *
     * @param k set key
     * @param v set member value
     * @return true if successful
     */
    public boolean sadd(String k, String v) {
        try {
            redisTemplate.opsForSet().add(k, v);
            return true;
        } catch (Throwable e) {
            log.error("[Redis] cache set failed. Current key:[{}] value:[{}]", k, v);
            log.error("[Redis] cache set failed. reason: ", e);
            return false;
        }
    }

    /**
     * retrieve set members of the key
     *
     * @param k key
     * @return set members
     */
    public Set<Object> smember(String k) {
        try {
            SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();
            return opsForSet.members(k);
        } catch (Throwable e) {
            log.error("[Redis] failed to get member from set. Current key:[{}]", k);
            log.error("[Redis] failed to get member from set. reason: ", e);
        }
        return null;
    }

    /**
     * remove a set member
     *
     * @param k set key
     * @param v set member value
     * @return true if successful
     */
    public boolean srem(String k, String v) {
        try {
            redisTemplate.opsForSet().remove(k, v);
            return true;
        } catch (Throwable e) {
            log.error("[Redis] remove set member failed. Current key:[{}] value:[{}]", k, v);
            log.error("[Redis] remove set member failed. reason: ", e);
            return false;
        }
    }

    // *********** Map operations ************

    /**
     * HashGet
     *
     * @param key        - Key for the hash. Cannot be null
     * @param elementKey - Item key for the hash element, cannot be null.
     * @return element value.
     */
    public Object hget(String key, String elementKey) {
        return redisTemplate.opsForHash().get(key, elementKey);
    }

    /**
     * Hash Set
     *
     * @param key - Key for the hash. Cannot be null
     * @param map - map for multi key-value pair. must no be null.
     * @return - true if successfully put.
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception ex) {
            log.error("[Redis] hmset operation failed!", ex);
            return false;
        }
    }

    /**
     * Get the map for the key specified
     *
     * @param key - hash key
     * @return value of the hash key.
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * put element into a hash
     *
     * @param key   - key for the hash
     * @param item  - hash element key
     * @param value - hash element value
     * @return - true if successfully put.
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            log.debug("[REDIS] updated element for entity [{}] key [{}]", key, item);
            return true;
        } catch (Exception ex) {
            log.error("[Redis] hset operation failed!", ex);
            return false;
        }
    }

    /**
     * Delete an element from a Hash
     *
     * @param key   - Hask Key
     * @param field - Hash element key
     * @return true if successfully deleted.
     */
    public boolean hdel(String key, String field) {
        try {
            redisTemplate.opsForHash().delete(key, field);
            log.debug("[REDIS] deleted element for entity [{}] key [{}]", key, field);
            return true;
        } catch (Exception ex) {
            log.error("[Redis] hdel operation failed!", ex);
            return false;
        }
    }

    public void publish(String topic, Object message) {
        try {
            redisTemplate.convertAndSend(topic, message);
            log.debug("[REDIS] publish for topic [{}] value [{}]", topic, message);
        } catch (Exception ex) {
            log.error("[Redis] publish for topic [{}] failed!", topic, ex);
        }
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

}
