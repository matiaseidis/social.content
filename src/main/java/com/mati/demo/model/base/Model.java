package com.mati.demo.model.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mati.demo.model.user.User;

public class Model implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, User> users = new HashMap<String, User>();
	
	public Collection<User> getUsers(){
		return users.values();
	}
	
	public Map<String, User> getUsersMap(){
		return users;
	}
	
	public User loadUserByUsername(String username) {
		return users.get(username);
	}

}
