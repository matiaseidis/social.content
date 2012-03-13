package com.mati.demo.model.validator.user;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.AbstractValidator;

public class UserValidator extends AbstractValidator<User> {
	
	private final User user;
	private final String fileSystemBasePath;
	private final String userPictureFolder;
	
//	public UserValidator(){}
	public UserValidator(User user, String fileSystemBasePath, String userPictureFolder){
		this.user = user;
		this.fileSystemBasePath = fileSystemBasePath;
		this.userPictureFolder = userPictureFolder;
	}
	
	public boolean validate() {
		
		boolean problems = false; 
		if(StringUtils.isEmpty(user.getUserName())){
			addError("userName","tiene que tener un nombre");
			problems = true;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			addError("password","tiene que tener una clave de seguridad");
			problems = true;
		}
		if(user.getImage() == null || user.getImage().getSize() == 0){
			addError("image","tiene que proveer una imagen");
			problems = true;
		}
		/*
		 * only do the img stuff if the rest is ok
		 */
		if(!problems){
			/* 
			 * TODO check image size 
			 * */
			CommonsMultipartFile multipartFile = user.getImage();

			String destinationPath = fileSystemBasePath + File.separator + userPictureFolder + File.separator; 

			String fileName = StringUtils.replace(user.getUserName().toLowerCase(), " ", "-");

			File file = new File(destinationPath + fileName + ".png");
			try {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				
				BufferedImage l_original_image  = ImageIO.read(multipartFile.getInputStream());
				
				ImageIO.write(l_original_image, "PNG", out);

				byte[] imageBytes = out.toByteArray(); 
				
				FileUtils.writeByteArrayToFile(file, imageBytes);
//				FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
				
			} catch (IOException e) {
				addError("save", "No se puedo guardar la imagen del usuario");
			}
		} else {
			addError("user", user);
		}
		
		return !problems;
	}

	public boolean exists(Model model) {
		
		if(user == null) return false;
		
		boolean unavailable = model.hasUser(user);
		if(unavailable){
			addError("unavailable","El nombre ya esta siendo usado por otro usuario");
		}
		return unavailable;
	}

	public boolean validate(User t) {
		// TODO Auto-generated method stub
		return false;
	}

}
