package com.example.springdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通常用于处理重复的请求参数逻辑。比如通过token获取到用户信息，当然也可以通过静态方法获取
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoUser {
}
