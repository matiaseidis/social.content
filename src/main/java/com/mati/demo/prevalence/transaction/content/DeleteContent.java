package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;

public class DeleteContent implements Transaction {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int id;
	
	public DeleteContent(int contentId) {
		this.id = contentId;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
			model.getLoggedInUser().deleteContent(id);
	}


}
