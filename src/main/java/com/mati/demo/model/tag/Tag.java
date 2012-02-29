package com.mati.demo.model.tag;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.user.User;

public class Tag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Set<User> taggedUsers = new HashSet<User>();
	private final Set<Content> taggedContent = new HashSet<Content>();
	
	@Getter @Setter private String tagName;
	
	public boolean isNotTaggingAnything(){
		return taggedUsers.isEmpty() && taggedContent.isEmpty();
	}
	
	public boolean isTagged(Taggable taggable){
		return taggedUsers.contains(taggable) || taggedContent.contains(taggable);
	}
	
	public void addTagged(User taggable){
		taggedUsers.add((User)taggable);
	}

	public void addTagged(Content taggable){
		taggedContent.add((Content)taggable);
	}
	
	public void removeTagged(User taggable){
		taggedUsers.remove((User)taggable);
	}
	
	public void removeTagged(Content taggable){
		taggedContent.remove((Content)taggable);
	}
	
}
