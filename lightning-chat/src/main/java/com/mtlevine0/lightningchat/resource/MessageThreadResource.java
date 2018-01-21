package com.mtlevine0.lightningchat.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mtlevine0.lightningchat.model.MessageThread;
import com.mtlevine0.lightningchat.model.dto.MessageCreateDTO;
import com.mtlevine0.lightningchat.model.dto.MessageDTO;
import com.mtlevine0.lightningchat.model.dto.MessageThreadDTO;
import com.mtlevine0.lightningchat.model.dto.UserDTO;
import com.mtlevine0.lightningchat.repository.MessageRepository;
import com.mtlevine0.lightningchat.service.MessageThreadService;
import com.mtlevine0.lightningchat.service.UserService;

@RestController
@RequestMapping("/api/v1/thread")
public class MessageThreadResource {
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	MessageThreadService messageThreadService;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {		
		return new ResponseEntity<List<MessageThreadDTO>>(messageThreadService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody MessageThread messageThread) {
		return new ResponseEntity<MessageThread>(messageThreadService.save(messageThread), HttpStatus.OK);
	}
	
	@PostMapping(path = "/{threadId}/user")
	public ResponseEntity<?> addUser(@RequestBody UserDTO user, @PathVariable("threadId") long threadId) {
		return new ResponseEntity<MessageThreadDTO>(messageThreadService.addUser(user, threadId), HttpStatus.OK);
	}
	
	@PostMapping(path = "/{threadId}/message")
	public ResponseEntity<?> addMessage(@RequestBody MessageCreateDTO message, @PathVariable("threadId") long threadId) {
		return new ResponseEntity<MessageDTO>(messageThreadService.addMessage(message, threadId), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{threadId}/message")
	public ResponseEntity<?> findMessage(@PathVariable("threadId") long threadId) {		
		return new ResponseEntity<List<MessageDTO>>(messageThreadService.findMessage(threadId), HttpStatus.OK);
	}

}
