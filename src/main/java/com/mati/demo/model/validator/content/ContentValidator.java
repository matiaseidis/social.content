package com.mati.demo.model.validator.content;

import lombok.Getter;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.validator.GenericValidator;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.validator.AbstractValidator;


public abstract class ContentValidator<T extends Content> extends AbstractValidator<T>{
	
	@Getter private final T content;
	@Getter private final Model model;
	private boolean ok;
	
	public boolean isOk(){
		return ok;
	}
	
	public ContentValidator(T content, Model model){
		this.content = content;
		this.model = model;
	}
	
	public boolean validate(){
		
		String title = content.getTitle();
		
		if(exists()){
			addError("title", "ya existe un contenido con el mismo titulo");
		}
		
		if(GenericValidator.isBlankOrNull(title) || !GenericValidator.maxLength(title, 64)){
			addError("title", "el titulo es obligatorio");
		}
		
		performValidation();
		ok = MapUtils.isEmpty(getErrors());
		if(!isOk()){
			addError("model", content);
		}
		return isOk();
	}
	
	protected abstract void performValidation();
	
	public boolean exists(){
		return model.getLoggedInUser().getContent(content.getId()) != null;
	}
	
}
