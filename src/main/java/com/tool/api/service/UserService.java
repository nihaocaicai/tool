package com.tool.api.service;
import com.tool.api.entity.User;

public interface UserService {

//	根据uid去查openid
	public String findUserIdById(int id);
//	根据UID查询用户信息
    public User findUserById(int id);
//  根据信息更新表格信息
    public void updateUser(User user);
//  根据id查询数据库是否有该用户
    public int findUserByIdIf(int id);
//  在user表删除该用户
    public void deleteUser(int id);

}
