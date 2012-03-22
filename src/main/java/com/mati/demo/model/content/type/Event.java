package com.mati.demo.model.content.type;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.content.Content;

public class Event extends Content{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private Date start;
	@Getter @Setter private Date end;
	@Getter @Setter private String body;
	
}
