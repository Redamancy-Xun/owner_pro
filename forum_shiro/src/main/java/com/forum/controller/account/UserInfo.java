package com.forum.controller.account;

import com.forum.common.Result;
import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.service.impl.UserServiceImpl;
import com.forum.common.*;
import com.forum.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiresRoles("user")
@RestController
@Slf4j
@Api("个人信息Controller")
public class UserInfo {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RecruitArticleServiceImpl articleService;

    //根据id获取个人信息
    @GetMapping("/UserInfo/{id}")
    @ApiOperation("用户个人信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Integer")
    public Result getUserInfo(@Validated @PathVariable("id") int id){

        return new Result(0, "获取成功", userService.getUserById(id), articleService.getRecruitArticleByUserId(id));
    }
}
