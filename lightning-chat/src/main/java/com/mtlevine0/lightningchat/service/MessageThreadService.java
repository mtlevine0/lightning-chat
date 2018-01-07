package com.mtlevine0.lightningchat.service;

import java.util.List;

import com.mtlevine0.lightningchat.model.Message;
import com.mtlevine0.lightningchat.model.MessageThread;
import com.mtlevine0.lightningchat.model.User;

public interface MessageThreadService {
	
	List<MessageThread> findAll();
	
	MessageThread save(MessageThread messageThread);

	MessageThread addUser(User user, Long threadId);
	
	Message addMessage(Message message, Long threadId);

}