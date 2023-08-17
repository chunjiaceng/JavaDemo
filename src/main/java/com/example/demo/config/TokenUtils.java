package com.example.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.pojo.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtils {
    // 过期时间 1小时
    private static final Long EXPIRE_TIME = 1000*60*60L;
    // 签名
    private static final String TOKEN_KEY = "ljdyaishijin**3nkjnj??";

    //生成Token
    public  static String sign(User user) {
        String token = null;
        try{
            Long expireTime = System.currentTimeMillis() + EXPIRE_TIME;
            token = JWT.create()
                    // 可完善
                    .withIssuer("haruka")
                    .withClaim("name",user.getName())
                    .withClaim("password",user.getPassword())
                    .sign(Algorithm.HMAC256(TOKEN_KEY));
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    //验证Token
    public  Boolean verify(String token){
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_KEY)).withIssuer("haruka").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            System.out.println("验证通过");
            System.out.println("username:"+decodedJWT.getClaim("name").asString());
            System.out.println("过期时间：      " + decodedJWT.getExpiresAt());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
