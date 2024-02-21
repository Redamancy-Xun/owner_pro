package com.forum.controller.user;

import com.forum.common.EnumExceptionType;
import com.forum.common.Result;
import com.forum.controller.request.UpdateUserMessageRequest;
import com.forum.controller.response.GetUserResponse;
import com.forum.dto.SessionData;
import com.forum.service.impl.AdminServiceImpl;
import com.forum.service.impl.UserServiceImpl;
import com.forum.util.PasswordUtil;
import com.forum.util.SessionUtil;
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
@Api("更新用户信息 Controller")
public class UpdateUserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    private SessionUtil sessionUtil;

    @PostMapping("/update")
    @ApiOperation("更改个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(长度2-20)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日(可选)", paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "email", value = "邮箱(可选)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "headportrait", value = "头像(可选)", paramType = "query", dataType = "String")
    })
    public Result update(@RequestParam(value = "username", required = false) @Validated String username,
                         @RequestParam(value = "birthday", required = false) @Validated Date birthday,
                         @RequestParam(value = "email", required = false) @Validated String email,
                         @RequestParam(value = "headportrait", required = false) @Validated String headportrait){

        SessionData sessionData = sessionUtil.getSessionData();
        Integer user_id = sessionData.getId();

        UpdateUserMessageRequest updateUserMessageRequest = new UpdateUserMessageRequest();
        updateUserMessageRequest.setId(user_id);
        if (username != null && userService.checkUsernameLength(username)) {
            if (userService.getUserByUsername(username) != null || adminService.getAdminByUsername(username) != null) {
                return Result.result(EnumExceptionType.USER_ALREADY_EXIST_BUT_CAN_UPGRADE);
            }
            updateUserMessageRequest.setUsername(username);
        }
        if (birthday != null) {
            updateUserMessageRequest.setBirthday(birthday);
        }
        if (email != null && userService.checkEmailForm(email)) {
            updateUserMessageRequest.setEmail(email);
        }
        if (headportrait != null) {
            updateUserMessageRequest.setHeadportrait(headportrait);
        }
        userService.updateUserInfoById(updateUserMessageRequest);

        return Result.success("更新成功", new GetUserResponse(userService.getUserById(user_id), 0));
    }

    @PostMapping("/updatePassword")
    @ApiOperation("更改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码(长度6-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码(长度6-20)", required = true, paramType = "query", dataType = "String")
    })
    public Result update(@Validated @RequestParam("oldPassword") String oldPassword,
                         @Validated @RequestParam("newPassword") String newPassword){

        SessionData sessionData = sessionUtil.getSessionData();
        Integer user_id = sessionData.getId();

        UpdateUserMessageRequest updateUserMessageRequest = new UpdateUserMessageRequest();
        updateUserMessageRequest.setId(user_id);
        if (oldPassword != null && userService.checkPasswordLength(oldPassword)) {
            updateUserMessageRequest.setPassword(PasswordUtil.convert(oldPassword));
        }
        userService.updatePasswordById(updateUserMessageRequest, PasswordUtil.convert(newPassword));

        return Result.success("更新成功", new GetUserResponse(userService.getUserById(user_id), 0));
    }
}