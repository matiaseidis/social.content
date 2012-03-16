package com.mati.demo.model2.validator;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public abstract class AbstractValidator<T> implements Validator<T> {
	
	@Getter final List<ValidationError> errors = new ArrayList<ValidationError>();
	
	public void addError(String code, Object message){
		getErrors().add(new ValidationError(code, message));
	}

}
