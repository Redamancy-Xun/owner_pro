package com.example.forum.controller.article;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.common.Result;
import com.example.forum.exception.MyException;
import com.example.forum.service.RecruitArticleServiceImpl;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("展示帖子Controller")
public class ShowArticleController {

    @Autowired
    RecruitArticleServiceImpl articleService;
    //根据指定排序条件获取帖子列表
    @GetMapping("/getArticleListOrderly/{order_by_sql}")
    public Result getUserListOrderly(@PathVariable("order_by_sql")String order_by_sql,
                                     HttpSession session){

        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            throw new MyException(EnumExceptionType.LOGIN_INVALID);

        return Result.success("搜索成功", articleService.getRecruitArticleListOrderly(order_by_sql));
    }
}
