package com.mati.demo.model2.validator.user;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.UserCommand;
import com.mati.demo.model.validator.AbstractValidator;

public class UserValidator extends AbstractValidator<UserCommand> {
	
	private final UserCommand user;
	private final String fileSystemBasePath;
	private final String userPictureFolder;
	
	public UserValidator(UserCommand user, String fileSystemBasePath, String userPictureFolder){
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
		if(StringUtils.isEmpty(user.getEmail())){
			addError("email","tiene que tener un email");
			problems = true;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			addError("password","tiene que tener una clave de seguridad");
			problems = true;
		}
		if(StringUtils.isEmpty(user.getConfirmPassword())){
			addError("password","tiene que tener una clave de seguridad");
			problems = true;
		}
		if(StringUtils.isNotEmpty(user.getPassword()) && StringUtils.isNotEmpty(user.getConfirmPassword()) && !user.getPassword().equals(user.getConfirmPassword())){
			addError("confirmPassword","las contraseñas no coinciden");
			problems = true;
		}
		
		if(!GenericValidator.isEmail(user.getEmail())){
			addError("email","el email no es valido");
			problems = true;
		}
		else {
			addError("user", user);
		}
		
		return !problems;
	}

	public boolean exists(Model model) {
		
		if(user == null) return false;
		
		boolean unavailable = model.loadUserByUsername(user.getUserName()) != null;
		if(unavailable){
			addError("unavailable","El nombre ya esta siendo usado por otro usuario");
		}
		return unavailable;
	}
}
