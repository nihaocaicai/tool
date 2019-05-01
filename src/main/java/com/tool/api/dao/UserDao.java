package com.tool.api.dao;
import com.tool.api.entity.User;
/*
* User接口文件
* */
public interface UserDao {
//    token使用star
    //根据user_id查询用户是否存在
    public int findUserByIdIf(String user_id);

    //根据user_id查询UID
    public int findUserByUserId(String user_id);

    //新增加一条用户信息
    public void insertUser(User user);
//token使用end

    //根据id查询用户信息
    public User findUserById(int id);

    //更新用户信息
    public void updateUser(User user);

}