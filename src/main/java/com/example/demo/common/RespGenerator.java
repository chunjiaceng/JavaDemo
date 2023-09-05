package com.example.demo.common;

import com.example.demo.exception.BaseException;
import com.example.demo.exception.BaseExceptionEnum;

/**
 * @author Haruka 曾春佳
 * @version 1.0.0
 * @ClassName RespGenerator.java
 * @Description TODO 全局返回Response
 * @createTime 2023年09月05日 20:32:00
 */


public class RespGenerator {
    /*
     * @description: TODO 请求接口成功时返回
     * @author: Haruka 曾春佳
     * @date: 2023/9/6 0:02
     * @param: [data]
     * @return: com.example.demo.common.BaseResponse
     **/
    public static BaseResponse success(Object data){
        return new BaseResponse(BaseExceptionEnum.SUUCESS.getCode(), "接口调用成功！",data);
    }
    /*
     * @description: TODO 发生错误时返回
     * @author: Haruka 曾春佳
     * @date: 2023/9/6 0:03
     * @param: []
     * @return: com.example.demo.common.BaseResponse
     **/
    public static BaseResponse error(){
        return new BaseResponse(BaseExceptionEnum.SERVER_ERROR.getCode(), "内部异常",null);
    }
}
