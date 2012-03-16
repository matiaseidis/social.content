package com.mati.demo.model.content.type;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.content.Content;

public class Video extends Content{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String body;
	String fileName;
	String url;
	transient String videoRef;
	transient String baseUrl;
	transient CommonsMultipartFile fileData;
//	@Getter private final String contentType = "video";
	
	
}
