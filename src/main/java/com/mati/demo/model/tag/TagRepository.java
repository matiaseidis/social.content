package com.mati.demo.model.tag;

import java.util.Collection;

public class TagRepository {

	/*
	 * Tags Collection
	 */
	Collection tagsBag;
	
	public static final TagRepository INSTANCE = new TagRepository();
	
	private TagRepository(){}
}
