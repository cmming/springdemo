/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.utils;

import com.csvreader.CsvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * javacsv 工具类 .
 *
 * @version 2.0.0 2020-12-28 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
public class JavaCsvUtil {

    /**
     * 读取csv文件 .
     * @param csvFilePath CSV路径
     */
    public static List<Map<String, String>> readCsv(final String csvFilePath) {
        final List<Map<String, String>> result = new ArrayList<>();
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("utf-8"));
            // 逐行读入数据
            while (reader.readRecord()) {
                csvFileList.add(reader.getValues());
            }
            reader.close();
            // 遍历读取的CSV文件
            String[] mapKey = csvFileList.get(0);
            for (int row = 1; row < csvFileList.size(); row++) {
                // 取得第row行第0列的数据
                Map<String, String> rowMap = new HashMap<>();
                for (int j = 0; j < mapKey.length; j++) {
                    rowMap.put(mapKey[j], csvFileList.get(row)[j]);
                }
                result.add(rowMap);
                String cell = csvFileList.get(row)[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
