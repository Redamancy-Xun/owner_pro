package com.example.forum.controller;

import com.example.forum.entity.User;
import com.example.forum.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.forum.common.*;
@RestController
@Slf4j
public class UserInfo {

    UserServiceImpl userService = new UserServiceImpl();

    @GetMapping("/UserInfo/{id}")
    public User getUserInfo(@PathVariable("id") int id, HttpSession session){

        if (session.getAttribute("user") == null)
            return null;

        User user = new User();
        user = userService.getUserById(id);

        return user;
    }

}
