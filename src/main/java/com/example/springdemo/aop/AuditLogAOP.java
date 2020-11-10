package com.example.springdemo.aop;

import com.example.springdemo.annotation.AuditLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @Author: chenming
 * @Description: 自定义审计日志切面
 * @Date: Create in 9:05 2020-11-04
 **/
@Aspect
@Component
@Slf4j
public class AuditLogAOP {

    // spel表达式解析器
    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    // 参数发现器
    private DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    // 定义切面的范围
    @Pointcut("@annotation(com.example.springdemo.annotation.AuditLog)")
//    @Pointcut("execution(public * com.example.springdemo.controller.*.*(..))")
    public void auditLog() {
    }
    
    @Before(value = "auditLog()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //注解中的参数
        AuditLog auditLog = methodSignature.getMethod().getAnnotation(AuditLog.class);
        String opeationInfo = auditLog.logContent();
        // Spel表达式解析日志信息
        // 获得方法参数名数组
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(methodSignature.getMethod());
        if (parameterNames != null && parameterNames.length > 0){
            EvaluationContext context = new StandardEvaluationContext();

            //获取方法参数值
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                context.setVariable(parameterNames[i],args[i]); // 替换spel里的变量值为实际值， 比如 #user -->  user对象
            }
            // 解析出实际的日志信息
            opeationInfo = spelExpressionParser.parseExpression(auditLog.logContent()).getValue(context).toString();
        }

        log.info("审计日志注解：{}，内容为：{}",auditLog.auditItemId(), opeationInfo);

    }
}
