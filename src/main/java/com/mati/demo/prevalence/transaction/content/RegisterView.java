package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;

public class RegisterView implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int contentId;
	
	public RegisterView(int id) {
		contentId = id;
	}

	@Override
	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model m = (Model)prevalentSystem;
		Content c = m.loadContentById(contentId);
		c.setVisited(c.getVisited()+1);
	}

}
