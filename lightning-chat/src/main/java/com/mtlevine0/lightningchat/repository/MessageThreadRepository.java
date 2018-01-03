package com.mtlevine0.lightningchat.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtlevine0.lightningchat.model.MessageThread;

@Transactional
public interface MessageThreadRepository extends JpaRepository<MessageThread, Long> { }
