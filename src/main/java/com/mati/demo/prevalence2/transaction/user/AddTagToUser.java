package com.mati.demo.prevalence2.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;

public class AddTagToUser implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Tag tag;

	public AddTagToUser(Tag tag) {
		this.tag = tag;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		
		model.getLoggedInUser().addTag(tag);
	}

}
