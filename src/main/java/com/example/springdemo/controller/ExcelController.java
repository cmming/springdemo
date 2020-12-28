/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.example.springdemo.utils.JavaCsvUtil;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @GetMapping("getData")
    public List<JSONObject> getData() {
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(FileUtil.file("F:\\java\\springdemo\\src\\main\\resources\\templates\\rulefunctions.csv"));
        List<CsvRow> rows = data.getRows();
//遍历行
        for (CsvRow csvRow : rows) {
            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
            Console.log(csvRow.getRawList());
        }
        return null;
    }

    @GetMapping("getDataByHuTool")
    public List<Map<String, String>> getDataByHuTool() {
        final List<Map<String, String>> result = JavaCsvUtil.readCsv("F:\\java\\springdemo\\src\\main\\resources\\templates\\rulefunctions.csv");
        return result;
    }
}
