package com.tool.api.utils;

import java.util.HashMap;

//成功返回json类
public class responseSuccess {
    public static HashMap<String,String> success(String request){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("error_code","0");
        map.put("msg","ok");
        map.put("request",request);
        return map;
    }
}
