package com.mati.demo.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.TagRepository;
import com.mati.demo.model.user.User;

public class Model implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	Map<Integer, Content> contentMap = new HashMap<Integer, Content>();

	Map<String, User> usersMap = new HashMap<String, User>();
	
	@Getter private TagRepository tagRepository = new TagRepository(); 
//			TagRepository.INSTANCE; 

	public Collection<User> getUsers(){ 
		return usersMap.values();
	}

	public Collection<Tag> getTags(){
		return tagRepository.getAllTags();
		}

	public User loadUserByUsername(String username) {
		return usersMap.get(username);
	}

	public Tag loadTagByTagName(String tagName) {
		return tagRepository.getByTagName(tagName);
	}

	public Content loadContentById(int id) {
		return contentMap.get(id);
	}

	public User getLoggedInUser() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = loadUserByUsername(userName);
		return user;
	}

	public void addTag(Tag tag){
		tagRepository.addTag(tag);
	}

	public void removeTag(Tag tag){
		tagRepository.removeTag(tag);
	}

	public boolean hasTag(Tag tag){
		return tagRepository.exists(tag);
	}

	public void addContent(Content c){
		contentMap.put(c.hashCode(), c);
		System.out.println("added content model");
	}

	public void addUser(User u){
		usersMap.put(u.getUserName(), u);
	}

	

	public boolean hasUser(User user){
		return usersMap.containsKey(user.getUserName());
	}
	
	public List<Content> searchContent(String pattern) {
		List<Content> result = new ArrayList<Content>();

		for(Content c : contentMap.values()){
			if(c.getTitle().contains(pattern)){
				result.add(c);
			}
		}
		return result;
	}

	public List<User> searchUsers(String pattern) {
		List<User> result = new ArrayList<User>();
		
		User loggedInUser = getLoggedInUser();
		
		for(User u : usersMap.values()){
			if(u.getUserName().contains(pattern) && !u.equals(loggedInUser)){
				result.add(u);
			}
		}
		return result;
	}

	public List<Tag> searchTags(String pattern) {
		List<Tag> result = new ArrayList<Tag>();
		
		for(Tag t : tagRepository.getAllTags()){
			if(t.getTagName().contains(pattern)){
				result.add(t);
			}
		}
		return result;
	}

	public List<Content> getTaggedContent(Tag tag) {
		List<Content> result = new ArrayList<Content>();
		
		for(Integer id : tag.getTaggedContent()){
			result.add(loadContentById(id));
		}
		
		return result;
	}
}