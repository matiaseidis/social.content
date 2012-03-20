package com.mati.demo.prevalence.transaction.content.comment;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Comment;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.user.User;

public class CreateComment implements Transaction {
	
	private final Comment comment;
	private final int contentId;
	private final String loggedInUserName;

	

	public CreateComment(Comment comment, int id, String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;

		this.comment = comment;
		this.contentId = id;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		User loggedInUser = model.loadUserByUsername(loggedInUserName);
		Content c = model.loadContentById(contentId);
		if(c == null || loggedInUser == null){
			throw new RuntimeException("Content with id "+ contentId+" does not exist.");
		}

		comment.setAuthor(loggedInUser);
		comment.setPostDate(new Date());
		c.comment(comment);
	}

}
