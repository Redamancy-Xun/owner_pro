package com.example.forum.controller;

import com.example.forum.entity.User;
import com.example.forum.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.forum.common.*;



import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;
    Return ret = new Return();

    @GetMapping("/deleteUserById/{id}")
    public Return deleteUserById(@PathVariable("id")Integer id,
                                 HttpSession session){
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null) {
            ret.setCode(0);
            ret.setMessage("登录超时");
            ret.setResult(null);
            return ret;
        }


        try {
            int count = userService.deleteUserById(id);
            log.info("count=" + count);
        } catch (RuntimeException e) {
            ret.setCode(1);
            ret.setMessage("未找到该用户");
            ret.setResult(null);
            return ret;
        }
        ret.setCode(0);
        ret.setMessage("删除成功");
        ret.setResult(null);
        return ret;
    }

    @GetMapping("/getUserById/{id}")
    public Return getUserById(@PathVariable("id")Integer id,
                            HttpSession session){

        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null) {
            ret.setCode(0);
            ret.setMessage("登录超时");
            ret.setResult(null);
            return ret;
        }

        ret.setCode(0);
        ret.setMessage("搜索成功");
        ret.setResult(userService.getUserById(id));
        return ret;
    }

    @GetMapping("/getUserByUsername/{username}")
    public Return getUserByUsername(@PathVariable("username")String username,
                                        HttpSession session){

        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null) {
            ret.setCode(0);
            ret.setMessage("登录超时");
            ret.setResult(null);
            return ret;
        }

        ret.setCode(0);
        ret.setMessage("搜索成功");
        ret.setResult(userService.getUserListByUsername(username));
        return ret;
    }

    @GetMapping("/getUserListOrderly/{order_by_sql}")
    public Return getUserListOrderly(@PathVariable("order_by_sql")String order_by_sql,
                                         HttpSession session){
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null) {
            ret.setCode(0);
            ret.setMessage("登录超时");
            ret.setResult(null);
            return ret;
        }

        ret.setCode(0);
        ret.setMessage("搜索成功");
        ret.setResult(userService.getUserListOrderly(order_by_sql));
        return ret;
    }
}
