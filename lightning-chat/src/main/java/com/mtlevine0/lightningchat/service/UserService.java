package com.mtlevine0.lightningchat.service;

import java.util.List;
import java.util.UUID;

import com.mtlevine0.lightningchat.model.User;

public interface UserService {

	public List<User> findAll();
	
	public User findById(UUID id);
	
	public User findByUsername(String username);
	
	public User add(User user);
	
	public User update(User user);
	
	public void removeById(UUID id);
	
}
