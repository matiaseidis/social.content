package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;

public class Unrate implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int contentId;
	private final String userName;
	
	public Unrate(int contentId, String userName) {
		this.contentId = contentId;
		this.userName = userName;
	}

	@Override
	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model)prevalentSystem;
		Content c = model.loadContentById(contentId);
		
		if(c == null){
			return;
		}
		
		c.removeRating(userName);
	}

}
