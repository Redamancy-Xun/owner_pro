package com.example.forum.controller;
import com.example.forum.common.*;
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

    Return ret = new Return();
    //根据用户名密码获取用户
    @PostMapping("/login")
    public Return login(@RequestParam(value = "username")String username,
                        @RequestParam(value = "password")String password,
                        HttpSession session){

        //验证@RestController修饰的class的对象 会被spring容器作为单例的Bean来管理
        log.info("object: " + this);
        log.info("thread: " + Thread.currentThread().getId());

        //参数校验
        //增加用户名与密码匹配，返回内容统一化
        if(username.length() < 2 || username.length() > 20
                || password.length() < 2 || password.length() > 20
                || userService.getUserListByUsername(username)==null){
            ret.setCode(0);
            ret.setMessage("用户不存在");
            ret.setResult(null);
            return ret;
        } else if (! password.equals(userService.getPasswordByUsername(username))) {
            ret.setCode(0);
            ret.setMessage("密码错误");
            ret.setResult(null);
            return ret;
        }

        //请求转发，会话管理
        try{
            session.setAttribute("user",userService.getUserByUsernameAndPassword(username,password));
        }catch (RuntimeException e){
            ret.setCode(1);
            ret.setMessage("登录超时");
            ret.setResult(null);
            return ret;
        }
        ret.setCode(0);
        ret.setMessage("登录成功");
        ret.setResult(null);
        return ret;
    }
}
