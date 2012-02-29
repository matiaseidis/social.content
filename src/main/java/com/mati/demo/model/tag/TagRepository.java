package com.mati.demo.model.tag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TagRepository {

	/*
	 * Tags Collection
	 */
	final Map<String, Tag> tagsBag = new HashMap<String, Tag>();
	
	public static final TagRepository INSTANCE = new TagRepository();
	
	private TagRepository(){}
	
	public Collection<Tag> getAllTags(){
		return tagsBag.values();
	}
	
	public Tag exists(Tag tag){
		return getByTagName(tag.getTagName());
	}
	
	public Tag getByTagName(String tagName) {
		return tagsBag.get(tagName);
	}

	public void addTag(Tag tag){
		tagsBag.put(tag.getTagName(), tag);
	}
	
	public void removeTag(Tag tag){
		tagsBag.remove(tag.getTagName());
	}
}
