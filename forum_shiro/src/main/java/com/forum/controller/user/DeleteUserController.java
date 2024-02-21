package com.forum.controller.user;

import com.forum.common.Result;
import com.forum.dto.SessionData;
import com.forum.service.impl.UserServiceImpl;
import com.forum.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("注销用户 Controller")
public class DeleteUserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SessionUtil sessionUtil;

    //根据id删除用户
    @GetMapping("/deleteUserById")
    @ApiOperation("注销用户")
    public Result deleteUserById() {

        SessionData sessionData = sessionUtil.getSessionData();
        Integer id = sessionData.getId();
        userService.deleteUserById(id);

        return Result.success("成功删除", id);
    }
}
