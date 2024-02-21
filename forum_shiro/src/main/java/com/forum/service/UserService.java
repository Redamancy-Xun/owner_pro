package com.forum.service;

import com.forum.controller.request.UpdateUserMessageRequest;
import com.forum.dto.SessionData;
import com.forum.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    //注册插入一个用户
    int signupUser(User user);

    //根据用户名和密码获取用户
    User getUserByUsernameAndPassword(String username, String password);

    //根据用户名获取用户列表
    User getUserByUsername(String username);

    //根据学号获取用户
    User getUserByStudentid(String studentid);

    //根据用户id获取用户
    User getUserById(@Param("id") Integer id);

    //根据指定排序条件获取用户
    List<User> getUserListOrderly(@Param("order_by_sql") String order_by_sql);

    //根据用户id找到用户，并更新用户信息
    void updateUserInfoById(UpdateUserMessageRequest updateUserMessageRequest);

    //根据用户id找到用户，并更新用户密码
    void updatePasswordById(UpdateUserMessageRequest updateUserMessageRequest, String newPassword);

    //根据用户id删除用户
    int deleteUserById(Integer id);

    //根据用户名获取密码
    String getPasswordByUsername(String username);

    //检查用户名和密码是否正确
    Integer checkLogin(String username, String password);

    //检查用户名长度是否正确
    Boolean checkUsernameLength(String username);

    //检查密码长度是否正确
    Boolean checkPasswordLength(String password);

    //检查邮箱格式是否正确
    Boolean checkEmailForm(String email);

    String uploadPortrait(MultipartFile multipartFile,Integer id);

    SessionData login(String username,String password,Integer role);

    Boolean logout(Integer id);

    User getUserBySessionId(String sessionId);

    Integer getStatus(Integer id);
}
