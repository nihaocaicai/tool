package com.tool.api.dao;
import com.tool.api.entity.User;
/*
* User接口文件
* */
public interface UserDao {
//    根据user_id查询用户是否存在
    public int findUserByIdIf(String user_id);

//    根据user_id查询UID
    public int findUserByUserId(String user_id);
    /*
    * 根据id查询用户信息
    * */
    public User findUserById(int id);
    
    /*
     * 新增加一条用户记录
     */
    public void insertUser(User user);
    
    /*
     * 更新用户信息
     */
    public void updateUser(User user);
    
    /*
     * 删除用户信息
     */
    public void deleteUser(String user_id);
}