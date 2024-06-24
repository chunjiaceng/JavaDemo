package com.example.demo.exception;

public enum BaseExceptionEnum implements BaseExceptionEnumInterface {
    SUUCESS(200,"成功！"),
    // 认证失败错误码
    AUTHENTICATION_ERROR(403,"认证失败"),
    // 授权失败错误码
    ACCREDIT_ERROR(401,"权限不足"),
    SERVER_ERROR(500,"服务器内部异常"),
    USER_NOT_EXIT(10001,"当前用户名不存在"),
    BODY_NULL(10002,"请求体不能为空"),
    USER_EXSIT(10003,"用户已经存在")
    ;

    private int code;
    private String message;


    BaseExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
