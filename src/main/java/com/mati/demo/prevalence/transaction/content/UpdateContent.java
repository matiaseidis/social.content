package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.validator.content.ContentValidator;

public class UpdateContent implements Transaction{

	private final Content oldContent;
	private final Content updatedContent;
	private final ContentValidator _validator;
	
	public UpdateContent(Content oldContent, Content updatedContent, ContentValidator validator) {
		this.oldContent = oldContent;
		this.updatedContent = updatedContent;
		_validator = validator;
	}
	public UpdateContent(Content oldContent, ContentValidator validator) {
		this(oldContent, null, validator);
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		// TODO validator goes out of here
		if(_validator.validate(/*oldContent*/)){
			if(updatedContent != null && _validator.validate(/*updatedContent*/)){
				model.getLoggedInUser().updateContent(oldContent, updatedContent);
			}
		}
	}
	
}
