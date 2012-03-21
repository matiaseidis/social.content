package com.mati.demo.model.validator;

import java.util.Map;

public interface Validator<T> {
	
	Map<String, Object> getErrors();
	
	boolean validate();
	
	void addError(String code, Object message);
	
	public boolean exists();

}
