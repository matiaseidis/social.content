package com.mati.demo.prevalence.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.TagRepository;
import com.mati.demo.model.user.User;

public class RemoveTagFromUser implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String loggedInUserName;
	private final Tag tag;

	public RemoveTagFromUser(Tag tag, String loggedInUserName) {
		this.tag = tag;
		this.loggedInUserName = loggedInUserName;

	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		
		Model model = (Model) prevalentSystem;
		
		Tag tagFromRepo = model.getTagRepository().getByTagName(tag.getTagName());
		User loggedInUser = model.loadUserByUsername(loggedInUserName);

		loggedInUser.removeTag(model.getTagRepository(), tagFromRepo);
	}

}
