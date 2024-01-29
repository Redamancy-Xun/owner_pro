package com.example.forum.controller.account;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.common.Result;
import com.example.forum.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api("登出Controller")
public class LogoutController {

    @GetMapping("/logout")
    @ApiOperation("登出")
    public Result logout(HttpSession session){
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            throw new MyException(EnumExceptionType.LOGIN_INVALID);

        //注销session（在服务器里删除该session）
        return Result.success("登出成功", null);
    }
}
