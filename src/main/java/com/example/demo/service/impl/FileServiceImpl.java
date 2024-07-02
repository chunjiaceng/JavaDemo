package com.example.demo.service.impl;

import com.example.demo.config.MinioConfig;
import com.example.demo.service.FileService;
import com.example.demo.utils.MinioUtils;
import io.minio.ObjectWriteResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName FileServiceImpl.java
 * @Description TODO 文件上传相关服务类
 * @createTime 2024年06月30日 12:37:00
 */

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Resource
    MinioUtils minioUtils;
    /*
     * @description: TODO 实现文件切片上传相关逻辑
     * @author: Ruby Ceng 曾春佳
     * @date: 2024/6/30 12:40
     * @param: [objectName, file]
     * @return: void
     **/
    @Override
    @SneakyThrows(Exception.class)
    public void uploadFileChunk(String objectName, MultipartFile file) {

    }
}
