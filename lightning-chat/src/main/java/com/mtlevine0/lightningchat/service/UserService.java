package com.mtlevine0.lightningchat.service;

import java.util.List;
import java.util.UUID;

import com.mtlevine0.lightningchat.model.User;
import com.mtlevine0.lightningchat.model.dto.UserDTO;
import com.mtlevine0.lightningchat.model.dto.UserRegisterDTO;;


public interface UserService {

	public List<UserDTO> findAll();
	
	public User findById(UUID id);
	
	public User findByUsername(String username);
	
	public UserDTO add(UserRegisterDTO user);
	
	public User update(User user);
	
	public void removeById(UUID id);
	
}
