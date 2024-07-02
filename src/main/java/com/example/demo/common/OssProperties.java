package com.example.demo.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName OssProperties.java
 * @Description TODO oss属性配置类
 * @createTime 2024年07月02日 18:31:00
 */

@Data
@ConfigurationProperties(prefix = "oss")
public class OssProperties {
    /**
     * 是否开启
     */
    Boolean enabled;

    /**
     * 存储对象服务器类型
     */
    OssType type;

    /**
     * OSS 访问端点，集群时需提供统一入口
     */
    String endpoint;

    /**
     * 用户名
     */
    String accessKey;

    /**
     * 密码
     */
    String secretKey;

    /**
     * 默认存储桶名，没有指定时，会放在默认的存储桶
     */
    String defaultBucketName;
}
