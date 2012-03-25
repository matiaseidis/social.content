package com.mati.demo.util;

import java.io.File;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.mati.demo.model.content.type.MediaFileContent;
import com.mati.demo.model.validator.Validator;

public class MediaUtils {
	
	@Getter @Setter private String fileSystemBasePath;
	
	public void saveMediaFile(MediaFileContent content, Validator<?> validator){
		try {

			String destinationPath = fileSystemBasePath + File.separator + content.getContentType() + File.separator; 

			String fileName = StringUtils.replace(content.getFileData().getOriginalFilename().toLowerCase(), " ", "-");

			File file = new File(destinationPath + fileName);

			FileUtils.writeByteArrayToFile(file, content.getFileData().getBytes());

			content.setFileName(fileName);

		} catch (IOException e) {
			validator.addError("save", "Hubo un problema intentando crear " + content.getContentType());
		}
	}
	
	public boolean hasUploadedFile(MediaFileContent content){
		return content.getFileData() != null && content.getFileData().getSize() > 0;
	}

}
