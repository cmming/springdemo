/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.controller;

import com.example.springdemo.utils.FileUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Date;

/**
 * 文件操作 .
 *  下载的时候，前端调试可以看响应值是为arraybuffer，
 * @version 2.0.0 2020-12-18 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/file/")
public class FileController {

    /**
     * 输出流大小 .
     */
    private static final int RESPONSE_SIZE = 1024;

    @PostMapping(value = "download")
    public ResponseEntity<FileSystemResource> downloadFile(@RequestParam(value = "filePath") String filePath) {
//        filePath = "C:\\Users\\chenming3\\Downloads\\123.xlsx";
//        String filePath = "D:\\软件\\AdobeXD24.rar";
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        try {
            headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            String contentType = Files.probeContentType(file.toPath());
            headers.add("Content-Type", contentType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .body(new FileSystemResource(file));
    }

    /**
     * 前端通过window.location.href可以下载
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "download3")
    public void download2(final HttpServletResponse response) throws IOException {
        String filePath = "D:\\软件\\AdobeXD24.rar";
        response.setContentType("application/octet-stream");

        // 这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition",
                "attachment;filename=" + java.net.URLEncoder.encode("图谱知识库导入文件模板2", "utf-8") + ".xlsx");

        final OutputStream output = response.getOutputStream();
        final InputStream input = new FileInputStream(new File(filePath));

        final byte[] buffer = new byte[RESPONSE_SIZE];
        int len = 0;
        while ((len = input.read(buffer)) > 0) {
            output.write(buffer, 0, len);
        }
        input.close();
        // 刷新缓冲
        response.flushBuffer();
    }

    @GetMapping(value = "download2")
    public String  download3(HttpServletResponse res) throws IOException {
        String filePath = "C:\\Users\\chenming3\\Downloads\\123.xlsx";
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + "123.xlsx");
        FileUtil.filedownload(res.getOutputStream(),filePath);
        res.flushBuffer();
        return "succes dowload";
    }
}
