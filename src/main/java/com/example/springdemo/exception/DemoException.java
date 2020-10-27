package com.example.springdemo.exception;

/**
 * @Author: chenming
 * @Description: 自定义异常 .
 * @Date: Create in 11:30 2020-10-20
 **/
public class DemoException extends RuntimeException {
    private Integer code;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DemoException(Integer code, String message) {
//        super(message);
        this.code = code;
        this.msg = message;
    }

}
