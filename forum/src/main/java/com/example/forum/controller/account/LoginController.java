package com.example.forum.controller.account;

import com.example.forum.common.EnumExceptionType;
import com.example.forum.common.Result;
import com.example.forum.config.ShiroConfig;
import com.example.forum.dto.UserDTO;
import com.example.forum.exception.MyException;
import com.example.forum.service.impl.UserServiceImpl;
import com.example.forum.util.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("登录Controller")
@Slf4j
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ShiroConfig shiroConfig;

    @PostMapping("/login")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(长度2-20)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(长度6-20)", required = true, paramType = "query", dataType = "String")
    })
    public Result login(@Validated @RequestParam("username") String username,
                        @Validated @RequestParam("password") String password){

        //手动将SecurityManager绑定到当前线程
        SecurityUtils.setSecurityManager(shiroConfig.securityManager());
        //获取subject对象
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username, PasswordUtil.convert(password));

        //参数校验
        if(userService.checkUsernameLength(username)
                && userService.checkPasswordLength(password)
                && userService.checkLogin(username, PasswordUtil.convert(password))) {
            try {
                //尝试登陆，将会调用realm的认证方法
                subject.login(token);
            } catch (AuthenticationException e) {
                if (e instanceof UnknownAccountException) {
                    return Result.result(EnumExceptionType.USER_NOT_EXIST);
                } else if (e instanceof LockedAccountException) {
                    return Result.fail("用户被禁用");
                } else if (e instanceof IncorrectCredentialsException) {
                    return Result.result(EnumExceptionType.PASSWORD_INCORRECT);
                } else {
                    return Result.fail("用户认证失败");
                }
            }
        }

        UserDTO principal = new UserDTO(userService.getUserByUsername(username));

        return Result.success("登录成功", principal);
    }
}
