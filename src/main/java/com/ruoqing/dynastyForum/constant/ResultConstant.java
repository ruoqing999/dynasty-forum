package com.ruoqing.dynastyForum.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultConstant {
    SUCCESS(200, "成功"),
    ERROR(5000, "服务器出错"),
    VALID_ERROR(5001, "参数校验失败"),
    SERVICE_ERROR(5002, "业务错误"),
    AUTHORIZATION_ERROR(5003, "请先登录"),
    LOGIN_ERROR(5004, "账号或密码错误"),
    REGISTER_ERROR(5005, "该账号已存在"),
    ;
    private final Integer code;
    private final String message;
}
