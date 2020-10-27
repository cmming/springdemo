package com.example.springdemo.task.quartz.simple;

import com.example.springdemo.task.quartz.simple.service.TestQuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: chenming
 * @Description:定义quartz的job
 * @Date: Create in 22:54 2020/10/27
 */

@Slf4j
public class SimpleJob extends QuartzJobBean {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private TestQuartzService testQuartzService;

    private String curTime;

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        final String curTimeString = dateFormat.format(new Date());
        log.info("SimpleJob开始执行:{}，获取到jobDetail的时间：{}", curTimeString, this.curTime);
        testQuartzService.delete(curTimeString);
    }
}
