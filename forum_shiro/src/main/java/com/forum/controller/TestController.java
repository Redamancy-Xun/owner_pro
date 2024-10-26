package com.forum.controller;

import com.forum.common.Result;
import com.forum.service.impl.AdminServiceImpl;
import com.forum.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    AdminServiceImpl adminService;

    @PostMapping("/test")
    public Result task(@RequestParam("username") String username,
                       @RequestParam("pwd") String pwd) {
        return Result.success("success", adminService.checkLogin(username, PasswordUtil.convert(pwd)));
    }
}
