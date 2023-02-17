package com.dongcheng.auth.redis;

import com.dongcheng.auth.constants.DefaultConstants;
import com.dongcheng.auth.entity.UserInfoEntity;
import com.dongcheng.auth.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class UserRedisCollection {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LoginService loginService;

    public UserInfoEntity getAuthUserInfoAndCache(Long userId) {
        log.info("获取用户权限并且设置缓存开始, userId: [{}]", userId);
        String key = DefaultConstants.USER_INFO_REDIS + userId;
        UserInfoEntity entity = (UserInfoEntity) redisTemplate.opsForValue().get(key);
        if (null != entity) {
            return entity;
        }
        entity = loginService.getById(userId);
        if (null == entity) {
            return null;
        }
        redisTemplate.opsForValue().set(key, entity, 60 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
        return entity;
    }
}
