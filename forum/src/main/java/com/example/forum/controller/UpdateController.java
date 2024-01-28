package com.example.forum.controller;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.common.Result;
import com.example.forum.exception.MyException;
import com.example.forum.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class UpdateController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/updateUsernameById/{username}/{password}/{birthday}/{email}/{headportrait}/{id}")
    public Result updateUsernameById(@PathVariable("username")String username,
                                     @PathVariable("password")String password,
                                     @PathVariable("birthday")String birthday,
                                     @PathVariable("email")String email,
                                     @PathVariable("headportrait")String headportrait,
                                     @PathVariable("id")Integer id,
                                     HttpSession session){

        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            throw new MyException(EnumExceptionType.LOGIN_INVALID);

        //参数校验
        if (userService.checkPasswordLength(password)
                &&userService.checkUsernameLength(username)
                &&userService.checkEmailForm(email)){
            //记录信息
            int count = userService.updateUsernameById(username, password, birthday, email, headportrait, id);
            log.info("count=" + count);

            return Result.success("更新成功", null);

        }

        return Result.fail("更新失败",null);
    }
}
