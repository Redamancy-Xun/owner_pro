package com.example.forum.controller.article;

import com.example.forum.common.Result;
import com.example.forum.entity.RecruitArticle;
import com.example.forum.service.impl.RecruitArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@Api("发布帖子Controller")
public class CreateArticleController {

    @Autowired
    private RecruitArticleServiceImpl articleService;

    //发布一个帖子
    @PostMapping("/createArticle")
    @ApiOperation("发布")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Integer"),
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
    public Result createArticle(@RequestParam(value = "id") @Validated Integer user_id,
                                @RequestParam(value = "update_date") @Validated Date update_date,
                                @RequestParam(value = "type") @Validated Integer type,
                                @RequestParam(value = "direction") @Validated Integer directon,
                                @RequestParam(value = "tag") @Validated String tag,
                                @RequestParam(value = "content") @Validated String content,
                                @RequestParam(value = "start_time") @Validated String start_time,
                                @RequestParam(value = "end_time") @Validated String end_time,
                                @RequestParam(value = "contact") @Validated String contact,
                                @RequestParam(value = "finish") @Validated Integer finish,
                                @RequestParam(value = "top") @Validated Integer top){

        //参数校验

        RecruitArticle article = new RecruitArticle(user_id, update_date, type, directon, tag, content, start_time,
                end_time, contact, finish, top);

        //createArticle返回插入的条数
        int count = articleService.insertRecruitArticle(article);
        log.info("count=" + count);
        log.info("id=" + article.getArticle_id());

        return Result.success("发布成功", article);
    }
}
