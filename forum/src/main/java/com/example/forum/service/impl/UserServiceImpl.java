package com.example.forum.service;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.entity.User;
import com.example.forum.exception.MyException;
import com.example.forum.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {
        log.info("call new UserServiceImpl()");
    }

    //注册插入一个用户
    @Override
    public int signupUser(User user) {
        //查看用户是否已存在
        if (userMapper.getUserByUsername(user.getUsername()) != null)
            throw new MyException(EnumExceptionType.USER_ALREADY_EXIST_BUT_CAN_UPGRADE);
        return userMapper.insertUser(user);
    }

    //根据用户名密码获取用户
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = userMapper.getUserByUsernameAndPassword(username, password);
        if (user == null)
            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        return user;
    }

    //根据用户名获取用户
    @Override
    public User getUserByUsername(String username) {
        User users = userMapper.getUserByUsername(username);
        if (users == null)
            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        return users;
    }

    //根据用户id获取用户
    @Override
    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        if (user == null)
            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        return user;
    }

    //根据指定排序条件获取用户列表
    @Override
    public List<User> getUserListOrderly(String order_by_sql) {
        return userMapper.getUserListOrderly(order_by_sql);
    }

    //根据用户id找到用户，并更新用户信息
    @Override
    public int updateUsernameById(String username, String password, Date birthday,
                                  String email, String headportrait, Integer id) {
        User user = userMapper.getUserById(id);
        if (user == null)
            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        return userMapper.updateUsernameById(username, password, birthday, email, headportrait, id);
    }

    //根据用户id删除用户
    @Override
    public int deleteUserById(Integer id) {
        User user = userMapper.getUserById(id);
        if (user == null)
            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        return userMapper.deleteUserById(id);
    }

    //根据用户名获取密码
    @Override
    public String getPasswordByUsername(String username) {
        String password = userMapper.getPasswordByUsername(username);
        if (password == null )
            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        return password;
    }

    //检查用户名和密码是否正确
    public Boolean checkLogin(String username, String password){
        User user = getUserByUsername(username);
        if (user == null)
            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        if (!user.getPassword().equals(password))
            throw new MyException(EnumExceptionType.PASSWORD_INCORRECT);
        return true;
    }

    //检查用户名长度是否正确
    public Boolean checkUsernameLength(String username){
        if (username.length() > 21 || username.length() < 2)
            throw new MyException(EnumExceptionType.LENGTH_INCORRECT);
        return true;
    }

    //检查密码长度是否正确
    public Boolean checkPasswordLength(String password){
        if (password.length() > 20 || password.length() <6)
            throw new MyException(EnumExceptionType.LENGTH_INCORRECT);
        return true;
    }

    //检查邮箱格式是否正确
    public Boolean checkEmailForm(String email){
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        if (!EMAIL_PATTERN.matcher(email).matches()){
            throw new MyException(EnumExceptionType.EMAIL_INVALID);
        }

        return true;
    }

}
