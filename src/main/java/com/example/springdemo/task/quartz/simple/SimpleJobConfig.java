package com.example.springdemo.task.quartz.simple;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: chenming
 * @Description: 创建quartz的配置，同时定义job detail和 触发器trigger
 * @Date: Create in 23:03 2020/10/27
 */
//@Configuration
public class SimpleJobConfig {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 为job添加配置 .
     * @return
     */
    @Bean
    public JobDetail simpleJobDetail() {
        final String curTimeString = dateFormat.format(new Date());
        return JobBuilder.newJob(SimpleJob.class)
                // 该JobDetail起一个id，便于之后的检索
                .withIdentity("simpleJob")
                // 即使没有Trigger关联时，也不需要删除该JobDetail
                .storeDurably()
                // 以Key-Value形式关联数据 传递给job 启动的时候就确定了，不会改变
                .usingJobData("curTime", curTimeString)
                .build();
    }

    @Bean
    public Trigger simpleJobTrigger() {
        //定义每三秒执行一次
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever();
        //定义触发器
        return TriggerBuilder.newTrigger().forJob(simpleJobDetail()).withIdentity("myJobTrigger").withSchedule(simpleScheduleBuilder).build();
    }
}
