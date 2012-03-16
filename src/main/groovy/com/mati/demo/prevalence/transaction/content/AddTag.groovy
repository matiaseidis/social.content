package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;

public class AddTag implements Transaction {
	
	private final Tag tag;
	private final int id;

	public AddTag(Tag tag, int id) {
		this.tag = tag;
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		Content c = model.loadContentById(id);
		if(c.hasTag(tag)){
			throw new RuntimeException("the content already has that tag :" + tag.getTagName());
		}
		c.addTag(tag);
		
		if(!model.hasTag(tag)){
			model.addTag(tag);
		}
	}

}
