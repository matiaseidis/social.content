package com.mati.demo.model.validator.content;

import java.io.File;
import java.io.IOException;

import lombok.Getter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Audio;

public class AudioValidator extends ContentValidator<Audio> {

	@Getter private final String fileSystemBasePath;

	public AudioValidator(Audio audio, Model model, String fileSystemBasePath) {
		super(audio, model);
		this.fileSystemBasePath = fileSystemBasePath;
	}

	@Override
	protected void performValidation() {

		try {
			CommonsMultipartFile multipartFile = getContent().getFileData();

			String destinationPath = fileSystemBasePath + File.separator + "audio" + File.separator; 

			String fileName = StringUtils.replace(multipartFile.getOriginalFilename().toLowerCase(), " ", "-");

			File file = new File(destinationPath + fileName);

			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());

			getContent().setFileName(fileName);

		} catch (IOException e) {
			addError("save", "No se puedo guardar el audio");
		}
	}
}
