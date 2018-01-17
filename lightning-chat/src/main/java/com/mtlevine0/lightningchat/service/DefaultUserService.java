package com.mtlevine0.lightningchat.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.mtlevine0.lightningchat.model.User;
import com.mtlevine0.lightningchat.repository.UserRepository;

@Component
public class DefaultUserService implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(UUID id) {
		return userRepository.findOne(id);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User add(User user) {
		
		String salt = BCrypt.gensalt();
		String password = BCrypt.hashpw(user.getPassword(), salt);
		user.setPassword(password);
		user.addAuthority(new SimpleGrantedAuthority("ROLE_USER"));
		
		return userRepository.save(user);
	}
	
	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public void removeById(UUID id) {
		userRepository.delete(id);
	}

}
