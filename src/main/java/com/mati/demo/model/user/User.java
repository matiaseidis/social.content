package com.mati.demo.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Audio;
import com.mati.demo.model.content.type.Event;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;
import com.mati.demo.util.GetContentQuery;
import static com.mati.demo.util.NavigationUtils.*;

public class User extends Taggable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String ROLE_USER = "ROLE_USER";
	final String ROLE_ADMIN = "ROLE_ADMIN";

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
	
	/*
	 * como se desde cuando esta siguiendo o es seguido por un usuario???
	 */
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
	
	public List<Content> getFollowedContent(int total, int page){	
		List<Content> fc = new ArrayList<Content>(); 
		for(User u : followedUsers.values()){
			fc.addAll(u.getContent());
		}
		Collections.sort(fc, new Comparator<Content>(){

			@Override
			public int compare(Content c1, Content c2) {
				return c1.getPostDate().compareTo(c2.getPostDate());
			}
			
		});
		
		return fc.subList(from(fc, page, total), to(fc, page, total));
	}
	
	public List<Event> getFollowedEvents(int total, int page){	
		List<Event> fe = new ArrayList<Event>(); 
		for(User u : followedUsers.values()){
			for(Event e : u.getEvents()){
				if(e.getStart().after(new Date())){
					fe.add(e);
				}
			}
		}
		Collections.sort(fe, new Comparator<Event>(){

			@Override
			public int compare(Event e1, Event e2) {
				return e1.getStart().compareTo(e2.getStart());
			}
			
		});
		
		return fe.subList(from(fe, page, total), to(fe, page, total));
	}
	
	
	
	public List<Content> getFollowedContent(){	
		List<Content> fc = new ArrayList<Content>(); 
		for(User u : followedUsers.values()){
			fc.addAll(u.getContent());
		}
		return fc;
	}
	
	public List<Video> getFollowedVideos(int total, int page){
		return getFollowedVideos().subList(from(getFollowedVideos(), page, total), to(getFollowedVideos(), page, total));
	}
	
	public List<Video> getFollowedVideos(){
		return new GetContentQuery<Video>().getContent(Video.class, getFollowedContent());
	}
	
	public List<Post> getFollowedPosts(){
		return new GetContentQuery<Post>().getContent(Post.class, getFollowedContent());
	}
	
	public List<Audio> getFollowedAudios(){
		return new GetContentQuery<Audio>().getContent(Audio.class, getFollowedContent());
	}
	
	public List<Event> getFollowedEvents(){
		return new GetContentQuery<Event>().getContent(Event.class, getFollowedContent());
	}
	
	@Override
	public void registerWithTag(Tag tag) {
		tag.addTagged(this);
	}

	@Override
	public void unregisterWithTag(Tag tag) {
		tag.removeTagged(this);
	}

	public void updateContent(Content oldContent) {
	
			/*
			 * solo actualizo
			 */
			addContent(oldContent);
	}
	
	public void updateContent(int idToReplace, Content newContent) {
			/*
			 * cambio el titulo, reemplazo
			 */
			deleteContent(idToReplace);
			newContent.setId(newContent.hashCode());
			updateContent(newContent);
	}
	
	public boolean deleteContent(int id) {
		return content.remove(id) == null;
	}

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
	
	public List<User> getFollowedUsers(int total, int page){	
		List<User> fu = new ArrayList<User>(); 
		
		fu.addAll(followedUsers.values());
		
		return fu.subList(from(fu, page, total), to(fu, page, total));
	}
	
	public List<User> getFollowedBy(int total, int page){	
		List<User> fu = new ArrayList<User>(); 
		
		fu.addAll(followedBy.values());
		
		return fu.subList(from(fu, page, total), to(fu, page, total));
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
	
	public boolean isFollowing(Tag t) {
		return followedTags.get(t.getTagName()) != null;
	}

	public List<Video> getVideos(){
		return new GetContentQuery<Video>().getContent(Video.class, getContent());
	}
	
	public List<Post> getPosts(){
		return new GetContentQuery<Post>().getContent(Post.class, getContent());
	}
	
	public List<Audio> getAudios() {
		return new GetContentQuery<Audio>().getContent(Audio.class, getContent());
	}
	
	public List<Event> getEvents() {
		return new GetContentQuery<Event>().getContent(Event.class, getContent());
	}

}
