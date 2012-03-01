package com.mati.demo.prevalence.transaction.content.post;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Post;

public class CreatePost implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Post post;

	public CreatePost(Post post) {
		this.post = post;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model)prevalentSystem;
		post.setId(post.hashCode());
		model.getLoggedInUser().addPost(post);
	}

}
