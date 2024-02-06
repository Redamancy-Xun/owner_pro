package com.forum.controller.admin;

import com.forum.common.Result;
import com.forum.controller.response.GetAdminResponse;
import com.forum.controller.response.GetUserResponse;
import com.forum.dto.UserDTO;
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
    public Result getUserInfo(){

        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String username = principal.getUsername();
        Integer id = principal.getId();

        Object[] result = new Object[2];
        result[0] = new GetAdminResponse(adminService.getAdminByUsername(username), principal.getType());
        result[1] = articleService.getRecruitArticleByUserId(id);

        return Result.success("获取成功", result);
    }

    
}
