package com.forum.controller.account;

import com.forum.common.Result;
import com.forum.dto.SessionData;
import com.forum.service.UserService;
import com.forum.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@Api("登出Controller")
public class LogoutController {

    @Autowired
    UserService userService;

    @Autowired
    SessionUtil sessionUtil;
    @RequiresRoles("online")
    @GetMapping("/logout")
    @ApiOperation("登出")
    public Result logout() throws IOException {


        SessionData sessionData = sessionUtil.getSessionData();
        String sessionId = sessionUtil.getSessionId();

        if (userService.logout(sessionData.getId())) {
            return Result.success("登出成功", null);
        }
        return Result.fail("登出失败，请重试");
    }
}
