package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;

public class Rate implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int contentId;
	private final String userName;
	private final float rating;
	
	public Rate(int contentId, String userName, float rating) {
		this.contentId = contentId;
		this.userName = userName;
		this.rating = rating;
	}

	@Override
	public void executeOn(Object prevalentSystem, Date executionTime) {
		
		Model model = (Model)prevalentSystem;
		Content c = model.loadContentById(contentId);
		
		if(c == null || c.getAuthor().getUserName().equals(userName)){
			return;
		}
		
		c.addRating(userName, rating);
	}

}
