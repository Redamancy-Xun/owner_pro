package com.forum.service;

import com.forum.entity.RecruitArticle;

import java.util.Date;
import java.util.List;

public interface RecruitArticleService {

    //创建招募帖子
    int insertRecruitArticle(RecruitArticle article);

    //根据article_id获取article
    RecruitArticle getRecruitArticleByArticleId(Integer article_id);

    //根据user_id获取article
    List<RecruitArticle> getRecruitArticleByUserId(Integer user_id);

    //根据指定排序顺序获取帖子列表
    List<RecruitArticle> getRecruitArticleListOrderly(String orderly_by_sql);

    //根据article_id删除帖子
    int deleteRecruitArticleByArticleId(Integer article_id);

    //根据article_id更新帖子
    int updateRecruitArticleByArticleId(Integer article_id, Date update_date, Integer type, Integer direction,
                                        String tag, String content, String start_time, String end_time,
                                        String contact, Integer finish, Integer top);

    //默认获取帖子
    List<RecruitArticle> defaultGetRecruitArticle(Integer type,Integer direction,Integer finish);

}
