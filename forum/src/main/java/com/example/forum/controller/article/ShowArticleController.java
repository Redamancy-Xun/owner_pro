package com.example.forum.controller.article;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.common.Result;
import com.example.forum.exception.MyException;
import com.example.forum.service.impl.RecruitArticleServiceImpl;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpSession;
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
    public Result defaultGetArticle(HttpSession session){

        //检查是否登录
        if (session.getAttribute("user")==null)
            throw new MyException(EnumExceptionType.LOGIN_INVALID);

        return Result.success(articleService.defaultGetRecruitArticle());
    }
}

