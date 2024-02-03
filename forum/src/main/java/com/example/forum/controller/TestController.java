package com.example.forum.controller;

import com.example.forum.common.Result;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequiresAuthentication
    @RequiresRoles("online")
    @PostMapping("/test")
    public Result task() {
        return Result.success("success", null);
    }
}
