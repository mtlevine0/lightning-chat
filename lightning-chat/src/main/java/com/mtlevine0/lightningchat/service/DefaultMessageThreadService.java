package com.mtlevine0.lightningchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlevine0.lightningchat.model.Message;
import com.mtlevine0.lightningchat.model.MessageThread;
import com.mtlevine0.lightningchat.model.User;
import com.mtlevine0.lightningchat.repository.MessageRepository;
import com.mtlevine0.lightningchat.repository.MessageThreadRepository;

@Service
public class DefaultMessageThreadService implements MessageThreadService {
	
	@Autowired
	MessageThreadRepository messageThreadRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	UserService userService;

	@Override
	public MessageThread addUser(User user, Long threadId) {
		
		user = userService.findById(user.getId());
		
		List<MessageThread> threads = user.getThreads();
		
		MessageThread thread = messageThreadRepository.findOne(threadId);
		
		threads.add(thread);
		
		user.setThreads(threads);
		
		userService.add(user);
		
		return messageThreadRepository.findOne(threadId);
	}

	@Override
	public List<MessageThread> findAll() {
		return messageThreadRepository.findAll();
	}

	@Override
	public MessageThread save(MessageThread messageThread) {
		return messageThreadRepository.save(messageThread);
	}

	@Override
	public Message addMessage(Message message, Long threadId) {
		
		MessageThread thread = messageThreadRepository.findOne(threadId);
		
		message.setThread(thread);
		
		User user =  userService.findById(message.getUser().getId());
					
		message.setUser(user);
		
		return messageRepository.save(message);
	}
	
}
