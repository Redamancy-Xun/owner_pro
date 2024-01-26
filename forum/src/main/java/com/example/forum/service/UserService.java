package com.example.forum.service;

import com.example.forum.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    //注册插入一个用户
    int signupUser(User user);

    //根据用户名和密码获取用户
    User getUserByUsernameAndPassword(String username, String password);

    //根据用户名获取用户列表
    List<User> getUserListByUsername(String username);

    //根据用户id获取用户
    User getUserById(@Param("id") Integer id);

    //根据指定排序条件获取用户列表
    List<User> getUserListOrderly(@Param("order_by_sql") String order_by_sql);

    //根据用户id找到用户，并更新用户信息
    int updateUsernameById(String username, String password, String birthday,
                           String email, String headportrait, Integer id);

    //根据用户id删除用户
    int deleteUserById(Integer id);
}
