package com.forum.service;

import com.forum.common.Page;
import com.forum.controller.request.UpdateArticleMessageRequest;
import com.forum.controller.response.ShowArticleResponse;
import com.forum.entity.RecruitArticle;

import java.util.Date;
import java.util.List;

public interface RecruitArticleService {

    //创建招募帖子
    int insertRecruitArticle(RecruitArticle article);

    //根据article_id获取article
    RecruitArticle getRecruitArticleByArticleId(Integer article_id);

    //根据user_id获取article
    Page<RecruitArticle> getRecruitArticleByUserId(Integer pageNum, Integer pageSize, Integer user_id);

    //根据admin_id获取article
    Page<RecruitArticle> getRecruitArticleByAdminId(Integer pageNum, Integer pageSize, Integer admin_id);

    //根据指定排序顺序获取帖子列表
    List<RecruitArticle> getRecruitArticleListOrderly(String orderly_by_sql);

    //根据article_id删除帖子
    int deleteRecruitArticleByArticleId(Integer article_id);

    //根据article_id更新帖子
    void updateRecruitArticleByArticleId(UpdateArticleMessageRequest updateArticleMessageRequest);

    //根据article_id置顶帖子
    RecruitArticle topRecruitArticleByArticleId(Integer article_id);

    //根据article_id取消置顶帖子
    RecruitArticle untopRecruitArticleByArticleId(Integer article_id);

    //根据article_id设置帖子完成状态
    RecruitArticle finishRecruitArticleByArticleId(Integer article_id);

    //根据article_id设置帖子未完成状态
    RecruitArticle unfinishRecruitArticleByArticleId(Integer article_id);

    //默认获取帖子
    Page<ShowArticleResponse> defaultGetRecruitArticle(Integer pageSize, Integer pageNum, List<String> type,
                                                       List<String> direction, List<String> tag, Integer finish);

}
