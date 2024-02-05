package com.forum.controller.article;

import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.common.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("展示帖子Controller")
public class ShowArticleController {

    @Autowired
    private RecruitArticleServiceImpl articleService;
    //根据指定排序条件获取帖子列表
    @GetMapping("/article")
    public Result defaultGetArticle(){

        return Result.success(articleService.defaultGetRecruitArticle());
    }
}

