package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.validator.content.ContentValidator;

public class CreateContent<T extends Content> implements Transaction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final T _content;

	public CreateContent(T content) {
		_content = content;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model)prevalentSystem;
			_content.setId(_content.hashCode());
			_content.setAuthor(model.getLoggedInUser());
			_content.setPostDate(new Date());
			model.getLoggedInUser().addContent(_content);
			model.addContent(_content);
	}
	
}