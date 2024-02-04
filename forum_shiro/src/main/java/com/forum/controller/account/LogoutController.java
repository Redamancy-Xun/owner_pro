package com.forum.controller.account;

import com.forum.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiresRoles("online")
@RestController
@Api("登出Controller")
public class LogoutController {

    @RequiresAuthentication
    @GetMapping("/logout")
    @ApiOperation("登出")
    public Result logout() throws IOException {
        SecurityUtils.getSubject().logout();
        return Result.success("登出成功", null);
    }
}
