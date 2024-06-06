package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName AsyncConfig.java
 * @Description TODO 异常处理配置类
 * @createTime 2024年06月06日 13:40:00
 */

@Configuration
public class AsyncConfig {
    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(10); // 默认线程池大小为10
    }
}
