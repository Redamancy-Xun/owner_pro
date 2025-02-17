package com.example.forum.controller.account;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.common.Result;
import com.example.forum.exception.MyException;
import com.example.forum.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

//@RequiresRoles("admin")
@RestController
@Slf4j
@Api("更新Controller")
public class UpdateController {

    @Autowired
    private UserServiceImpl userService;

    @RequiresAuthentication
    @RequiresRoles("user")
    @PostMapping("/update")
    @ApiOperation("更改个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "username", value = "用户名(长度2-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(长度6-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "studentid", value = "学号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "studentname", value = "姓名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日(可选)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "email", value = "邮箱(可选)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "headportrait", value = "头像(可选)", required = false, paramType = "query", dataType = "String")
    })
    public Result update(@Validated @RequestParam("id") int id,
                         @Validated @RequestParam("username") String username,
                         @Validated @RequestParam("password") String password,
                         @Validated @RequestParam("studentid") String studentid,
                         @Validated @RequestParam("studentname") String studentname,
                         @RequestParam(value = "birthday", required = false) @Validated Date birthday,
                         @RequestParam(value = "email", required = false) @Validated String email,
                         @RequestParam(value = "headportrait", required = false) @Validated String headportrait){

        //参数校验
        if (userService.checkPasswordLength(password)
                &&userService.checkUsernameLength(username)
                &&userService.checkEmailForm(email)){
            //记录信息
            int count = userService.updateUsernameById(username, password, birthday, email, headportrait, id);
            log.info("count=" + count);

            return Result.success("更新成功", null);

        }

        return Result.fail("更新失败",null);
    }
}
