package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName JWTUtils.java
 * @Description TODO JWT令牌工具类
 * @createTime 2024年06月02日 21:07:00
 */

@Component
@Slf4j
public class JWTUtils {
    private static String SECRET;//创建加密盐
    //过期时间
    private static Long expiration;

    @Value("${jwt.data.SECRET}")
    private void setSECRET(String SECRET){
        this.SECRET = SECRET;
    }

    @Value("${jwt.data.expiration}")
    private void setExpiration(Long expiration){
        this.expiration = expiration;
    }
    /*
     * @description: TODO 随机生成UUID
     * @author: Ruby Ceng 曾春佳
     * @date: 2024/6/3 12:54
     * @param: []
     * @return: java.lang.String
     **/
    public  String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }


  /*
   * @description: TODO 生成jwt
   * @author: Ruby Ceng 曾春佳
   * @date: 2024/6/3 12:48
   * @param: [subject] 传入的payload 数据体json格式
   * @return: java.lang.String
   **/
    public  String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, expiration, getUUID());// 设置过期时间
        return builder.compact();
    }
    /*
     * @description: TODO jwt的生成内部类
     * @author: Ruby Ceng 曾春佳
     * @date: 2024/6/3 13:00
     * @param: [subject 主题，数据内容, expiration 过期时间, uuid 生成jwt的唯一ID]
     * @return: io.jsonwebtoken.JwtBuilder
     **/
    private  JwtBuilder getJwtBuilder(String subject, Long expiration, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 加密后的密钥
        SecretKey secretKey = generalKey();
        // 此刻的时间戳
        long nowMillis = System.currentTimeMillis();
        // 获取此时时间
        Date now = new Date(nowMillis);


        if(expiration==null){
            expiration= JWTUtils.expiration;
        }

        // 到期时间
        long expMillis = nowMillis + expiration;
        Date expDate = new Date(expMillis);


        return Jwts.builder()
                .setId(uuid)              //标识jwt这个唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("Ruby")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 自己设定ID以及过期时间
     * @param id
     * @param subject
     * @param id expiration subject
     * @return
     */
    public  String createJWT(String id, String subject, Long expiration) {
        JwtBuilder builder = getJwtBuilder(subject, expiration, id);// 设置过期时间
        return builder.compact();
    }


    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public  SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWTUtils.SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /*
     * @description: TODO 解析jwt
     * @author: Ruby Ceng 曾春佳
     * @date: 2024/6/3 13:03
     * @param: [jwt]
     * @return: Claims
     **/
    public  Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
