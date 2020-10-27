package com.example.springdemo.task.own;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: chenming
 * @Description: 定时任务输出当前时间（自带定时任务使用教程）.
 * @Date: Create in 17:08 2020-10-20
 **/
//@Component
public class SchedulerTask {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每5s执行1次
     */
    @Scheduled(fixedRate = 5000)
    private void reportTime () {
        System.out.println(simpleDateFormat.format(new Date()));
    }


    /**
     * 从0秒开始每5秒运行一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    private void reportTimeCron () {
        System.out.println("cron表到式输出时间：" + simpleDateFormat.format(new Date()));
    }
}
