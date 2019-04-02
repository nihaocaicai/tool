package com.tool.api.dao;
import com.tool.api.entity.User;
/*
* User接口文件
* */
public interface UserDao {
    /*
    * 根据id查询用户信息
    * */
    public User findUserById(String id);

}