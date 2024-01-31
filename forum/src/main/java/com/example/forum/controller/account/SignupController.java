package com.example.forum.controller.account;


import com.example.forum.common.Result;
import com.example.forum.entity.User;
import com.example.forum.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@Api("注册Controller")
public class SignupController {

    @Autowired
    UserServiceImpl userService;

    //注册插入一个用户
    @PostMapping("/signup")
    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(长度2-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(长度6-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "studentid", value = "学号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "studentname", value = "姓名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日(可选)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "email", value = "邮箱(可选)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "headportrait", value = "头像(可选)", required = false, paramType = "query", dataType = "String"),
    })
    public Result signup(@Validated String username,
                         @Validated String password,
                         @Validated String studentid,
                         @Validated String studentname,
                         @RequestParam(value = "birthday", required = false) @Validated Date birthday,
                         @RequestParam(value = "email", required = false) @Validated String email,
                         @RequestParam(value = "headportrait", required = false) @Validated String headportrait){

        User user = new User();

        if (userService.checkUsernameLength(username)){
            user.setUsername(username);
        }
        if (userService.checkPasswordLength(password)){
            user.setPassword(password);
        }

        user.setStudentid(studentid);
        user.setStudentname(studentname);
        user.setBirthday(birthday);
        if (userService.checkEmailForm(email)) {
            user.setEmail(email);
        }
        user.setHeadportrait(headportrait);
        user.setSession_id(0);

        //insertUser返回插入的条数
        int count = userService.signupUser(user);
        log.info("count=" + count);
        log.info("id=" + user.getId());

        return Result.success("注册成功", null);
    }
}
