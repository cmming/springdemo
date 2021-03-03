package com.example.springdemo;/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;

/**
 * 类的描述 .
 *
 * @version 2.0.0 2020-12-18 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileTest {

    @Test
    public void getFileContentType() {
        String pathname = "C:\\Users\\chenming3\\Desktop\\接口对接\\documents-export-2020-09-07\\报账系统图谱设计和基本数据\\报账系统图谱设计和基本数据\\基础数据\\城市.xlsx";
        String type = new MimetypesFileTypeMap().getContentType(new File(pathname));
        System.out.println("第二种javax.activation: "+type);

        try {
            String s = Files.probeContentType(new File(pathname).toPath());
            System.out.println("第三种java.nio: "+s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentType = fileNameMap.getContentTypeFor(pathname);
        System.out.println("第四种java.net: "+contentType);

    }
}
