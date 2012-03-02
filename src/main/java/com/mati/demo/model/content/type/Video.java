package com.mati.demo.model.content.type;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.content.Content;

public class Video extends Content{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter @Setter String body;
	@Getter @Setter String uri;
	transient @Getter @Setter private CommonsMultipartFile fileData;
}
