package com.forum.controller.account;


import com.forum.controller.response.GetAdminResponse;
import com.forum.controller.response.GetUserResponse;
import com.forum.entity.Admin;
import com.forum.entity.User;
import com.forum.service.impl.AdminServiceImpl;
import com.forum.service.impl.UserServiceImpl;
import com.forum.common.Result;
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

import java.util.Date;

@RestController
@Slf4j
@Api("注册Controller")
public class SignupController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    //注册插入一个用户
    @PostMapping("/signup")
    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(长度2-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(长度6-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "studentid", value = "学号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "studentname", value = "姓名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日(可选)", paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "email", value = "邮箱(可选)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "headportrait", value = "头像(可选)", paramType = "query", dataType = "String"),
    })
    public Result signup(@Validated @RequestParam("username") String username,
                         @Validated @RequestParam("password") String password,
                         @Validated @RequestParam("studentid") String studentid,
                         @Validated @RequestParam("studentname") String studentname,
                         @RequestParam(value = "birthday", required = false) @Validated Date birthday,
                         @RequestParam(value = "email", required = false) @Validated String email,
                         @RequestParam(value = "headportrait", required = false) @Validated String headportrait){

        User user = new User();

            if (userService.checkUsernameLength(username)){
                user.setUsername(username);
            }
            if (userService.checkPasswordLength(password)){
                user.setPassword(PasswordUtil.convert(password));
            }
            user.setStudentid(studentid);
            user.setStudentname(studentname);
            if (birthday!=null)
                user.setBirthday(birthday);
            if (email!=null && userService.checkEmailForm(email)) {
                user.setEmail(email);
            }
            if (headportrait!=null)
                user.setHeadportrait(headportrait);

            //insertUser返回插入的条数
            int count = userService.signupUser(user);
            log.info("count=" + count);
            log.info("id=" + user.getId());

        return Result.success("注册成功", new GetUserResponse(user, 0));
    }

    /**
     * 此Controller用于录入admin
     * 无需设计页面，仅为录入admin，实际使用时关闭
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/signup/admin")
    @ApiOperation("录入管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(长度2-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(长度6-20)", required = true, paramType = "query", dataType = "String")
    })
    public Result signup(@Validated @RequestParam("username") String username,
                         @Validated @RequestParam("password") String password){

        Admin admin = new Admin();

        if (adminService.checkUsernameLength(username)){
            admin.setUsername(username);
        }
        if (userService.checkPasswordLength(password)){
            admin.setPassword(PasswordUtil.convert(password));
        }

        //insertUser返回插入的条数
        int count = adminService.signupAdmin(admin);
        log.info("count=" + count);
        log.info("id=" + admin.getAdmin_id());

        return Result.success("注册成功", new GetAdminResponse(admin, 1));
    }
}
