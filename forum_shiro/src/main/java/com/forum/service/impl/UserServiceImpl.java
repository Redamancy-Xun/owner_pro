package com.forum.service.impl;

import com.forum.common.EnumExceptionType;
import com.forum.controller.request.UpdateUserMessageRequest;
import com.forum.entity.Admin;
import com.forum.entity.User;
import com.forum.exception.MyException;
import com.forum.mapper.AdminMapper;
import com.forum.mapper.UserMapper;
import com.forum.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    public UserServiceImpl() {
        log.info("call new UserServiceImpl()");
    }

    //注册插入一个用户
    @Override
    public int signupUser(User user) {
        //查看用户是否已存在
        if (userMapper.getUserByUsername(user.getUsername()) != null || adminMapper.getAdminByUsername((user.getUsername())) != null)
            throw new MyException(EnumExceptionType.USER_ALREADY_EXIST_BUT_CAN_UPGRADE);
        if (userMapper.getUserByStudentid(user.getStudentid()) != null)
            throw new MyException(EnumExceptionType.STUDENTID_USED);
        return userMapper.insertUser(user);
    }

    //根据用户名密码获取用户
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userMapper.getUserByUsernameAndPassword(username, password);
    }

    //根据用户名获取用户
    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public User getUserByStudentid(String studentid) {
        return userMapper.getUserByStudentid(studentid);
    }

    //根据用户id获取用户
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    //根据指定排序条件获取用户列表
    @Override
    public List<User> getUserListOrderly(String order_by_sql) {
        return userMapper.getUserListOrderly(order_by_sql);
    }

    //根据用户id找到用户，并更新用户信息
    @Override
    public void updateUsernameById(UpdateUserMessageRequest updateUserMessageRequest) {
        Integer id = updateUserMessageRequest.getId();
        String username = updateUserMessageRequest.getUsername();
        String password = updateUserMessageRequest.getPassword();
        Date birthday = updateUserMessageRequest.getBirthday();
        String headportrait = updateUserMessageRequest.getHeadportrait();

        if (username != null)
            userMapper.updateUserUsername(username, id);
        if (password != null)
            userMapper.updateUserPassword(password, id);
        if (birthday != null)
            userMapper.updateUserBirthday(birthday, id);
        if (headportrait != null)
            userMapper.updateUserHeadportrait(headportrait, id);

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
        Admin admin = adminMapper.getAdminByUsername(username);
        if (user == null && admin == null)
            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        if (user != null && !user.getPassword().equals(password))
            throw new MyException(EnumExceptionType.PASSWORD_INCORRECT);
        if (admin != null && !admin.getPassword().equals(password))
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

    @Override
    public String uploadPortrait(MultipartFile multipartFile, Integer id) {

        String root = "/var/www/html/image/";
        User user = userMapper.getUserById(id);

        if (user == null) throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        if (multipartFile == null) throw new MyException(EnumExceptionType.EMPTY_FILE);
        if (!("image/png".equals(multipartFile.getContentType()))
                && !("image/jpeg".equals(multipartFile.getContentType())))
            throw new MyException(EnumExceptionType.FILE_FORMAT_ERROE);

        String oldPortrait = user.getHeadportrait();
        String originalFilename = multipartFile.getOriginalFilename();
        String path = root +originalFilename;
        user.setHeadportrait(path);
        userMapper.updateUserHeadportrait(user.getHeadportrait(),user.getId());
        File destFile = new File(path);
        try {
            multipartFile.transferTo(destFile);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        String newPortrait = userMapper.getUserById(id).getHeadportrait();
        if (newPortrait==null || oldPortrait.equals(newPortrait) )
            throw new MyException(EnumExceptionType.PORTRAIT_UPDATE_FAILED);

        return "http://116.62.103.210/image/"+originalFilename;
    }

}
