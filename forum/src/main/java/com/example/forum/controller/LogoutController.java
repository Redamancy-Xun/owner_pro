package com.example.forum.controller;

import com.example.forum.common.Return;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogoutController {

    @GetMapping("/logout")
    public Return logout(HttpSession session){
        //检查是否登录（session是否存在）
        Return ret = new Return();
        if (session.getAttribute("user") == null){
            ret.setCode(0);
            ret.setMessage("请先登录");
            ret.setResult(null);
            return ret;
        }
        //注销session（在服务器里删除该session）
        session.invalidate();
        ret.setCode(0);
        ret.setMessage("登出成功");
        ret.setResult(null);
        return ret;
    }
}
