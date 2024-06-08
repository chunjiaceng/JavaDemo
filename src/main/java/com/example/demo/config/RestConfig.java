package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName RestConfig.java
 * @Description TODO RestTemlate发送请求模板配置
 * @createTime 2024年06月08日 16:26:00
 */

@Configuration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
//    @Bean
//    public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory(){
//        return  new OkHttp3ClientHttpRequestFactory();
//    }
}
