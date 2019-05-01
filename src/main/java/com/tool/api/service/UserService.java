package com.tool.api.service;
import com.tool.api.entity.User;

public interface UserService {
    public User findUserById(int id);
    
    public void updateUser(int id,User user);
    
    public void deleteUser(String id);
}
