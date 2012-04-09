package com.mati.demo.model.base;

import static com.mati.demo.util.NavigationUtils.from;
import static com.mati.demo.util.NavigationUtils.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import lombok.Getter;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.TagRepository;
import com.mati.demo.model.user.User;

public class Model implements Serializable{
	
	public Logger logger = Logger.getLogger(getClass());

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Map<Integer, Content> contentMap = new HashMap<Integer, Content>();
	
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
		c.getAuthor().addContent(c);
		logger.info("added content model");
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

	public List<User> getTaggedUsers(Tag tag) {
		List<User> result = new ArrayList<User>();
		
		for(String userName : tag.getTaggedUsers()){
			result.add(loadUserByUsername(userName));
		}
		
		return result;
	}

	public List<User> getFollowedBy(Tag tag) {
		List<User> result = new ArrayList<User>();
		
		for(String userName : tag.getFollowedBy()){
			result.add(loadUserByUsername(userName));
		}
		
		return result;
	}
	
	public void updateContent(Content content) {
		if(content.getId() != content.hashCode()){
			
			/*
			 * cambio el titulo, reemplazo
			 */
			int oldContentId = content.getId();
			deleteContent(oldContentId);
			content.setId(content.hashCode());
			addContent(content);

			/*
			 * fowards the update to logged in user
			 */
			content.getAuthor().updateContent(oldContentId, content);

			
		} else {
			/*
			 * solo actualizo
			 */
			addContent(content);
			/*
			 * fowards the update to logged in user
			 */
			content.getAuthor().updateContent(content);
		}

	}
	
	public boolean deleteContent(int id) {
		getLoggedInUser().deleteContent(id);
		return contentMap.remove(id) == null;
	}

	public List<Content> getContent() {
		List<Content> result = new ArrayList<Content>();
		result.addAll(contentMap.values());
		return result;
	}
	
	public List<Content> getContent(int total, int page){	
		List<Content> c = new ArrayList<Content>(); 
			c.addAll(contentMap.values());
		/*
		 * 
		 *	TODO pasar este ordenamiento a la declaracion del Map en Model y en User
		 *	para no tener que ordenar en cada llamado
		 */
			Collections.sort(c, new Comparator<Content>(){

			@Override
			public int compare(Content c1, Content c2) {
				return c1.getPostDate().compareTo(c2.getPostDate());
			}
			
		});
		
		return c.subList(from(c, page, total), to(c, page, total));
	}

	public List<Content> bestRated() {
		return mostBla(new Comparator<Content>() {

			@Override
			public int compare(Content c1, Content c2) {
				if (c1.getRate() == c2.getRate()) 
					return 0;
				
				boolean greater = c1.getRate() >= c2.getRate(); 
				return greater ? -1 : 1;
			}
		}, 6);
	}

	public List<Content> mostVisited() {
		return mostBla(new Comparator<Content>() {

			@Override
			public int compare(Content c1, Content c2) {
				if (c1.getVisited() == c2.getVisited()) 
					return 0;
				
				boolean greater = c1.getVisited() >= c2.getVisited(); 
				return greater ? -1 : 1;
			}
		}, 6);
	}
	
	public List<Content> mostRecent() {
		return mostBla(new Comparator<Content>() {

			@Override
			public int compare(Content c1, Content c2) {
				return c2.getPostDate().compareTo(c1.getPostDate());
			}
		}, 6);
	}
	
	public List<Content> mostBla(Comparator<Content> comp, int max) {
		List<Content> result = new ArrayList<Content>(getContent());
		Collections.sort(result, comp);
		return result.subList(from(result,0 , max), to(result, 0, max));
	}
}
