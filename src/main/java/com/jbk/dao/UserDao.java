package com.jbk.dao;

import com.jbk.dto.LoginRequest;
import com.jbk.entities.User;

public interface UserDao {
	public int createUser(User user);
	
	public User getUserByUsername(String username);
	
	public int deleteUser(String username);

	public User loginUser(LoginRequest loginRequest);
}
