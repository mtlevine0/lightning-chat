package com.mtlevine0.lightningchat.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtlevine0.lightningchat.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username); 

}