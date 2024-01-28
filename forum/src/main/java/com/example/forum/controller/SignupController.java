package com.example.forum.controller;


import com.example.forum.common.Result;
import com.example.forum.entity.User;
import com.example.forum.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SignupController {

    @Autowired
    UserServiceImpl userService;

    //注册插入一个用户
    @PostMapping("/signup")
    public Result signup(@RequestParam("username")String username,
                         @RequestParam("password")String password,
                         @RequestParam("studentid")String studentid,
                         @RequestParam("studentname")String studentname,
                         @RequestParam("birthday")String birthday,
                         @RequestParam("email")String email,
                         @RequestParam("headportrait")String headportrait){

        User user = new User();




        if (userService.checkUsernameLength(username)){
            user.setUsername(username);
        }
        if (userService.checkPasswordLength(password)){
            user.setPassword(password);
        }

        user.setStudentid(studentid);
        user.setStudentname(studentname);
        user.setBirthday(birthday);
        if (userService.checkEmailForm(email)) {
            user.setEmail(email);
        }
        user.setHeadportrait(headportrait);

        //insertUser返回插入的条数
        int count = userService.signupUser(user);
        log.info("count=" + count);
        log.info("id=" + user.getId());

        return Result.success("注册成功", null);
    }
}
