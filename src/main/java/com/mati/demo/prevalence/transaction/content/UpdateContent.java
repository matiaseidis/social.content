package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.user.User;

public class UpdateContent implements Transaction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Content oldContent;
//	private final Content updatedContent;
	private final String loggedInUserName;
	
	/*
	 * constructor for title change 
	 */
//	public UpdateContent(Content oldContent, Content updatedContent, String loggedInUserName) {
//		this.loggedInUserName = loggedInUserName;
//		this.oldContent = oldContent;
//		this.updatedContent = updatedContent;
//	}
	
	/*
	 * cons for title not changed
	 */
	public UpdateContent(Content oldContent, String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
		this.oldContent = oldContent;
//		this.updatedContent = null;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		User loggedInUser = model.loadUserByUsername(loggedInUserName);

//		if(updatedContent != null){ // title changed
//			loggedInUser.updateContent(oldContent, updatedContent);
//		} else {
			model.updateContent(oldContent);
//		}
	}
}
