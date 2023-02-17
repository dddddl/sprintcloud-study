package com.dongcheng.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongcheng.auth.entity.UserInfoEntity;
import com.dongcheng.auth.request.LoginRequest;

import javax.servlet.http.HttpServletResponse;

public interface LoginService extends IService<UserInfoEntity> {


    /**
     * todo 一般不会直接返回entity，而是使用response专用对象，这里是为了方便
     * @param loginRequest
     * @return
     */
    UserInfoEntity login(LoginRequest loginRequest, HttpServletResponse response);

    void logout();


}
