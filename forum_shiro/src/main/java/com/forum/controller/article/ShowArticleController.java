package com.forum.controller.article;

import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("展示帖子Controller")
public class ShowArticleController {

    @Autowired
    private RecruitArticleServiceImpl articleService;
    //根据指定排序条件获取帖子列表
    @GetMapping("/article")
    @ApiOperation("展示帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",value = "类型",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "direction",value = "方向",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "finish",value = "是否完成",required = false,paramType = "query",dataType = "int")
    })
    public Result defaultGetRecruitArticle(@RequestParam("type") Integer type,
                                           @RequestParam("direction") Integer direction,
                                           @RequestParam("finish")Integer finish) {

        return Result.success(articleService.defaultGetRecruitArticle(type,direction,finish));
    }

}

