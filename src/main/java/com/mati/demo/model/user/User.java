package com.mati.demo.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Audio;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;
import com.mati.demo.util.GetContentQuery;


public class User extends Taggable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String ROLE_USER = "ROLE_USER";
	final String ROLE_ADMIN = "ROLE_ADMIN";
	/*
	 * tengo que tener una lista por cada tipo de content y una de content
	 * todo el contenido con referencias duplicadas
	 */

	@Getter @Setter private int id;
	@Getter @Setter private String userName;
	@Getter @Setter private String password;
	@Getter @Setter private String email;
	@Getter @Setter private String info;
	@Getter @Setter private transient CommonsMultipartFile image;
	
	@Getter @Setter private List<String> roles = new ArrayList<String>();
	
	private Map<Integer, Content> content = new HashMap<Integer, Content>();
//	private Map<Integer, Post> posts = new HashMap<Integer, Post>();
//	private Map<Integer, Video> videos = new HashMap<Integer, Video>();
//	private Map<Integer, Audio> audios = new HashMap<Integer, Audio>();
	
	Map<String, User> followedBy = new HashMap<String, User>();
	
	Map<String, User> followedUsers = new HashMap<String, User>();
	Map<String, Tag> followedTags = new HashMap<String, Tag>();
	
	
	public void addContent(Content c){
		content.put(c.hashCode(), c);
		System.out.println("added content user");
	}
	
	public List<Content> getContent(){	
		List<Content> fc = new ArrayList<Content>(); 
		fc.addAll(content.values());
		return fc;
	}
	
	public List<Video> getVideos(){
		return new GetContentQuery<Video>().getContent(Video.class, getContent());
	}
	
	public List<Post> getPosts(){
		return new GetContentQuery<Post>().getContent(Post.class, getContent());
	}
	
	public List<Content> getFollowedContent(){	
		List<Content> fc = new ArrayList<Content>(); 
		for(User u : followedUsers.values()){
			fc.addAll(u.getContent());
		}
		return fc;
	}
	
	public List<Video> getFollowedVideos(){
		return new GetContentQuery<Video>().getContent(Video.class, getFollowedContent());
	}
	
	public List<Post> getFollowedPosts(){
		return new GetContentQuery<Post>().getContent(Post.class, getFollowedContent());
	}
	
	@Override
	public void registerWithTag(Tag tag) {
		tag.addTagged(this);
	}

	@Override
	public void unregisterWithTag(Tag tag) {
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
			deleteContent(oldContentId);
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

//	def Collection getContent(Class clazz) {
//		return content.values().findAll { it.class == clazz  }
//	}

	public Content getContent(int nodeId) {
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

	public boolean isFollowing(User u) {
		return followedUsers.get(u.getUserName()) != null;
	}

	public List<Audio> getAudios() {
		return new GetContentQuery<Audio>().getContent(Audio.class, getContent());
	}

}
