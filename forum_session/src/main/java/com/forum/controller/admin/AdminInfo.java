package com.forum.controller.admin;

import com.forum.common.Page;
import com.forum.common.Result;
import com.forum.controller.response.GetAdminResponse;
import com.forum.controller.response.GetUserResponse;
import com.forum.controller.response.ShowArticleResponse;
import com.forum.dto.UserDTO;
import com.forum.entity.RecruitArticle;
import com.forum.service.impl.AdminServiceImpl;
import com.forum.service.impl.RecruitArticleServiceImpl;
import com.forum.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Api("管理员个人信息 Controller")
public class AdminInfo {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private RecruitArticleServiceImpl articleService;

    //根据id获取个人信息
    @RequiresRoles("admin")
    @GetMapping("/adminInfo")
    @ApiOperation("管理员个人信息以及帖子管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize",value = "每页显示数量(不小于0)",required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页数(不小于0)", required = true, paramType = "query", dataType = "Integer")
    })
    public Result getAdminInfo(@RequestParam("pageSize")Integer pageSize,
                              @RequestParam("pageNum")Integer pageNum){

        //参数校验
        if (pageNum == null || pageNum < 1){
            pageNum = 1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 10;
        }

        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String username = principal.getUsername();
        Integer admin_id = principal.getId();

        Page<ShowArticleResponse> articles = articleService.getRecruitArticleByAdminId(pageNum, pageSize, admin_id);

        Object[] result = new Object[2];
        result[0] = new GetAdminResponse(adminService.getAdminByUsername(username), principal.getType());
        result[1] = articles;

        return Result.success("获取成功", result);
    }

    
}
