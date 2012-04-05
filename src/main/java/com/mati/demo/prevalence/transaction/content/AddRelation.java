package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.relationships.Relation;

public class AddRelation implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int contentId;
	private final Relation r;
	
	public AddRelation(int contentId, Relation r) {
		this.contentId = contentId;
		this.r = r;
	}

	@Override
	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		Content c = model.loadContentById(contentId);
		c.addRelation(r);
	}

}
