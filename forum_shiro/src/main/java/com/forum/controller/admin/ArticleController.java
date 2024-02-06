package com.forum.controller.admin;

import com.forum.common.Result;
import com.forum.controller.response.ShowArticleResponse;
import com.forum.entity.RecruitArticle;
import com.forum.service.impl.RecruitArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("帖子信息管理 Controller")
public class ArticleController {

    @Autowired
    private RecruitArticleServiceImpl articleService;

    //置顶一个帖子
    @RequiresRoles("admin")
    @GetMapping("/topArticle/{article_id}")
    @ApiOperation("置顶帖子")
    @ApiImplicitParam(name = "article_id", value = "帖子id", required = true, paramType = "path", dataType = "Integer")
    public Result topArticle(@PathVariable(value = "article_id")Integer article_id) {

        RecruitArticle article = articleService.topRecruitArticleByArticleId(article_id);

        return Result.success("成功置顶", new ShowArticleResponse(article));
    }

    //取消一个帖子的置顶状态
    @RequiresRoles("admin")
    @GetMapping("/untopArticle/{article_id}")
    @ApiOperation("取消置顶帖子")
    @ApiImplicitParam(name = "article_id", value = "帖子id", required = true, paramType = "path", dataType = "Integer")
    public Result untopArticle(@PathVariable(value = "article_id")Integer article_id) {

        RecruitArticle article = articleService.untopRecruitArticleByArticleId(article_id);

        return Result.success("成功取消置顶", new ShowArticleResponse(article));
    }
}
