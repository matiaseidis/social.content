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
		T originalContent = (T)model.getLoggedInUser().getContent(content.getId());
		String originalTitle = null;
		
		if(originalContent != null){
			/*
			 * we are editing, not creating
			 */
			originalTitle = originalContent.getTitle();
			if(!title.equals(originalTitle)){
				/*
				 * los titulos no son iguales ->
				 * puede ser que el nuevo titulo no este disponible
				 */
				int newContentId = content.hashCode();
				if(model.getLoggedInUser().getContent(newContentId) != null){
					addError("title", "ya existe un contenido con el mismo titulo: '"+title+"'");
				}
			}
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
