package com.example.forum.mapper;

import com.example.forum.entity.RecruitArticle;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecruitArticleMapper {

    //创建一个帖子
    @Insert("INSERT INTO recruit_article (user_id, update_date, type, direction, tag, content, start_time, end_time, contact, finish, top)" +
            "VALUES (#{user_id}, #{update_date}, #{type}, #{direction}, #{tag}, #{content}, #{start_time}, #{end_time}, #{contact}, #{finish}, #{top});")
    @Options(useGeneratedKeys = true, keyProperty = "article_id")
    int insertRecruitArticle(RecruitArticle article);

    //根据招募帖子id获取帖子
    @ResultType(RecruitArticle.class)
    @Select("SELECT * FROM recruit_article WHERE article_id = #{article_id};")
    RecruitArticle getRecruitArticleByArticleId(@Param("article_id")Integer article_id);

    //根据用户id获取他的帖子
    @ResultType(RecruitArticle.class)
    @Select("SELECT * FROM recruit_article WHERE article_id = #{user_id};")
    List<RecruitArticle> getRecruitArticleByUserId(@Param("user_id")Integer user_id);

    //根据默认顺序获取帖子列表
    @Select("SELECT * FROM recruit_article ORDER BY top DESC, update_date DESC;")
    List<RecruitArticle> defaultGetRecruitArticle();

    //根据指定排序条件获取帖子列表

    @Select("SELECT * FROM recruit_article ORDER BY ${order_by_sql};")
    List<RecruitArticle> getRecruitArticleListOrderly(@Param("order_by_sql")String order_by_sql);

    //根据帖子id删除帖子
    @Delete("DELETE FROM recruit_article WHERE article_id = #{article_id};")
    int deleteRecruitArticleByArticleId(@Param("article_id")Integer article_id);

    //根据帖子id找到帖子，并更新招募帖子信息
    @Update("UPDATE recruit_article SET update_date = #{update_date}, type = #{type}, direction = #{direction}, " +
            "tag = #{tag}, content = #{content}, start_time = #{start_time}, end_time = #{end_time}, " +
            "contact = #{contact}, finish = #{finish}, top = #{top} WHERE article_id = #{article_id};")
    int updateRecruitArticleByArticleId(@Param("article_id") Integer article_id,@Param("update_date")Date update_date,
                                        @Param("type")Integer type, @Param("direction")Integer direction,
                                        @Param("tag") String tag, @Param("content")String content,
                                        @Param("start_time")String start_time, @Param("end_time")String end_time,
                                        @Param("contact")String contact, @Param("finish")Integer finish,
                                        @Param("top")Integer top);

}
