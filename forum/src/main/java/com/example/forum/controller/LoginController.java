package com.example.forum.controller;
import com.example.forum.common.*;
import com.example.forum.exception.MyException;
import com.example.forum.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public Result login(@RequestParam(value = "username")String username,
                        @RequestParam(value = "password")String password,
                        HttpSession session){

        //记录信息
        log.info("object: " + this);
        log.info("thread: " + Thread.currentThread().getId());

        //参数校验
        if(userService.checkUsernameLength(username)
                && userService.checkPasswordLength(password)
                || !userService.checkLogin(username,password)){
            throw new MyException(EnumExceptionType.PARAMETER_FORMAT_INCORRECT);
        }

        //请求转发，会话管理
        try{
            session.setAttribute("user",userService.getUserByUsernameAndPassword(username,password));
        }catch (RuntimeException e){
            throw new MyException(EnumExceptionType.SYSTEM_INTERNAL_ANOMALY);
        }
        return Result.success("登录成功", null);
    }
}
