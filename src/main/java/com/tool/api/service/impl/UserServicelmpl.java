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
//    查询用户
    public User findUserById(int id){
    	return this.userDao.findUserById(id);
    }
	@Override
	public void updateUser(int id,User user) {
		// TODO Auto-generated method stub
		this.userDao.updateUser(user);
	}
	@Override
	public void deleteUser(String user_id) {
		// TODO Auto-generated method stub
		this.userDao.deleteUser(user_id);
	}
}
