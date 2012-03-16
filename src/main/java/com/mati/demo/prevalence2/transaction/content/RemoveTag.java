package com.mati.demo.prevalence2.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;

public class RemoveTag implements Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Tag tag;
	private final int id;

	public RemoveTag(Tag tag, int id) {
		this.tag = tag;
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		Content c = model.loadContentById(id);
		if(!c.hasTag(tag)){
			throw new RuntimeException("the content doesn't have that tag :" + tag.getTagName());
		}
		c.removeTag(tag);
		
		if(model.hasTag(tag)){
			model.removeTag(tag);
		}
	}

}
