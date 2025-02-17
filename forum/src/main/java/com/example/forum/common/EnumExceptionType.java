package com.example.forum.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.shiro.authz.UnauthenticatedException;

@Getter
@AllArgsConstructor
public enum EnumExceptionType {
    SYSTEM_INTERNAL_ANOMALY(1000, "网络不给力，请稍后重试"),
    PASSWORD_INCORRECT(1001, "密码错误"),
    USER_NOT_EXIST(1002,"用户不存在"),
    USER_ALREADY_EXIST_BUT_CAN_UPGRADE(1003,"账号已存在"),
    ADMIN_ALREADY_EXISTS(1004,"该管理员已存在"),
    LOGIN_INVALID(1,"登录状态失效，请重新登录"),
    PARAMETER_FORMAT_INCORRECT(-2, "参数格式不正确"),
    LENGTH_INCORRECT(1005, "参数长度不正确"),
    EMAIL_INVALID(1006,"邮箱格式不正确" ),
    ARTICLE_ID_NOT_EXIST(1007,"帖子不存在"),
    PERMISSION_NOT_EXIST(1008, "无相关权限"),
    AUTHORIZATION_EXCEPTION(1009, "认证失败");

    private final int errorCode;

    private final String codeMessage;

}
