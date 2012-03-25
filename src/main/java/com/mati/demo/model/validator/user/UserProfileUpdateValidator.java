package com.mati.demo.model.validator.user;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;

import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.AbstractValidator;

public class UserProfileUpdateValidator extends AbstractValidator<User> {

	private final User user;

	public UserProfileUpdateValidator(User user){
		this.user = user;
	}
	
	@Override
	public boolean validate() {
		if(StringUtils.isEmpty(user.getEmail())){
			addError("email","tiene que tener un email");
		} else if(!GenericValidator.isEmail(user.getEmail())){
			addError("email","el email no es valido");
		}
		if(!isOk())	{
			addError("user", user);
		}

		return isOk();
	}

	@Override
	public boolean exists() {
		throw new RuntimeException("????");
	}

}
