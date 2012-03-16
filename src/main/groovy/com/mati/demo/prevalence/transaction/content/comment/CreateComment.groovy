package com.mati.demo.prevalence.transaction.content.comment;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Comment;
import com.mati.demo.model.content.Content;

public class CreateComment implements Transaction {
	
	private final Comment comment;
	private final int contentId;
	

	public CreateComment(Comment comment, int id) {
		this.comment = comment;
		this.contentId = id;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		Content c = model.loadContentById(contentId);
		if(c == null){
			throw new RuntimeException("Content with id "+ contentId+" does not exist.");
		}
		comment.setAuthor(model.getLoggedInUser());
		comment.setPostDate(new Date());
		c.comment(comment);
	}

}
