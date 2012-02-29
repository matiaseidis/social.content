package com.mati.demo.model.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.Setter;

import org.prevayler.Prevayler;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.transaction.CreateUser;

public class BaseModel implements Serializable{
	
	@Setter private Prevayler prevayler;
	
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
