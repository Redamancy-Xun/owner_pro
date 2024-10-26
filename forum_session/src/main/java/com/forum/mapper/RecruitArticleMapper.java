package com.forum.mapper;

import com.forum.MyMapper;
import com.forum.entity.RecruitArticle;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecruitArticleMapper extends MyMapper<RecruitArticle> {

    //创建一个帖子
    @Insert("INSERT INTO recruit_article (admin_id, user_id, update_date, type, direction, tag, content, start_time, end_time, contact, finish, top)" +
            "VALUES (#{admin_id}, #{user_id}, #{update_date}, #{type}, #{direction}, #{tag}, #{content}, #{start_time}, #{end_time}, #{contact}, #{finish}, #{top});")
    @Options(useGeneratedKeys = true, keyProperty = "article_id")
    int insertRecruitArticle(RecruitArticle article);

    //根据招募帖子id获取帖子
    @ResultType(RecruitArticle.class)
    @Select("SELECT * FROM recruit_article WHERE article_id = #{article_id};")
    RecruitArticle getRecruitArticleByArticleId(@Param("article_id")Integer article_id);

    //根据用户id获取他的帖子
    @ResultType(RecruitArticle.class)
    @Select("SELECT * FROM recruit_article WHERE user_id = #{user_id} ORDER BY top DESC, update_date DESC")
    List<RecruitArticle> getRecruitArticleByUserId(@Param("user_id")Integer user_id);

    //根据管理员id获取他的帖子
    @ResultType(RecruitArticle.class)
    @Select("SELECT * FROM recruit_article WHERE admin_id = #{admin_id} ORDER BY top DESC, update_date DESC")
    List<RecruitArticle> getRecruitArticleByAdminId(@Param("admin_id")Integer admin_id);

    //根据默认顺序获取帖子列表
    @Select("SELECT * FROM recruit_article ORDER BY top DESC, update_date DESC")
    List<RecruitArticle> defaultGetRecruitArticle();

    //根据指定排序条件获取帖子列表
    @Select("SELECT * FROM recruit_article ORDER BY ${order_by_sql};")
    List<RecruitArticle> getRecruitArticleListOrderly(@Param("order_by_sql")String order_by_sql);

    //根据帖子id删除帖子
    @Delete("DELETE FROM recruit_article WHERE article_id = #{article_id};")
    int deleteRecruitArticleByArticleId(@Param("article_id")Integer article_id);

    //根据帖子id找到帖子，并更新招募帖子信息
    @Update("UPDATE recruit_article SET update_date = #{update_date} WHERE article_id = #{article_id};")
    void updateUpdateDate(@Param("update_date")Date update_date, @Param("article_id")Integer article_id);

    @Update("UPDATE recruit_article SET type = #{type} WHERE article_id = #{article_id};")
    void updateType(@Param("type")String type, @Param("article_id")Integer article_id);

    @Update("UPDATE recruit_article SET direction = #{direction} WHERE article_id = #{article_id};")
    void updateDirection(@Param("direction")String direction, @Param("article_id")Integer article_id);

    @Update("UPDATE recruit_article SET tag = #{tag} WHERE article_id = #{article_id};")
    void updateTag(@Param("tag")String tag, @Param("article_id")Integer article_id);

    @Update("UPDATE recruit_article SET content = #{content} WHERE article_id = #{article_id};")
    void updateContent(@Param("content")String content, @Param("article_id")Integer article_id);

    @Update("UPDATE recruit_article SET start_time = #{start_time} WHERE article_id = #{article_id};")
    void updateStartTime(@Param("start_time")String start_time, @Param("article_id")Integer article_id);

    @Update("UPDATE recruit_article SET end_time = #{end_time} WHERE article_id = #{article_id};")
    void updateEndTime(@Param("end_time")String end_time, @Param("article_id")Integer article_id);

    @Update("UPDATE recruit_article SET contact = #{contact} WHERE article_id = #{article_id};")
    void updateContact(@Param("contact")String contact, @Param("article_id")Integer article_id);

    @Update("UPDATE recruit_article SET finish = #{finish} WHERE article_id = #{article_id};")
    void updateFinish(@Param("finish")Integer finish, @Param("article_id")Integer article_id);

    @Update("UPDATE recruit_article SET top = #{top} WHERE article_id = #{article_id};")
    void updateTop(@Param("top")Integer top, @Param("article_id")Integer article_id);

}
