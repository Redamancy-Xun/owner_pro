package com.forum.controller.article;

import com.alibaba.fastjson.JSON;
import com.forum.controller.request.UpdateArticleMessageRequest;
import com.forum.controller.response.ShowArticleResponse;
import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.common.Result;
import com.forum.entity.RecruitArticle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@Api("更新帖子 Controller")
public class UpdateArticleController {

    @Autowired
    private RecruitArticleServiceImpl articleService;

    //更新一个帖子
    @PostMapping("/updateArticle")
    @ApiOperation("更新帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article_id", value = "帖子id", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "招募任务类型", required = false, paramType = "query", dataType = "List<String>"),
            @ApiImplicitParam(name = "direction", value = "需求方向", required = false, paramType = "query", dataType = "List<String>"),
            @ApiImplicitParam(name = "tag", value = "技术栈", required = false, paramType = "query", dataType = "List<String>"),
            @ApiImplicitParam(name = "content", value = "详情内容", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "start_time", value = "任务开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "end_time", value = "任务结束时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "contact", value = "联系方式", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "finish", value = "完成状态", required = false, paramType = "query", dataType = "Integer"),
    })
    public Result updateArticle(@RequestBody @Validated UpdateArticleMessageRequest request) {

        RecruitArticle article = articleService.getRecruitArticleByArticleId(request.getArticle_id());

        Integer id;
        if (article.getAdmin_id() != 0)
            id = article.getAdmin_id();
        else
            id = article.getUser_id();

        Integer article_id = request.getArticle_id();
        Integer top = article.getTop();
        String content = request.getContent();
        String start_time = request.getStart_time();
        String end_time = request.getEnd_time();
        String contact = request.getContact();
        Integer finish = request.getFinish();

        String typeJson = JSON.toJSONString(request.getType());
        String directionJson = JSON.toJSONString(request.getDirection());
        String tagJson = JSON.toJSONString(request.getTag());

        Date update_date = new Date();

        UpdateArticleMessageRequest articleNew = new UpdateArticleMessageRequest(article_id, update_date, typeJson,
                directionJson, tagJson, content, start_time, end_time, contact, finish);
        articleService.updateRecruitArticleByArticleId(articleNew);

        return Result.success("更新成功", new ShowArticleResponse(articleNew, id, top));
    }

    //设置一个帖子的完成状态

    @GetMapping("/finishArticle/{article_id}")
    @ApiOperation("帖子设置完成状态")
    @ApiImplicitParam(name = "article_id", value = "帖子id", required = true, paramType = "path", dataType = "Integer")
    public Result finishArticle(@PathVariable(value = "article_id")Integer article_id) {

        RecruitArticle article = articleService.finishRecruitArticleByArticleId(article_id);

        return Result.success("招募完成", new ShowArticleResponse(article));
    }

    //设置一个帖子的未完成状态

    @GetMapping("/unfinishArticle/{article_id}")
    @ApiOperation("帖子设置未完成状态")
    @ApiImplicitParam(name = "article_id", value = "帖子id", required = true, paramType = "path", dataType = "Integer")
    public Result unfinishArticle(@PathVariable(value = "article_id")Integer article_id) {

        RecruitArticle article = articleService.unfinishRecruitArticleByArticleId(article_id);

        return Result.success("招募未完成", new ShowArticleResponse(article));
    }

}
