package com.example.springdemo.task.quartz.simple.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: chenming
 * @Description: 用于quartz中job执行的业务 TODO 参数传递没有部分没有清楚.
 * @Date: Create in 22:52 2020/10/27
 */
@Service
@Slf4j
public class TestQuartzService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public void delete(final String id) {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("TestQuartzService 执行成功！！开始时间：{},实际执行时间：{};间隔6s", id, dateFormat.format(new Date()));
    }
}
