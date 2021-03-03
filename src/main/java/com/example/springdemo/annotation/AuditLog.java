package com.example.springdemo.annotation;

import java.lang.annotation.*;

/**
 * @Author: chenming
 * @Description:
 * @Date: Create in 9:01 2020-11-04
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AuditLog {

    /**
     * 审计项ID .
     *
     * @return
     */
    String auditItemId();

    /**
     * 审计日志的内容 .
     * @return
     */
    String logContent();

    /**
     * 响应值 .
     * @return
     */
    String resultContent() default "";
}
