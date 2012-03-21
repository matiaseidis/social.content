package com.mati.demo.model.validator;

import java.util.List;
import java.util.Map;

import com.mati.demo.model.base.Model;

public interface Validator<T> {
	
	Map<String, Object> getErrors();
	
	boolean validate();
	
	void addError(String code, Object message);
	
	public boolean exists();

}
