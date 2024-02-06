package com.forum.controller.user;

import com.forum.common.EnumExceptionType;
import com.forum.common.Result;
import com.forum.controller.request.UpdateUserMessageRequest;
import com.forum.dto.UserDTO;
import com.forum.service.impl.AdminServiceImpl;
import com.forum.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    @RequiresRoles("user")
    @PostMapping("/update")
    @ApiOperation("更改个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(长度2-20)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(长度6-20)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日(可选)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "email", value = "邮箱(可选)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "headportrait", value = "头像(可选)", required = false, paramType = "query", dataType = "String")
    })
    public Result update(@RequestParam(value = "username", required = false) @Validated String username,
                         @RequestParam(value = "password", required = false) @Validated String password,
                         @RequestParam(value = "birthday", required = false) @Validated Date birthday,
                         @RequestParam(value = "email", required = false) @Validated String email,
                         @RequestParam(value = "headportrait", required = false) @Validated String headportrait){

        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        Integer user_id = principal.getId();

        UpdateUserMessageRequest updateUserMessageRequest = new UpdateUserMessageRequest();
        updateUserMessageRequest.setId(user_id);
        if (username != null && userService.checkUsernameLength(username)) {
            if (userService.getUserByUsername(username) != null || adminService.getAdminByUsername(username) != null) {
                return Result.result(EnumExceptionType.USER_ALREADY_EXIST_BUT_CAN_UPGRADE);
            }
            updateUserMessageRequest.setUsername(username);
        }
        if (password != null && userService.checkPasswordLength(password)) {
            updateUserMessageRequest.setPassword(password);
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
        userService.updateUsernameById(updateUserMessageRequest);

        return Result.success("更新成功",userService.getUserById(user_id));
    }
}
