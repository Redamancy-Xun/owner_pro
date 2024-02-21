package com.forum.controller.account;

import com.forum.common.EnumExceptionType;
import com.forum.common.Result;
import com.forum.controller.response.LoginResponse;
import com.forum.dto.SessionData;
import com.forum.service.impl.AdminServiceImpl;
import com.forum.service.impl.UserServiceImpl;
import com.forum.util.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("登录Controller")
@Slf4j
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/login")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(长度2-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(长度6-20)", required = true, paramType = "query", dataType = "String")
    })
    public Result login(@Validated @RequestParam("username") String username,
                        @Validated @RequestParam("password") String password){

//        //获取subject对象
//        Subject subject = SecurityUtils.getSubject();
//        AuthenticationToken token = new UsernamePasswordToken(username, PasswordUtil.convert(password));

        //参数校验
        if(userService.checkUsernameLength(username)
                && userService.checkPasswordLength(password)) {
            //登录实现
            Integer role = userService.checkLogin(username, PasswordUtil.convert(password));
            if (role == -1) return Result.fail("登录失败，请重试");
            SessionData sessionData = userService.login(username,password,role);
            LoginResponse loginResponse = new LoginResponse(sessionData,sessionData.getSessionId());
            return Result.success("登录成功",loginResponse);
            }

        return Result.result(EnumExceptionType.LOGIN_ERROR);
        }
    }
