package com.forum.service.impl;

import com.forum.common.Page;
import com.forum.common.EnumExceptionType;
import com.forum.controller.request.UpdateArticleMessageRequest;
import com.forum.controller.response.DownloadArticleResponse;
import com.forum.controller.response.ShowArticleResponse;
import com.forum.dto.Excel;
import com.forum.entity.RecruitArticle;
import com.forum.exception.MyException;
import com.forum.mapper.RecruitArticleMapper;
import com.forum.service.RecruitArticleService;
import com.forum.util.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@Transactional
public class RecruitArticleServiceImpl implements RecruitArticleService {

    @Autowired
    private RecruitArticleMapper recruitArticleMapper;

    @Autowired
    private ExcelUtil excelUtil;

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
    public Page<ShowArticleResponse> getRecruitArticleByUserId(Integer pageNum, Integer pageSize, Integer user_id) {
        PageHelper.startPage(pageNum, pageSize);
        List<RecruitArticle> articlesList = recruitArticleMapper.getRecruitArticleByUserId(user_id);
        if (articlesList == null)
            throw new MyException(EnumExceptionType.ARTICLE_ID_NOT_EXIST);

        Page<RecruitArticle> originalPage = new Page<>(new PageInfo<>(articlesList));
        Page<ShowArticleResponse> Page = new Page<>();
        Page.setPageSize(originalPage.getPageSize());
        Page.setPageNum(originalPage.getPageNum());
        Page.setPages(originalPage.getPages());
        Page.setTotal(originalPage.getTotal());

        List<ShowArticleResponse> articleResponses = new ArrayList<>();
        for (RecruitArticle article : originalPage.getItems()) {
            articleResponses.add(new ShowArticleResponse(article));
        }
        Page.setItems(articleResponses);

        return Page;
    }

    //根据admin_id获取articles
    @Override
    public Page<ShowArticleResponse> getRecruitArticleByAdminId(Integer pageNum, Integer pageSize, Integer admin_id) {
        PageHelper.startPage(pageNum, pageSize);
        List<RecruitArticle> articlesList = recruitArticleMapper.getRecruitArticleByAdminId(admin_id);
        if (articlesList == null)
            throw new MyException(EnumExceptionType.ARTICLE_ID_NOT_EXIST);
        List<ShowArticleResponse> articleResponse = new ArrayList<>();
        for (RecruitArticle article : articlesList) {
            articleResponse.add(new ShowArticleResponse(article));
        }

        return new Page<>(new PageInfo<>(articleResponse));
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
    public Page<ShowArticleResponse> defaultGetRecruitArticle(Integer pageSize, Integer pageNum, List<String> type,
                                                              List<String> direction, List<String> tag, Integer finish) {
        List<RecruitArticle> articleList = recruitArticleMapper.defaultGetRecruitArticle();
        List<ShowArticleResponse> articleResponseList = new ArrayList<>();
        long count = 0;
        for (RecruitArticle article : articleList) {
            ShowArticleResponse articleResponse = new ShowArticleResponse(article);
            if ((type == null || new HashSet<>(articleResponse.getType()).containsAll(type))
                    && (direction == null || new HashSet<>(articleResponse.getDirection()).containsAll(direction))
                    && (tag == null || new HashSet<>(articleResponse.getTag()).containsAll(tag))
                    && (Objects.equals(articleResponse.getFinish(), finish) || finish == null)) {

                count++;
                if (count >= (long) (pageNum - 1) * pageSize && count < (long) pageNum * pageSize) {
                    articleResponseList.add(articleResponse);
                }
            }
        }

        Page<ShowArticleResponse> responsePage = new Page<>();
        responsePage.setPageNum(pageNum);
        responsePage.setPageSize(pageSize);
        responsePage.setTotal(count);
        responsePage.setPages((int) ((count - 1) / pageSize) + 1);
        responsePage.setItems(articleResponseList);

        return responsePage;
    }

    @Override
    public boolean downloadArticle() throws IOException {
        List<RecruitArticle> articleList = recruitArticleMapper.defaultGetRecruitArticle();
        List<Object[]> objects = new ArrayList<>();
        for (RecruitArticle recruitArticle : articleList) {
            objects.add(new DownloadArticleResponse(recruitArticle).toList());
        }
        Excel excel = new Excel(objects);
        return excelUtil.exportRecruitArticle(excel);
    }


}
