package com.forum.controller.admin;

import com.forum.common.Result;
import com.forum.dto.UserDTO;
import com.forum.service.impl.AdminServiceImpl;
import com.forum.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("个人信息处理 Controller")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

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

    //根据用户id查询用户
    @GetMapping("/getUserById/{id}")
    public Result getUserById(@PathVariable("id")Integer id){

        return Result.success("搜索成功", userService.getUserById(id));
    }

    //根据用户名查询用户
    @GetMapping("/getUserByUsername/{username}")
    public Result getUserByUsername(@PathVariable("username")String username){

        return Result.success("搜索成功", userService.getUserByUsername(username));
    }

    //根据指定排序条件获取用户列表
    @GetMapping("/getUserListOrderly/{order_by_sql}")
    public Result getUserListOrderly(@PathVariable("order_by_sql")String order_by_sql){

        return Result.success("搜索成功", userService.getUserListOrderly(order_by_sql));
    }
}
