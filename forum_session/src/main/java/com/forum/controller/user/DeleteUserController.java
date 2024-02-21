package com.forum.controller.user;

import com.forum.common.Result;
import com.forum.dto.UserDTO;
import com.forum.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("注销用户 Controller")
public class DeleteUserController {

    @Autowired
    private UserServiceImpl userService;

    //根据id删除用户
    @RequiresRoles("user")
    @GetMapping("/deleteUserById")
    @ApiOperation("注销用户")
    public Result deleteUserById() {

        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        Integer id = principal.getId();
        userService.deleteUserById(id);

        return Result.success("成功删除", id);
    }
}
