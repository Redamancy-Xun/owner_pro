package com.example.forum.controller.article;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.common.Result;
import com.example.forum.entity.RecruitArticle;
import com.example.forum.exception.MyException;
import com.example.forum.service.RecruitArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@Api("更新帖子Controller")
public class UpdateArticleController {

    @Autowired
    RecruitArticleServiceImpl articleService;

    //更新一个帖子
    @PostMapping("/updateArticle")
    @ApiOperation("更新帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article_id", value = "帖子id", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "update_date", value = "发布日期", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "type", value = "招募任务类型", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "direction", value = "需求方向", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "tag", value = "技术栈", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "详情内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "start_time", value = "任务开始时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "end_time", value = "任务结束时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "contact", value = "联系方式", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "finish", value = "完成状态", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "top", value = "置顶状态", required = true, paramType = "query", dataType = "Integer")
    })
    public Result createArticle(@RequestParam(value = "article_id") Integer article_id,
                                @RequestParam(value = "update_date") @Validated Date update_date,
                                @RequestParam(value = "type") @Validated Integer type,
                                @RequestParam(value = "direction") @Validated Integer direction,
                                @RequestParam(value = "tag") @Validated String tag,
                                @RequestParam(value = "content") @Validated String content,
                                @RequestParam(value = "start_time") @Validated String start_time,
                                @RequestParam(value = "end_time") @Validated String end_time,
                                @RequestParam(value = "contact") @Validated String contact,
                                @RequestParam(value = "finish") @Validated Integer finish,
                                @RequestParam(value = "top") @Validated Integer top,
                                HttpSession session){

        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            throw new MyException(EnumExceptionType.LOGIN_INVALID);

        //参数校验

        //记录信息
        int count = articleService.updateRecruitArticleByArticleId(article_id, update_date, type, direction, tag, content,
                start_time, end_time, contact, finish, top);
        RecruitArticle articleNew = articleService.getRecruitArticleByArticleId(article_id);
        log.info("count=" + count);

        return Result.success("更新成功", articleNew);
    }
}
