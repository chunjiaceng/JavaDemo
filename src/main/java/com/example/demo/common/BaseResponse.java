package com.example.demo.common;

import com.example.demo.exception.BaseException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResponse<T> {
     static final int CODE_SUCCESS = 200;

     static final int CODE_ERROR = 500;



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

    public  static BaseResponse success() {
        return new BaseResponse(CODE_SUCCESS, "success");
    }
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(CODE_SUCCESS, "success", data);
    }

    public static  BaseResponse error(BaseException e) {
        return new BaseResponse(e.getCode(), e.getMessage());
    }

    public static  BaseResponse error() {
        return new BaseResponse(CODE_ERROR, "服务器出现异常");
    }


}

