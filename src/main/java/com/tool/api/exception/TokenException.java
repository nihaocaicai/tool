package com.tool.api.exception;


public class TokenException extends BaseException{
    public String code = "401";
    public String msg = "Token已过期或无效Token";
    public String errorCode = "10001";
    public TokenException(String code,String msg,String errorCode){
        super(code,msg,errorCode);//显示调用父类的有参构造方法
    }
}
