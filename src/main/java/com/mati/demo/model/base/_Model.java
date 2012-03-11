package com.mati.demo.model.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;
import com.mati.demo.model.user.User;

public class _Model implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<Integer, Content> content = new HashMap<Integer, Content>();
	
	private Map<String, User> users = new HashMap<String, User>();
	
	private Map<String, Tag> tags = new HashMap<String, Tag>();
	
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
	
	public Tag loadTagByTagName(String tagname) {
		return tags.get(tagname);
	}
	
	public Content loadContentById(int id) {
		return content.get(id);
	}
	
	public User getLoggedInUser() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = loadUserByUsername(userName); 
		return user;
	}
	
	public void addTag(Tag tag){
		tags.put(tag.getTagName(), tag);
	}
	
	public void removeTag(Tag tag){
		tags.remove(tag.getTagName());
	}

	public Collection<Tag> getTags() {
		return tags.values();
	}
}
