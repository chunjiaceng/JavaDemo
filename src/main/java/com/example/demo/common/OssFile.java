package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName OssFile.java
 * @Description TODO 文件上传后的文件对象
 * @createTime 2024年07月02日 18:07:00
 */

@Data
@AllArgsConstructor
public class OssFile {
    // 原始文件名
    String originalFileName;
    // 存储的oss地址
    String ossFilePath;
}
