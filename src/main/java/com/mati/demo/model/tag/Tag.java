package com.mati.demo.model.tag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.relationships.Followable;
import com.mati.demo.model.relationships.Follower;
import com.mati.demo.model.user.User;

public class Tag implements Followable{
	
	/**
	 * 
	 */
	
	/*
	 * TODO ver de eliminar el tag y no romper con eso a followedBy
	 */
	private static final long serialVersionUID = 1L;

	@Getter @Setter private int id;
	@Getter @Setter private String tagName;
	
	private final Set<Content> taggedContent = new HashSet<Content>();
	private final Set<User> taggedUsers = new HashSet<User>();
	
	private Map<Integer, Follower> followedBy = new HashMap<Integer, Follower>();
	
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

	public void startFollowing(User follower) {
		followedBy.put(follower.getId(), follower);
	}

	public void stopFollowing(User follower) {
		followedBy.remove(follower.getId());
		
	}
	
}
