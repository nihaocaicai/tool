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

    public User findUserById(int id){
    	return this.userDao.findUserById(id);
    }

	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}

	public void deleteUser(int id) {
		this.userDao.deleteUser(id);
	}

	public int findUserByIdIf(int id) {
		return this.userDao.findUserByIdIf(id);
	}

	@Override
	public String findUserIdById(int id) {
		// TODO Auto-generated method stub
		return this.userDao.findUserIdById(id);
	}
}
