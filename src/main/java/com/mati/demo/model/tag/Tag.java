package com.mati.demo.model.tag;

import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.relationships.Followable;
import com.mati.demo.model.user.User;

@EqualsAndHashCode(of="tagName")
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
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}
	
	public Tag(String tagName){
		this.tagName = tagName;
	}
	
	@Getter private Set<Integer> taggedContent = new HashSet<Integer>();
	
	@Getter private Set<String> taggedUsers = new HashSet<String>();
	
	@Getter private Set<String> followedBy = new HashSet<String>();
	
//	private Map<Integer, User> followedBy = new HashMap<Integer, User>();
	
//	public Collection<User> getFollowers(){
//		return followedBy.values();
//	}
	
	public boolean isNotTaggingAnything(){
		return taggedUsers.isEmpty() && taggedContent.isEmpty();
	}
	
	public boolean isTagged(User user){
		return taggedUsers.contains(user.getUserName());
	}
	
	public boolean isTagged(Content content){
		return taggedContent.contains(content.getId());
	}
	
	public void addTagged(User taggable){
		taggedUsers.add(taggable.getUserName());
	}

	public void addTagged(Content taggable){
		taggedContent.add(taggable.getId());
	}
	
	public void removeTagged(User taggable){
		taggedUsers.remove((User)taggable);
	}
	
	public void removeTagged(Content taggable){
		taggedContent.remove((Content)taggable);
	}

	public void startFollowing(User follower) {
		followedBy.add(follower.getUserName());
	}

	public void stopFollowing(User follower) {
		followedBy.remove(follower.getUserName());
		
	}
}
