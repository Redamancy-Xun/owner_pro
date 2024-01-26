package com.example.forum.controller;

import com.example.forum.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    //根据用户名密码获取用户
    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable(value = "username")String username,
                        @PathVariable(value = "password")String password,
                        HttpSession session){

        //验证@RestController修饰的class的对象 会被spring容器作为单例的Bean来管理
        log.info("object: " + this);
        log.info("thread: " + Thread.currentThread().getId());

        //参数校验
        if(username.length() < 2 || username.length() > 20
                || password.length() < 2 || password.length() > 20){
            return "Login failed";
        }

        //请求转发，会话管理
        try{
            session.setAttribute("user",userService.getUserByUsernameAndPassword(username,password));
        }catch (RuntimeException e){
            return "Login failed";
        }
        return "Login successfully";
    }
}
