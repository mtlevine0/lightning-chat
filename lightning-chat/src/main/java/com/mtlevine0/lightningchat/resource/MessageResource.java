package com.mtlevine0.lightningchat.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlevine0.lightningchat.model.Message;
import com.mtlevine0.lightningchat.repository.MessageRepository;

@RestController
@RequestMapping("/api/v1/message")
public class MessageResource {
	
	@Autowired
	MessageRepository messageRepository;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<Message>>(messageRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Message message) {
		return new ResponseEntity<Message>(messageRepository.save(message), HttpStatus.OK);
	}

}
