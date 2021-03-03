/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.example.springdemo.dao.User;
import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.DemoException;
import com.example.springdemo.repository.UserRepository;
import com.example.springdemo.utils.JavaCsvUtil;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * excel学习使用 .
 *
 * @version 2.0.0 2020-12-28 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/excel/")
public class ExcelController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("getData")
    public List<JSONObject> getData() {
        final List<JSONObject> result = new ArrayList<>();
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(FileUtil.file("F:\\java\\springdemo\\src\\main\\resources\\templates\\rulefunctions.csv"));
        // 读取所有的行
        List<CsvRow> rows = data.getRows();
        // 获取第一行
        CsvRow headers = rows.get(0);
        for (int i = 1; i < rows.size(); i++) {
            JSONObject item = new JSONObject();
            for (int j = 0; j < headers.size(); j++) {
                item.put(headers.get(j), rows.get(i).get(j));
            }
            result.add(item);

        }
        return result;
    }

    @GetMapping("getDataByJavaCsvUtil")
    public List<Map<String, String>> getDataByJavaCsvUtil() {
        final List<Map<String, String>> result = JavaCsvUtil.readCsv("F:\\java\\springdemo\\src\\main\\resources\\templates\\rulefunctions.csv");
        return result;
    }

    @GetMapping("getExcelData")
    public List<Map<String, String>> getExcelData(){
        final List<Map<String, String>> result = new ArrayList<>();
        final String filePath = "F:\\java\\springdemo\\src\\main\\resources\\templates\\城市.xlsx";
        final File file = new File(filePath);
        if (!file.exists()) {
            throw new DemoException(ResultEnum.FILE_NOT_FOUND);
        }
        Sheet sheet = null;
        // 同时支持Excel 2003、2007
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workBook = WorkbookFactory.create(fileInputStream);
            sheet = workBook.getSheetAt(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Row headerRow = sheet.getRow(0);
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            int cellNum = headerRow.getPhysicalNumberOfCells();
            final Map<String, String> rowMap = new HashMap<>();
            for (int j = 0; j <cellNum; j++) {
                // 有可能存在某一行没有某一列的的数据
                if (sheet.getRow(i).getCell(j) != null)
                {
                    rowMap.put(headerRow.getCell(j).getStringCellValue(), sheet.getRow(i).getCell(j).getStringCellValue());
                }
            }
            result.add(rowMap);
        }
        return result;
    }

    @PostMapping("exportExcel")
    public void importExcel(HttpServletResponse response) {
        List<User> users = userRepository.findAll();
        String[] titles = {"姓名", "年龄"};
        // 1. 工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 2. 为表格创建一张表格
        Sheet sheet = workbook.createSheet();
        // 3. 为表格创建请求头
        Row row = sheet.createRow(0);
        AtomicInteger headerIndex = new AtomicInteger();
        for (String title : titles) {
            Cell cell = row.createCell(headerIndex.getAndIncrement());
            cell.setCellValue(title);
        }
        // 4. 为表格填充数据
        AtomicInteger dataIndex = new AtomicInteger(1);
        Cell cell = null;
        for (User user : users) {
            Row itemRow = sheet.createRow(dataIndex.getAndIncrement());
            itemRow.createCell(0).setCellValue(user.getName());
            itemRow.createCell(1).setCellValue(user.getAge());
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        String fileName = "文件名.xls";
//        try {
//            fileName = URLEncoder.encode("文件名.xls", "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        //刷新缓冲
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //workbook将Excel写入到response的输出流中，供页面下载
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
