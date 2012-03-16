package com.mati.demo.model.tag;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.relationships.Followable;
import com.mati.demo.model.user.User;

//@EqualsAndHashCode(of="tagName")
public class Tag implements Followable{
	
	/**
	 * 
	 */
	
	/*
	 * TODO ver de eliminar el tag y no romper con eso a followedBy
	 */
	private static final long serialVersionUID = 1L;

	int id;
	String tagName;
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}
	
	public Tag(String tagName){
		this.tagName = tagName;
	}
	
	final Set<Content> taggedContent = new HashSet<Content>();
	final Set<User> taggedUsers = new HashSet<User>();
	
	private Map<Integer, User> followedBy = new HashMap<Integer, User>();
	
	public Collection<User> getFollowers(){
		return followedBy.values();
	}
	
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
	
	/*
	 * TODO equals and hasncode by tag name
	 */
}
