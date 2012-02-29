package com.mati.demo.model.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.tag.TagRepository;
import com.mati.demo.model.tag.Taggable;
import com.mati.demo.model.user.User;

public class Model implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, User> users = new HashMap<String, User>();
	@Getter private Map<String, Taggable> taggables = new HashMap<String, Taggable>();
	
	public Collection<User> getUsers(){
		return users.values();
	}
	
	public Map<String, User> getUsersMap(){
		return users;
	}
	
	public User loadUserByUsername(String username) {
		return users.get(username);
	}
	
	public User getLoggedInUser() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = loadUserByUsername(userName); 
		return user;
	}
}
