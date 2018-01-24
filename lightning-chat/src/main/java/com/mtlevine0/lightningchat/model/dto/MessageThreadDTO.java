package com.mtlevine0.lightningchat.model.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class MessageThreadDTO {
	
	private Long id;
	private String threadname;
	private Set<UserDTO> users;
	
	@JsonIgnoreProperties("thread")
	private List<MessageDTO> messages;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThreadname() {
		return threadname;
	}
	
	public void setThreadname(String threadname) {
		this.threadname = threadname;
	}
	
	public Set<UserDTO> getUsers() {
		return users;
	}
	
	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}
	
	public List<MessageDTO> getMessages() {
		return messages;
	}
	
	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}

}
