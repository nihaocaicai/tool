package com.tool.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.api.dao.UserDao;
import com.tool.api.entity.User;
import com.tool.api.service.UserService;

@Service
@Transactional
public class UserServicelmpl implements UserService{
//    注解注入UserDao
    @Autowired
    private UserDao userDao;
    //根据uid来查openid
	@Override
	public String findUserIdById(int id) {
		// TODO Auto-generated method stub
		return userDao.findUserIdById(id);
	}
//    查询用户
    public User findUserById(int id){
    	return this.userDao.findUserById(id);
    }

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.userDao.updateUser(user);
	}

	//	删除用户信息
	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteUser(id);
	}

	//	根据id查询用户是否存在
	@Override
	public int findUserByIdIf(int id) {
		return this.userDao.findUserByIdIf(id);
	}
}
