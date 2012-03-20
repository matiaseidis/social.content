package com.mati.demo.prevalence.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;

public class AddTagToUser implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Tag tag;
	private final String loggedInUserName;

	public AddTagToUser(Tag tag, String loggedInUserName) {
		this.tag = tag;
		this.loggedInUserName = loggedInUserName;

	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		User loggedInUser = model.loadUserByUsername(loggedInUserName);

		loggedInUser.addTag(model.getTagRepository(), tag);
	}

}
