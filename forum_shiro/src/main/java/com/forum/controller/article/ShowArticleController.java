package com.forum.controller.article;

import com.forum.controller.response.ShowArticleResponse;
import com.forum.entity.RecruitArticle;
import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api("展示帖子Controller")
public class ShowArticleController {

    @Autowired
    private RecruitArticleServiceImpl articleService;

    //根据指定排序条件获取帖子列表
    @RequiresRoles("online")
    @GetMapping("/article")
    @ApiOperation("展示帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",value = "类型",required = false,paramType = "query",dataType = "List<String>"),
            @ApiImplicitParam(name = "direction",value = "方向",required = false,paramType = "query",dataType = "List<String>"),
            @ApiImplicitParam(name = "tag",value = "技术栈",required = false,paramType = "query",dataType = "List<String>"),
            @ApiImplicitParam(name = "finish",value = "是否完成",required = false,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示数量(不小于0)",required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页数(不小于0)", required = true, paramType = "query", dataType = "Integer")
    })
    public Result defaultGetRecruitArticle(@RequestParam(value = "type", required = false)List<String> type,
                                           @RequestParam(value = "direction", required = false)List<String> direction,
                                           @RequestParam(value = "tag", required = false)List<String> tag,
                                           @RequestParam(value = "finish", required = false)Integer finish,
                                           @RequestParam("pageSize")Integer pageSize,
                                           @RequestParam("pageNum")Integer pageNum){
        //参数校验
        if (pageNum == null || pageNum < 1){
            pageNum = 1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 10;
        }

        return Result.success(articleService.defaultGetRecruitArticle(pageSize, pageNum, type, direction, tag, finish));
    }

    //展示帖子详情
    @RequiresRoles("online")
    @GetMapping("/articleDetail/{article_id}")
    @ApiOperation("帖子详情")
    @ApiImplicitParam(name = "article_id", value = "帖子id", required = true, paramType = "path", dataType = "Integer")
    public Result topArticle(@PathVariable(value = "article_id")Integer article_id) {

        RecruitArticle article = articleService.getRecruitArticleByArticleId(article_id);

        return Result.success("成功显示", new ShowArticleResponse(article));
    }

}

