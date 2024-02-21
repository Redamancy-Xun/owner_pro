
package com.forum.controller.article;

import com.alibaba.fastjson.JSON;
import com.forum.common.Result;
import com.forum.controller.response.ShowArticleResponse;
import com.forum.dto.SessionData;
import com.forum.entity.RecruitArticle;
import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@Api("发布帖子 Controller")
public class CreateArticleController {

    @Autowired
    private RecruitArticleServiceImpl articleService;

    @Autowired
    private SessionUtil sessionUtil;

    //发布一个帖子
    @PostMapping("/createArticle")
    @ApiOperation("发布帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "update_date", value = "发布日期", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "type", value = "招募任务类型", required = true, paramType = "query", dataType = "List<String>"),
            @ApiImplicitParam(name = "direction", value = "需求方向", required = true, paramType = "query", dataType = "List<String>"),
            @ApiImplicitParam(name = "tag", value = "技术栈", required = true, paramType = "query", dataType = "List<String>"),
            @ApiImplicitParam(name = "content", value = "详情内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "start_time", value = "任务开始时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "end_time", value = "任务结束时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "contact", value = "联系方式", required = true, paramType = "query", dataType = "String")
    })
    public Result createArticle(@RequestParam(value = "update_date") @Validated Date update_date,
                                @RequestParam(value = "type") @Validated List<String> type,
                                @RequestParam(value = "direction") @Validated List<String> direction,
                                @RequestParam(value = "tag") @Validated List<String> tag,
                                @RequestParam(value = "content") @Validated String content,
                                @RequestParam(value = "start_time") @Validated String start_time,
                                @RequestParam(value = "end_time") @Validated String end_time,
                                @RequestParam(value = "contact") @Validated String contact){

        SessionData sessionData = sessionUtil.getSessionData();
        Integer admin_id = 0;
        Integer user_id = 0;
        if (sessionData.getRole() == 1)
            admin_id = sessionData.getId();
        else
            user_id = sessionData.getId();

        String typeJson = JSON.toJSONString(type);
        String directionJson = JSON.toJSONString(direction);
        String tagJson = JSON.toJSONString(tag);

        RecruitArticle article = new RecruitArticle(admin_id, user_id, update_date, typeJson, directionJson, tagJson,
                content, start_time, end_time, contact, 0, 0);

        //createArticle返回插入的条数
        int count = articleService.insertRecruitArticle(article);
        log.info("count=" + count);
        log.info("id=" + article.getArticle_id());

        return Result.success("发布成功", new ShowArticleResponse(article));
    }
}