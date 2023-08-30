package com.example.demo.common;

import lombok.Data;

@Data
public class BaseException extends RuntimeException{
    String code;
    String message;
    public BaseException(){
        super();
    }
    public BaseException(BaseExceptionEnum e){
        super(e.getCode());
        this.code = e.getCode();
        this.message = e.getMessage();
    }
}
