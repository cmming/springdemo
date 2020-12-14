/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA配置类 .
 *
 * @version 2.0.0 2020-12-14 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
