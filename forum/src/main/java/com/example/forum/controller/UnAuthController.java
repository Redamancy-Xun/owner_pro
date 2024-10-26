package com.example.forum.controller;

import com.example.forum.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("未登录Controller")
public class UnAuthController {

    @RequestMapping("/unauth")
    @ApiOperation("未登录")
    public Result unauth() {
        return Result.fail("未登录");
    }

}
