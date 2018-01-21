package com.mtlevine0.lightningchat.service;

import java.util.List;

import com.mtlevine0.lightningchat.model.MessageThread;
import com.mtlevine0.lightningchat.model.dto.MessageCreateDTO;
import com.mtlevine0.lightningchat.model.dto.MessageDTO;
import com.mtlevine0.lightningchat.model.dto.MessageThreadDTO;
import com.mtlevine0.lightningchat.model.dto.UserDTO;

public interface MessageThreadService {
	
	List<MessageThreadDTO> findAll();
	
	MessageThread save(MessageThread messageThread);

	MessageThreadDTO addUser(UserDTO dto, Long threadId);
	
	MessageDTO addMessage(MessageCreateDTO dto, Long threadId);
	
	List<MessageDTO> findMessage(Long threadId);

}