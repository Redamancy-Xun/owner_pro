package com.forum.controller.user;

import com.forum.common.Page;
import com.forum.common.Result;
import com.forum.controller.response.GetUserResponse;
import com.forum.controller.response.ShowArticleResponse;
import com.forum.dto.SessionData;
import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.service.impl.UserServiceImpl;
import com.forum.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("用户个人信息 Controller")
public class UserInfoController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RecruitArticleServiceImpl articleService;

    @Autowired
    private SessionUtil sessionUtil;
    //根据id获取个人信息
    @GetMapping("/userInfo")
    @ApiOperation("用户个人信息以及帖子管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize",value = "每页显示数量(不小于0)",required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页数(不小于0)", required = true, paramType = "query", dataType = "Integer")
    })
    public Result getUserInfo(@RequestParam("pageSize")Integer pageSize,
                              @RequestParam("pageNum")Integer pageNum){

        //参数校验
        if (pageNum == null || pageNum < 1){
            pageNum = 1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 10;
        }

        SessionData sessionData = sessionUtil.getSessionData();
        String username = sessionData.getUsername();
        Integer user_id = sessionData.getId();

        Page<ShowArticleResponse> articles = articleService.getRecruitArticleByUserId(pageNum, pageSize, user_id);

        Object[] result = new Object[2];
        result[0] = new GetUserResponse(userService.getUserByUsername(username), sessionData.getRole());
        result[1] = articles;

        return Result.success("获取成功", result);
    }

    
}
