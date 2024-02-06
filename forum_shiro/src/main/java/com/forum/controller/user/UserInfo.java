package com.forum.controller.user;

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
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Api("用户个人信息 Controller")
public class UserInfo {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RecruitArticleServiceImpl articleService;

    //根据id获取个人信息
    @RequiresRoles("user")
    @GetMapping("/userInfo")
    @ApiOperation("用户个人信息以及帖子管理")
    public Result getUserInfo(){

        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String username = principal.getUsername();
        Integer id = principal.getId();

        List<RecruitArticle> articles = articleService.getRecruitArticleByUserId(id);
        List<ShowArticleResponse> articleResponse = new ArrayList<>();
        for (RecruitArticle article : articles) {
            articleResponse.add(new ShowArticleResponse(article));
        }

        Object[] result = new Object[2];
        result[0] = new GetUserResponse(userService.getUserByUsername(username), principal.getType());
        result[1] = articleResponse;

        return Result.success("获取成功", result);
    }

    
}
