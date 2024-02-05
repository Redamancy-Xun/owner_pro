package com.example.forum.mapper;

import com.example.forum.MyMapper;
import com.example.forum.entity.Admin;
import com.example.forum.entity.RecruitArticle;
import com.example.forum.entity.User;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * user  generated at 2019-11-30 18:52:33 by: undestiny
 */

@Repository
public interface AdminMapper extends MyMapper<User> {

    //根据管理员用户名获取管理员
    @ResultType(Admin.class)
    @Select("SELECT * FROM admin WHERE userName=#{userName}")
    Admin getAdminByUsername(String username);

}
