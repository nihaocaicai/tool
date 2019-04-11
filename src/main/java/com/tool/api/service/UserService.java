package com.tool.api.service;
import com.tool.api.entity.User;

public interface UserService {
    public User findUserById(String id);
    
    public void insertUser(User user);
    
    public void updateUser(User user);
    
    public void deleteUser(String id);
}
