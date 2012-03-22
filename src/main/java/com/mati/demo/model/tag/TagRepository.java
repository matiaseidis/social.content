package com.mati.demo.model.tag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TagRepository {

	/*
	 * Tags Collection
	 */
//	final 
	private Map<String, Tag> tagsBag = new HashMap<String, Tag>();
	
//	public static final TagRepository INSTANCE = new TagRepository();
	
//	private TagRepository(){}
	
	public Collection<Tag> getAllTags(){
		return tagsBag.values();
	}
	
	public boolean exists(Tag tag){
		return tagsBag.containsKey(tag.getTagName());
	}
	
	public Tag getByTagName(String tagName) {
		return tagsBag.get(tagName);
	}

	public void addTag(Tag tag){
		if(!tagsBag.containsKey(tag.getTagName())){
			tagsBag.put(tag.getTagName(), tag);
		}
	}
	
	public void removeTag(Tag tag){
		tagsBag.remove(tag.getTagName());
	}
}
