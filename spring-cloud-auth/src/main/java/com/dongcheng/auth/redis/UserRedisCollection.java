package com.dongcheng.auth.redis;

import com.dongcheng.common.bean.UserInfoBean;
import com.dongcheng.auth.entity.UserInfoEntity;
import com.dongcheng.common.constants.DefaultConstants;
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


    public UserInfoBean getAuthUserInfoAndCache(Long userId) {
        log.info("获取用户权限并且设置缓存开始, userId: [{}]", userId);
        String key = DefaultConstants.USER_INFO_REDIS + userId;
        UserInfoBean bean = (UserInfoBean) redisTemplate.opsForValue().get(key);
        if (null != bean) {
            return bean;
        }
        UserInfoEntity entity = loginService.getById(userId);
        if (null == entity) {
            return null;
        }

        bean = new UserInfoBean(entity.getId(), entity.getAccount(), entity.getStatus(), entity.getPassword());

        redisTemplate.opsForValue().set(key, bean, 60 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
        return bean;
    }

    public void destroyUserInfo(String userId) {
        String key = DefaultConstants.USER_INFO_REDIS + userId;
        redisTemplate.delete(key);
    }


}
