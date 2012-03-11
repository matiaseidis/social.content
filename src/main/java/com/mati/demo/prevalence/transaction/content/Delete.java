package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.validator.content.ContentValidator;

public class Delete implements Transaction {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int id;
	
	public Delete(int contentId) {
		this.id = contentId;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
			model.getLoggedInUser().deleteContent(id);
	}


}
