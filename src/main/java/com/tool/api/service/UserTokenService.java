package com.tool.api.service;

import com.tool.api.entity.User;

public interface UserTokenService {
//    获取token
    public String getToken(String code) throws Exception;

    public User findUserById(String id);

    public void insertUser(User user);

    public void updateUser(User user);
}
