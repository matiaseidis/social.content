package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.content.ContentValidator;

public class UpdateContent implements Transaction{

	private final Content oldContent;
	private final Content updatedContent;
	private final ContentValidator _validator;
	private final String loggedInUserName;

	
	public UpdateContent(Content oldContent, Content updatedContent, ContentValidator validator, String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;

		this.oldContent = oldContent;
		this.updatedContent = updatedContent;
		_validator = validator;
	}
	public UpdateContent(Content oldContent, ContentValidator validator, String loggedInUserName) {
		this(oldContent, null, validator, loggedInUserName);
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		User loggedInUser = model.loadUserByUsername(loggedInUserName);

		// TODO validator goes out of here
		if(_validator.validate(/*oldContent*/)){
			if(updatedContent != null && _validator.validate(/*updatedContent*/)){
				loggedInUser.updateContent(oldContent, updatedContent);
			}
		}
	}
	
}
