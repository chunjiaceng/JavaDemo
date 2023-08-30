package com.example.demo.common;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private static final int CODE_SUCCESS = 200;

    private static final int CODE_FAIL = 500;

    private static final int CODE_ERROR = 500;

    private static final int CODE_NO_LOGIN = 300;

    private int code;

    private String msg;

    private T data;

    private String token;

    public BaseResponse(String token,int code, String msg, T data) {
        this.setToken(token);
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }
    public BaseResponse(int code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public static <T> BaseResponse<T> success(String token,T data) {
        return new BaseResponse<T>(token ,CODE_SUCCESS, "success", data);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(CODE_SUCCESS, "success", data);
    }
    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<T>(CODE_ERROR, "error", data);
    }

}

