package com.example.forum.service;

import com.example.forum.entity.User;
import com.example.forum.mapper.UserMapper;
import com.example.forum.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.forum.entity.*;



import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {
        log.info("call new UserServiceImpl()");
    }

    //注册插入一个用户
    @Override
    public int signupUser(User user) {
        return userMapper.insertUser(user);
    }

    //根据用户名密码获取用户
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = userMapper.getUserByUsernameAndPassword(username, password);
        if (user == null)
            throw new RuntimeException();
        return user;
    }

    @Override
    public List<User> getUserListByUsername(String username) {
        return userMapper.getUserListByUsername(username);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUserListOrderly(String order_by_sql) {
        return userMapper.getUserListOrderly(order_by_sql);
    }

    //根据用户id找到用户，并更新用户信息
    @Override
    public int updateUsernameById(String username, String password, String birthday,
                                  String email, String headportrait, Integer id) {
        User user = userMapper.getUserById(id);
        if (user == null)
            throw new RuntimeException();
        return userMapper.updateUsernameById(username, password, birthday, email, headportrait, id);
    }

    //根据用户id删除用户
    @Override
    public int deleteUserById(Integer id) {
        User user = userMapper.getUserById(id);
        if (user == null)
            throw new RuntimeException();
        return userMapper.deleteUserById(id);
    }

    //根据用户名获取密码
    @Override
    public String getPasswordByUsername(String username) {
        String password = userMapper.getPasswordByUsername(username);
        if (password == null )
            throw new RuntimeException();
        return userMapper.getPasswordByUsername(username);
    }


}
