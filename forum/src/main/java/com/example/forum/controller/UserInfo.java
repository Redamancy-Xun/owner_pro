package com.example.forum.controller;

import com.example.forum.exception.MyException;
import com.example.forum.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.forum.common.*;
@RestController
@Slf4j
public class UserInfo {

    @Autowired
    UserServiceImpl userService;

    //根据id获取个人信息
    @GetMapping("/UserInfo/{id}")
    public Result getUserInfo(@PathVariable("id") int id, HttpSession session){

        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            throw new MyException(EnumExceptionType.LOGIN_INVALID);

        return Result.success("获取成功", userService.getUserById(id));
    }

}
