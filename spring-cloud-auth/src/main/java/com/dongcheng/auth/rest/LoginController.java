package com.dongcheng.auth.rest;

import com.dongcheng.auth.request.LoginRequest;
import com.dongcheng.auth.service.LoginService;
import com.dongcheng.common.bean.UserInfoBean;
import com.dongcheng.common.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "登录API")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @ApiOperation("登录")
    @PostMapping("")
    public CommonResult<UserInfoBean> login(
            @RequestBody
            LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        return CommonResult.success(loginService.login(loginRequest, response));
    }

    @ApiOperation("登出")
    @DeleteMapping("")
    public CommonResult<?> logout() {
        loginService.logout();
        return CommonResult.success();
    }

}
