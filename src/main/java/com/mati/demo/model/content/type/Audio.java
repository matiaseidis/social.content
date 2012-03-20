package com.mati.demo.model.content.type;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.content.Content;

public class Audio extends Content{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter @Setter private String body;
	@Getter @Setter private String fileName;
	transient @Getter @Setter private String audioRef;
	transient @Getter @Setter private CommonsMultipartFile fileData;

}
