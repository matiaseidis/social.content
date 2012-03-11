package com.mati.demo.model.validator.content;

import lombok.Getter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.validator.GenericValidator;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.validator.AbstractValidator;


public abstract class ContentValidator<T extends Content> extends AbstractValidator<T>{
	
	@Getter private final T content;
	@Getter private final Model model;
	
	public ContentValidator(T content, Model model){
		this.content = content;
		this.model = model;
	}
	
	public boolean validate(){
		boolean withErrors = false;
		
		String title = content.getTitle();
		if(GenericValidator.isBlankOrNull(title) || !GenericValidator.maxLength(title, 64)){
			addError("title", "el titulo es obligatorio");
			
		}
		
		performValidation();
		
		if(CollectionUtils.isNotEmpty(getErrors())) throw new RuntimeException("Validation failed");
		return true;
	}
	
	protected abstract void performValidation();
	
	public boolean exists(Model model){
		return model.loadContentById(content.getId()) != null;
	}
	
}
