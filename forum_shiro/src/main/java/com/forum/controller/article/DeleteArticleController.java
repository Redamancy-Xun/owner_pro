package com.forum.controller.article;

import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("删除帖子 Controller")
public class DeleteArticleController {

    @Autowired
    private RecruitArticleServiceImpl articleService;

    //删除一个帖子
<<<<<<< HEAD
    @RequiresRoles("admin")
    @GetMapping("/deleteArticle")
=======
    @GetMapping("/deleteArticle/{article_id}")
>>>>>>> 8984b0b6562a6cf7f1248f787b89496f01bca72c
    @ApiOperation("删帖")
    @ApiImplicitParam(name = "article_id", value = "帖子id", required = true, paramType = "query", dataType = "Integer")
    public Result deleteArticle(@PathVariable(value = "article_id") Integer article_id) {

        int count = articleService.deleteRecruitArticleByArticleId(article_id);
        log.info("count=" + count);

        return Result.success("成功删除", article_id);
    }
}
