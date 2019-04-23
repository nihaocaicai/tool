package com.tool.api.exception;

//基类，所有的异常类都继承于此
public class BaseException extends Exception{
    private String code = "400";
    private String msg = "invalid parameters";
    private String errorCode = "999";

    /**
     * 构造函数
     * code、msg和errorCode，且不应该是空值
     */
    public BaseException(String code,String msg,String errorCode)
    {
        if(code.isEmpty()||msg.isEmpty()||errorCode.isEmpty()){
            return;
        }
        if(!code.isEmpty()){
            this.code = code;
        }
        if(!msg.isEmpty()){
            this.msg = msg;
        }
        if(!errorCode.isEmpty()){
            this.errorCode = errorCode;
        }
    }
}
