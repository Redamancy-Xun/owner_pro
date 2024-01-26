package com.example.forum.controller;

import com.example.forum.entity.User;
import com.example.forum.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SignupController {

    @Autowired
    UserServiceImpl userService;

    //注册插入一个用户
    @GetMapping("/signup/{username}/{password}/{studentid}/{studentname}/{birthday}/{email}/{headportrait}")
    public String signup(@PathVariable("username")String username,
                              @PathVariable("password")String password,
                              @PathVariable("studentid")String studentid,
                              @PathVariable("studentname")String studentname,
                              @PathVariable("birthday")String birthday,
                              @PathVariable("email")String email,
                              @PathVariable("headportrait")String headportrait){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setStudentid(studentid);
        user.setStudentname(studentname);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setHeadportrait(headportrait);

        //insertUser返回插入的条数
        int count = userService.signupUser(user);
        log.info("count=" + count);
        log.info("id=" + user.getId());
        return "Signup successfully";
    }
}
