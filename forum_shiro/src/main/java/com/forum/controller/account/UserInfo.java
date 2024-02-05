package com.forum.controller.account;

import com.forum.common.Result;
import com.forum.controller.response.GetAdminResponse;
import com.forum.controller.response.GetUserResponse;
import com.forum.dto.UserDTO;
import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.service.impl.UserServiceImpl;
import com.forum.common.*;
import com.forum.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("个人信息Controller")
public class UserInfo {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    //根据id获取个人信息
    @RequiresRoles("online")
    @GetMapping("/userInfo")
    @ApiOperation("个人信息")
    public Result getUserInfo(){

        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String username = principal.getUsername();
        if(principal.getType()==1)
            return Result.success("获取成功",new GetAdminResponse(adminService.getAdminByUsername(username), principal.getType()));
        return Result.success("获取成功", new GetUserResponse(userService.getUserByUsername(username), principal.getType()));
    }

    
}
