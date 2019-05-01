package com.tool.api.exception;

//基类，所有的异常类都继承于此
public class BaseException extends Exception{

    //异常信息
    private String message;
    public BaseException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
