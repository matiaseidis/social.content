package com.mati.demo.model.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Taggable {
	
	/*
	 * the tags for this taggable
	 */
	List<Tag> tags = new ArrayList<Tag>();
	
	TagRepository tagRepository;
	
	public boolean addTag(Tag tag){
		
		/*
		 * TODO
		 * check if repo contains tag
		 */
		
		return this.getTags().add(tag);
	}

	public List<Tag> getTags() {
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
