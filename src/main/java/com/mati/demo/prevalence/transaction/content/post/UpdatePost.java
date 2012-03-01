package com.mati.demo.prevalence.transaction.content.post;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Post;

public class UpdatePost implements Transaction {

	private final Post oldPost;
	private final Post updatedPost;
	
	public UpdatePost(Post oldPost, Post updatedPost) {
		this.oldPost = oldPost;
		this.updatedPost = updatedPost;
	}
	public UpdatePost(Post oldPost) {
		this(oldPost, null);
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		model.getLoggedInUser().updatePost(oldPost, updatedPost);
	}

}
