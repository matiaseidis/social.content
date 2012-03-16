package com.mati.demo.model2.content.type;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.content.Content;

public class Video extends Content{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter @Setter private String body;
	@Getter @Setter private String fileName;
	@Getter @Setter private String url;
	transient @Getter @Setter private String videoRef;
	transient @Getter @Setter private String baseUrl;
	transient @Getter @Setter private CommonsMultipartFile fileData;
//	@Getter private final String contentType = "video";
	
	
}
