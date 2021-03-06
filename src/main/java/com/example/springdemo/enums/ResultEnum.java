package com.example.springdemo.enums;

import lombok.Getter;

/**
 * 用于记录错误消息 .
 */
@Getter
public enum ResultEnum {
    UNKNOW_ERROR(-1, "位置错误"),
    SCCESS(0, "成功"),

    USER_NOT_FOUND(400001, "用户不存在"),

    NOT_FOUND(404000, "接口不存在"),

    FILE_NOT_FOUND(404001, "文件不存在"),

    // 参数异常已422 开头
    PARAMS_ERR0R(422000, "参数错误"),
    // 422001
    PARAMS_CONTAIN_SPECIAL_SINGLE(422001, "参数包含特殊字符"),
    // 422002
    PARAMS_ERROR(422002, "请求参数异常"),

    // 服务器异常已500 开头
    SERVER_ERR0R(500000, "服务器异常"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
