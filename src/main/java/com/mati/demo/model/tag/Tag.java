package com.mati.demo.model.tag;

import java.io.Serializable;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

public class Tag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter private Collection<Taggable> tagged;
	
	@Getter @Setter private String tagName;

}
