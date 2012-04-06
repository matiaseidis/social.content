package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.relationships.Relation;

public class RemoveRelation implements Transaction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Content c;
	private final Relation r;
	
	public RemoveRelation(Content c, Relation r) {
		this.c = c;
		this.r = r;
	}

	@Override
	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		Content content = model.loadContentById(c.getId());
		content.removeRelation(r);
	}

}
