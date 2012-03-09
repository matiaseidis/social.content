package com.mati.demo.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.relationships.Followable;
import com.mati.demo.model.relationships.Follower;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;

public class User extends Taggable implements Followable, Follower{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3080275587386819680L;

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	@Getter @Setter private int id;
	@Setter @Getter private String userName;
	@Setter @Getter private String password;
	
	@Setter @Getter private List<String> roles = new ArrayList<String>();
	
	private Map<Integer, Post> posts = new HashMap<Integer, Post>();
	private Map<Integer, Video> videos = new HashMap<Integer, Video>();
	
	private Map<String, User> followedBy = new HashMap<String, User>();
	
	private Map<String, User> followedUsers = new HashMap<String, User>();
	private Map<String, Tag> followedTags = new HashMap<String, Tag>();
	
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
	
	public void updateVideo(Video oldVideo, Video updatedVideo) {
		int oldVideoId = oldVideo.getTitle().hashCode();
		
		if(updatedVideo == null){
			videos.put(oldVideoId, oldVideo);
		} else {
			deleteVideo(oldVideoId);
			videos.put(updatedVideo.getTitle().hashCode(), updatedVideo);
		}
		
	}

	public boolean deletePost(int id) {
		return posts.remove(id) == null;
	}

	public void addVideo(Video video) {
		videos.put(video.hashCode(), video);
	}
	
	public boolean deleteVideo(int id) {
		return videos.remove(id) == null;
	}

	public Collection getVideos() {
		return videos.values();
	}

	public Video getVideo(int nodeId) {
		return videos.get(nodeId);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.mati.demo.model.relationships.Followable#startFollowing(com.mati.demo.model.user.User)
	 */
	public void startFollowing(User follower) {
		followedBy.put(follower.getUserName(), follower);
	}

	public void stopFollowing(User follower) {
		followedBy.remove(follower.getUserName());
	}

	public void follow(User u) {
		followedUsers.put(u.getUserName(), u);
	}

	public void unfollow(User u) {
		followedUsers.remove(u.getUserName());		
	}

	public void follow(Tag t) {
		followedTags.put(t.getTagName(), t);
		
	}

	public void unfollow(Tag t) {
		followedTags.remove(t.getTagName());		
	}
	
	public Collection<User> getFollowedUsers(){
		return followedUsers.values();
	}

	public Collection<Tag> getFollowedTags(){
		return followedTags.values();
	}
	
	public Collection<User> getFollowedBy(){
		return followedBy.values();
	}

	public void addFollower(User user) {
		followedBy.put(user.getUserName(), user);
	}
	
	public void removeFollower(User user) {
		followedBy.remove(user.hashCode());
	}

}
