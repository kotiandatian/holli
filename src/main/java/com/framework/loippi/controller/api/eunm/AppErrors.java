package com.framework.loippi.controller.api.eunm;

public enum AppErrors {

    SYSTEM_ERROR_5000000("5000000", "系统错误"),
    PARAM_ERROR_5000001("5000001", "参数错误"),
    NOT_LOGIN_IN_5000002("5000002", "请先登录"),
    LOGIN_INVALID_5000003("5000003", "登录过期，请重新登录"),
    ;

    public String code;
    public String message;

    AppErrors(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
