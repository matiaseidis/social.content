package com.mati.demo.model.tag;

import java.util.List;


public abstract class Taggable {
	
	/*
	 * the tags for this taggable
	 */
	List<Tag> tags = new ArrayList<Tag>();
	
	TagRepository tagRepository = TagRepository.INSTANCE;
	
	public boolean addTag(Tag tag){
		
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
		registerWithTag(tag);
		
		return getTags().add(tagFromRepo);
	}

	protected abstract void registerWithTag(Tag tag);
	
	protected abstract void unregisterWithTag(Tag tag);

	public boolean removeTag(Tag tag){
		
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
