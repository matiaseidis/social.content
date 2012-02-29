package com.mati.demo.model.tag;

import java.util.Collection;

public class Taggable {
	
	Collection<Tag> tags;
	TagRepository tagRepository;
	
	public boolean addTag(Tag tag){
		
		/*
		 * TODO
		 * check if repo contains tag
		 */
		
		return this.getTags().add(tag);
	}

	public Collection<Tag> getTags() {
		return tags;
	}
	
	public boolean removeTag(Tag tag){
		
		/*
		 * TODO
		 * check if repo contains tag
		 */
		
		return tags.remove(tag);
	}

}
