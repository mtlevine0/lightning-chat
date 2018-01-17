package com.mtlevine0.lightningchat.repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtlevine0.lightningchat.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {

	User findByUsername(String username); 

}