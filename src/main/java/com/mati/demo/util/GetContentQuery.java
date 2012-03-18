package com.mati.demo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mati.demo.model.content.Content;

public class GetContentQuery<T extends Content> {

	
	public List<T> getContent(Class clazz, Collection<Content> allFollowedContent){
		List<T> followedContent  = new ArrayList<T>();
		
		for(Content c : allFollowedContent){
			if(c.getClass().isAssignableFrom(clazz)){
				followedContent.add((T)c);
			}
		}
		return followedContent;
	}
}
