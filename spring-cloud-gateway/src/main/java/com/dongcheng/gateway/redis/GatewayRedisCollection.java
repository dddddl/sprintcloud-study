package com.dongcheng.gateway.redis;

import com.dongcheng.common.bean.UserInfoBean;
import com.dongcheng.common.constants.DefaultConstants;
import com.dongcheng.common.utils.CommonAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class GatewayRedisCollection {


    @Autowired
    private RedisTemplate redisTemplate;


    public UserInfoBean getAuthUserInfoAndCache(Long userId) {
        CommonAssert.meetCondition(userId == null, "为获取到userId");
        String key = DefaultConstants.USER_INFO_REDIS + userId;

        UserInfoBean entity = (UserInfoBean) redisTemplate.opsForValue().get(key);
        if (null != entity) {
            redisTemplate.opsForValue().set(key, entity, 60 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
            return entity;
        }

        CommonAssert.meetCondition(true, "当前用户未登陆，未获取到登陆信息");
        return null;
    }


}
