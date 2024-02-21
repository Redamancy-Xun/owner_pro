package com.forum.service;

import com.forum.dto.SessionData;
import com.forum.entity.Admin;
import com.forum.entity.User;

public interface AdminService {

    //注册插入一个管理员
    int signupAdmin(Admin admin);

    //根据管理员用户名获取管理员
    Admin getAdminByUsername(String username);

    //检查用户名和密码是否正确
    Boolean checkLogin(String username, String password);

    //检查用户名长度是否正确
    Boolean checkUsernameLength(String username);

    //检查密码长度是否正确
    Boolean checkPasswordLength(String password);

    SessionData login(String username,String password,Integer role);
}
