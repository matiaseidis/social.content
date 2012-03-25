package com.mati.demo.model.validator.content;

import java.io.File;
import java.io.IOException;

import lombok.Getter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Audio;
import com.mati.demo.util.MediaUtils;

public class AudioValidator extends ContentValidator<Audio> {

	
	private final MediaUtils mediaUtils;

	public AudioValidator(Audio audio, Model model, MediaUtils mediaUtils) {
		super(audio, model);
		this.mediaUtils = mediaUtils;
	}

	@Override
	protected void performValidation() {

		if(mediaUtils.hasUploadedFile(getContent())){

			mediaUtils.saveMediaFile(getContent(), this);
		
		} else if(StringUtils.isEmpty(getContent().getFileName())){
			/*
			 * file does not exist, so this is new content.
			 * Have to report error
			 */
			addError("url", "Debe cargar un audio.");
		}
	}
}
