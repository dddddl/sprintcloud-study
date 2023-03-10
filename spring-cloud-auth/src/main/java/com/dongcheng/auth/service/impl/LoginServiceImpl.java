package com.dongcheng.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongcheng.common.bean.UserInfoBean;
import com.dongcheng.common.constants.DefaultConstants;
import com.dongcheng.auth.entity.UserInfoEntity;
import com.dongcheng.common.enums.ResultCodeEnum;
import com.dongcheng.auth.mapper.UserInfoMapper;
import com.dongcheng.auth.redis.UserRedisCollection;
import com.dongcheng.auth.request.LoginRequest;
import com.dongcheng.auth.service.LoginService;
import com.dongcheng.common.utils.CommonAssert;
import com.dongcheng.common.utils.JwtUtil;
import com.dongcheng.common.utils.UserInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/7/29 13:48
 **/
@Service
public class LoginServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements LoginService {


    @Autowired
    private UserRedisCollection userRedisCollection;

    @Override
    public UserInfoBean login(LoginRequest loginRequest, HttpServletResponse response) {
        //根据账号查找用户信息
        UserInfoEntity infoEntity = lambdaQuery().eq(UserInfoEntity::getAccount, loginRequest.getAccount()).one();
        //判断账号
        CommonAssert.meetCondition(infoEntity == null, ResultCodeEnum.FORBIDDEN, "账号或密码错误");
        CommonAssert.meetCondition(!infoEntity.getPassword().equals(loginRequest.getPassword()), ResultCodeEnum.FORBIDDEN, "账号或密码错误");
        CommonAssert.meetCondition(infoEntity.getStatus().equals(0), ResultCodeEnum.FORBIDDEN, "账号被冻结");//注意，实际中不要直接用字面量，需要定义成枚举或者其他的
        //生成token
        return createToken(infoEntity.getId(), response);
    }

    @Override
    public void logout() {
        String userIdByCurrent = UserInfoUtils.getUserIdByCurrent();
        userRedisCollection.destroyUserInfo(userIdByCurrent);
    }

    private UserInfoBean createToken(Long userId, HttpServletResponse response) {
        Map<String, Object> map = new HashMap();
        map.put(DefaultConstants.USERID, userId);
        String token = JwtUtil.createToken(DefaultConstants.USER, map, DefaultConstants.SECRET_KEY);
        UserInfoBean result = userRedisCollection.getAuthUserInfoAndCache(userId);
        result.setPassword(null);
        response.setHeader(DefaultConstants.TOKEN, token);
        return result;
    }
}