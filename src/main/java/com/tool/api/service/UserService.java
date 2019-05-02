package com.tool.api.service;
import com.tool.api.entity.User;

public interface UserService {
	public String findUserIdById(int id);
	
    public User findUserById(int id);

    
    public void updateUser(User user);

    public void deleteUser(int id);

    public int findUserByIdIf(int id);


}
