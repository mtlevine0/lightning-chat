package com.mtlevine0.lightningchat.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtlevine0.lightningchat.model.Message;

@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> { }
