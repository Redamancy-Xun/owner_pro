package com.forum.mapper;

import com.forum.MyMapper;
import com.forum.entity.Admin;
import com.forum.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * user  generated at 2019-11-30 18:52:33 by: undestiny
 */

@Mapper
public interface AdminMapper extends MyMapper<User> {

    //插入一个管理员
    @Insert("INSERT INTO admin(username, password) VALUES (#{username}, #{password});")
    @Options(useGeneratedKeys = true, keyProperty = "admin_id")
    int insertAdmin(Admin admin);

    //根据管理员用户名获取管理员
    @ResultType(Admin.class)
    @Select("SELECT * FROM admin WHERE userName=#{userName}")
    Admin getAdminByUsername(String username);

}
