package com.mati.demo.model.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Taggable {
	
	/*
	 * the tags for this taggable
	 */
	List<Tag> tags = new ArrayList<Tag>();
	
	TagRepository tagRepository = TagRepository.INSTANCE;
	
	public boolean addTag(Tag tag){
		
		Tag tagFromRepo = tagRepository.getByTagName(tag.getTagName());
		
		if(tagFromRepo == null){
			tagRepository.addTag(tag);
			tagFromRepo = tag;
		}
		
		return this.getTags().add(tagFromRepo);
	}

	public List<Tag> getTags() {
		return tags;
	}
	
	public boolean removeTag(Tag tag){
		
		this.getTags().remove(tag);
		if(tag.getTagged().isEmpty()){
			tagRepository.removeTag(tag);
		}
		
		/*
		 * TODO
		 * check if repo contains tag
		 */
		
		return tags.remove(tag);
	}

}
