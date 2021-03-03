package com.example.springdemo.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chenming
 * @Description: tomcat配置
 * @Date: Create in 15:01 2020-10-28
 **/
@Configuration
public class TomcatConfig {

    @Bean
    public TomcatServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers((Connector connector) -> {
            connector.setProperty("relaxedPathChars","\\^%");
            connector.setProperty("relaxedQueryChars", "\\^%");
        });

        return tomcatServletWebServerFactory;
    }
}
