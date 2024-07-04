package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.example.demo.common.OssFile;
import com.example.demo.utils.MinioUtils;
import io.minio.CreateMultipartUploadResponse;
import io.minio.ListPartsResponse;
import io.minio.errors.*;
import io.minio.messages.Part;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Object upload(MultipartFile file, String bucketName, HttpServletRequest request) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String uniqueIdentifier = request.getParameter("uniqueIdentifier");
        if(minioUtils.md5Verify(null,file.getOriginalFilename(),uniqueIdentifier) && StrUtil.isEmpty(uniqueIdentifier)){
            return minioUtils.getPresignedObjectUrl(null,file.getOriginalFilename());
        }
        OssFile ossFile = minioUtils.putObject(file.getInputStream(), bucketName, file.getOriginalFilename());
        return minioUtils.getPresignedObjectUrl(null,ossFile.getOssFilePath());
    }


    /**
     * 返回分片上传需要的签名数据URL及 uploadId
     * 前端获取uploadId可以根据uploadId实现断点上床
     * @param bucketName
     * @param fileName
     * @return
     */
    @GetMapping("/createMultipartUpload")
    @SneakyThrows
    public Map<String, Object> createMultipartUpload(String bucketName, String fileName, Integer chunkSize) {
        // 1. 根据文件名创建签名
        Map<String, Object> result = new HashMap<>();
        // 2. 获取uploadId
        CreateMultipartUploadResponse response = minioUtils.uploadId(bucketName, null, fileName, null, null);
        String uploadId = response.result().uploadId();
        result.put("uploadId", uploadId);
        // 3. 请求Minio 服务，获取每个分块带签名的上传URL
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("uploadId", uploadId);
        List<String> partList = new ArrayList<>();
        // 4. 循环分块数 从1开始
        for (int i = 1; i <= chunkSize; i++) {
            reqParams.put("partNumber", String.valueOf(i));
            String uploadUrl = minioUtils.getPresignedObjectUrl(bucketName, fileName, reqParams);// 获取URL
            result.put("chunk_" + (i - 1), uploadUrl); // 添加到集合
        }
        return result;
    }
    /**
     * 分片上传完后合并
     *
     * @param objectName 文件全路径名称
     * @param uploadId   返回的uploadId
     * @return /
     */
    @GetMapping("/completeMultipartUpload")
    @SneakyThrows
    public boolean completeMultipartUpload(String bucketName,String objectName, String uploadId) {
        try {
            Part[] parts = new Part[10000];
            ListPartsResponse partResult = minioUtils.listMultipart(bucketName, null, objectName, 1000, 0, uploadId, null, null);
            int partNumber = 1;
            System.err.println(partResult.result().partList().size() + "========================");
            for (Part part : partResult.result().partList()) {
                parts[partNumber - 1] = new Part(partNumber, part.etag());
                partNumber++;
            }
            minioUtils.completeMultipartUpload(bucketName, null, objectName, uploadId, parts, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }









}
