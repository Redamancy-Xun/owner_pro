package com.forum.controller.article;


import com.forum.common.Result;
import com.forum.service.RecruitArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@Api("下载帖子信息的Excel文件")
public class DownloadArticleController {

    @Autowired
    RecruitArticleService recruitArticleService;

    @ApiOperation("下载帖子的Excel文件")
    @GetMapping("/download")
    public Result downloadArticle() throws IOException {

        if (recruitArticleService.downloadArticle())
            return Result.success("下载成功");
        else
            return Result.fail("下载失败，请重试");
    }


}
