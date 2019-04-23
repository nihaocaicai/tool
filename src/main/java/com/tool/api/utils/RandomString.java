package com.tool.api.utils;

import java.util.Random;

public class RandomString {
    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer s=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            s.append(str.charAt(number));
        }
        return s.toString();
    }

}
