package com.mati.demo.model.content;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private String title;
	@Getter @Setter private String body;

}
