package com.example.demo.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException{
    Integer code;
    String message;
    public BaseException(){
        super();
    }
    public BaseException(BaseExceptionEnum e){
        super(e.getMessage());
        this.code = e.getCode();
        this.message = e.getMessage();
    }
}
