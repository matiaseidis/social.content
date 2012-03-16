package com.mati.demo.model.validator;


public class ValidationError {
	
	final String code;
	final Object message;
	
	public ValidationError(String code, Object message){
		this.code = code;
		this.message = message;
	}
	

}
