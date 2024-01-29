package com.example.forum.controller.account;

import com.example.forum.exception.MyException;
import com.example.forum.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.forum.common.*;
@RestController
@Slf4j
@Api("个人信息Controller")
public class UserInfo {

    @Autowired
    UserServiceImpl userService;

    //根据id获取个人信息
    @GetMapping("/UserInfo/{id}")
    @ApiOperation("个人信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Integer")
    public Result getUserInfo(@Validated @PathVariable("id") int id, HttpSession session){

        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            throw new MyException(EnumExceptionType.LOGIN_INVALID);

        return Result.success("获取成功", userService.getUserById(id));
    }

}
