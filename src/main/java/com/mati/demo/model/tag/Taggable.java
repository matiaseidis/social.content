package com.mati.demo.model.tag;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public abstract class Taggable {
	
	/*
	 * the tags for this taggable
	 */
	@Getter private List<Tag> tags = new ArrayList<Tag>();
	
	public boolean addTag(TagRepository tagRepository, Tag tag){
		
		/*
		 * retrieve tag form repo
		 */
		Tag tagFromRepo = tagRepository.getByTagName(tag.getTagName());
		
		if(tagFromRepo == null){
			/*
			 * add tag to repo if applies
			 */
			tagRepository.addTag(tag);
			tagFromRepo = tag;
		}
		/*
		 * add this taggable to the tag
		 */
		registerWithTag(tagFromRepo);
		
		return getTags().add(tagFromRepo);
	}

	protected abstract void registerWithTag(Tag tag);
	
	protected abstract void unregisterWithTag(Tag tag);

	public boolean removeTag(TagRepository tagRepository, Tag tag){
		
		/*
		 * remove this taggable form the tag
		 */
		unregisterWithTag(tag);

		if(tag.isNotTaggingAnything()){
			/*
			 * remove the tag form the repository if it is not tagging any content
			 */
			tagRepository.removeTag(tag);
		}
		
		/*
		 * remove tag form this taggable
		 */
		return getTags().remove(tag);
	}

}
