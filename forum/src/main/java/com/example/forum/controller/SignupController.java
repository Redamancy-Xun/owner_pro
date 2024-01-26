package com.example.forum.controller;


import com.example.forum.common.Return;
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
    public Return signup(@RequestParam("username")String username,
                              @RequestParam("password")String password,
                              @RequestParam("studentid")String studentid,
                              @RequestParam("studentname")String studentname,
                              @RequestParam("birthday")String birthday,
                              @RequestParam("email")String email,
                              @RequestParam("headportrait")String headportrait){

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

        Return ret = new Return();
        ret.setCode(0);
        ret.setMessage("注册成功");
        ret.setResult(null);
        return ret;
    }
}
