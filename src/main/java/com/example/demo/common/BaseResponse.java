package com.example.demo.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResponse<T> {
     static final int CODE_SUCCESS = 200;

     static final int CODE_ERROR = 500;

    // 认证失败错误码
     static final int AUTHENTICATION_ERROR = 403;
    // 授权失败错误码
     static final int ACCREDIT_ERROR = 401;

    private int code;

    private String msg;

    private T data;


    public BaseResponse(int code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }
    public BaseResponse(int code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }


    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(CODE_SUCCESS, "success", data);
    }
    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<T>(CODE_ERROR, "error", data);
    }
    public static <T> BaseResponse<T> error(String msg) {
        return new BaseResponse<T>(CODE_ERROR, msg);
    }
    public static <T> BaseResponse<T> error(int code, String msg) {
        return new BaseResponse<T>(code, msg);
    }

}

