package com.mati.demo.model2.validator;

import java.util.List;

import com.mati.demo.model.base.Model;

public interface Validator<T> {
	
	List<ValidationError> getErrors();
	
	boolean validate();
	
	void addError(String code, Object message);
	
	public boolean exists(Model model);

}
