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

import com.mtlevine0.lightningchat.model.Message;
import com.mtlevine0.lightningchat.model.MessageThread;
import com.mtlevine0.lightningchat.model.User;
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
		return new ResponseEntity<List<MessageThread>>(messageThreadService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody MessageThread messageThread) {
		return new ResponseEntity<MessageThread>(messageThreadService.save(messageThread), HttpStatus.OK);
	}
	
	@PostMapping(path = "/{threadId}/user")
	public ResponseEntity<?> addUser(@RequestBody User user, @PathVariable("threadId") long threadId) {
		return new ResponseEntity<MessageThread>(messageThreadService.addUser(user, threadId), HttpStatus.OK);
	}
	
	@PostMapping(path = "/{threadId}/message")
	public ResponseEntity<?> addMessage(@RequestBody Message message, @PathVariable("threadId") long threadId) {
		return new ResponseEntity<Message>(messageThreadService.addMessage(message, threadId), HttpStatus.OK);
	}

}
