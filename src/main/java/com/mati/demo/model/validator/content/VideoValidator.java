package com.mati.demo.model.validator.content;

import java.io.File;
import java.io.IOException;

import lombok.Getter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.util.MediaUtils;

public class VideoValidator extends ContentValidator<Video> {

//	@Getter private final String fileSystemBasePath;
	private final MediaUtils mediaUtils;

	
	public VideoValidator(Video video, Model model, MediaUtils mediaUtils) {
		super(video, model);
		this.mediaUtils = mediaUtils;
//		this.fileSystemBasePath = fileSystemBasePath;
	}

	@Override
	protected void performValidation() {

		/*
		 * only look up the uploaded file if url is not provided
		 */
		if(StringUtils.isEmpty(getContent().getUrl())){
			if(mediaUtils.hasUploadedFile(getContent())){
				
				mediaUtils.saveMediaFile(getContent(), this);
				
			} else {
				
				addError("url", "Debe cargar un video.");
			}
		}
	}
}
