package com.dongcheng.gateway.redis;

import com.dongcheng.auth.constants.DefaultConstants;
import com.dongcheng.auth.entity.UserInfoEntity;
import com.dongcheng.auth.utils.CommonAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component("gatewayUserRedisCollection")
public class UserRedisCollection {


    @Autowired
    private RedisTemplate redisTemplate;


    public UserInfoEntity getAuthUserInfoAndCache(Long userId) {
        CommonAssert.meetCondition(userId == null, "为获取到userId");
        String key = DefaultConstants.USER_INFO_REDIS + userId;

        UserInfoEntity entity = (UserInfoEntity) redisTemplate.opsForValue().get(key);
        if (null != entity) {
            redisTemplate.opsForValue().set(key, entity, 60 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
            return entity;
        }

        CommonAssert.meetCondition(true, "当前用户未登陆，未获取到登陆信息");
        return null;
    }


}
