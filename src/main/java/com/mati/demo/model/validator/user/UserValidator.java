package com.mati.demo.model.validator.user;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.UserCommand;
import com.mati.demo.model.validator.AbstractValidator;

public class UserValidator extends AbstractValidator<UserCommand> {

	private final UserCommand user;
	private final String fileSystemBasePath;
	private final String userPictureFolder;
	private final Model model;

	public UserValidator(UserCommand user, String fileSystemBasePath, String userPictureFolder, Model model){
		this.user = user;
		this.fileSystemBasePath = fileSystemBasePath;
		this.userPictureFolder = userPictureFolder;
		this.model = model;
	}

	public boolean validate() {

		/*
		 * TODO ver disponibilidad de nombre aca
		 */
		if(StringUtils.isEmpty(user.getUserName())){
			addError("userName","tiene que tener un nombre");
		}
		
		if(StringUtils.isEmpty(user.getEmail())){
			addError("email","tiene que tener un email");
		} else if(!GenericValidator.isEmail(user.getEmail())){
			addError("email","el email no es valido");
		}
		
		if(StringUtils.isEmpty(user.getPassword())){
			addError("password","tiene que tener una clave de seguridad");
		}
		if(StringUtils.isEmpty(user.getConfirmPassword())){
			addError("password","tiene que tener una clave de seguridad");
		}
		if(StringUtils.isNotEmpty(user.getPassword()) && StringUtils.isNotEmpty(user.getConfirmPassword()) && !user.getPassword().equals(user.getConfirmPassword())){
			addError("confirmPassword","las contrase��as no coinciden");
		}

		
		if(!isOk())	{
			addError("user", user);
		}

		return isOk(); 
	}

	public boolean exists() {

		if(user == null) return false;

		boolean unavailable = model.loadUserByUsername(user.getUserName()) != null;
		if(unavailable){
			addError("unavailable","El nombre ya esta siendo usado por otro usuario");
		}
		return unavailable;
	}
}
