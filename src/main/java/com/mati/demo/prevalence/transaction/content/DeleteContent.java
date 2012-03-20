package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;

public class DeleteContent implements Transaction {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int id;
	private final String loggedInUserName;

	
	public DeleteContent(int contentId, String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
		this.id = contentId;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		User loggedInUser = model.loadUserByUsername(loggedInUserName);

		loggedInUser.deleteContent(id);
	}


}
