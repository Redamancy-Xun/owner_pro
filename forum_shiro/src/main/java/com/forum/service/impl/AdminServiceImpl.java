package com.forum.service.impl;

import com.forum.common.EnumExceptionType;
import com.forum.entity.Admin;
import com.forum.entity.User;
import com.forum.exception.MyException;
import com.forum.mapper.AdminMapper;
import com.forum.mapper.UserMapper;
import com.forum.service.AdminService;
import com.forum.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private SessionUtil sessionUtil;

    @Override
    public int signupAdmin(Admin admin) {
        //查看用户是否已存在
        if (userMapper.getUserByUsername(admin.getUsername()) != null || adminMapper.getAdminByUsername((admin.getUsername())) != null)
            throw new MyException(EnumExceptionType.USER_ALREADY_EXIST_BUT_CAN_UPGRADE);
        admin.setSessionId(sessionUtil.generateSessionId());
        return adminMapper.insertAdmin(admin);
    }

    //根据管理员用户名获取管理员
    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.getAdminByUsername(username);
    }

    //检查用户名和密码是否正确
    @Override
    public Boolean checkLogin(String username, String password){
        Admin admin = getAdminByUsername(username);
        User user = userMapper.getUserByUsername(username);
        if (admin == null && user == null)
            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        if (admin != null && !admin.getPassword().equals(password))
            throw new MyException(EnumExceptionType.PASSWORD_INCORRECT);
        if (user != null && !user.getPassword().equals(password))
            throw new MyException(EnumExceptionType.PASSWORD_INCORRECT);
        return true;
    }

    //检查用户名长度是否正确
    @Override
    public Boolean checkUsernameLength(String username){
        if (username.length() > 21 || username.length() < 2)
            throw new MyException(EnumExceptionType.LENGTH_INCORRECT);
        return true;
    }

    //检查密码长度是否正确
    @Override
    public Boolean checkPasswordLength(String password){
        if (password.length() > 20 || password.length() <6)
            throw new MyException(EnumExceptionType.LENGTH_INCORRECT);
        return true;
    }
}
