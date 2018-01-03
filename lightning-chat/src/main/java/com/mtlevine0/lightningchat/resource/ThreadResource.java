package com.mtlevine0.lightningchat.resource;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mtlevine0.lightningchat.model.MessageThread;
import com.mtlevine0.lightningchat.model.User;
import com.mtlevine0.lightningchat.repository.MessageRepository;
import com.mtlevine0.lightningchat.repository.MessageThreadRepository;
import com.mtlevine0.lightningchat.service.UserService;

@RestController
@RequestMapping("/api/v1/thread")
public class ThreadResource {
	
	@Autowired
	MessageThreadRepository messageThreadRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> create() {
		
		User user = userService.findAll().get(0);
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user);
		
		MessageThread myThread = new MessageThread();
		myThread.setThreadName("test thread");
		myThread.setUsers(userList);
		
		ArrayList<MessageThread> threads = new ArrayList<MessageThread>();
		threads.add(myThread);
		user.setThreads(threads);
		
		
		
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

}
