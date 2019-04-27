package com.tool.api.service;

import com.tool.api.entity.User;

public interface UserTokenService {
//    获取token
    public String getToken(String code) throws Exception;

}
