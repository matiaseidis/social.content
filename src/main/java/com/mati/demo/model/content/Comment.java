package com.mati.demo.model.content;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.user.User;

public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private String body;
	@Getter @Setter private User author;
	@Getter @Setter private Date postDate;

}
