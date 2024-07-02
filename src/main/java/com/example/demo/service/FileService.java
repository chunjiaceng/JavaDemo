package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName FileService.java
 * @Description TODO 文件上传相关服务接口
 * @createTime 2024年06月30日 12:36:00
 */


public interface FileService {
    public void uploadFileChunk(String objectName, MultipartFile file);
}
