package com.example.forum.config;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.forum.dto.UserDTO;

/**
 * test测试类
 */
@RestController
public class TestController {

    @GetMapping("/userTest")
    public UserDTO userTest(){
        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        return principal;
    }

    @GetMapping("/anonTest")
    public String anonTest(){
        return "this is anon";
    }

    @GetMapping("/exception")
    public void exception() {
        throw new NullPointerException("asd");
    }


}
