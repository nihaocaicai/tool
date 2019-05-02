package com.tool.api.service;
import com.tool.api.entity.User;

public interface UserService {
	public String findUserIdById(int id);
	
    public User findUserById(int id);
    
    public void insertUser(User user);
    
    public void updateUser(User user);
    
    public void deleteUser(String id);
}
