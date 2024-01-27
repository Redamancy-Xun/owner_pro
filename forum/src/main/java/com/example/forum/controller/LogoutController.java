package com.example.forum.controller;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.common.Result;
import com.example.forum.exception.MyException;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogoutController {

    @GetMapping("/logout")
    public Result logout(HttpSession session){
        //检查是否登录（session是否存在）
        Result ret = new Result();
        if (session.getAttribute("user") == null)
            throw new MyException(EnumExceptionType.LOGIN_INVALID);

        //注销session（在服务器里删除该session）
        return Result.success("登出成功", null);
    }
}
