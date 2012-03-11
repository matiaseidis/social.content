package com.mati.demo.model.validator.user;

import org.apache.commons.lang.StringUtils;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.AbstractValidator;

public class UserValidator extends AbstractValidator<User> {
	
	private User user;
	
//	public UserValidator(){}
	public UserValidator(User user){
		this.user = user;
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
		if(problems){
			addError("user", user);
		}
		
		return !problems;
	}

	public boolean exists(Model model) {
		
		if(user == null) return false;
		
		boolean unavailable = model.containsUser(user);
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
