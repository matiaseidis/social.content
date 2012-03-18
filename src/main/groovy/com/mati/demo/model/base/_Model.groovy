package com.mati.demo.model.base

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder

import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.TagRepository;
import com.mati.demo.model.tag.Taggable
import com.mati.demo.model.user._User;

class _Model implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	TagRepository tagRepository = TagRepository.INSTANCE;

	//   private Map<Integer, Content> content = new HashMap<Integer, Content>();
	def contentMap = new HashMap<Integer, Content>();

	def usersMap = new HashMap<String, _User>();

	def tagsMap = [:]
	//   new HashMap<String, Tag>();

	def taggables = new HashMap<String, Taggable>();

	//   def users = {  usersMap.values()  }

	//   def tags = {return tagsMap.values}

	def Collection<_User> getUsers(){ usersMap.values() }
	def Collection<Tag> getTags(){ tagsMap.values() }

	def _User loadUserByUsername(String username) {
		usersMap[username]
	}

	def Tag loadTagByTagName(String tagname) {
//		tagsMap[tagname]
		tagRepository.getByTagName(tagname)
	}

	def Content loadContentById(int id) {
		contentMap[id]
	}

	def _User getLoggedInUser() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		_User user = loadUserByUsername(userName);
		user
	}

	def addTag(Tag tag){
		tagsMap.put(tag.tagName, tag)
	}

	def removeTag(Tag tag){
		tagsMap.put(tag.tagName, null)
	}

	//   def addContent(Content c){
	//	   contentMap[c.hashCode] = c;
	//   }

	def addContent(Content c){
		try{
		contentMap.put(c.hashCode(), c);
		print "added content model"
		}catch(Exception e){
		println e
		}
	}

	def addUser(_User u){
		usersMap.put(u.userName, u)
	}

	def boolean hasTag(Tag tag){
		tagsMap[tag.tagName] != null
	}

	def boolean hasUser(_User user){
		usersMap[user.userName] != null
	}
	
	def List<Content> searchContent(String pattern) {
		def result = []

		contentMap.values().each {
			def m = it.title =~ pattern
			if(m.getCount() > 0){
				result.add(it)
			}
		}
		return result;
	}

	def List<_User> searchUsers(String pattern) {
		def result = []

		usersMap.values().each {
			def m = it.userName =~ pattern
			if(m.getCount() > 0){
				result.add(it)
			}
		}
		return result;
	}

	def List<Tag> searchTags(String pattern) {
		def result = []

		tagsMap.values().each {
			def m = it.tagName =~ pattern
			if(m.getCount() > 0){
				result.add(it)
			}
		}
		return result;
	}

}
