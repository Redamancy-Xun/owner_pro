package com.example.forum.mapper;

import com.example.forum.MyMapper;
import com.example.forum.entity.User;
import org.apache.ibatis.annotations.*;


import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper extends MyMapper<User> {

    //插入一个用户
    @Insert("INSERT INTO user(username, password, studentid, studentname, birthday, email, headportrait, type) " +
            "VALUES (#{username}, #{password}, #{studentid}, #{studentname}, #{birthday}, #{email}, #{headportrait}, #{type});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    //根据用户id获取用户
    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(@Param("id")Integer id);

    //根据用户名和密码获取用户
    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password};")
    User getUserByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

    //根据用户名获取用户
    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE username = #{username};")
    User getUserByUsername(@Param("username")String username);

    //根据指定排序条件获取用户列表

    @Select("SELECT * FROM user ORDER BY ${order_by_sql};")
    List<User> getUserListOrderly(@Param("order_by_sql")String order_by_sql);

    //根据用户id删除用户
    @Delete("DELETE FROM user WHERE id = #{id};")
    int deleteUserById(@Param("id")Integer id);

    //根据用户id找到用户，并更新用户信息
    @Update("UPDATE user SET username = #{username}, password = {password}, birthday = {birthday}, " +
            "email = {email}, headportrait = {headportrait} WHERE id = #{id};")
    int updateUsernameById(@Param("username")String username, @Param("password")String password,
                           @Param("birthday")Date birthday, @Param("email")String email,
                           @Param("headportrait")String headportrait, @Param("id")Integer id);

    //根据用户名获取密码
    @Select("SELECT password FROM user WHERE username = #{username}")
    String getPasswordByUsername(@Param("username")String username);
}
