package com.mati.demo.model.base

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;
import com.mati.demo.model.user.User;

class Model implements Serializable{
	
	/**
	*
	*/
   private static final long serialVersionUID = 1L;

//   private Map<Integer, Content> content = new HashMap<Integer, Content>();
   def final contentMap = new HashMap<Integer, Content>();
   
   def final usersMap = new HashMap<String, User>();
   
   def final tagsMap = [:] 
//   new HashMap<String, Tag>();
   
   def final taggables = new HashMap<String, Taggable>();
   
//   def users = {  usersMap.values()  }
   
//   def tags = {return tagsMap.values}
   
   def Collection<User> getUsers(){ usersMap.values() }
   def Collection<Tag> getTags(){ tagsMap.values() }
   
   def User loadUserByUsername(String username) {
	   usersMap[username]
   }
   
   def Tag loadTagByTagName(String tagname) {
	   tagsMap[tagname]
   }
   
   def Content loadContentById(int id) {
	   contentMap[id]
   }
   
   def User getLoggedInUser() {
	   String userName = SecurityContextHolder.getContext().getAuthentication().getName();
	   User user = loadUserByUsername(userName);
	   user
   }
   
   def addTag(Tag tag){
	   tagsMap[tag.tagName] = tag
   }
   
   def removeTag(Tag tag){
	   tagsMap[tag.tagName] = null
   }

   def addContent(Content c){
	   contentMap[c.hashCode] = c;
   }
   
   def addUser(User u){
   		usersMap[u.userName] = u
   }
   
   def boolean containsTag(Tag tag){
		tagsMap[tag.tagName] != null   
   }
   
   def boolean containsUser(User user){
	   usersMap[user.userName] != null
  }
  
}
