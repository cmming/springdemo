/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo;

import lombok.Data;

/**
 * 类的描述 .
 *
 * @version 1.0.0  <br>
 * @author: chenming <br>
 * @since JDK 1.8
 */
@Data
public class PostDTO {
    private int userId;
    private int id;
    private String title;
    private String body;
}
