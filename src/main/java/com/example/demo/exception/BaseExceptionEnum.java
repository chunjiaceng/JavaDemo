package com.example.demo.exception;

public enum BaseExceptionEnum implements BaseExceptionEnumInterface {
    SUUCESS(200,"成功！"),
    SERVER_ERROR(500,"服务器内部异常"),
    USER_NOT_EXIT(10001,"当前用户名不存在"),
    BODY_NULL(10002,"请求体不能为空"),
    USER_EXSIT(10003,"用户已经存在"),
    TOKEN_ERROR(10003,"Token错误"),
    PASSWORD_ERROR(10004,"密码错误请重试")
    ;
    BaseExceptionEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
