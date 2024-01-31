package com.example.forum.service;

import com.example.forum.entity.RecruitArticle;
import com.example.forum.entity.RecruitArticle;
import com.example.forum.entity.User;
import io.swagger.models.auth.In;

import java.util.Date;
import java.util.List;

public interface RecruitArticleService {

    //创建招募帖子
    public int insertRecruitArticle(RecruitArticle article);

    //根据article_id获取article
    public RecruitArticle getRecruitArticleByArticleId(Integer article_id);

    //根据user_id获取article
    public List<RecruitArticle> getRecruitArticleByUserId(Integer user_id);

    //根据指定排序顺序获取帖子列表
    public List<RecruitArticle> getRecruitArticleListOrderly(String orderly_by_sql);

    //根据article_id删除帖子
    public int deleteRecruitArticleByArticleId(Integer article_id);

    //根据article_id更新帖子
    public int updateRecruitArticleByArticleId(Integer article_id, Date update_date, Integer type, Integer direction,
                                               String tag, String content, String start_time, String end_time,
                                               String contact, Integer finish, Integer top);


}
