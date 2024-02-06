package com.forum.service.impl;

import com.forum.common.EnumExceptionType;
import com.forum.controller.request.UpdateArticleMessageRequest;
import com.forum.entity.RecruitArticle;
import com.forum.exception.MyException;
import com.forum.mapper.RecruitArticleMapper;
import com.forum.service.RecruitArticleService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class RecruitArticleServiceImpl implements RecruitArticleService {

    @Autowired
    private RecruitArticleMapper recruitArticleMapper;

    public RecruitArticleServiceImpl(){
        log.info("call new RecruitArticleServiceImpl");
    }

    //创建招募帖子
    @Override
    public int insertRecruitArticle(RecruitArticle article){
        return recruitArticleMapper.insertRecruitArticle(article);
    }

    //根据article_id获取article
    @Override
    public RecruitArticle getRecruitArticleByArticleId(Integer article_id){
        RecruitArticle article = recruitArticleMapper.getRecruitArticleByArticleId(article_id);
        if (article == null)
            throw new MyException(EnumExceptionType.ARTICLE_ID_NOT_EXIST);
        return article;
    }

    //根据user_id获取articles
    @Override
    public List<RecruitArticle> getRecruitArticleByUserId(Integer user_id) {
        List<RecruitArticle> articles = recruitArticleMapper.getRecruitArticleByUserId(user_id);
        if (articles == null)
            throw new MyException(EnumExceptionType.ARTICLE_ID_NOT_EXIST);
        return articles;
    }

    //根据指定顺序获取article列表
    @Override
    public List<RecruitArticle> getRecruitArticleListOrderly(String orderly_by_sql){
        return recruitArticleMapper.getRecruitArticleListOrderly(orderly_by_sql);
    }

    //根据article_id删除帖子
    @Override
    public int deleteRecruitArticleByArticleId(Integer article_id){
        if (recruitArticleMapper.getRecruitArticleByArticleId(article_id) == null)
            throw new MyException(EnumExceptionType.ARTICLE_ID_NOT_EXIST);
        return recruitArticleMapper.deleteRecruitArticleByArticleId(article_id);
    }

    //根据id更新帖子
    @Override
    public void updateRecruitArticleByArticleId(UpdateArticleMessageRequest updateArticleMessageRequest){
        Integer article_id = updateArticleMessageRequest.getArticle_id();
        Date update_date = updateArticleMessageRequest.getUpdate_date();
        String type = updateArticleMessageRequest.getType();
        String direction = updateArticleMessageRequest.getDirection();
        String tag = updateArticleMessageRequest.getTag();
        String content = updateArticleMessageRequest.getContent();
        String start_time = updateArticleMessageRequest.getStart_time();
        String end_time = updateArticleMessageRequest.getEnd_time();
        String contact = updateArticleMessageRequest.getContact();
        Integer finish = updateArticleMessageRequest.getFinish();

        recruitArticleMapper.updateUpdateDate(update_date, article_id);
        if (type != null)
            recruitArticleMapper.updateType(type, article_id);
        if (direction != null)
            recruitArticleMapper.updateDirection(direction, article_id);
        if (tag != null)
            recruitArticleMapper.updateTag(tag, article_id);
        if (content != null)
            recruitArticleMapper.updateContent(content, article_id);
        if (start_time != null)
            recruitArticleMapper.updateStartTime(start_time, article_id);
        if (end_time != null)
            recruitArticleMapper.updateEndTime(end_time, article_id);
        if (contact != null)
            recruitArticleMapper.updateContact(contact, article_id);
        if (finish != null)
            recruitArticleMapper.updateFinish(finish, article_id);

    }

    @Override
    public RecruitArticle topRecruitArticleByArticleId(Integer article_id) {
        recruitArticleMapper.updateTop(1, article_id);
        return recruitArticleMapper.getRecruitArticleByArticleId(article_id);
    }

    @Override
    public RecruitArticle untopRecruitArticleByArticleId(Integer article_id) {
        recruitArticleMapper.updateTop(0, article_id);
        return recruitArticleMapper.getRecruitArticleByArticleId(article_id);
    }

    @Override
    public RecruitArticle finishRecruitArticleByArticleId(Integer article_id) {
        recruitArticleMapper.updateFinish(1, article_id);
        return recruitArticleMapper.getRecruitArticleByArticleId(article_id);
    }

    @Override
    public RecruitArticle unfinishRecruitArticleByArticleId(Integer article_id) {
        recruitArticleMapper.updateFinish(0, article_id);
        return recruitArticleMapper.getRecruitArticleByArticleId(article_id);
    }

    @Override
    public List<RecruitArticle> defaultGetRecruitArticle(Integer type,Integer direction,Integer fish) {
        return recruitArticleMapper.defaultGetRecruitArticle(type,direction,fish);
    }



}
