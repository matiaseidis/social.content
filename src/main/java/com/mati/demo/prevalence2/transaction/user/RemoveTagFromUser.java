package com.mati.demo.prevalence2.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.TagRepository;
import com.mati.demo.model.tag.Taggable;

public class RemoveTagFromUser implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Tag tag;

	public RemoveTagFromUser(Tag tag) {
		this.tag = tag;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		
		Model model = (Model) prevalentSystem;
		
		Tag tagFromRepo = TagRepository.INSTANCE.getByTagName(tag.getTagName());
		
		model.getLoggedInUser().removeTag(tagFromRepo);
	}

}
