package com.forum.controller.account;

import com.forum.service.impl.UserServiceImpl;
import com.forum.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //根据用户id删除用户
    @GetMapping("/deleteUserById/{id}")
    public Result deleteUserById(@PathVariable("id")Integer id) {

        int count = userService.deleteUserById(id);
        log.info("count=" + count);

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
