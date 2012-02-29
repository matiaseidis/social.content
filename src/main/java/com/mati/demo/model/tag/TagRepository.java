package com.mati.demo.model.tag;

import java.util.Collection;

public class TagRepository {

	/*
	 * Tags Collection
	 */
	Collection tagsBag;
	
	public static final TagRepository INSTANCE = new TagRepository();
	
	private TagRepository(){}
	
	public Collection getTagsBag(){
		return tagsBag;
	}
	
	public boolean addTag(Tag tag){
		/*
		 * TODO
		 * put if absent
		 */
		return getTagsBag().add(tag);
	}
	
	public boolean removeTag(Tag tag){
		/*
		 * TODO
		 * remove if exists
		 */
		return getTagsBag().remove(tag);
	}
}
