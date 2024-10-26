package com.forum.mapper;

import com.forum.MyMapper;
import com.forum.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper extends MyMapper<User> {

    //插入一个用户
    @Insert("INSERT INTO user(username, password, studentid, studentname, birthday, email, headportrait) " +
            "VALUES (#{username}, #{password}, #{studentid}, #{studentname}, #{birthday}, #{email}, #{headportrait});")
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

    //根据学号获取用户
    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE studentid = #{studentid};")
    User getUserByStudentid(@Param("studentid")String studentid);

    //根据指定排序条件获取用户列表
    @Select("SELECT * FROM user ORDER BY ${order_by_sql};")
    List<User> getUserListOrderly(@Param("order_by_sql")String order_by_sql);

    //根据用户id删除用户
    @Delete("DELETE FROM user WHERE id = #{id};")
    int deleteUserById(@Param("id")Integer id);

    //根据用户id找到用户，并更新用户信息
    @Update("UPDATE user SET username = #{username} WHERE id = #{id};")
    void updateUserUsername(@Param("username")String username, @Param("id")Integer id);

    @Update("UPDATE user SET password = #{password} WHERE id = #{id};")
    void updateUserPassword(@Param("password")String password, @Param("id")Integer id);

    @Update("UPDATE user SET birthday = #{birthday} WHERE id = #{id};")
    void updateUserBirthday(@Param("birthday")Date birthday, @Param("id")Integer id);

    @Update("UPDATE user SET email = #{email} WHERE id = #{id};")
    void updateUserEmail(@Param("email")String email, @Param("id")Integer id);

    @Update("UPDATE user SET headportrait = #{headportrait} WHERE id = #{id};")
    void updateUserHeadportrait(@Param("headportrait")String headportrait, @Param("id")Integer id);

    //根据用户名获取密码
    @Select("SELECT password FROM user WHERE username = #{username}")
    String getPasswordByUsername(@Param("username")String username);
}
