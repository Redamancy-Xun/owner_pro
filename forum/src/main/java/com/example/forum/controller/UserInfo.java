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
@RestController
@Slf4j
public class UserInfo {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/UserInfo/{id}")
    public Return getUserInfo(@PathVariable("id") int id, HttpSession session){

        Return ret = new Return();

        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null) {
            ret.setCode(0);
            ret.setMessage("登录超时");
            ret.setResult(null);
            return ret;
        }

        ret.setCode(0);
        ret.setMessage("个人信息成功显示");
        ret.setResult(userService.getUserById(id));
        return ret;
    }

}
