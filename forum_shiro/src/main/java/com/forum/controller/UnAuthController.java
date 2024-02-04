package com.forum.controller;

import com.forum.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnAuthController {

    @RequestMapping(value = "/unauth")
    public Result unauth() {
        return Result.fail("未登录");
    }

}
