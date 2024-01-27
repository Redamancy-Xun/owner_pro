package com.example.forum.controller;

import com.example.forum.common.Return;
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
    public Return updateUsernameById(@PathVariable("username")String username,
                                   @PathVariable("password")String password,
                                   @PathVariable("birthday")String birthday,
                                   @PathVariable("email")String email,
                                   @PathVariable("headportrait")String headportrait,
                                   @PathVariable("id")Integer id,
                                   HttpSession session){

        Return ret = new Return();
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null) {
            ret.setCode(1);
            ret.setMessage("登录超时");
            ret.setResult(null);
            return ret;
        }

        try {
            int count = userService.updateUsernameById(username, password, birthday, email, headportrait, id);
            log.info("count=" + count);
        } catch (RuntimeException e) {
            ret.setCode(0);
            ret.setMessage("用户不存在");
            ret.setResult(null);
            return ret;
        }
        ret.setCode(0);
        ret.setMessage("信息更新成功");
        ret.setResult(null);
        return ret;
    }
}
