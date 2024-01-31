package com.example.forum.service;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.entity.RecruitArticle;
import com.example.forum.exception.MyException;
import com.example.forum.mapper.RecruitArticleMapper;
import jakarta.transaction.Transactional;
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

    private RecruitArticleServiceImpl(){log.info("call new RecruitArticleServiceImpl");}

    //创建招募帖子
    @Override
    public int insertRecruitArticle(RecruitArticle article){
        return recruitArticleMapper.insertRecruitArticle(article);
    }

    //根据article_id获取article
    @Override
    public RecruitArticle getRecruitArticleByArticleId(Integer article_id){
        RecruitArticle article = recruitArticleMapper.getRecruitArticleByArticleId(article_id);
        if (article==null)
            throw new  MyException(EnumExceptionType.ARTICLE_ID_NOT_EXIST);
        return article;
    }

    @Override
    public RecruitArticle getRecruitArticleByUserId(Integer user_id) {
        RecruitArticle article = recruitArticleMapper.getRecruitArticleByUserId(user_id);
        if (article==null)
            throw new MyException(EnumExceptionType.ARTICLE_ID_NOT_EXIST);
        return article;
    }

    //根据指定顺序获取article列表
    @Override
    public List<RecruitArticle> getRecruitArticleListOrderly(String orderly_by_sql){
        return recruitArticleMapper.getRecruitArticleListOrderly(orderly_by_sql);
    }

    //根据id删除帖子
    public int deleteRecruitArticleByArticleId(Integer article_id){
        return recruitArticleMapper.deleteRecruitArticleByArticleId(article_id);
    }

    //根据id更新帖子
    public int updateRecruitArticleByArticleId(Integer article_id, Date update, Integer type, Integer direction, String content, String start_time, String end_time, String contact, Integer finish, Integer top){
        return recruitArticleMapper.updateRecruitArticleByArticleId(article_id,update,type,direction,content,start_time,end_time,contact,finish,top);
    }

}
