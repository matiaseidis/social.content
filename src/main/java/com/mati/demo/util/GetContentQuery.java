package com.mati.demo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mati.demo.model.content.Content;

public class GetContentQuery<T extends Content> {

	
	public List<T> getContent(Class clazz, Collection<Content> contentCollection){
		List<T> result  = new ArrayList<T>();
		
		for(Content c : contentCollection){
			if(c.getClass().isAssignableFrom(clazz)){
				result.add((T)c);
			}
		}
		return result;
	}
}
