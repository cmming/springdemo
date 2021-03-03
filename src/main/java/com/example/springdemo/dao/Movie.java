package com.example.springdemo.dao;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @Author: chenming
 * @Description:
 * @Date: Create in 22:09 2021/2/22
 */
@Data
@NodeEntity
public class Movie {

    /**
     * 标题 .
     */
    private String title;

    /**
     * 宣传语 .
     */
    private String tagline;

    /**
     * 上映时间 .
     */
    private Long released;
}
