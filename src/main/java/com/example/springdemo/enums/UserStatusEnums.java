package com.example.springdemo.enums;

import lombok.Getter;

/**
 * 用户状态枚举 .
 */

@Getter
public enum UserStatusEnums {
    STOP(0, "禁用"),
    NORMAL(1, "正常");

    private Integer statusCode;

    private String statusName;

    UserStatusEnums(int statusCode, String statusNameString) {
        this.statusCode = statusCode;
        this.statusName = statusNameString;
    }
}
