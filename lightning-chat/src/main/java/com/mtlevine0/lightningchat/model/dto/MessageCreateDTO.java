package com.mtlevine0.lightningchat.model.dto;

public class MessageCreateDTO {
	
	private String body;
	private UserDTO user;
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
