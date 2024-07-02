package com.example.demo.controller;

import com.example.demo.common.OssFile;
import com.example.demo.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName OssController.java
 * @Description TODO
 * @createTime 2024年07月02日 18:44:00
 */

@RestController
@RequestMapping("/oss")
public class OssController {
    @Autowired
    MinioUtils minioUtils;
    @PostMapping("/upload")
    public Object upload(MultipartFile file, String bucketName) throws IOException {
        OssFile ossFile = minioUtils.putObject(file.getInputStream(), bucketName, file.getOriginalFilename());
        return minioUtils.getPresignedObjectUrl(null,ossFile.getOssFilePath());
    }

}
