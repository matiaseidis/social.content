package com.mati.demo.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;

public class User extends Taggable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3080275587386819680L;

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	@Setter @Getter private String userName;
	@Setter @Getter private String password;
	
	@Setter @Getter private List<String> roles = new ArrayList<String>();
	
	private Map<Integer, Post> posts = new HashMap<Integer, Post>();
	
	public User() {
		
	}
	
	public void addPost(Post post) {
		posts.put(post.hashCode(), post);
	}
	
	public Collection<Post> getPosts(){
		return posts.values();
	}

	@Override
	protected void registerWithTag(Tag tag) {
		tag.addTagged(this);
	}

	@Override
	protected void unregisterWithTag(Tag tag) {
		tag.removeTagged(this);
	}

	public Post getPost(int nodeId) {
		return posts.get(nodeId);
	}

	public void updatePost(Post oldPost, Post updatedPost) {
		int oldPostId = oldPost.getTitle().hashCode();
		
		if(updatedPost == null){
			posts.put(oldPostId, oldPost);
		} else {
			deletePost(oldPostId);
			posts.put(updatedPost.getTitle().hashCode(), updatedPost);
		}
		
	}

	public boolean deletePost(int id) {
		return posts.remove(id) == null;
	}

}
