package com.mati.demo.prevalence.transaction;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.user.User;

public class CreatePost implements Transaction {

	private User user;
	private Post post;

	public CreatePost(User user, Post post) {
		this.user = user;
		this.post = post;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model)prevalentSystem;
		model.loadUserByUsername(user.getUserName()).getPosts().add(post);
	}

}
