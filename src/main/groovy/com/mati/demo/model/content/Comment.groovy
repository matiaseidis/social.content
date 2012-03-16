package com.mati.demo.model.content;

import java.io.Serializable;
import java.util.Date;

import com.mati.demo.model.user.User;

public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String body;
	User author;
	Date postDate;

}
