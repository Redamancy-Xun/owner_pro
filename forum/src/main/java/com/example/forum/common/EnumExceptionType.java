package com.example.forum.common;

public enum EnumExceptionType {
    SYSTEM_INTERNAL_ANOMALY(1000, "网络不给力，请稍后重试"),
    PASSWORD_INCORRECT(1001, "密码错误"),
    USER_NOT_EXIST(1002,"用户不存在"),
    USER_ALREADY_EXIST_BUT_CAN_UPGRADE(1003,"账号已存在，请直接登录"),
    ADMIN_ALREADY_EXISTS(1004,"该管理员已存在"),
    LOGIN_INVALID(1,"登录状态失效，请重新登录"),
    PARAMETER_FORMAT_INCORRECT(-2, "参数格式不正确");



    private int errorCode;

    private String codeMessage;

    EnumExceptionType(int errorCode, String codeMessage) {
        this.errorCode = errorCode;
        this.codeMessage = codeMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getCodeMessage() {
        return codeMessage;
    }
}
