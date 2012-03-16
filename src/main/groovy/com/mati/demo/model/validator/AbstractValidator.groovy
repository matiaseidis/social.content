package com.mati.demo.model.validator;

import java.util.List;


public abstract class AbstractValidator<T> implements Validator<T> {
	
	final List<ValidationError> errors = new ArrayList<ValidationError>();
	
	public void addError(String code, Object message){
		getErrors().add(new ValidationError(code, message));
	}

}
