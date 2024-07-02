package com.example.demo.config;

import com.example.demo.common.OssProperties;
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
@ConditionalOnClass({MinioClient.class})
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnExpression("${oss.enabled}")
@ConditionalOnProperty(value = "oss.type", havingValue = "minio")
public class MinioConfig {

    @Bean
    @SneakyThrows
    @ConditionalOnMissingBean(MinioClient.class)
    public MinioClient minioClient(OssProperties ossProperties) {
        return MinioClient.builder()
                .endpoint(ossProperties.getEndpoint())
                .credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                .build();
    }

    @Bean
    @ConditionalOnBean({MinioClient.class})
    @ConditionalOnMissingBean(MinioUtils.class)
    public MinioUtils minioUtils(MinioClient minioClient, OssProperties ossProperties) {
        return new MinioUtils(minioClient, ossProperties);
    }
}