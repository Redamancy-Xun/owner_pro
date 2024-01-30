package com.example.forum.mapper;

import com.example.forum.entity.RecruitArticle;
import com.example.forum.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecruitArticleMapper {

    //插入一个帖子
    @Insert("INSERT INTO recruitarticle (user_id, update_date, type, direction, content, start_time, end_time, contact, finish, top)" +
            "VALUES (#{user_id}, #{update_date}, #{type}, #{direction}, #{content}, #{start_time}, #{end_time}, #{contact}, #{finish}, #{top});")
    @Options(useGeneratedKeys = true, keyProperty = "article_id")
    int insertRecruitArticle(RecruitArticle article);

    //根据招募帖子id获取帖子
    @ResultType(RecruitArticle.class)
    @Select("SELECT * FROM recruitarticle WHERE article_id = #{article_id};")
    RecruitArticle getRecruitArticleByArticleId(@Param("article_id")Integer article_id);

    //根据用户id获取他、帖子
    @ResultType(RecruitArticle.class)
    @Select("SELECT * FROM recruitarticle WHERE article_id = #{user_id};")
    RecruitArticle getRecruitArticleByUserId(@Param("user_id")Integer user_id);


    //根据指定排序条件获取帖子列表
    @ResultMap("recruitArticleList")
    @Select("SELECT * FROM recruitarticle ORDER BY ${order_by_sql};")
    List<RecruitArticle> getRecruitArticleListOrderly(@Param("order_by_sql")String order_by_sql);

    //根据帖子id删除帖子
    @Delete("DELETE FROM recruitarticle WHERE article_id = #{article_id};")
    int deleteRecruitArticleByArticleId(@Param("article_id")Integer article_id);

    //根据帖子id找到帖子，并更新招募帖子信息
    @Update("UPDATE recruitarticle SET update_date = #{update_date}, type = #{type}, direction = #{direction}, " +
            "content = #{content}, start_time = #{start_time}, end_time = #{end_time}, contact = #{contact}, " +
            "finish = #{finish}, top = #{top};")
    int updateRecruitArticleByArticleId(@Param("update_date")Date update_date, @Param("type")Integer type,
                                        @Param("direction")Integer direction, @Param("content")String content,
                                        @Param("start_time")String start_time, @Param("end_time")String end_time,
                                        @Param("contact")String contact, @Param("finish")Integer finish,
                                        @Param("top")Integer top);

}
