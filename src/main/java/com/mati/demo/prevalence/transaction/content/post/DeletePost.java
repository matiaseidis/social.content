package com.mati.demo.prevalence.transaction.content.post;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;

public class DeletePost implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int id;
	
	public DeletePost(int nodeId) {
		this.id = nodeId;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		model.getLoggedInUser().deletePost(id);
	}

}
