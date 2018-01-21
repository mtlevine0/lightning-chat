package com.mtlevine0.lightningchat.service;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlevine0.lightningchat.model.Message;
import com.mtlevine0.lightningchat.model.MessageThread;
import com.mtlevine0.lightningchat.model.User;
import com.mtlevine0.lightningchat.model.dto.MessageCreateDTO;
import com.mtlevine0.lightningchat.model.dto.MessageDTO;
import com.mtlevine0.lightningchat.model.dto.MessageThreadDTO;
import com.mtlevine0.lightningchat.model.dto.UserDTO;
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

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public MessageThreadDTO addUser(UserDTO dto, Long threadId) {
		
		User user = userService.findById(dto.getId());
		
		MessageThread thread = messageThreadRepository.findOne(threadId);
				
		Set<User> users = thread.getUsers();
		
		users.add(user);
		
		thread.setUsers(users);
		
		return modelMapper.map(messageThreadRepository.save(thread), MessageThreadDTO.class);
	}

	@Override
	public List<MessageThreadDTO> findAll() {
		return modelMapper.map(messageThreadRepository.findAll(), new TypeToken<List<MessageThreadDTO>>() {}.getType());
	}

	@Override
	public MessageThread save(MessageThread messageThread) {
		return messageThreadRepository.save(messageThread);
	}

	@Override
	public MessageDTO addMessage(MessageCreateDTO dto, Long threadId) {
		
		Message message = modelMapper.map(dto, Message.class);
		
		MessageThread thread = messageThreadRepository.findOne(threadId);
		
		message.setThread(thread);
		
		User user =  userService.findById(message.getUser().getId());
					
		message.setUser(user);
		
//		return messageRepository.save(message);
		return modelMapper.map(messageRepository.save(message), MessageDTO.class);
	}

	@Override
	public List<MessageDTO> findMessage(Long threadId) {
		
		MessageThread thread = messageThreadRepository.findOne(threadId);
		
		List<MessageDTO> messageDTOList = modelMapper.map(thread.getMessages(),  new TypeToken<List<MessageDTO>>() {}.getType());
		
		return messageDTOList;
	}
	
}
