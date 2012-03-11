package com.mati.demo.model.validator;

import lombok.Getter;

public class ValidationError {
	
	@Getter final String code;
	@Getter final Object message;
	
	public ValidationError(String code, Object message){
		this.code = code;
		this.message = message;
	}
	

}
