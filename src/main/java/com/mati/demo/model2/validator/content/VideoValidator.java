package com.mati.demo.model2.validator.content;

import java.io.File;
import java.io.IOException;

import lombok.Getter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Video;

public class VideoValidator extends ContentValidator<Video> {

	@Getter private final String fileSystemBasePath;
	
	public VideoValidator(Video video, Model model, String fileSystemBasePath) {
		super(video, model);
		this.fileSystemBasePath = fileSystemBasePath;
	}

	@Override
	protected void performValidation() {

		/*
		 * only look up the uploaded file if url is not provided
		 */
		if(StringUtils.isEmpty(getContent().getUrl())){
			try {
				CommonsMultipartFile multipartFile = getContent().getFileData();

				String destinationPath = fileSystemBasePath + File.separator + "video" + File.separator; 

				String fileName = StringUtils.replace(multipartFile.getOriginalFilename().toLowerCase(), " ", "-");

				File file = new File(destinationPath + fileName);

				FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
				
				getContent().setFileName(fileName);
				
			} catch (IOException e) {
				addError("save", "No se puedo guardar el video");
			}
		}




	}



}
