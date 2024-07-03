package com.example.demo.config;

import com.example.demo.common.OssProperties;
import com.example.demo.common.PearlMinioClient;
import com.example.demo.utils.MinioUtils;
import io.minio.MinioClient;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName MinioConfig.java
 * @Description TODO Minio相关配置类
 * @createTime 2024年06月30日 00:23:00
 */


@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({PearlMinioClient.class})
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnExpression("${oss.enabled}")
@ConditionalOnProperty(value = "oss.type", havingValue = "minio")
public class MinioConfig {

    @Bean
    @SneakyThrows
    public PearlMinioClient minioClient(OssProperties ossProperties) {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(ossProperties.getEndpoint())
                .credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                .build();
        return new PearlMinioClient(minioClient);
    }
    @Bean
    public MinioUtils minioUtils(PearlMinioClient minioClient, OssProperties ossProperties) {
        return new MinioUtils(minioClient, ossProperties);
    }


}