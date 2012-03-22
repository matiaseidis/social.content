package com.mati.demo.model.validator;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public abstract class AbstractValidator<T> implements Validator<T> {
	
	@Getter final Map<String, Object> errors = new HashMap<String, Object>();
	
	private boolean ok= true;
	
	public boolean isOk(){
		return ok;
	}
	public void addError(String code, Object message){
		getErrors().put(code, message);
		ok = false;
	}

}
