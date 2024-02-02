package com.example.forum.controller.article;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.common.Result;
import com.example.forum.exception.MyException;
import com.example.forum.service.impl.RecruitArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("删除帖子Controller")
public class DeleteArticleController {

    @Autowired
    RecruitArticleServiceImpl articleService;

    //删除一个帖子
    @PostMapping("/deleteArticle")
    @ApiOperation("删帖")
    @ApiImplicitParam(name = "article_id", value = "帖子id", required = true, paramType = "query", dataType = "Integer")
    public Result deleteArticle(@RequestParam(value = "article_id") Integer article_id,
                                HttpSession session) {
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            throw new MyException(EnumExceptionType.LOGIN_INVALID);

        int count = articleService.deleteRecruitArticleByArticleId(article_id);
        log.info("count=" + count);

        return Result.success("成功删除", article_id);
    }
}
