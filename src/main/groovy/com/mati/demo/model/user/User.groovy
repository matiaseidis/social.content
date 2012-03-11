package com.mati.demo.model.user

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.mati.demo.model.content.Content
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.relationships.Followable;
import com.mati.demo.model.relationships.Follower;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;

class User extends Taggable implements Followable, Follower{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	int id;
	String userName;
	String password;
	transient CommonsMultipartFile image;
	
	List<String> roles = new ArrayList<String>();
	
	def content = new HashMap<Integer, Content>();
	
//	private Map<Integer, Post> posts = new HashMap<Integer, Post>();
//	private Map<Integer, Video> videos = new HashMap<Integer, Video>();
	
	private Map<String, User> followedBy = new HashMap<String, User>();
	
	private Map<String, User> followedUsers = new HashMap<String, User>();
	private Map<String, Tag> followedTags = new HashMap<String, Tag>();
	
	public _User() {
		
	}
	
	def addContent(Content c){
		content.put(c.hashCode(), c);
		print "added content user"
	}
	
//	public void addPost(Post post) {
//		content.put(post.hashCode(), post);
//	}
	
	public Collection<Post> getPosts(){return getContent(Post.class);}
	public Collection<Post> getVideos(){return getContent(Post.class);}

	@Override
	protected void registerWithTag(Tag tag) {
		tag.addTagged(this);
	}

	@Override
	protected void unregisterWithTag(Tag tag) {
		tag.removeTagged(this);
	}

//	public Post getPost(int nodeId) {
//		return content.get(nodeId);
//	}

	public void updateContent(Content oldContent, Content updatedContent) {
		int oldContentId = oldContent.getTitle().hashCode();
		
		if(updatedContent == null){
			content.put(oldContentId, oldContent);
		} else {
			deletePost(oldContentId);
			content.put(updatedContent.getTitle().hashCode(), updatedContent);
		}
		
	}
	
//	public void updateVideo(Video oldVideo, Video updatedVideo) {
//		int oldVideoId = oldVideo.getTitle().hashCode();
//		
//		if(updatedVideo == null){
//			videos.put(oldVideoId, oldVideo);
//		} else {
//			deleteVideo(oldVideoId);
//			videos.put(updatedVideo.getTitle().hashCode(), updatedVideo);
//		}
//		
//	}

	public boolean deleteContent(int id) {
		return content.remove(id) == null;
	}

//	public void addVideo(Video video) {
//		content.put(video.hashCode(), video);
//	}
//	
//	public boolean deleteVideo(int id) {
//		return videos.remove(id) == null;
//	}

	def Collection getContent(Class clazz) {
		return content.values().findAll { it.class.equals(clazz) }
	}

	def Content getContent(int nodeId) {
		return content.get(nodeId);
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
