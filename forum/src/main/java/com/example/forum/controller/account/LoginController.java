package com.example.forum.controller.account;

import com.example.forum.common.*;
import com.example.forum.exception.MyException;
import com.example.forum.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("登录Controller")
@Slf4j
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(长度2-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(长度6-20)", required = true, paramType = "query", dataType = "String")
    })
    public Result login(@Validated @RequestParam("username") String username,
                        @Validated @RequestParam("password") String password, HttpSession session){

        //记录信息
        log.info("object: " + this);
        log.info("thread: " + Thread.currentThread().getId());

        //参数校验
        //请求转发，会话管理

            if(userService.checkUsernameLength(username)
                    && userService.checkPasswordLength(password)
                    && userService.checkLogin(username,password)) {
                session.setAttribute("user", userService.getUserByUsernameAndPassword(username, password));
            }
        return Result.success("登录成功", null);
    }
}
