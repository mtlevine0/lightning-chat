package com.mtlevine0.lightningchat.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User implements UserDetails{
	
	private static final long serialVersionUID = -7773893815119106817L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnoreProperties("user")
	@OneToMany(mappedBy="user", cascade = {CascadeType.ALL})
	Set<Message> messages;
	
	@JsonIgnoreProperties("users")
	@ManyToMany(mappedBy = "users")
	List<MessageThread> threads = new ArrayList<MessageThread>();
	
	@Column(unique = true)
	String username;
	String password;
	ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date creationDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setAuthorities(ArrayList<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public void addAuthority(SimpleGrantedAuthority simpleGrantedAuthority) {
		this.authorities.add(simpleGrantedAuthority);
	}
	public List<MessageThread> getThreads() {
		return threads;
	}
	public void setThreads(List<MessageThread> threads) {
		this.threads = threads;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
